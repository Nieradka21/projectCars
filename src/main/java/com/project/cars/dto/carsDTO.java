package com.project.cars.dto;

import org.modelmapper.ModelMapper;

import com.project.cars.model.Cars;

public class carsDTO {

	private Long id;
	private String name;
	private String type;
	private String url_foto;

	/*
	 * public carsDTO(cars c) { this.id = c.getId(); this.name = c.getName();
	 * this.type = c.getType(); }
	 */
	public static carsDTO create(Cars c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, carsDTO.class);
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl_foto() {
		return url_foto;
	}

	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}

}
