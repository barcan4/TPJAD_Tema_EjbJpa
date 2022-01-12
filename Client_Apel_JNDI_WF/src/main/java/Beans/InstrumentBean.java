package Beans;

import Entities.Instrument;
import Interfaces.InstrumentService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static javaUtils.javaUtils.InsEntityToInsDto;

@Stateless
@Remote(InstrumentService.class)
public class InstrumentBean implements InstrumentService {

    @PersistenceContext(unitName = "jndi")
    private EntityManager entityManager;

    @Override
    public Instrument addIns(Instrument instrument) {
        entityManager.persist(instrument);
        return instrument;
    }

    @Override
    public List<Instrument> getAll() {
        TypedQuery<Instrument> query = entityManager.createQuery("SELECT ins from Instrument ins", Instrument.class);
        return query.getResultList();
    }

    @Override
    public Instrument deleteIns(Instrument ins) {
        Instrument instrument = findIns(ins.getIdInstrument());
        entityManager.remove(instrument);
        return instrument;
    }

    @Override
    public Instrument findIns(long index) {
        return entityManager.find(Instrument.class, index);
    }

    @Override
    public Instrument updateIns(Instrument ins) {
        Instrument instrument = findIns(ins.getIdInstrument());
        if (instrument != null) {
            entityManager.createQuery("update Instrument instr set instr.name = '" + ins.getName()
                    + "', instr.type = '" + ins.getType()
                    + "', instr.price = " + ins.getPrice()
                    + " where instr.idInstrument = " + ins.getIdInstrument()).executeUpdate();
            return instrument;
        }
        return null;
    }
}
