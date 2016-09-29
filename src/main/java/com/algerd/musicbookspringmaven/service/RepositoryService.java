
package com.algerd.musicbookspringmaven.service;

import com.algerd.musicbookspringmaven.repository.AlbumGenre.AlbumGenreRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumRepository;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.ArtistGenreRepository;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistRepository;
import com.algerd.musicbookspringmaven.repository.Genre.GenreRepository;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentRepository;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumRepository;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.MusicianGenreRepository;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupRepository;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianRepository;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongRepository;
import com.algerd.musicbookspringmaven.repository.SongGenre.SongGenreRepository;
import com.algerd.musicbookspringmaven.repository.Song.SongRepository;

public interface RepositoryService {
    
    ArtistRepository getArtistRepository();
    AlbumRepository getAlbumRepository();
    SongRepository getSongRepository();
    GenreRepository getGenreRepository();
    ArtistReferenceRepository getArtistReferenceRepository();
    ArtistGenreRepository getArtistGenreRepository();
    MusicianRepository getMusicianRepository();
    MusicianGroupRepository getMusicianGroupRepository();
    AlbumGenreRepository getAlbumGenreRepository();
    SongGenreRepository getSongGenreRepository();
    MusicianGenreRepository getMusicianGenreRepository();
    MusicianAlbumRepository getMusicianAlbumRepository();
    MusicianSongRepository getMusicianSongRepository();
    InstrumentRepository getInstrumentRepository();
    MusicianInstrumentRepository getMusicianInstrumentRepository();
}
