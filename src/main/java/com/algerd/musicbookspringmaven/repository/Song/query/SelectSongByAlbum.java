
package com.algerd.musicbookspringmaven.repository.Song.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectSongByAlbum")
public class SelectSongByAlbum extends MappingSqlQuery<SongEntity> {
    
    private BaseRepository<SongEntity> repository;
    
    private static final String QUERY = 
       "select * from song where id_album = ?";
    
    @Autowired        
    public SelectSongByAlbum(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_album", Types.INTEGER));
        compile();
    }
     
    @Override
    protected SongEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return repository.getEntity(resultSet);
    }

    public void setRepository(BaseRepository<SongEntity> repository) {
        this.repository = repository;
    }
}
