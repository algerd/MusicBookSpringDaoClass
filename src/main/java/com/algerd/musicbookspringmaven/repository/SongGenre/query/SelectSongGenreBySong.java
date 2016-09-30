
package com.algerd.musicbookspringmaven.repository.SongGenre.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.SongGenre.SongGenreEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectSongGenreBySong")
public class SelectSongGenreBySong extends MappingSqlQuery<SongGenreEntity> {
    
    private BaseRepository<SongGenreEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "song_genre.id, "
                + "song_genre.id_genre, "
                + "song_genre.id_song, "              
                + "genre.name "
            + "from song_genre "
                + "left join genre on song_genre.id_genre = genre.id "
            + "where song_genre.id_song = ?";
    
    @Autowired        
    public SelectSongGenreBySong(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_song", Types.INTEGER));
        compile();
    }
     
    @Override
    protected SongGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        SongGenreEntity songGenre = repository.getEntity(resultSet);
        GenreEntity genre = new GenreEntity();
        genre.setId(songGenre.getId_genre());
        genre.setName(resultSet.getString("name"));
        songGenre.setGenre(genre);
        return songGenre;
    }

    public void setRepository(BaseRepository<SongGenreEntity> repository) {
        this.repository = repository;
    }
}
