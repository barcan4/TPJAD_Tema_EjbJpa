package Interfaces;

import Entities.Instrument;
import Entities.Shop;

import java.util.Collection;
import java.util.List;

public interface ShopService {
    List<Instrument> getAllInstruments(long idShop);
    List<Shop> getShops();
    Shop findShop(long idShop);
    Shop addInsToShop(Instrument ins, Shop shop);
    Shop removeInsFromShop(Instrument ins, Shop shop);
    Collection<Instrument> getFreeIns();
    Instrument findIns(long index);
}
