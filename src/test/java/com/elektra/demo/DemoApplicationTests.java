package com.elektra.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.elektra.demo.exception.CustomException;
import com.elektra.demo.repository.ClientRepository;
import com.elektra.demo.service.ClientService;
import com.elektra.demo.service.imp.ClientServiceImpl;

@SpringBootTest
class DemoApplicationTests {

	ClientRepository clientRepository;
	ClientService clientService;
	
	@BeforeEach
	void setUp() {
		clientRepository = mock(ClientRepository.class);
		clientService = new ClientServiceImpl(clientRepository);
	}
	
	@Test
	void contextLoads() throws CustomException {
		Integer id = 1;
		
		assertEquals("1", id.toString());
		
		//Client client = clientService.getClientById(1);
		//List<Client> clients = clientService.getAllClient();
		
		
	}

}
