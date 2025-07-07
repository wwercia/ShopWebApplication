package com.werka.shopwebapplication.domain.api.services;

import com.werka.shopwebapplication.domain.address.ClientAddressData;
import com.werka.shopwebapplication.domain.address.ClientAddressDataDao;
import com.werka.shopwebapplication.domain.api.ClientAddressDataInfo;
import com.werka.shopwebapplication.domain.api.ClientInfo;
import com.werka.shopwebapplication.domain.client.Client;
import com.werka.shopwebapplication.domain.client.ClientDAO;

import java.util.Optional;

public class ClientService {

    private final ClientDAO clientDAO = new ClientDAO();
    private final ClientAddressDataDao addressDataDao = new ClientAddressDataDao();

    public Client login(String email, String password) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            if(clientDAO.isFoundClientByMailAndPass(email, password)) {
                return clientDAO.getClientDataByClientEmail(email);
            }
        }
        return null;
    }

    public boolean isRegistrationDataCorrect(String name, String surname, String email, String password) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()
                && name != null && !name.isEmpty() && surname != null && !surname.isEmpty()) {
            return clientDAO.isMailFree(email);
        }else {
            return false;
        }
    }

    public boolean isPasswordCorrect(String password) {
        if(password != null && !password.isEmpty()){
            return clientDAO.isPasswordCorrect(password);
        }else {
            return false;
        }
    }

    public Client saveClient(String name, String surname, String email, String password) {
        Client client = new Client(-1, name, surname, email, password);
        int id = clientDAO.save(client);
        client.setId(id);
        return client;
    }

    public void updateClientData(String name, String surname, int clientId) {
        clientDAO.updateClientNameAndSurname(name, surname, clientId);
    }

    public void updateClientAddressData(String phoneNumber, String address, String town, String postcode, int clientId) {
        addressDataDao.updateAddress(phoneNumber, address, town, postcode, clientId);
    }

    public Optional<ClientAddressDataInfo> getClientAddressData(int clientId) {
        ClientAddressData data = addressDataDao.getClientAddressDataByClientId(clientId);
        if(data != null) {
            Optional<ClientAddressData> dataReadyToMap = Optional.of(data);
            return dataReadyToMap.map(AddressInfoMapper::map);
        }else {
            return Optional.empty();
        }
    }

    public Optional<ClientInfo> getClientInfo(int clientId) {
        Client data = clientDAO.getClientDataByClientId(clientId);
        if(data != null) {
            data.setPassword(codePassword(data.getPassword()));
            Optional<Client> dataReadyToMap = Optional.of(data);
            return dataReadyToMap.map(ClientInfoMapper::map);
        }else {
            return Optional.empty();
        }
    }

    private String codePassword(String password) {
        return password.replaceAll(".", "*");
    }

    public void changePassword(String newPassword, int clientId) {
        clientDAO.updatePassword(newPassword, clientId);
    }

    public void saveClientAddressInfo(ClientAddressData clientAddressData) {
        addressDataDao.save(clientAddressData);
    }

    private static class AddressInfoMapper {
        static ClientAddressDataInfo map(ClientAddressData c) {
            return new ClientAddressDataInfo(
                    c.getId(),
                    c.getClientId(),
                    c.getPhoneNumber(),
                    c.getAddress(),
                    c.getTown(),
                    c.getPostcode()
            );
        }
    }

    private static class ClientInfoMapper {
        static ClientInfo map(Client c) {
            return new ClientInfo(
                    c.getId(),
                    c.getEmail(),
                    c.getPassword(),
                    c.getName(),
                    c.getSurname()
            );
        }
    }

}
