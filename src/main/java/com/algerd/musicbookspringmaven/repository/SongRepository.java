
package com.algerd.musicbookspringmaven.repository;

import com.algerd.musicbookspringmaven.dbDriver.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.entity.Song;
import java.util.List;

public interface SongRepository extends CrudRepository<Song> {
    
    boolean containsAlbum(AlbumEntity album);
    List<Song> selectByAlbum(AlbumEntity album);
    List<Song> selectJoin();
    boolean containsSong(String name, AlbumEntity album);
    
}
