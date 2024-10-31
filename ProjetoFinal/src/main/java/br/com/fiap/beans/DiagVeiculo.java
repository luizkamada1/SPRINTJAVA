package br.com.fiap.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DiagVeiculo {

    private int idDiag;
    private String nome;
    private String compBordo;
    private int codigoFalha;
    private String sintomas;
    private int veiculoPlaca; // Chave estrangeira para referenciar a placa do veículo

    public DiagVeiculo() {
        super();
    }

    public DiagVeiculo(int idDiag, String nome, String compBordo, int codigoFalha, String sintomas, int veiculoPlaca) {
        super();
        this.idDiag = idDiag;
        this.nome = nome;
        this.compBordo = compBordo;
        this.codigoFalha = codigoFalha;
        this.sintomas = sintomas;
        this.veiculoPlaca = veiculoPlaca;
    }

    public int getIdDiag() {
        return idDiag;
    }

    public void setIdDiag(int idDiag) {
        this.idDiag = idDiag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCompBordo() {
        return compBordo;
    }

    public void setCompBordo(String compBordo) {
        this.compBordo = compBordo;
    }

    public int getCodigoFalha() {
        return codigoFalha;
    }

    public void setCodigoFalha(int codigoFalha) {
        this.codigoFalha = codigoFalha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public int getVeiculoPlaca() {
        return veiculoPlaca;
    }

    public void setVeiculoPlaca(int veiculoPlaca) {
        this.veiculoPlaca = veiculoPlaca;
    }
}
