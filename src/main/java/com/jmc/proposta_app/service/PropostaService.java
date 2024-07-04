package com.jmc.proposta_app.service;

import com.jmc.proposta_app.dto.PropostaResponseDto;

import com.jmc.proposta_app.entity.Proposta;
import com.jmc.proposta_app.mapper.PropostaMapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.jmc.proposta_app.repository.PropostaRepository;
import com.jmc.proposta_app.dto.PropostaRequestDto;

import java.util.List;

@AllArgsConstructor
@Service
public class PropostaService {

	private PropostaRepository propostaRepository;
	
	public PropostaResponseDto criar(PropostaRequestDto requestDto) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
		propostaRepository.save(proposta);

		return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
	}

	public List<PropostaResponseDto> obterProposta() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
	}
}