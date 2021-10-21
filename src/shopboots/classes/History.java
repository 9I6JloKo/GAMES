/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots.classes;
import java.util.Date;
/**
 *
 * @author anana
 */
public class History {
    private Date dateOfBuying;
    private String client;
    private String product;

    public Date getDateOfBuying() {
        return dateOfBuying;
    }

    public String getClient() {
        return client;
    }

    public String getProduct() {
        return product;
    }

    public void setDateOfBuying(Date dateOfBuying) {
        this.dateOfBuying = dateOfBuying;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    @Override
    public String toString(){
        return "History [" + "Name= " + client + ", product= " + product + ", dateOfBuying= " + dateOfBuying + "]";
    }
    
}
