
package com.algerd.musicbookspringmaven.repository.ArtistReference;

import com.algerd.musicbookspringmaven.dbDriver.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import java.util.List;

public interface ArtistReferenceRepository extends CrudRepository<ArtistReferenceEntity> {
    
    List<ArtistReferenceEntity> selectArtistReferenceByArtist(ArtistEntity artist);
    
}
