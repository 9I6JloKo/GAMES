/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import shopboots.classes.Client;
import shopboots.classes.History;
import shopboots.classes.Product;
/**
 *
 * @author anana
 */
public interface Keeping {
    public void saveClients(Client[] clients);
    public Client[] loadClients();
    public void saveProducts(Product[] products);
    public Product[] loadProducts();
    public void saveHistories(History[] histories);
    public History[] loadHistories();
}
