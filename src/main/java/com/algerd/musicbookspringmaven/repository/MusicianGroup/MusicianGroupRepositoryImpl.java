
package com.algerd.musicbookspringmaven.repository.MusicianGroup;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.query.CountMusicianGroupByMusicianAndArtist;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.query.SelectMusicianGroupByArtist;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.query.SelectMusicianGroupByMusician;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicianGroupRepositoryImpl extends CrudRepositoryImpl<MusicianGroupEntity> implements MusicianGroupRepository {
  
    private SelectMusicianGroupByMusician selectMusicianGroupByMusician;
    private SelectMusicianGroupByArtist selectMusicianGroupByArtist;
    private CountMusicianGroupByMusicianAndArtist countMusicianGroupByMusicianAndArtist;
    
    @Override
    public List<MusicianGroupEntity> selectMusicianGroupByMusician(MusicianEntity musician) {
        return selectMusicianGroupByMusician.execute(musician.getId());
    }
    
    @Override
    public List<MusicianGroupEntity> selectMusicianGroupByArtist(ArtistEntity artist) {
        return selectMusicianGroupByArtist.execute(artist.getId());
    }
    
    @Override
    public boolean containsMusicianGroupByMusicianAndArtist(MusicianEntity musician, ArtistEntity artist) {
        return countMusicianGroupByMusicianAndArtist.findObject(musician.getId(), artist.getId()) > 0;
    }

    @Autowired
    public void setSelectMusicianGroupByMusician(SelectMusicianGroupByMusician selectMusicianGroupByMusician) {
        this.selectMusicianGroupByMusician = selectMusicianGroupByMusician;
        this.selectMusicianGroupByMusician.setRepository(this);
    }

    @Autowired
    public void setSelectMusicianGroupByArtist(SelectMusicianGroupByArtist selectMusicianGroupByArtist) {
        this.selectMusicianGroupByArtist = selectMusicianGroupByArtist;
        this.selectMusicianGroupByArtist.setRepository(this);
    }

    @Autowired
    public void setCountMusicianGroupByMusicianAndArtist(CountMusicianGroupByMusicianAndArtist countMusicianGroupByMusicianAndArtist) {
        this.countMusicianGroupByMusicianAndArtist = countMusicianGroupByMusicianAndArtist;
    }   
  
}
