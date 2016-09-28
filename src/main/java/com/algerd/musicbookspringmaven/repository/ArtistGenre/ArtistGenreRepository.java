
package com.algerd.musicbookspringmaven.repository.ArtistGenre;

import com.algerd.musicbookspringmaven.dbDriver.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.entity.Genre;
import java.util.List;

public interface ArtistGenreRepository extends CrudRepository<ArtistGenreEntity> {
    
    List<ArtistGenreEntity> selectArtistGenreByArtist(ArtistEntity artist);
    List<ArtistGenreEntity> selectArtistGenreByGenre(Genre genre);
    void deleteArtistGenreByArtist(ArtistEntity artist);
    int countArtistGenreByGenre(Genre genre);
    
}
