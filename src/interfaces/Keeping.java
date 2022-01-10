/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.util.List;
import shopboots.classes.AntiHistory;
import shopboots.classes.Client;
import shopboots.classes.History;
import shopboots.classes.Product;
/**
 *
 * @author anana
 */
public interface Keeping {
    public void saveClients(List<Client> clients);
    public List<Client> loadClients();
    public void saveProducts(List<Product> products);
    public List<Product> loadProducts();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();
    public void saveAntiHistories(List<AntiHistory> antihistories);
    public List<AntiHistory> loadAntiHistories();/*
    public void saveWages(double wages);
    public double loadWages();*/
}
