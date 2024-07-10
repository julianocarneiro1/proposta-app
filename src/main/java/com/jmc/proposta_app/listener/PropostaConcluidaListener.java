package com.jmc.proposta_app.listener;

import com.jmc.proposta_app.dto.PropostaResponseDto;
import com.jmc.proposta_app.entity.Proposta;
import com.jmc.proposta_app.mapper.PropostaMapper;
import com.jmc.proposta_app.repository.PropostaRepository;
import com.jmc.proposta_app.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private WebSocketService webSocketService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        propostaRepository.save(proposta);
        PropostaResponseDto responseDto = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        webSocketService.notificar(responseDto);
    }
}