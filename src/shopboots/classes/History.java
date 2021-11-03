/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots.classes;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
/**
 *
 * @author anana
 */
public class History implements Serializable{
    private Date dateOfBuying;
    private int clientNumber;
    private String product;
    private int size;
    private String clientName;
    private LocalDate localDate;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getSize() {
        return size;
    }
    
    public Date getDateOfBuying() {
        return dateOfBuying;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public String getProduct() {
        return product;
    }

    public void setDateOfBuying(Date dateOfBuying) {
        this.dateOfBuying = dateOfBuying;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public String toString(){
        return "History [" + "Number= " + clientNumber + ", NAME= " + clientName + ", product= " + product + ", size= " + size + ", dateOfBuying= " + dateOfBuying + ", Backing= " + localDate.getDayOfMonth() + "." + localDate.getMonthValue() + "." + localDate.getYear() + "]";
    }
    
}
