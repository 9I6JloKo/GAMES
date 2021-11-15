/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots.classes;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
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
public class History implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBuying;
    private int clientNumber;
    private String product;
    private int size;
    private String clientName;
    private LocalDate localDate;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
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
        return "Об истории: [" + "Номер покупателя: " + clientNumber + "; Его имя: " + clientName + "; модель(подробная): " + product + "; размер: " + size + "; Дата покупки: " + 
                dateOfBuying + "; Время, до которого возможно вернуть: " + localDate.getDayOfMonth() + "." + localDate.getMonthValue() + "." + localDate.getYear() + "]";
    }
    
}
