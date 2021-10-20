/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots.classes;

/**
 *
 * @author anana
 */
public class Product {
    private String modell;
    private String size;
    private String bywho;
    private Double price;

    public String getModell() {
        return modell;
    }

    public String getSize() {
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

    public void setSize(String size) {
        this.size = size;
    }

    public void setBywho(String bywho) {
        this.bywho = bywho;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return "Фирма - " + bywho + "\nМодель - " + modell + "\nРазмер " + size + "\nЦена " + price + "€";
    }
}
