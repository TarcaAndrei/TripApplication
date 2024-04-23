package org.example;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public class Entitate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
