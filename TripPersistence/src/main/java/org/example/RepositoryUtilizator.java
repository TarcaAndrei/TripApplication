package org.example;



import java.util.Optional;

public interface RepositoryUtilizator extends Repository<Utilizator> {
    Optional<Utilizator> findUtilizatorByUsername(String username);
}
