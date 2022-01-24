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
/*import shopboots.classes.Wages;*/

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
                }else{
                    em.merge(clients.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Client> loadClients() {
        List<Client> clients;
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
        tx.begin();
            for (int i = 0; i < products.size(); i++) {
                if(products.get(i).getId() == null){
                    em.persist(products.get(i));
                }else{
                    em.merge(products.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Product> loadProducts() {
        List<Product> products;
        try {
            products = em.createQuery("SELECT product FROM Product product")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return products;
    }

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    em.persist(histories.get(i));
                }else{
                    em.merge(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        List<History> histories;
        try {
            histories = em.createQuery("SELECT history FROM History history")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return histories;
    }

    @Override
    public void saveAntiHistories(List<AntiHistory> antihistories) {
        tx.begin();
            for (int i = 0; i < antihistories.size(); i++) {
                if(antihistories.get(i).getId() == null){
                    em.persist(antihistories.get(i));
                }else{
                    em.merge(antihistories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<AntiHistory> loadAntiHistories() {
        List<AntiHistory> antiHistories;
        try {
            antiHistories = em.createQuery("SELECT antiHistory FROM AntiHistory antiHistory")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return antiHistories;
    }
/*
    @Override
    public void saveWages(Wages wages) {
        tx.begin();
            em.persist(wages);
        tx.commit();
    }
    @Override
    public double loadWages() {
        Wages wages = null;
        try {
            wages = em.createQuery("SELECT wages FROM Wages wages")
                .getParameter();
        } catch (Exception e) {
            return 0;
        }
        return wages;
    }*/
}