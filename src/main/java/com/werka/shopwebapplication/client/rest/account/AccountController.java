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

@WebServlet("/account")
public class AccountController extends HttpServlet {

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

        request.getRequestDispatcher("/WEB-INF/pages/account/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        req.getRequestDispatcher("/WEB-INF/pages/account/account.jsp").forward(req, resp);
    }

}
