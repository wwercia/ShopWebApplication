package com.werka.shopwebapplication.client.rest.account;

import com.werka.shopwebapplication.domain.api.ClientAddressDataInfo;
import com.werka.shopwebapplication.domain.api.ClientInfo;
import com.werka.shopwebapplication.domain.api.services.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editAccountData")
public class EditDataController extends HttpServlet {

    private ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientInfo clientInfo = clientService.getClientInfo().orElseThrow();
        ClientAddressDataInfo clientAddress = clientService.getClientAddressData().orElseThrow();
        request.setAttribute("email", clientInfo.getEmail());
        request.setAttribute("password", clientInfo.getPassword());
        request.setAttribute("name", clientInfo.getName());
        request.setAttribute("surname", clientInfo.getSurname());
        request.setAttribute("phoneNumber", clientAddress.getPhoneNumber());
        request.setAttribute("address", clientAddress.getAddress());
        request.setAttribute("town", clientAddress.getTown());
        request.setAttribute("postcode", clientAddress.getPostcode());
        request.getRequestDispatcher("/WEB-INF/pages/account/editAccountData.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String town = request.getParameter("town");
        String postcode = request.getParameter("postcode");

        clientService.updateClientData(name, surname);
        clientService.updateClientAddressData(phoneNumber, address, town, postcode);
        response.sendRedirect(request.getContextPath() + "/account");
    }
}
