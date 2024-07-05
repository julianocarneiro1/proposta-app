package com.jmc.proposta_app.service;

import com.jmc.proposta_app.dto.PropostaResponseDto;

import com.jmc.proposta_app.entity.Proposta;
import com.jmc.proposta_app.mapper.PropostaMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jmc.proposta_app.repository.PropostaRepository;
import com.jmc.proposta_app.dto.PropostaRequestDto;

import java.util.List;

@Service
public class PropostaService {

	private PropostaRepository propostaRepository;

	private NotificacaoService notificacaoService;

	private String exchange;

	public PropostaService(PropostaRepository propostaRepository,
						   NotificacaoService notificacaoService,
						   @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		this.propostaRepository = propostaRepository;
		this.notificacaoService = notificacaoService;
		this.exchange = exchange;
	}

	public PropostaResponseDto criar(PropostaRequestDto requestDto) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
		propostaRepository.save(proposta);

		PropostaResponseDto response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
		notificacaoService.notificar(response, exchange);

		return response;
	}

	public List<PropostaResponseDto> obterProposta() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
	}
}