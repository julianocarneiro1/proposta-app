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

	private final PropostaRepository propostaRepository;

	private final NotificacaoRabbitService notificacaoRabbitService;

	private final String exchange;

	public PropostaService(PropostaRepository propostaRepository,
						   NotificacaoRabbitService notificacaoRabbitService,
						   @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		this.propostaRepository = propostaRepository;
		this.notificacaoRabbitService = notificacaoRabbitService;
		this.exchange = exchange;
	}

	public PropostaResponseDto criar(PropostaRequestDto requestDto) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
		propostaRepository.save(proposta);

		notificarRabbitMQ(proposta);

		return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
	}

	private void notificarRabbitMQ(Proposta proposta) {
		try {
			notificacaoRabbitService.notificar(proposta, exchange);
		} catch (RuntimeException ex) {
			proposta.setIntegrada(false);
			propostaRepository.save(proposta);
		}
	}

	public List<PropostaResponseDto> obterProposta() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
	}
}