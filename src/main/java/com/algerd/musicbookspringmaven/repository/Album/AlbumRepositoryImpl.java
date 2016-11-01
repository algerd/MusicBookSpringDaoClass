
package com.algerd.musicbookspringmaven.repository.Album;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Album.query.CountAlbumByArtist;
import com.algerd.musicbookspringmaven.repository.Album.query.CountAlbumByArtistAndAlbumName;
import com.algerd.musicbookspringmaven.repository.Album.query.SelectAlbumByArtist;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepositoryImpl extends CrudRepositoryImpl<AlbumEntity> implements AlbumRepository {
     
    private CountAlbumByArtist countAlbumByArtist;
    private CountAlbumByArtistAndAlbumName countAlbumByArtistAndAlbumName;
    private SelectAlbumByArtist selectAlbumByArtist;
    
    // запретить удаление записи с id = 1 (Unknown album)
    @Override
    public void delete(AlbumEntity entity) {        
        if (entity.getId() != 1) {
            super.delete(entity);
        }      
    }
  
    // запретить добавление/редактирование записи с id = 1 (Unknown album)
    @Override 
    public void save(AlbumEntity entity) {      
        if (entity.getId() != 1) {
            super.save(entity);
        }
    }
    
    @Override
    public List<AlbumEntity> selectAlbumByArtist(ArtistEntity artist) {
        return selectAlbumByArtist.execute(artist.getId());     
    }   
    
    @Override
    public boolean containsArtist(ArtistEntity artist) {  
        return countAlbumByArtist.findObject(artist.getId()) > 0;
    }
     
    @Override
    public boolean containsAlbum(String name, ArtistEntity artist) {
        return countAlbumByArtistAndAlbumName.findObject(artist.getId(), name.trim()) > 0;
    }

    @Autowired
    public void setCountAlbumByArtist(CountAlbumByArtist countAlbumByArtist) {
        this.countAlbumByArtist = countAlbumByArtist;
    }
    
    @Autowired
    public void setCountAlbumByArtistAndAlbumName(CountAlbumByArtistAndAlbumName countAlbumByArtistAndAlbumName) {
        this.countAlbumByArtistAndAlbumName = countAlbumByArtistAndAlbumName;
    }

    @Autowired
    public void setSelectAlbumByArtist(SelectAlbumByArtist selectAlbumByArtist) {
        this.selectAlbumByArtist = selectAlbumByArtist;
        this.selectAlbumByArtist.setRepository(this);
    }
           
}
