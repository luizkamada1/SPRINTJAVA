package br.com.fiap.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CentroAuto {

    private int idCentro;
    private String nome;
    private String endereco;
    private String cep;

    public CentroAuto() {
        super();
    }

    public CentroAuto(int idCentro, String nome, String endereco, String cep) {
        super();
        this.idCentro = idCentro;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
