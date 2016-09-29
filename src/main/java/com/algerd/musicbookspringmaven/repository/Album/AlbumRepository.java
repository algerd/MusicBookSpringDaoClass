
package com.algerd.musicbookspringmaven.repository.Album;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import java.util.List;

public interface AlbumRepository extends CrudRepository<AlbumEntity> {
    
    List<AlbumEntity> selectAlbumByArtist(ArtistEntity artist);
    boolean containsArtist(ArtistEntity artist);
    boolean containsAlbum(String name, ArtistEntity artist);
}
