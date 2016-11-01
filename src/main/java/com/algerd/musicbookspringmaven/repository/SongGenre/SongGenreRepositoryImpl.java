
package com.algerd.musicbookspringmaven.repository.SongGenre;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.repository.impl.WrapChangedEntity;
import com.algerd.musicbookspringmaven.repository.SongGenre.query.CountSongGenreByGenre;
import com.algerd.musicbookspringmaven.repository.SongGenre.query.DeleteSongGenreBySong;
import com.algerd.musicbookspringmaven.repository.SongGenre.query.SelectSongGenreByGenre;
import com.algerd.musicbookspringmaven.repository.SongGenre.query.SelectSongGenreBySong;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SongGenreRepositoryImpl extends CrudRepositoryImpl<SongGenreEntity> implements SongGenreRepository {

    private SelectSongGenreBySong selectSongGenreBySong;
    private SelectSongGenreByGenre selectSongGenreByGenre;
    private DeleteSongGenreBySong deleteSongGenreBySong;
    private CountSongGenreByGenre countSongGenreByGenre;
    
    @Override
    public List<SongGenreEntity> selectSongGenreBySong(SongEntity song) {
        return selectSongGenreBySong.execute(song.getId());         
    }
        
    @Override
    public List<SongGenreEntity> selectSongGenreByGenre(GenreEntity genre) {
        return selectSongGenreByGenre.execute(genre.getId());
    };
    
    @Override
    public void deleteSongGenreBySong(SongEntity song) {
        deleteSongGenreBySong.update(song.getId());
        setDeleted(new WrapChangedEntity<>(null, null));
    }
    
    @Override
    public int countSongGenreByGenre(GenreEntity genre) {
        return countSongGenreByGenre.findObject(genre.getId());
    }

    @Autowired
    public void setSelectSongGenreBySong(SelectSongGenreBySong selectSongGenreBySong) {
        this.selectSongGenreBySong = selectSongGenreBySong;
        this.selectSongGenreBySong.setRepository(this);
    }  

    @Autowired
    public void setSelectSongGenreByGenre(SelectSongGenreByGenre selectSongGenreByGenre) {
        this.selectSongGenreByGenre = selectSongGenreByGenre;
        selectSongGenreByGenre.setRepository(this);
    }
    
    @Autowired
    public void setDeleteSongGenreBySong(DeleteSongGenreBySong deleteSongGenreBySong) {
        this.deleteSongGenreBySong = deleteSongGenreBySong;
    }

    @Autowired
    public void setCountSongGenreByGenre(CountSongGenreByGenre countSongGenreByGenre) {
        this.countSongGenreByGenre = countSongGenreByGenre;
    }  
}
