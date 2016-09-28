
package com.algerd.musicbookspringmaven.repository.AlbumGenre;

import com.algerd.musicbookspringmaven.dbDriver.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.entity.Genre;
import java.util.List;

public interface AlbumGenreRepository extends CrudRepository<AlbumGenreEntity> {
    
    List<AlbumGenreEntity> selectAlbumGenreByAlbum(AlbumEntity album);
    List<AlbumGenreEntity> selectAlbumGenreByGenre(Genre genre);
    void deleteAlbumGenreByAlbum(AlbumEntity album);
    int countAlbumGenreByGenre(Genre genre);    
}
