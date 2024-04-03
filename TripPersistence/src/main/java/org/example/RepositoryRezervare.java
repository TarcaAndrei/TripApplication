package org.example;



public interface RepositoryRezervare extends Repository<Integer, Rezervare> {
    Integer getNumarOcupate(Integer idExcursie);
}
