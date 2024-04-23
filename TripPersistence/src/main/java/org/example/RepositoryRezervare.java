package org.example;



public interface RepositoryRezervare extends Repository<Rezervare> {
    Integer getNumarOcupate(Integer idExcursie);
}
