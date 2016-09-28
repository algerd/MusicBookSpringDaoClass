
package com.algerd.musicbookspringmaven.repository.ArtistReference;

import com.algerd.musicbookspringmaven.dbDriver.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.ArtistReference.query.SelectArtistReferenceByArtist;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ArtistReferenceRepositoryImpl extends CrudRepositoryImpl<ArtistReferenceEntity> implements ArtistReferenceRepository {
   
    private SelectArtistReferenceByArtist selectArtistReferenceByArtist;
    
    @Override
    public List<ArtistReferenceEntity> selectArtistReferenceByArtist(ArtistEntity artist) {
        return selectArtistReferenceByArtist.execute(artist.getId());
    }

    @Autowired
    public void setSelectArtistReferenceByArtist(SelectArtistReferenceByArtist selectArtistReferenceByArtist) {
        this.selectArtistReferenceByArtist = selectArtistReferenceByArtist;
        selectArtistReferenceByArtist.setRepository(this);
    }   
}
