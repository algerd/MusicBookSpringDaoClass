
package com.algerd.musicbookspringmaven.repository.ArtistGenre;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import java.util.List;

public interface ArtistGenreRepository extends CrudRepository<ArtistGenreEntity> {
    
    List<ArtistGenreEntity> selectArtistGenreByArtist(ArtistEntity artist);
    List<ArtistGenreEntity> selectArtistGenreByGenre(GenreEntity genre);
    void deleteArtistGenreByArtist(ArtistEntity artist);
    int countArtistGenreByGenre(GenreEntity genre);
    
}
