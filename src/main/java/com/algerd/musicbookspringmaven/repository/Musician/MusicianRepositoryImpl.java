
package com.algerd.musicbookspringmaven.repository.Musician;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.Musician.query.SelectMusicianByInstrument;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class MusicianRepositoryImpl extends CrudRepositoryImpl<MusicianEntity> implements MusicianRepository {
  
    private SelectMusicianByInstrument selectMusicianByInstrument;
    
    @Override
    public List<MusicianEntity> selectMusucianByInstrument(InstrumentEntity instrument) {
        return selectMusicianByInstrument.execute(instrument.getId());
    }

    @Autowired
    public void setSelectMusicianByInstrument(SelectMusicianByInstrument selectMusicianByInstrument) {
        this.selectMusicianByInstrument = selectMusicianByInstrument;
        this.selectMusicianByInstrument.setRepository(this);
    }
    
}
