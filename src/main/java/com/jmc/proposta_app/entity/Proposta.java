package com.jmc.proposta_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double valorSolicitado;
	
	private int prazoPagamento;
	
	private Boolean aprovada;
	
	private boolean integrada;
	
	private String observacao;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_usuario")
	@JsonManagedReference //Proposta é quem gerencia o relacionamento, proposta tem o id do usuário (não usuário possui id da proposta)
	private Usuario usuario;
}