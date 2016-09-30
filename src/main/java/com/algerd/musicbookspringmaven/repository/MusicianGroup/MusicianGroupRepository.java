
package com.algerd.musicbookspringmaven.repository.MusicianGroup;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import java.util.List;

public interface MusicianGroupRepository extends CrudRepository<MusicianGroupEntity> {
    
    List<MusicianGroupEntity> selectMusicianGroupByMusician(MusicianEntity musician);
    List<MusicianGroupEntity> selectMusicianGroupByArtist(ArtistEntity artist);
    boolean containsMusicianGroupByMusicianAndArtist(MusicianEntity musician, ArtistEntity artist);
}
