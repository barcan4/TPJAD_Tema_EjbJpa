package Interfaces;

import Entities.Instrument;

import java.util.List;

public interface InstrumentService {
    Instrument addIns(Instrument instrument);
    List<Instrument> getAll();
    Instrument deleteIns(Instrument ins);
    Instrument findIns(long index);
    Instrument updateIns(Instrument ins);
}
