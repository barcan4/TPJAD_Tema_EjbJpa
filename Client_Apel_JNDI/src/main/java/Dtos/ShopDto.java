package Dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShopDto implements Serializable {

    private long id;
    private String name;
    private String city;
    private String street;
    private List<InstrumentDto> instrumentDtos = new ArrayList<>();

    public ShopDto() {}

    public ShopDto(long id, String name, String city, String street) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShopDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public List<InstrumentDto> getInstrumentDtos() {
        return instrumentDtos;
    }

    public void setInstrumentDtos(List<InstrumentDto> instrumentDtos) {
        this.instrumentDtos = instrumentDtos;
    }
}
