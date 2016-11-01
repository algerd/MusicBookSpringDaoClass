
package com.algerd.musicbookspringmaven.repository.MusicianInstrument;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.impl.WrapChangedEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.query.CountMusicianInstrumentByInstrument;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.query.DeleteMusicianInstrumentByMusician;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.query.SelectMusicianInstrumentByInstrument;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.query.SelectMusicianInstrumentByMusician;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicianInstrumentRepositoryImpl extends CrudRepositoryImpl<MusicianInstrumentEntity> implements MusicianInstrumentRepository {
  
    private SelectMusicianInstrumentByMusician selectMusicianInstrumentByMusician;
    private SelectMusicianInstrumentByInstrument selectMusicianInstrumentByInstrument;
    private CountMusicianInstrumentByInstrument countMusicianInstrumentByInstrument;
    private DeleteMusicianInstrumentByMusician deleteMusicianInstrumentByMusician;
    
    @Override
    public List<MusicianInstrumentEntity> selectMusicianInstrumentByMusician(MusicianEntity musician) {
        return selectMusicianInstrumentByMusician.execute(musician.getId());
    }
    
    @Override
    public List<MusicianInstrumentEntity> selectMusicianInstrumentByInstrument(InstrumentEntity instrument) {
        return selectMusicianInstrumentByInstrument.execute(instrument.getId());
    }

    @Override
    public int countMusicianInstrumentByInstrument(InstrumentEntity instrument) {
        return countMusicianInstrumentByInstrument.findObject(instrument.getId());
    }
    
    @Override
    public void deleteMusicianInstrumentByMusician(MusicianEntity musician) {
        deleteMusicianInstrumentByMusician.update(musician.getId());
        setDeleted(new WrapChangedEntity<>(null, null));
    }

    @Autowired
    public void setSelectMusicianInstrumentByMusician(SelectMusicianInstrumentByMusician selectMusicianInstrumentByMusician) {
        this.selectMusicianInstrumentByMusician = selectMusicianInstrumentByMusician;
        this.selectMusicianInstrumentByMusician.setRepository(this);
    }
    
    @Autowired
    public void setSelectMusicianInstrumentByInstrument(SelectMusicianInstrumentByInstrument selectMusicianInstrumentByInstrument) {
        this.selectMusicianInstrumentByInstrument = selectMusicianInstrumentByInstrument;
        this.selectMusicianInstrumentByInstrument.setRepository(this);
    }

    @Autowired
    public void setCountMusicianInstrumentByInstrument(CountMusicianInstrumentByInstrument countMusicianInstrumentByInstrument) {
        this.countMusicianInstrumentByInstrument = countMusicianInstrumentByInstrument;
    }

    @Autowired
    public void setDeleteMusicianInstrumentByMusician(DeleteMusicianInstrumentByMusician deleteMusicianInstrumentByMusician) {
        this.deleteMusicianInstrumentByMusician = deleteMusicianInstrumentByMusician;
    }
        
}
