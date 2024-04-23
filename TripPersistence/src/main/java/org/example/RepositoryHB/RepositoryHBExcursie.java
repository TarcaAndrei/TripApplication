package org.example.RepositoryHB;

import org.example.Excursie;
import org.example.RepositoryExcursie;
import org.example.Utilizator;
import org.hibernate.SessionFactory;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class RepositoryHBExcursie implements RepositoryExcursie {
    private SessionFactory sessionFactory;

    public RepositoryHBExcursie(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Excursie> findByFilter(String obiectiv, LocalTime deLa, LocalTime panaLa) {
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT E FROM Excursie E WHERE E.obiectivTuristic like :obiectiv_turistic AND E.oraPlecarii BETWEEN :dela AND :panala", Excursie.class);
            query.setParameter("obiectiv_turistic", '%' + obiectiv + '%');
            query.setParameter("dela", deLa.toString());
            query.setParameter("panala", panaLa.toString());
            return query.list();
        }
        catch (Exception e){
            return List.of();
        }
    }

    @Override
    public Optional<Excursie> save(Excursie entity) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return Optional.of(entity);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Excursie> update(Excursie entity) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return Optional.of(entity);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Excursie> delete(Integer idEntity) {
        var excursieOpt = findOne(idEntity);
        if(excursieOpt.isEmpty())
            return Optional.empty();
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.delete(excursieOpt.get());
            transaction.commit();
            return excursieOpt;
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Excursie> findOne(Integer idEntity) {
        try(var session = sessionFactory.openSession()){
            var excursie = session.get(Excursie.class, idEntity);
            return Optional.ofNullable(excursie);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<Excursie> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT E FROM Excursie E", Excursie.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
}
