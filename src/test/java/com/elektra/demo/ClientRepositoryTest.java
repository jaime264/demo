package com.elektra.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.elektra.demo.model.entity.ClientEntity;
import com.elektra.demo.repository.ClientRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTest {

	@Autowired
	ClientRepository clientRepository;

	@Test
	void testFindById() {
		Optional<ClientEntity> client = clientRepository.findById(1);
		assertTrue(client.isPresent());
		assertEquals("John", client.get().getName());
	}

	@Test
	void testFindAll() {
		List<ClientEntity> clients = clientRepository.findAll();
		assertFalse(clients.isEmpty());
		assertEquals(2, clients.size());
	}

	@Test
	void testSave() {
		ClientEntity clientCarla = ClientEntity.builder().id(1).name("Carla").fatherLastName("Moran")
				.motherLastName("Perales").birthDate(LocalDateTime.of(1992, 11, 12, 0, 0)).sex("Femenino")
				.email("Carla@gmail.com").build();

		ClientEntity client = clientRepository.save(clientCarla);

		assertEquals("Carla", client.getName());
		assertEquals("Carla@gmail.com", client.getEmail());
	}
	
	@Test
	void testFindByData() {
		Optional<ClientEntity> client = clientRepository.findByNameAndFatherLastNameAndMotherLastName("John", "Doe", "Smith");
		assertTrue(client.isPresent());
		assertEquals("John", client.get().getName());
	}

}
