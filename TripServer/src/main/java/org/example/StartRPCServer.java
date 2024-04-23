package org.example;

import org.example.RepositoryDB.RepositoryDBExcursie;
import org.example.RepositoryDB.RepositoryDBFirmaTransport;
import org.example.RepositoryDB.RepositoryDBRezervare;
import org.example.RepositoryDB.RepositoryDBUtilizator;
import org.example.RepositoryHB.RepositoryHBUtilizator;
import org.example.Utils.AbstractServer;
import org.example.Utils.RPCConcurrentServerProto;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileReader;
import java.util.Properties;

public class StartRPCServer {
    private static SessionFactory sessionFactory;
    private static int defaultPort = 55555;


    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("../bd.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Cannot find bd.properties " + e);
        }
        Properties propertiesServer = new Properties();
        try {
            propertiesServer.load(new FileReader("server.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Cannot find server.properties " + e);
        }

        setUp();

//        RepositoryUtilizator utilizatorRepository = new RepositoryDBUtilizator(properties);
        RepositoryUtilizator utilizatorRepository = new RepositoryHBUtilizator(sessionFactory);
        RepositoryFirmaTransport firmaTransportRepository = new RepositoryDBFirmaTransport(properties);
        RepositoryExcursie excursieRepository = new RepositoryDBExcursie(properties, firmaTransportRepository);
        RepositoryRezervare repositoryRezervare = new RepositoryDBRezervare(properties, excursieRepository);
        ITripServices tripServices = new TripServices(utilizatorRepository, excursieRepository, firmaTransportRepository, repositoryRezervare);

        String pass=propertiesServer.getProperty("Port");
        var intPass = Integer.parseInt(pass);
        System.out.println("Starting server on port " + intPass);
//        AbstractServer server = new RPCConcurrentServer(intPass, tripServices);
        AbstractServer server = new RPCConcurrentServerProto(intPass, tripServices);
        try{
            server.start();
        } catch (Exception e) {
            System.err.println("Error starting the server " + e.getMessage());
        }
        finally {
            tearDown();
        }
    }
}
