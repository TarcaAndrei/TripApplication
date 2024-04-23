package org.example.RepositoryHB;

import org.example.Excursie;
import org.example.RepositoryRezervare;
import org.example.Rezervare;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class RepositoryHBRezervare implements RepositoryRezervare {
    private SessionFactory sessionFactory;

    public RepositoryHBRezervare(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer getNumarOcupate(Integer idExcursie) {
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT SUM(R.numarBilete) FROM Rezervare R WHERE R.excursie.id = :idExc ");
            query.setParameter("idExc", idExcursie);
            if(query.uniqueResult() != null)
                return Math.toIntExact((Long) query.uniqueResult());
            else
                return 0;
//            return (Integer) query.uniqueResult();
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public Optional<Rezervare> save(Rezervare entity) {
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
    public Optional<Rezervare> update(Rezervare entity) {
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
    public Optional<Rezervare> delete(Integer idEntity) {
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
    public Optional<Rezervare> findOne(Integer idEntity) {
        try(var session = sessionFactory.openSession()){
            var excursie = session.get(Rezervare.class, idEntity);
            return Optional.ofNullable(excursie);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<Rezervare> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT R FROM Rezervare R", Rezervare.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
}
