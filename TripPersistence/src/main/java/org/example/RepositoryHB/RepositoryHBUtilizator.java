package org.example.RepositoryHB;

import org.example.RepositoryUtilizator;
import org.example.Utilizator;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class RepositoryHBUtilizator implements RepositoryUtilizator {
    private SessionFactory sessionFactory;

    public RepositoryHBUtilizator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Utilizator> findUtilizatorByUsername(String username) {
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT U FROM Utilizator U WHERE U.username = :username", Utilizator.class);
            query.setParameter("username", username);
            return Optional.ofNullable(query.uniqueResult());
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Utilizator> save(Utilizator entity) {
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
    public Optional<Utilizator> update(Utilizator entity) {
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
    public Optional<Utilizator> delete(Integer idEntity) {
        var cititorOpt = findOne(idEntity);
        if(cititorOpt.isEmpty())
            return Optional.empty();
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.delete(cititorOpt.get());
            transaction.commit();
            return cititorOpt;
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Utilizator> findOne(Integer idEntity) {
        try(var session = sessionFactory.openSession()){
            var cititor = session.get(Utilizator.class, idEntity);
            return Optional.ofNullable(cititor);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<Utilizator> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT U FROM Utilizator U", Utilizator.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
}
