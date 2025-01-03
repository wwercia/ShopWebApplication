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

@WebServlet("/changePassword")
public class ChangePasswordController extends HttpServlet {

    private final ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/account/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        if(newPassword.equals(confirmNewPassword)) {
            if(clientService.isPasswordCorrect(currentPassword)){

                request.setAttribute("wrongData", "");
                clientService.changePassword(newPassword);

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
            }else {
                request.setAttribute("wrongData", "Current password is incorrect!");
            }
        }else {
            request.setAttribute("wrongData", "Passwords do not match!");
        }
        request.getRequestDispatcher("/WEB-INF/pages/account/changePassword.jsp").forward(request, response);
    }
}
