
package com.algerd.musicbookspringmaven.repository.AlbumGenre;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import java.util.List;

public interface AlbumGenreRepository extends CrudRepository<AlbumGenreEntity> {
    
    List<AlbumGenreEntity> selectAlbumGenreByAlbum(AlbumEntity album);
    List<AlbumGenreEntity> selectAlbumGenreByGenre(GenreEntity genre);
    void deleteAlbumGenreByAlbum(AlbumEntity album);
    int countAlbumGenreByGenre(GenreEntity genre);    
}
