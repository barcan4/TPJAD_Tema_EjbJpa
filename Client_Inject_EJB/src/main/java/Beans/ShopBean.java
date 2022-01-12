package Beans;

import Entities.Instrument;
import Entities.Shop;
import Interfaces.ShopService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Collection;
import java.util.List;

@Stateless
@Remote(ShopService.class)
public class ShopBean implements ShopService {

    @PersistenceContext(unitName = "ejb")
    private EntityManager entityManager;

    @Override
    public List<Instrument> getAllInstruments(long idShop) {
        TypedQuery<Instrument> query = entityManager.createQuery("select ins from Instrument ins " +
                "where ins.shop = " + idShop, Instrument.class);
        return query.getResultList();
    }

    @Override
    public List<Shop> getShops() {
        TypedQuery<Shop> query = entityManager.createQuery("select shop from Shop shop", Shop.class);
        return query.getResultList();
    }

    @Override
    public Shop findShop(long idShop) {
        return entityManager.find(Shop.class, idShop);
    }

    @Override
    public Shop addInsToShop(Instrument ins, Shop shop) {
        Shop shopMerge = entityManager.merge(shop);
        Instrument insMerge = entityManager.merge(ins);
        shopMerge.getInstruments().add(insMerge);
        insMerge.setShop(shop);
        return shopMerge;
    }

    @Override
    public Shop removeInsFromShop(Instrument ins, Shop shop) {
        Shop shopMerge = entityManager.merge(shop);
        Instrument insMerge = entityManager.merge(ins);
        shopMerge.getInstruments().remove(insMerge);
        insMerge.setShop(null);
        return shopMerge;
    }

    @Override
    public Collection<Instrument> getFreeIns() {
        TypedQuery<Instrument> query = entityManager.createQuery("select ins from Instrument ins where ins.shop is null", Instrument.class);
        return query.getResultList();
    }

    @Override
    public Instrument findIns(long index) {
        return entityManager.find(Instrument.class, index);
    }
}
