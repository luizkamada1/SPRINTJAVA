package br.com.fiap.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrcaReparo {

    private int idOrca;
    private double precoEstim;
    private String reparoEmbr;
    private String reparoRad;
    private String subsCorreia;
    private int diagVeiculoIdDiagnostico; // Chave estrangeira para referenciar o diagnóstico do veículo

    public OrcaReparo() {
        super();
    }

    public OrcaReparo(int idOrca, double precoEstim, String reparoEmbr, String reparoRad, String subsCorreia, int diagVeiculoIdDiagnostico) {
        super();
        this.idOrca = idOrca;
        this.precoEstim = precoEstim;
        this.reparoEmbr = reparoEmbr;
        this.reparoRad = reparoRad;
        this.subsCorreia = subsCorreia;
        this.diagVeiculoIdDiagnostico = diagVeiculoIdDiagnostico;
    }

    public int getIdOrca() {
        return idOrca;
    }

    public void setIdOrca(int idOrca) {
        this.idOrca = idOrca;
    }

    public double getPrecoEstim() { 
        return precoEstim;
    }

    public void setPrecoEstim(double precoEstim) {
        this.precoEstim = precoEstim;
    }

    public String getReparoEmbr() {
        return reparoEmbr;
    }

    public void setReparoEmbr(String reparoEmbr) {
        this.reparoEmbr = reparoEmbr;
    }

    public String getReparoRad() {
        return reparoRad;
    }

    public void setReparoRad(String reparoRad) {
        this.reparoRad = reparoRad;
    }

    public String getSubsCorreia() {
        return subsCorreia;
    }

    public void setSubsCorreia(String subsCorreia) {
        this.subsCorreia = subsCorreia;
    }

    public int getDiagVeiculoIdDiagnostico() {
        return diagVeiculoIdDiagnostico;
    }

    public void setDiagVeiculoIdDiagnostico(int diagVeiculoIdDiagnostico) {
        this.diagVeiculoIdDiagnostico = diagVeiculoIdDiagnostico;
    }
}
