
package com.algerd.musicbookspringmaven.repository.MusicianSong;

import com.algerd.musicbookspringmaven.repository.impl.CrudRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.query.CountMusicianSongByMusicianAndSong;
import com.algerd.musicbookspringmaven.repository.MusicianSong.query.SelectMusicianSongByMusician;
import com.algerd.musicbookspringmaven.repository.MusicianSong.query.SelectMusicianSongBySong;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicianSongRepositoryImpl extends CrudRepositoryImpl<MusicianSongEntity> implements MusicianSongRepository {

    private SelectMusicianSongByMusician selectMusicianSongByMusician;
    private SelectMusicianSongBySong selectMusicianSongBySong;
    private CountMusicianSongByMusicianAndSong countMusicianSongByMusicianAndSong;
    
    @Override
    public List<MusicianSongEntity> selectMusicianSongByMusician(MusicianEntity musician) {
        return selectMusicianSongByMusician.execute(musician.getId());
    }
    
    @Override
    public List<MusicianSongEntity> selectMusicianSongBySong(SongEntity song) {
        return selectMusicianSongBySong.execute(song.getId());
    }
    
    @Override
    public boolean containsMusicianSongByMusicianAndSong(MusicianEntity musician, SongEntity song) {
        return countMusicianSongByMusicianAndSong.findObject(musician.getId(), song.getId()) > 0;
    }

    @Autowired
    public void setSelectMusicianSongByMusician(SelectMusicianSongByMusician selectMusicianSongByMusician) {
        this.selectMusicianSongByMusician = selectMusicianSongByMusician;
        this.selectMusicianSongByMusician.setRepository(this);
    }

    @Autowired
    public void setSelectMusicianSongBySong(SelectMusicianSongBySong selectMusicianSongBySong) {
        this.selectMusicianSongBySong = selectMusicianSongBySong;
        this.selectMusicianSongBySong.setRepository(this);
    }

    @Autowired
    public void setCountMusicianSongByMusicianAndSong(CountMusicianSongByMusicianAndSong countMusicianSongByMusicianAndSong) {
        this.countMusicianSongByMusicianAndSong = countMusicianSongByMusicianAndSong;
    }
    
}
