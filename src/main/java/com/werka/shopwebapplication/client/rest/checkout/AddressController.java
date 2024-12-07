package com.werka.shopwebapplication.client.rest.checkout;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.address.ClientAddressData;
import com.werka.shopwebapplication.domain.api.BookService;
import com.werka.shopwebapplication.domain.api.ClientAddressDataInfo;
import com.werka.shopwebapplication.domain.api.ClientService;
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

        if(bookService.getOrderTotal() == 0) {
            response.sendRedirect(request.getContextPath() + "/basket");
            return;
        }

        Optional<ClientAddressDataInfo> info = clientService.getClientAddressData();

        if(info.isPresent()){
            request.getRequestDispatcher("/WEB-INF/pages/checkout/deliveryCheckout.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/WEB-INF/pages/checkout/addressCheckout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String town = request.getParameter("town");
        String postcode = request.getParameter("postcode");

        if(phoneNumber != null && !phoneNumber.isEmpty()
                && address != null && !address.isEmpty()
                && town != null && !town.isEmpty()
                && postcode != null && !postcode.isEmpty()){
            clientService.saveClientAddressInfo(new ClientAddressData(-1, DataHelper.getClientId(), phoneNumber, address, town, postcode));
            request.getRequestDispatcher("/WEB-INF/pages/checkout/deliveryCheckout.jsp").forward(request, response);
        } else {
            request.setAttribute("incorrectData", "true");
            request.getRequestDispatcher("/WEB-INF/pages/checkout/addressCheckout.jsp").forward(request, response);
        }
    }
}
