
package com.algerd.musicbookspringmaven.repository.Album.query;

import com.algerd.musicbookspringmaven.dbDriver.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectAlbumByArtist")
public class SelectAlbumByArtist extends MappingSqlQuery<AlbumEntity> {
    
    private BaseRepository<AlbumEntity> repository;

    private static final String QUERY = 
        "select * from album where id_artist = ?";
    
    @Autowired 
    public SelectAlbumByArtist(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_artist", Types.INTEGER));
        compile();
    }

    @Override
    protected AlbumEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return repository.getEntity(rs);
    }
    
    public void setRepository(BaseRepository<AlbumEntity> repository) {
        this.repository = repository;
    }
            
}
