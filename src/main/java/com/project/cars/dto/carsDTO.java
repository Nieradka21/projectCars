package com.project.cars.dto;

import org.modelmapper.ModelMapper;

import com.project.cars.model.cars;

import lombok.Data;

@Data
public class carsDTO {

	private Long id;
	private String name;
	private String type;

	/*
	 * public carsDTO(cars c) { this.id = c.getId(); this.name = c.getName();
	 * this.type = c.getType(); }
	 */
	public static carsDTO create(cars c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, carsDTO.class);
	}
}
