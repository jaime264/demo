package com.elektra.demo.model.daos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	private Integer id;
	private String name;
	private String fatherLastName;
	private String motherLastName;
	private LocalDateTime birthDate;
	private String sex;
	private String email;
	private String phone;	
}
