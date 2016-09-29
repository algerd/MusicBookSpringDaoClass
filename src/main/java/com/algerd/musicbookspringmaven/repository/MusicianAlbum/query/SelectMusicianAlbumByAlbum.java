
package com.algerd.musicbookspringmaven.repository.MusicianAlbum.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianAlbumByAlbum")
public class SelectMusicianAlbumByAlbum extends MappingSqlQuery<MusicianAlbumEntity>  {
    
    private BaseRepository<MusicianAlbumEntity> repository;
    
    private static final String QUERY =
        "select "
                + "musician_album.id, "
                + "musician_album.id_musician, "
                + "musician_album.id_album, "
                + "musician.name, "
                + "musician.rating "
            + "from musician_album "
                + "left join musician on musician_album.id_musician = musician.id "
            + "where musician_album.id_album = ?"; 
    
    @Autowired 
    public SelectMusicianAlbumByAlbum(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_album", Types.INTEGER));
        compile();
    }
    
    @Override
    protected MusicianAlbumEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianAlbumEntity musicianAlbum = repository.getEntity(resultSet);
        MusicianEntity musician = new MusicianEntity();
        musician.setId(musicianAlbum.getId_musician());
        musician.setName(resultSet.getString("name"));
        musician.setRating(resultSet.getInt("rating"));
        musicianAlbum.setMusician(musician);
        return musicianAlbum;
    }
    
    public void setRepository(BaseRepository<MusicianAlbumEntity> repository) {
        this.repository = repository;
    }

}
