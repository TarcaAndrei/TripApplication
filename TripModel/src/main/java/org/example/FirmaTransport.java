package org.example;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FIrmeTransport")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ID"))
})
public class FirmaTransport extends Entitate  implements Serializable {

    @Column(name = "NUME")
    private String nume;

    public String getNume() {
        return nume;
    }

    public FirmaTransport() {
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public FirmaTransport(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "FirmaTransport{" +
                "nume='" + nume + '\'' +
                '}';
    }
}
