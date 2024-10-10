package com.werka.shopwebapplication.domain.api;

import com.werka.shopwebapplication.domain.client.Client;
import com.werka.shopwebapplication.domain.client.ClientDAO;

public class ClientService {

    private ClientDAO clientDAO = new ClientDAO();

    public boolean isLoginDataCorrect(String email, String password) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            return clientDAO.isFoundClientByMailAndPass(email, password);
        }else {
            return false;
        }
    }

    public boolean isRegistrationDataCorrect(String name, String surname, String email, String password) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()
                && name != null && !name.isEmpty() && surname != null && !surname.isEmpty()) {
            boolean isGood = clientDAO.isMailFree(email);
            return isGood;
        }else {
            return false;
        }
    }

    public void saveClient(String name, String surname, String email, String password) {
        Client client = new Client(-1, name, surname, email, password);
        clientDAO.save(client);
    }

}
