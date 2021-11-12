/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;


import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import shopboots.classes.AntiHistory;
import shopboots.classes.Client;
import shopboots.classes.History;
import shopboots.classes.Product;

/**
 *
 * @author pupil
 */
public class SaverToBase implements Keeping {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopBootsPU");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();
    @Override
    public void saveClients(List<Client> clients) {
        tx.begin();
            for (int i = 0; i < clients.size(); i++) {
                if(clients.get(i).getId() == null){
                    em.persist(clients.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Client> loadClients() {
        List<Client> clients = null;
        try {
            clients = em.createQuery("SELECT client FROM Client client")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return clients;
    }

    @Override
    public void saveProducts(List<Product> products) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> loadProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveHistories(List<History> histories) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<History> loadHistories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAntiHistories(List<AntiHistory> antihistories) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AntiHistory> loadAntiHistories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}