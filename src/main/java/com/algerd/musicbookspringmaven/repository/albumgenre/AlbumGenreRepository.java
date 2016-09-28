
package com.algerd.musicbookspringmaven.repository.albumgenre;

import com.algerd.musicbookspringmaven.dbDriver.CrudRepository;
import com.algerd.musicbookspringmaven.entity.Album;
import com.algerd.musicbookspringmaven.entity.AlbumGenre;
import com.algerd.musicbookspringmaven.entity.Genre;
import java.util.List;

public interface AlbumGenreRepository extends CrudRepository<AlbumGenre> {
    
    List<AlbumGenre> selectAlbumGenreByAlbum(Album album);
    List<AlbumGenre> selectAlbumGenreByGenre(Genre genre);
    void deleteAlbumGenreByAlbum(Album album);
    int countAlbumsByGenre(Genre genre);    
}
