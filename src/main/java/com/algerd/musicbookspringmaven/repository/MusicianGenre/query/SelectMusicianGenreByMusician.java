
package com.algerd.musicbookspringmaven.repository.MusicianGenre.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.MusicianGenreEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianGenreByMusician")
public class SelectMusicianGenreByMusician extends MappingSqlQuery<MusicianGenreEntity> {
    
    private BaseRepository<MusicianGenreEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "musician_genre.id, "
                + "musician_genre.id_genre, "
                + "musician_genre.id_musician, "              
                + "genre.name "
            + "from musician_genre "
                + "left join genre on musician_genre.id_genre = genre.id "
            + "where musician_genre.id_musician = ?";  
    
    @Autowired        
    public SelectMusicianGenreByMusician(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianGenreEntity musicianGenre = repository.getEntity(resultSet);
        GenreEntity genre = new GenreEntity();
        genre.setId(musicianGenre.getId_genre());
        genre.setName(resultSet.getString(4));
        musicianGenre.setGenre(genre);
        return musicianGenre;
    }

    public void setRepository(BaseRepository<MusicianGenreEntity> repository) {
        this.repository = repository;
    }
    
}
