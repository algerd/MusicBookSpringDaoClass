
package com.algerd.musicbookspringmaven.repository.Artist;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepositoryImpl extends CrudRepositoryImpl<ArtistEntity> implements ArtistRepository {   
     
    // запретить удаление записи с id = 1 (Unknown artist)
    @Override
    public void delete(ArtistEntity entity) {       
        if (entity.getId() != 1) {
            super.delete(entity);
        }      
    }
    
    // запретить добавление/редактирование записи с id = 1 (Unknown artist)
    @Override 
    public void save(ArtistEntity entity) {       
        if (entity.getId() != 1) {
            super.save(entity);
        }
    } 
    
}
