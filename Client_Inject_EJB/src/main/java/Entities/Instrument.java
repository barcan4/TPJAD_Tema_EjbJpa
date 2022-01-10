package Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "instruments")
public class Instrument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInstrument;
    private String name;
    private String type;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idShop")
    private Shop shop;

    public Instrument() {
    }

    public Instrument(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public long getIdInstrument() {
        return idInstrument;
    }

    public void setIdInstrument(long idInstrument) {
        this.idInstrument = idInstrument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "idInstrument=" + idInstrument +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
