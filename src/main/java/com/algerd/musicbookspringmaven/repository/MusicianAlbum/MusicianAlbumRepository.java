
package com.algerd.musicbookspringmaven.repository.MusicianAlbum;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import java.util.List;


public interface MusicianAlbumRepository extends CrudRepository<MusicianAlbumEntity> {
    
    List<MusicianAlbumEntity> selectMusicianAlbumByMusician(MusicianEntity musician);
    List<MusicianAlbumEntity> selectMusicianAlbumByAlbum(AlbumEntity album);
    boolean containsMusicianAlbumByMusicianAndAlbum(MusicianEntity musician, AlbumEntity album);
    
}
