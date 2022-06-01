package com.javamachine.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.javamachine.dto.CustomResponse;
import com.javamachine.entity.Category;

@Component
public class Convertor {

	public CustomResponse entityToDto(Category category) {
		CustomResponse dto = new CustomResponse();
		dto.setCategorytName(category.getCategoryName());
		return dto;
	}
	
	public List<CustomResponse> entityToDtoList(List<Category> categories) {
		return categories.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
}
