package com.generation.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity //vai transformar o objeto em uma tabela no banco de dado
@Table(name="postagem") // coloca o nome na tabela
public class Postagem {
	
	@Id //chave primaria o mesmo que primary key, sempre acima da aplicação
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull //obriga a preencher o campo
	@Size(min = 5, max = 100)
	private String titulo;
	
	@NotNull
	//message para informar ao usuario min e max de letras
	@Size(min = 10, max = 500) //delimita o tamanho do conteudo, não é obrigatorio ter
	private String texto;
	
	//@updatetimestamp versão mais atualizada do codigo
	@Temporal(TemporalType.TIMESTAMP)
	
	// LocalDateTime outra opção de inserir data automaticamente
	private Date date = new java.util.Date(System.currentTimeMillis());
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	};
	
	

}
