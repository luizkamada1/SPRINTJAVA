package br.com.fiap.beans;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AgReparo {

    private int idReparo;
    private Date dataAg;
    private String horaAg;
    private int clienteCpf; // Chave estrangeira para referenciar o CPF do cliente
    private int centroAutoIdCentro; // Chave estrangeira para referenciar o ID do centro automotivo

    public AgReparo() {
        super();
    }

    public AgReparo(int idReparo, Date dataAg, String horaAg, int clienteCpf, int centroAutoIdCentro) {
        super();
        this.idReparo = idReparo;
        this.dataAg = dataAg;
        this.horaAg = horaAg;
        this.clienteCpf = clienteCpf;
        this.centroAutoIdCentro = centroAutoIdCentro;
    }

    public int getIdReparo() {
        return idReparo;
    }

    public void setIdReparo(int idReparo) {
        this.idReparo = idReparo;
    }

    public Date getDataAg() {
        return dataAg;
    }

    public void setDataAg(Date dataAg) {
        this.dataAg = dataAg;
    }

    public String getHoraAg() {
        return horaAg;
    }

    public void setHoraAg(String horaAg) {
        this.horaAg = horaAg;
    }

    public int getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(int clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public int getCentroAutoIdCentro() {
        return centroAutoIdCentro;
    }

    public void setCentroAutoIdCentro(int centroAutoIdCentro) {
        this.centroAutoIdCentro = centroAutoIdCentro;
    }
}
