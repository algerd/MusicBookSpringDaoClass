
package com.algerd.musicbookspringmaven.repository.Musician;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import java.util.List;

public interface MusicianRepository extends CrudRepository<MusicianEntity> {
    
    List<MusicianEntity> selectMusucianByInstrument(InstrumentEntity instrument);
    
}
