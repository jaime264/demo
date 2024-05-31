package com.elektra.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.elektra.demo.exception.CustomException;
import com.elektra.demo.model.daos.Client;
import com.elektra.demo.model.entity.ClientEntity;
import com.elektra.demo.repository.ClientRepository;
import com.elektra.demo.service.imp.ClientServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {
	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientServiceImpl clientService;

	@Test
	void testGetAllClient() throws CustomException {
		ClientEntity clientEntity1 = ClientEntity.builder().id(1).name("Jaime").fatherLastName("Rios").motherLastName("De La Gala")
				.birthDate(LocalDateTime.of(1991, 10, 5, 0, 0)).sex("Masculino").email("jaime@gmail.com").phone("963852741").build();
		ClientEntity clientEntity2 = ClientEntity.builder().id(1).name("Carla").fatherLastName("Moran").motherLastName("Perales")
				.birthDate(LocalDateTime.of(1992, 11, 12, 0, 0)).sex("Femenino").email("Carla@gmail.com").phone("987654321").build();

		when(clientRepository.findAll()).thenReturn(Arrays.asList(clientEntity1, clientEntity2));

		List<Client> clients = clientService.getAllClient();
		assertEquals(2, clients.size());
		assertEquals("Jaime", clients.get(0).getName());
		assertEquals("Carla", clients.get(1).getName());
	}

	@Test
	void testCreateClient() throws CustomException {
		Client client = Client.builder().id(1).name("Jaime").fatherLastName("Rios").motherLastName("De La Gala")
				.birthDate(LocalDateTime.of(1991, 10, 5, 0, 0)).sex("Masculino").email("jaime@gmail.com").phone("963852741").build();

		ClientEntity clientEntity = ClientEntity.builder().id(1).name(client.getName())
				.fatherLastName(client.getFatherLastName()).motherLastName(client.getMotherLastName())
				.birthDate(client.getBirthDate()).sex(client.getSex()).email(client.getEmail()).phone(client.getPhone())
				.build();

		when(clientRepository.findByNameAndFatherLastNameAndMotherLastName(any(), any(), any()))
				.thenReturn(Optional.empty());
		when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

		Integer clientId = clientService.createClient(client);
		assertEquals(1, clientId);
	}

	@Test
	void testCreateClient_ThrowsException_WhenClientExists() {
		Client client = Client.builder().id(1).name("Jaime").fatherLastName("Rios").motherLastName("De La Gala")
				.birthDate(LocalDateTime.of(1991, 10, 5, 0, 0)).sex("Masculino").email("jaime@gmail.com").phone("963852741").build();

		when(clientRepository.findByNameAndFatherLastNameAndMotherLastName(any(), any(), any()))
				.thenReturn(Optional.of(new ClientEntity()));

		CustomException exception = assertThrows(CustomException.class, () -> {
			clientService.createClient(client);
		});

		assertEquals("Ya existe un cliente con el mismo nombre completo.", exception.getMessage());
	}

	@Test
	void testGetClientById() throws CustomException {
		ClientEntity clientEntity = ClientEntity.builder().id(1).name("Jaime").fatherLastName("Rios").motherLastName("De La Gala")
				.birthDate(LocalDateTime.of(1991, 10, 5, 0, 0)).sex("Masculino").email("jaime@gmail.com").phone("963852741").build();

		when(clientRepository.getById(1)).thenReturn(clientEntity);

		Client client = clientService.getClientById(1);
		assertEquals("Jaime", client.getName());
		assertEquals("Rios", client.getFatherLastName());
		assertEquals("De La Gala", client.getMotherLastName());
	}

	@Test
	    void testGetClientById_ThrowsException_WhenNotFound() {
	        when(clientRepository.getById(1)).thenReturn(null);

	        CustomException exception = assertThrows(CustomException.class, () -> {
	            clientService.getClientById(1);
	        });

	        assertEquals("Error al obtener cliente", exception.getMessage());
	    }
}
