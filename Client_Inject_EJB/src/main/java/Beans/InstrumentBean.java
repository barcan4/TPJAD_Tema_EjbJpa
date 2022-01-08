package Beans;

import Dtos.InstrumentDto;
import Entities.Instrument;
import Interfaces.InstrumentService;
import Interfaces.InstrumentServiceR;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static javaUtils.javaUtils.InsEntityToInsDto;

@Stateless
@Local(InstrumentServiceR.class)
@Remote(InstrumentService.class)
public class InstrumentBean implements InstrumentService, InstrumentServiceR {

    @PersistenceContext(unitName = "ejb")
    private EntityManager entityManager;

    @Override
    public Instrument addIns(Instrument instrument) {
        entityManager.persist(instrument);
        return instrument;
    }

    @Override
    public InstrumentDto addIns(InstrumentDto insDto) {
        Instrument ins = new Instrument(insDto.getName(), insDto.getType(), insDto.getPrice());
        return InsEntityToInsDto(addIns(ins));
    }

    @Override
    public List<InstrumentDto> getAllDto() {
        List<InstrumentDto> instrumentDtoList = new ArrayList<>();
        List<Instrument> instrumentList = getAll();
        for (Instrument instrument: instrumentList) {
            InstrumentDto instrumentDto = InsEntityToInsDto(instrument);
            instrumentDtoList.add(instrumentDto);
        }
        return instrumentDtoList;
    }

    @Override
    public List<Instrument> getAll() {
        TypedQuery<Instrument> query = entityManager.createQuery("SELECT ins from Instrument ins", Instrument.class);
        return query.getResultList();
    }

    @Override
    public InstrumentDto deleteIns(InstrumentDto insDto) {
        Instrument ins = findIns(insDto.getId());
        return InsEntityToInsDto(deleteIns(ins));
    }

    @Override
    public Instrument deleteIns(Instrument ins) {
        Instrument instrument = findIns(ins.getIdInstrument());
        entityManager.remove(instrument);
        return instrument;
    }

    @Override
    public InstrumentDto findInsDto(long index) {
        return InsEntityToInsDto(findIns(index));
    }

    @Override
    public Instrument findIns(long index) {
        return entityManager.find(Instrument.class, index);
    }

    @Override
    public Instrument updateIns(Instrument ins) {
        Instrument instrument = findIns(ins.getIdInstrument());
        if (instrument != null) {
            entityManager.createQuery("update Instrument instr set instr.name = " + ins.getName()
                    + ", instr.type = " + ins.getType()
                    + ", instr.price = " + ins.getPrice()
                    + " where instr.idInstrument = " + ins.getIdInstrument()).executeUpdate();
            return instrument;
        }
        return null;
    }

    @Override
    public InstrumentDto updateIns(InstrumentDto insDto) {
        InstrumentDto instrumentDto = findInsDto(insDto.getId());
        if (instrumentDto != null) {
            Instrument ins = findIns(insDto.getId());
            ins.setName(insDto.getName());
            ins.setType(insDto.getType());
            ins.setPrice(insDto.getPrice());
            return InsEntityToInsDto(ins);
        }
        return null;
    }
}
