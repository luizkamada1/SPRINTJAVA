package br.com.fiap.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cliente {

	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	
	public Cliente() {
		super();
	}

	public Cliente(String cpf, String nome, String telefone, String email, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
