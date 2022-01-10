package Beans;

import Dtos.InstrumentDto;
import Dtos.ShopDto;
import Entities.Instrument;
import Entities.Shop;
import Interfaces.ShopService;
import Interfaces.ShopServiceR;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javaUtils.javaUtils.InsEntityToInsDto;
import static javaUtils.javaUtils.ShopEntityToShopDto;

@Stateless
@Local(ShopServiceR.class)
@Remote(ShopService.class)
public class ShopBean implements ShopService, ShopServiceR {

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
    public List<InstrumentDto> getAllInstrumentsDto(long idShop) {
        List<InstrumentDto> instrumentDtoList = new ArrayList<>();
        List<Instrument> instrumentList = getAllInstruments(idShop);
        for (Instrument instrument: instrumentList) {
            InstrumentDto instrumentDto = InsEntityToInsDto(instrument);
            instrumentDtoList.add(instrumentDto);
        }
        return instrumentDtoList;
    }

    @Override
    public ShopDto findShopDto(long index) {
        return ShopEntityToShopDto(findShop(index));
    }

    @Override
    public ShopDto addInsToShop(InstrumentDto instrumentDto, ShopDto shopDto) {
        instrumentDto.setShopDto(shopDto);
        shopDto.getInstrumentDtos().add(instrumentDto);
        InstrumentBean instrumentBean = new InstrumentBean();
        Shop shop = findShop(shopDto.getId());
        Instrument ins = instrumentBean.findIns(instrumentDto.getId());
        return ShopEntityToShopDto(addInsToShop(ins, shop));
    }

    @Override
    public ShopDto removeInsFromShop(InstrumentDto insDto, ShopDto shopDto) {
        insDto.setShopDto(null);
        shopDto.getInstrumentDtos().remove(insDto);
        Shop shop = findShop(shopDto.getId());
        Instrument ins = findIns(insDto.getId());
        return ShopEntityToShopDto(removeInsFromShop(ins, shop));
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
