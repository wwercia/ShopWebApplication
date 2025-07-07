package com.werka.shopwebapplication.client.rest.checkout;

import com.werka.shopwebapplication.domain.address.ClientAddressData;
import com.werka.shopwebapplication.domain.api.services.BookService;
import com.werka.shopwebapplication.domain.api.ClientAddressDataInfo;
import com.werka.shopwebapplication.domain.api.services.ClientService;
import com.werka.shopwebapplication.domain.client.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/addressCheckout")
public class AddressController extends HttpServlet {

    private final ClientService clientService = new ClientService();
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Client currentClient = (Client) request.getSession().getAttribute("client");

        if(bookService.getOrderTotal(currentClient.getId()) == 0) {
            response.sendRedirect(request.getContextPath() + "/basket");
            return;
        }

        Optional<ClientAddressDataInfo> info = clientService.getClientAddressData(currentClient.getId());

        if(info.isPresent()){
            request.getRequestDispatcher("/WEB-INF/pages/checkout/deliveryCheckout.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/WEB-INF/pages/checkout/addressCheckout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client currentClient = (Client) request.getSession().getAttribute("client");

        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String town = request.getParameter("town");
        String postcode = request.getParameter("postcode");

        if(phoneNumber != null && !phoneNumber.isEmpty()
                && address != null && !address.isEmpty()
                && town != null && !town.isEmpty()
                && postcode != null && !postcode.isEmpty()){
            clientService.saveClientAddressInfo(new ClientAddressData(-1, currentClient.getId(), phoneNumber, address, town, postcode));
            request.getRequestDispatcher("/WEB-INF/pages/checkout/deliveryCheckout.jsp").forward(request, response);
        } else {
            request.setAttribute("incorrectData", "true");
            request.getRequestDispatcher("/WEB-INF/pages/checkout/addressCheckout.jsp").forward(request, response);
        }
    }
}
