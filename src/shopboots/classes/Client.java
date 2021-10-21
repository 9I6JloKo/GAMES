/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots.classes;
import java.io.Serializable;
/**
 *
 * @author anana
 */
public class Client implements Serializable{
    private String clientName;
    private String clientSurname;
    private int clientNumber;
    private double clientMoney;

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public double getClientMoney() {
        return clientMoney;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setClientMoney(double clientMoney) {
        this.clientMoney = clientMoney;
    }
    @Override
    public String toString(){
        return "Client [Name= " + clientName + ", Surname= " + clientSurname + ", PhoneNumber= " + clientNumber + ", Money= " + clientMoney + "]";
    }
    
}
