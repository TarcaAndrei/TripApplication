package org.example;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "Rezervari")
@Entity
@AttributeOverrides({
        @javax.persistence.AttributeOverride(name = "id", column = @javax.persistence.Column(name = "ID"))
})
public class Rezervare extends Entitate  implements Serializable {

    @Column(name = "NUME_CLIENT")
    private String numeClient;
    @Column(name = "TELEFON_CLIENT")
    private String telefonClient;
    @ManyToOne
    @JoinColumn(name = "ID_EXCURSIE")
    private Excursie excursie;
    @Column(name = "NR_BILETE")
    private Integer numarBilete;

    public String getNumeClient() {
        return numeClient;
    }


    public Rezervare() {
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getTelefonClient() {
        return telefonClient;
    }

    public void setTelefonClient(String telefonClient) {
        this.telefonClient = telefonClient;
    }

    public Excursie getExcursie() {
        return excursie;
    }

    public void setExcursie(Excursie excursie) {
        this.excursie = excursie;
    }
    public Integer getNumarBilete() {
        return numarBilete;
    }

    public void setNumarBilete(Integer numarBilete) {
        this.numarBilete = numarBilete;
    }

    public Rezervare(String numeClient, String telefonClient, Excursie excursie, Integer numarBilete) {
        this.numeClient = numeClient;
        this.telefonClient = telefonClient;
        this.excursie = excursie;
        this.numarBilete = numarBilete;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "numeClient='" + numeClient + '\'' +
                ", telefonClient='" + telefonClient + '\'' +
                ", excursie=" + excursie +
                ", numarBilete=" + numarBilete +
                '}';
    }
}
