
package com.algerd.musicbookspringmaven.repository.Song;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import java.util.List;

public interface SongRepository extends CrudRepository<SongEntity> {
    
    boolean containsSongByAlbum(AlbumEntity album);
    List<SongEntity> selectSongByAlbum(AlbumEntity album);
    List<SongEntity> selectAllSongs();
    boolean containsSongBySongNameAndAlbum(String name, AlbumEntity album);
    
}
