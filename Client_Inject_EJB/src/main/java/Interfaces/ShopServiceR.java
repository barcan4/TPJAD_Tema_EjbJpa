package Interfaces;

import Dtos.InstrumentDto;
import Dtos.ShopDto;

import java.util.List;

public interface ShopServiceR {
    List<InstrumentDto> getAllInstrumentsDto(long idShop);
    ShopDto findShopDto(long index);
    ShopDto addInsToShop(InstrumentDto instrumentDto, ShopDto shopDto);
}
