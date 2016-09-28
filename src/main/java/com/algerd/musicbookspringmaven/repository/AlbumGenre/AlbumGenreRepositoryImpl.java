
package com.algerd.musicbookspringmaven.repository.AlbumGenre;

import com.algerd.musicbookspringmaven.dbDriver.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.entity.Genre;
import com.algerd.musicbookspringmaven.dbDriver.impl.WrapChangedEntity;
import com.algerd.musicbookspringmaven.repository.AlbumGenre.query.CountAlbumGenreByGenre;
import com.algerd.musicbookspringmaven.repository.AlbumGenre.query.DeleteAlbumGenreByAlbum;
import com.algerd.musicbookspringmaven.repository.AlbumGenre.query.SelectAlbumGenreByAlbum;
import com.algerd.musicbookspringmaven.repository.AlbumGenre.query.SelectAlbumGenreByGenre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumGenreRepositoryImpl extends CrudRepositoryImpl<AlbumGenreEntity> implements AlbumGenreRepository {
   
    private SelectAlbumGenreByGenre selectAlbumGenreByGenre;
    private SelectAlbumGenreByAlbum selectAlbumGenreByAlbum;
    private DeleteAlbumGenreByAlbum deleteAlbumGenreByAlbum;
    private CountAlbumGenreByGenre countAlbumGenreByGenre;
     
    @Override
    public List<AlbumGenreEntity> selectAlbumGenreByAlbum(AlbumEntity album) {
        return selectAlbumGenreByAlbum.execute(album.getId());
    }
    
    @Override
    public List<AlbumGenreEntity> selectAlbumGenreByGenre(Genre genre) {
        return selectAlbumGenreByGenre.execute(genre.getId());
    }    
  
    @Override
    public void deleteAlbumGenreByAlbum(AlbumEntity album) {
        deleteAlbumGenreByAlbum.update(album.getId());  
        setDeleted(new WrapChangedEntity<>(null, null));
    }
       
    @Override
    public int countAlbumGenreByGenre(Genre genre) {
        return countAlbumGenreByGenre.findObject(genre.getId());       
    }
    
    @Autowired
    public void setSelectAlbumGenreByGenre(SelectAlbumGenreByGenre selectAlbumGenreByGenre) {
        this.selectAlbumGenreByGenre = selectAlbumGenreByGenre;
        this.selectAlbumGenreByGenre.setRepository(this);
    }
    
    @Autowired
    public void setSelectAlbumGenreByAlbum(SelectAlbumGenreByAlbum selectAlbumGenreByAlbum) {
        this.selectAlbumGenreByAlbum = selectAlbumGenreByAlbum;
        this.selectAlbumGenreByAlbum.setRepository(this);
    }

    @Autowired
    public void setDeleteAlbumGenreByAlbum(DeleteAlbumGenreByAlbum deleteAlbumGenreByAlbum) {
        this.deleteAlbumGenreByAlbum = deleteAlbumGenreByAlbum;
    }

    @Autowired
    public void setCountAlbumGenreByGenre(CountAlbumGenreByGenre countAlbumGenreByGenre) {
        this.countAlbumGenreByGenre = countAlbumGenreByGenre;
    }
             
}
