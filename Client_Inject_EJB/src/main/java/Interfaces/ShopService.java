package Interfaces;

import Entities.Instrument;
import Entities.Shop;

import java.util.List;

public interface ShopService {
    List<Instrument> getAllInstruments(long idShop);
    Shop findShop(long idShop);
    Shop addInsToShop(Instrument ins, Shop shop);
}
