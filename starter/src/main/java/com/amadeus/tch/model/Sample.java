package com.amadeus.tch.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.amadeus.tch.model.dto.DTOConvertible;
import com.amadeus.tch.model.dto.SampleDTO;

@Entity
public class Sample implements Serializable, DTOConvertible<SampleDTO> {

	private static final long serialVersionUID = 4988346695434579229L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(length = 5)
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public SampleDTO toDTO() {
		SampleDTO dto = new SampleDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setCode(code);
		return dto;
	}

}
