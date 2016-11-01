
package com.algerd.musicbookspringmaven.repository.ArtistGenre;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.impl.WrapChangedEntity;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.query.CountArtistGenreByGenre;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.query.DeleteArtistGenreByArtist;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.query.SelectArtistGenreByArtist;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.query.SelectArtistGenreByGenre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistGenreRepositoryImpl extends CrudRepositoryImpl<ArtistGenreEntity> implements ArtistGenreRepository {
   
    private SelectArtistGenreByArtist selectArtistGenreByArtist;
    private SelectArtistGenreByGenre selectArtistGenreByGenre;
    private DeleteArtistGenreByArtist deleteArtistGenreByArtist;
    private CountArtistGenreByGenre countArtistGenreByGenre;
      
    @Override
    public List<ArtistGenreEntity> selectArtistGenreByArtist(ArtistEntity artist) { 
        return selectArtistGenreByArtist.execute(artist.getId()); 
    }
    
    @Override
    public List<ArtistGenreEntity> selectArtistGenreByGenre(GenreEntity genre) {
        return selectArtistGenreByGenre.execute(genre.getId());
    };
    
    @Override
    public void deleteArtistGenreByArtist(ArtistEntity artist) {
        deleteArtistGenreByArtist.update(artist.getId());
        setDeleted(new WrapChangedEntity<>(null, null));
    }
    
    @Override
    public int countArtistGenreByGenre(GenreEntity genre) {
        return countArtistGenreByGenre.findObject(genre.getId());
    }

    @Autowired
    public void setSelectArtistGenreByArtist(SelectArtistGenreByArtist selectArtistGenreByArtist) {
        this.selectArtistGenreByArtist = selectArtistGenreByArtist;
        this.selectArtistGenreByArtist.setRepository(this);
    }
    
    @Autowired
    public void setSelectArtistGenreByGenre(SelectArtistGenreByGenre selectArtistGenreByGenre) {
        this.selectArtistGenreByGenre = selectArtistGenreByGenre;
        this.selectArtistGenreByGenre.setRepository(this);
    }

    @Autowired
    public void setDeleteArtistGenreByArtist(DeleteArtistGenreByArtist deleteArtistGenreByArtist) {
        this.deleteArtistGenreByArtist = deleteArtistGenreByArtist;
    }

    @Autowired
    public void setCountArtistGenreByGenre(CountArtistGenreByGenre countArtistGenreByGenre) {
        this.countArtistGenreByGenre = countArtistGenreByGenre;
    }      
}
