
package com.algerd.musicbookspringmaven.repository.MusicianAlbum;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.query.CountMusicianAlbumByMusicianAndAlbum;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.query.SelectMusicianAlbumByAlbum;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.query.SelectMusicianAlbumByMusician;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class MusicianAlbumRepositoryImpl extends CrudRepositoryImpl<MusicianAlbumEntity> implements MusicianAlbumRepository {

    private SelectMusicianAlbumByMusician selectMusicianAlbumByMusician;
    private SelectMusicianAlbumByAlbum selectMusicianAlbumByAlbum;
    private CountMusicianAlbumByMusicianAndAlbum countMusicianAlbumByMusicianAndAlbum;
      
    @Override
    public List<MusicianAlbumEntity> selectMusicianAlbumByMusician(MusicianEntity musician) {
        return selectMusicianAlbumByMusician.execute(musician.getId()); 
    }
    
    @Override
    public List<MusicianAlbumEntity> selectMusicianAlbumByAlbum(AlbumEntity album) {
        return selectMusicianAlbumByAlbum.execute(album.getId());
    }
    
    @Override
    public boolean containsMusicianAlbumByMusicianAndAlbum(MusicianEntity musician, AlbumEntity album) {
        return countMusicianAlbumByMusicianAndAlbum.findObject(musician.getId(), album.getId()) > 0; 
    }

    @Autowired
    public void setSelectMusicianAlbumByMusician(SelectMusicianAlbumByMusician selectMusicianAlbumByMusician) {
        this.selectMusicianAlbumByMusician = selectMusicianAlbumByMusician;
        this.selectMusicianAlbumByMusician.setRepository(this);
    }

    @Autowired
    public void setSelectMusicianAlbumByAlbum(SelectMusicianAlbumByAlbum selectMusicianAlbumByAlbum) {
        this.selectMusicianAlbumByAlbum = selectMusicianAlbumByAlbum;
        this.selectMusicianAlbumByAlbum.setRepository(this);
    }

    @Autowired
    public void setCountMusicianAlbumByMusicianAndAlbum(CountMusicianAlbumByMusicianAndAlbum countMusicianAlbumByMusicianAndAlbum) {
        this.countMusicianAlbumByMusicianAndAlbum = countMusicianAlbumByMusicianAndAlbum;
    }       
}
