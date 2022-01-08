package Interfaces;

import Entities.Instrument;

import java.util.List;

public interface InstrumentService {
    public Instrument addIns(Instrument instrument);
    public List<Instrument> getAll();
    public Instrument deleteIns(Instrument ins);
    public Instrument findIns(long index);
    public Instrument updateIns(Instrument ins);
}
