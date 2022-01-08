package Interfaces;

import Dtos.InstrumentDto;

import java.util.List;

public interface InstrumentServiceR {
    public InstrumentDto addIns(InstrumentDto insDto);
    public List<InstrumentDto> getAllDto();
    public InstrumentDto deleteIns(InstrumentDto insDto);
    public InstrumentDto findInsDto(long index);
    public InstrumentDto updateIns(InstrumentDto insDto);
}
