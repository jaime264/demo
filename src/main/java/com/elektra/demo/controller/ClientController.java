package com.elektra.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elektra.demo.exception.CustomException;
import com.elektra.demo.model.daos.Client;
import com.elektra.demo.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

	private final Logger log = LoggerFactory.getLogger(ClientController.class);

	private final ClientService clientService;

	@PostMapping
	public ResponseEntity<Integer> crearCliente(@RequestBody Client client) throws CustomException {
		log.info("client: {}", client.toString());
		
		Integer clientId = clientService.createClient(client);
		return new ResponseEntity<>(clientId, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Client>> getAllClient() throws CustomException {
		List<Client> clients = clientService.getAllClient();
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> obtenerClientePorId(@PathVariable("id") Integer id) throws CustomException {
		log.info("id client: {} ", id);

		Client client = clientService.getClientById(id);
		if (client != null)
			return new ResponseEntity<>(client, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
