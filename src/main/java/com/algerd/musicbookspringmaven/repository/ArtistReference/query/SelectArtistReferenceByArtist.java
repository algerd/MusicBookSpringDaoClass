
package com.algerd.musicbookspringmaven.repository.ArtistReference.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectArtistReferenceByArtist")
public class SelectArtistReferenceByArtist extends MappingSqlQuery<ArtistReferenceEntity>  {

    private BaseRepository<ArtistReferenceEntity> repository;
    
    private static final String QUERY = 
        "select * from artist_reference where id_artist = ?";
    
    @Autowired        
    public SelectArtistReferenceByArtist(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_artist", Types.INTEGER));
        compile();
    }        
            
    @Override
    protected ArtistReferenceEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return repository.getEntity(resultSet);
    }
    
    public void setRepository(BaseRepository<ArtistReferenceEntity> repository) {
        this.repository = repository;
    } 
}
