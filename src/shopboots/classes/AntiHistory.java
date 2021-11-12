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
public class AntiHistory {
    private Date dateOfVozvrata;
    private String client;
    private String product;
    private int clientNumber;
    public Date getDateOfVozvrata() {
        return dateOfVozvrata;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClient() {
        return client;
    }

    public String getProduct() {
        return product;
    }

    public void setDateOfVozvrata(Date dateOfVozvrata) {
        this.dateOfVozvrata = dateOfVozvrata;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    @Override
    public String toString(){
        return "AntiHistory [" + "Name= " + client + ", clientNumber= " + clientNumber + ", product= " + product + ", dateOfVOZVRATA= " + dateOfVozvrata + "]";
    }
}
