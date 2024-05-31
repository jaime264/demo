package com.elektra.demo.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.elektra.demo.exception.CustomException;
import com.elektra.demo.model.daos.Client;
import com.elektra.demo.model.entity.ClientEntity;
import com.elektra.demo.repository.ClientRepository;
import com.elektra.demo.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	@Override
	public List<Client> getAllClient() throws CustomException {
		try {
			return clientRepository.findAll().stream().map(c -> c.toClient()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new CustomException("Error al obtener los clientes");
		}
	}

	@Override
	public Integer createClient(Client client) throws CustomException {

		if (clientRepository.findByNameAndFatherLastNameAndMotherLastName(client.getName(), client.getFatherLastName(),
				client.getMotherLastName()).isPresent()) {
			throw new CustomException("Ya existe un cliente con el mismo nombre completo.");
		}

		try {
			ClientEntity clientEntity = ClientEntity.builder().name(client.getName())
					.fatherLastName(client.getFatherLastName()).motherLastName(client.getMotherLastName())
					.birthDate(client.getBirthDate()).sex(client.getSex()).email(client.getEmail())
					.phone(client.getPhone()).build();
			clientRepository.save(clientEntity);

			return clientRepository.save(clientEntity).getId();
		} catch (Exception e) {
			throw new CustomException("Error al registrar un cliente");

		}

	}

	@Override
	public Client getClientById(Integer id) throws CustomException {
		try {
			return clientRepository.getById(id).toClient();
		} catch (Exception e) {
			throw new CustomException("Error al obtener cliente");
		}
	}

}
