package com.elektra.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elektra.demo.model.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer>{
    Optional<ClientEntity> findByNameAndFatherLastNameAndMotherLastName(String name, String fatherLastName, String motherLastName);
}
