package com.elektra.demo.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.elektra.demo.model.daos.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String fatherLastName;
	
	@Column
	private String motherLastName;
	
	@JsonFormat(pattern = "yyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime birthDate;
	
	@Column
	private String sex;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	public Client toClient() {
		Client client = new Client();
		BeanUtils.copyProperties(this, client);
		return client;
	}
	
}
