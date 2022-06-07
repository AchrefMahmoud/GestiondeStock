package com.tn.GestiondeStock.handlers;

import java.util.List;
import java.util.ArrayList;


import com.tn.GestiondeStock.exception.ErrorCodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

	private Integer httpCode;
	
	private ErrorCodes code;
	
	private String message;
	
	private List<String> errors = new ArrayList<>();
}
