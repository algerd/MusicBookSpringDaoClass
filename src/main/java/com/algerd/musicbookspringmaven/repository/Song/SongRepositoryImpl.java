
package com.algerd.musicbookspringmaven.repository.Song;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Song.query.CountSongByAlbum;
import com.algerd.musicbookspringmaven.repository.Song.query.CountSongBySongNameAndAlbum;
import com.algerd.musicbookspringmaven.repository.Song.query.SelectAllSongs;
import com.algerd.musicbookspringmaven.repository.Song.query.SelectSongByAlbum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SongRepositoryImpl extends CrudRepositoryImpl<SongEntity> implements SongRepository {

    private SelectSongByAlbum selectSongByAlbum;
    private SelectAllSongs selectAllSongs;
    private CountSongByAlbum countSongByAlbum;
    private CountSongBySongNameAndAlbum countSongBySongNameAndAlbum;
    
    @Override
    public boolean containsSongByAlbum(AlbumEntity album) {
        return countSongByAlbum.findObject(album.getId()) > 0;  
    }  
    
    @Override
    public List<SongEntity> selectSongByAlbum(AlbumEntity album) {
        return selectSongByAlbum.execute(album.getId());
    }
     
    @Override
    public List<SongEntity> selectAllSongs() {
        return selectAllSongs.execute();
    }
    
    @Override
    public boolean containsSongBySongNameAndAlbum(String name, AlbumEntity album) {
        return countSongBySongNameAndAlbum.findObject(album.getId(), name.trim()) > 0;
    }

    @Autowired
    public void setSelectAllSongs(SelectAllSongs selectAllSongs) {
        this.selectAllSongs = selectAllSongs;
    }
    
    @Autowired
    public void setSelectSongByAlbum(SelectSongByAlbum selectSongByAlbum) {
        this.selectSongByAlbum = selectSongByAlbum;
        this.selectSongByAlbum.setRepository(this);
    }

    @Autowired
    public void setCountSongByAlbum(CountSongByAlbum countSongByAlbum) {
        this.countSongByAlbum = countSongByAlbum;
    }  

    @Autowired
    public void setCountSongBySongNameAndAlbum(CountSongBySongNameAndAlbum countSongBySongNameAndAlbum) {
        this.countSongBySongNameAndAlbum = countSongBySongNameAndAlbum;
    }
   
}
