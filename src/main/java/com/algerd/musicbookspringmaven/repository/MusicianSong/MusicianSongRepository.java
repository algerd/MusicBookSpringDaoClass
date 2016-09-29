
package com.algerd.musicbookspringmaven.repository.MusicianSong;

import com.algerd.musicbookspringmaven.repository.CrudRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import java.util.List;

public interface MusicianSongRepository extends CrudRepository<MusicianSongEntity> {
    
    List<MusicianSongEntity> selectMusicianSongByMusician(MusicianEntity musician);
    List<MusicianSongEntity> selectMusicianSongBySong(SongEntity song);
    boolean containsMusicianSongByMusicianAndSong(MusicianEntity musician, SongEntity song);
    
}
