package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;


@Entity
@Table(name="Excursii")
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name="ID"))
})
public class Excursie extends Entitate  implements Serializable {

    @Column(name="OBIECTIV_TURISTIC")
    private String obiectivTuristic;

    @ManyToOne
    @JoinColumn(name="ID_FIRMA_TRANSPORT")
    private FirmaTransport firmaTransport;

    @Column(name="ORA_PLECARII")
    private LocalTime oraPlecarii;

    @Column(name="PRET")
    private Double pret;

    @Column(name="NR_LOCURI")
    private Integer numarLocuriTotale;


    public Excursie() {
    }

    public Excursie(String obiectivTuristic, FirmaTransport firmaTransport, LocalTime oraPlecarii, Double pret, Integer numarLocuriTotale) {
        this.obiectivTuristic = obiectivTuristic;
        this.firmaTransport = firmaTransport;
        this.oraPlecarii = oraPlecarii;
        this.pret = pret;
        this.numarLocuriTotale = numarLocuriTotale;
    }

    public String getObiectivTuristic() {
        return obiectivTuristic;
    }

    public void setObiectivTuristic(String obiectivTuristic) {
        this.obiectivTuristic = obiectivTuristic;
    }

    public FirmaTransport getFirmaTransport() {
        return firmaTransport;
    }

    public void setFirmaTransport(FirmaTransport firmaTransport) {
        this.firmaTransport = firmaTransport;
    }
    public LocalTime getOraPlecarii() {
        return oraPlecarii;
    }

    public void setOraPlecarii(LocalTime oraPlecarii) {
        this.oraPlecarii = oraPlecarii;
    }

    public Integer getNumarLocuriTotale() {
        return numarLocuriTotale;
    }

    public void setNumarLocuriTotale(Integer numarLocuriTotale) {
        this.numarLocuriTotale = numarLocuriTotale;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Excursie{" +
                "obiectivTuristic='" + obiectivTuristic + '\'' +
                ", firmaTransport=" + firmaTransport +
                ", oraPlecarii=" + oraPlecarii +
                ", pret=" + pret +
                ", numarLocuriTotale=" + numarLocuriTotale +
                '}';
    }
}
