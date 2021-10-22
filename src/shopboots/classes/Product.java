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
public class Product implements Serializable{
    private String modell;
    private int size;
    private String bywho;
    private Double price;
    private int piece;
    private int maxPiece;

    public int getMaxPiece() {
        return maxPiece;
    }
    
    public int getPiece() {
        return piece;
    }
    
    public String getModell() {
        return modell;
    }

    public int getSize() {
        return size;
    }

    public String getBywho() {
        return bywho;
    }

    public Double getPrice() {
        return price;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBywho(String bywho) {
        this.bywho = bywho;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMaxPiece(int maxPiece) {
        this.maxPiece = maxPiece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }
    
    @Override
    public String toString() {
        return "Product [Seller= " + bywho + ", modell= " + modell + ", price= " + price + ", size= " + size + ", piece= " + piece + "]";
    }
}
