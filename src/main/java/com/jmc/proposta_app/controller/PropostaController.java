package com.jmc.proposta_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jmc.proposta_app.dto.PropostaRequestDto;
import com.jmc.proposta_app.dto.PropostaResponseDto;
import com.jmc.proposta_app.service.PropostaService;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {
	
	private PropostaService propostaService;
	
	@PostMapping
	public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto) {
		PropostaResponseDto response = propostaService.criar(requestDto);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(response.getId())
			.toUri()).body(response);
	}

	@GetMapping
	public ResponseEntity<List<PropostaResponseDto>> obterProposta() {
		return ResponseEntity.ok(propostaService.obterProposta());
	}


}
