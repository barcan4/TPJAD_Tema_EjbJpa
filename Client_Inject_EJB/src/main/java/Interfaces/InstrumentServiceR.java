package Interfaces;

import Dtos.InstrumentDto;

import java.util.List;

public interface InstrumentServiceR {
    InstrumentDto addIns(InstrumentDto insDto);
    List<InstrumentDto> getAllDto();
    InstrumentDto deleteIns(InstrumentDto insDto);
    InstrumentDto findInsDto(long index);
    InstrumentDto updateIns(InstrumentDto insDto);
}
