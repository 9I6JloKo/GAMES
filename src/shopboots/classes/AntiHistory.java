/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author anana
 */
@Entity
public class AntiHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfVozvrata;
    private String client;
    private String product;
    private int clientNumber;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
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
        return "AntiHistory: [" + "Имя: " + client + "; Номер клиента: " + clientNumber + "; Продукт: " + product + "; Дата возврата: " + dateOfVozvrata + "]";
    }
}
