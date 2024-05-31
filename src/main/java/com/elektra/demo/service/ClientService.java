package com.elektra.demo.service;

import java.util.List;

import com.elektra.demo.exception.CustomException;
import com.elektra.demo.model.daos.Client;

public interface ClientService {

	public List<Client> getAllClient() throws CustomException;
	public Integer createClient(Client client) throws CustomException;
	public Client getClientById(Integer id) throws CustomException;

}
