
package com.algerd.musicbookspringmaven.repository.albumgenre;

import com.algerd.musicbookspringmaven.dbDriver.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.entity.Album;
import com.algerd.musicbookspringmaven.entity.Genre;
import com.algerd.musicbookspringmaven.dbDriver.impl.WrapChangedEntity;
import com.algerd.musicbookspringmaven.repository.albumgenre.query.DeleteAlbumGenreByAlbum;
import com.algerd.musicbookspringmaven.repository.albumgenre.query.SelectAlbumGenreByAlbum;
import com.algerd.musicbookspringmaven.repository.albumgenre.query.SelectAlbumGenreByGenre;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

public class AlbumGenreRepositoryImpl extends CrudRepositoryImpl<AlbumGenre> implements AlbumGenreRepository {
   
    private SelectAlbumGenreByGenre selectAlbumGenreByGenre;
    private SelectAlbumGenreByAlbum selectAlbumGenreByAlbum;
    private DeleteAlbumGenreByAlbum deleteAlbumGenreByAlbum;
     
    @Override
    public List<AlbumGenre> selectAlbumGenreByAlbum(Album album) {
        return selectAlbumGenreByAlbum.execute(album.getId());
    }
    
    @Override
    public List<AlbumGenre> selectAlbumGenreByGenre(Genre genre) {
        return selectAlbumGenreByGenre.execute(genre.getId());
    }    
  
    @Override
    public void deleteAlbumGenreByAlbum(Album album) {
        deleteAlbumGenreByAlbum.update(album.getId());  
        setDeleted(new WrapChangedEntity<>(null, null));
    }
    
    @Override
    public int countAlbumsByGenre(Genre genre) {
        String prepareQuery = 
            "select count(id) "
                + "from album_genre "
                + "where id_genre = ?";      
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
            prepareQuery, 
            new int[] {Types.INTEGER}    
        ); 
        return jdbcTemplate.query(pscf.newPreparedStatementCreator(
            new Object[] {genre.getId()}), 
            (ResultSet rs) -> rs.getInt(1)
        );     
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
          
}
