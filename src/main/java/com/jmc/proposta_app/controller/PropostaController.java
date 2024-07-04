package com.jmc.proposta_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmc.proposta_app.dto.PropostaRequestDto;
import com.jmc.proposta_app.dto.PropostaResponseDto;
import com.jmc.proposta_app.service.PropostaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {
	
	private PropostaService propostaService;
	
	@PostMapping
	public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto) {
		PropostaResponseDto response = propostaService.criar(requestDto);
		return ResponseEntity.ok(response);
	}
}
