package org.example.RepositoryHB;

import org.example.Excursie;
import org.example.FirmaTransport;
import org.example.RepositoryFirmaTransport;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class RepositoryHBFirmaTransport implements RepositoryFirmaTransport {
    private SessionFactory sessionFactory;


    public RepositoryHBFirmaTransport(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<FirmaTransport> save(FirmaTransport entity) {
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
    public Optional<FirmaTransport> update(FirmaTransport entity) {
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
    public Optional<FirmaTransport> delete(Integer idEntity) {
        var firmaTransportOpt = findOne(idEntity);
        if(firmaTransportOpt.isEmpty())
            return Optional.empty();
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.delete(firmaTransportOpt.get());
            transaction.commit();
            return firmaTransportOpt;
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<FirmaTransport> findOne(Integer idEntity) {
        try(var session = sessionFactory.openSession()){
            var firmaTransport = session.get(FirmaTransport.class, idEntity);
            return Optional.ofNullable(firmaTransport);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<FirmaTransport> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT FT FROM FirmaTransport FT", FirmaTransport.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
}
