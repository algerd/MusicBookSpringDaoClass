
package com.algerd.musicbookspringmaven.service;

import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceEntity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;


public interface DeleteAlertService {
    
    void show(AlbumEntity album);
    void show(ArtistEntity artist);
    void show(GenreEntity genre);
    void show(InstrumentEntity instrument);
    void show(MusicianAlbumEntity musicianAlbum);
    void show(MusicianEntity musician);
    void show(MusicianSongEntity musicianSong);
    void show(SongEntity song);
    void show(MusicianGroupEntity musicianGroup);
    void show(ArtistReferenceEntity artistReference);
    
}
