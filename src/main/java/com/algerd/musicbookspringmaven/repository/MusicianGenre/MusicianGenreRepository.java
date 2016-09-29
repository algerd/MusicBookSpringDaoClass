
package com.algerd.musicbookspringmaven.repository.MusicianGenre;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import java.util.List;

public interface MusicianGenreRepository extends CrudRepository<MusicianGenreEntity> {
    
    List<MusicianGenreEntity> selectMusicianGenreByMusician(MusicianEntity musician);
    List<MusicianGenreEntity> selectMusicianGenreByGenre(GenreEntity genre);
    void deleteMusicianGenreByMusician(MusicianEntity musician);
    int countMusicianGenreByGenre(GenreEntity genre);
    
}
