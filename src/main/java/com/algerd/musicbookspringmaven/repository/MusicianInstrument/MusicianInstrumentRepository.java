
package com.algerd.musicbookspringmaven.repository.MusicianInstrument;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentEntity;
import java.util.List;

public interface MusicianInstrumentRepository extends CrudRepository<MusicianInstrumentEntity> {
    
    List<MusicianInstrumentEntity> selectMusicianInstrumentByMusician(MusicianEntity musician);
    List<MusicianInstrumentEntity> selectMusicianInstrumentByInstrument(InstrumentEntity instrument);
    int countMusicianInstrumentByInstrument(InstrumentEntity instrument);
    void deleteMusicianInstrumentByMusician(MusicianEntity musician);
    
}
