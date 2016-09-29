
package com.algerd.musicbookspringmaven.repository.MusicianGenre.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.MusicianGenreEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianGenreByGenre")
public class SelectMusicianGenreByGenre extends MappingSqlQuery<MusicianGenreEntity> {
    
    private BaseRepository<MusicianGenreEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "musician_genre.id, "
                + "musician_genre.id_genre, "
                + "musician_genre.id_musician, "             
                + "musician.name, "
                + "musician.rating "
            + "from musician_genre "
                + "left join musician on musician_genre.id_musician = musician.id "
            + "where musician_genre.id_genre = ?";
    
    @Autowired        
    public SelectMusicianGenreByGenre(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_genre", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianGenreEntity musicianGenre = repository.getEntity(resultSet);
        MusicianEntity musician = new MusicianEntity();
        musician.setId(musicianGenre.getId_musician());
        musician.setName(resultSet.getString(4));
        musician.setRating(resultSet.getInt(5));
        musicianGenre.setMusician(musician);
        return musicianGenre;
    }

    public void setRepository(BaseRepository<MusicianGenreEntity> repository) {
        this.repository = repository;
    }

}
