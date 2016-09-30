
package com.algerd.musicbookspringmaven.repository.SongGenre;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import java.util.List;

public interface SongGenreRepository extends CrudRepository<SongGenreEntity> {
    
    List<SongGenreEntity> selectSongGenreBySong(SongEntity song);
    List<SongGenreEntity> selectSongGenreByGenre(GenreEntity genre);
    void deleteSongGenreBySong(SongEntity song);
    int countSongGenreByGenre(GenreEntity genre);
}
