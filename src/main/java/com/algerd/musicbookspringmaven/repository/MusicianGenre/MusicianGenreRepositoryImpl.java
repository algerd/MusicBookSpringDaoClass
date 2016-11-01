
package com.algerd.musicbookspringmaven.repository.MusicianGenre;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.impl.WrapChangedEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.query.CountMusicianGenreByGenre;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.query.DeleteMusicianGenreByMusician;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.query.SelectMusicianGenreByGenre;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.query.SelectMusicianGenreByMusician;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicianGenreRepositoryImpl extends CrudRepositoryImpl<MusicianGenreEntity> implements MusicianGenreRepository {

    private SelectMusicianGenreByMusician selectMusicianGenreByMusician;
    private SelectMusicianGenreByGenre selectMusicianGenreByGenre;
    private DeleteMusicianGenreByMusician deleteMusicianGenreByMusician;
    private CountMusicianGenreByGenre countMusicianGenreByGenre;
    
    @Override
    public List<MusicianGenreEntity> selectMusicianGenreByMusician(MusicianEntity musician) {
        return selectMusicianGenreByMusician.execute(musician.getId());
    }
    
    @Override
    public List<MusicianGenreEntity> selectMusicianGenreByGenre(GenreEntity genre) {
        return selectMusicianGenreByGenre.execute(genre.getId());
    }
      
    @Override
    public void deleteMusicianGenreByMusician(MusicianEntity musician) {
        deleteMusicianGenreByMusician.update(musician.getId());
        setDeleted(new WrapChangedEntity<>(null, null));
    }
    
    @Override
    public int countMusicianGenreByGenre(GenreEntity genre) {
        return countMusicianGenreByGenre.findObject(genre.getId());
    }

    @Autowired
    public void setSelectMusicianGenreByMusician(SelectMusicianGenreByMusician selectMusicianGenreByMusician) {
        this.selectMusicianGenreByMusician = selectMusicianGenreByMusician;
        this.selectMusicianGenreByMusician.setRepository(this);
    }

    @Autowired
    public void setSelectMusicianGenreByGenre(SelectMusicianGenreByGenre selectMusicianGenreByGenre) {
        this.selectMusicianGenreByGenre = selectMusicianGenreByGenre;
        this.selectMusicianGenreByGenre.setRepository(this);
    }

    @Autowired
    public void setDeleteMusicianGenreByMusician(DeleteMusicianGenreByMusician deleteMusicianGenreByMusician) {
        this.deleteMusicianGenreByMusician = deleteMusicianGenreByMusician;
    }  

    @Autowired
    public void setCountMusicianGenreByGenre(CountMusicianGenreByGenre countMusicianGenreByGenre) {
        this.countMusicianGenreByGenre = countMusicianGenreByGenre;
    }
        
}
