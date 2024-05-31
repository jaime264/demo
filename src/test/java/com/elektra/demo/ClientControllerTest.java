package com.elektra.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.elektra.demo.model.daos.Client;
import com.elektra.demo.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClientControllerTest.class)
public class ClientControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;
	
    @Test
    void testCrearCliente() throws Exception {
        Client client = Client.builder().id(1).name("Jaime").fatherLastName("Rios").motherLastName("De La Gala")
				.birthDate(LocalDateTime.of(1991, 10, 5, 0, 0)).sex("Masculino").email("jaime@gmail.com").phone("963852741").build();
        
        when(clientService.createClient(client)).thenReturn(1);

        mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(1));
    }
    
    @Test
    void testGetAllClient() throws Exception {
        Client client1 = Client.builder().id(1).name("Jaime").fatherLastName("Rios").motherLastName("De La Gala")
				.birthDate(LocalDateTime.of(1991, 10, 5, 0, 0)).sex("Masculino").email("jaime@gmail.com").phone("963852741").build();
        
        Client client2 = Client.builder().id(1).name("Carla").fatherLastName("Delgado").motherLastName("Peralta")
				.birthDate(LocalDateTime.of(1995, 11, 10, 0, 0)).sex("Femenino").email("Carla@gmail.com").phone("987654321").build();
        
        List<Client> clients = Arrays.asList(client1, client2);
        when(clientService.getAllClient()).thenReturn(clients);

        mockMvc.perform(get("/client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }
    
    @Test
    void testObtenerClientePorId() throws Exception {
        Client client = Client.builder().id(1).name("Jaime").fatherLastName("Rios").motherLastName("De La Gala")
				.birthDate(LocalDateTime.of(1991, 10, 5, 0, 0)).sex("Masculino").email("jaime@gmail.com").phone("963852741").build();
        when(clientService.getClientById(1)).thenReturn(client);

        mockMvc.perform(get("/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));

        when(clientService.getClientById(2)).thenReturn(null);

        mockMvc.perform(get("/client/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
