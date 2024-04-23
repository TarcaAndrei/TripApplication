package org.example;


import java.time.LocalTime;
import java.util.List;

public interface RepositoryExcursie extends Repository<Excursie> {
    List<Excursie> findByFilter(String obiectiv, LocalTime deLa, LocalTime panaLa);
}
