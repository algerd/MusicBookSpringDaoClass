
package com.algerd.musicbookspringmaven.repository;

import com.algerd.musicbookspringmaven.dbDriver.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.entity.Musician;
import com.algerd.musicbookspringmaven.entity.MusicianAlbum;
import java.util.List;


public interface MusicianAlbumRepository extends CrudRepository<MusicianAlbum> {
    
    List<MusicianAlbum> selectJoinByMusician(Musician musician);
    List<MusicianAlbum> selectJoinByAlbum(AlbumEntity album);
    boolean containsMusicianAlbum(Musician musician, AlbumEntity album);
    
}
