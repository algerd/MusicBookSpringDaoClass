
package com.algerd.musicbookspringmaven.service;

import com.algerd.musicbookspringmaven.repository.AlbumGenre.AlbumGenreRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumRepository;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.ArtistGenreRepository;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistRepository;
import com.algerd.musicbookspringmaven.repository.GenreRepository;
import com.algerd.musicbookspringmaven.repository.InstrumentRepository;
import com.algerd.musicbookspringmaven.repository.MusicianAlbumRepository;
import com.algerd.musicbookspringmaven.repository.MusicianGenreRepository;
import com.algerd.musicbookspringmaven.repository.MusicianGroupRepository;
import com.algerd.musicbookspringmaven.repository.MusicianInstrumentRepository;
import com.algerd.musicbookspringmaven.repository.MusicianRepository;
import com.algerd.musicbookspringmaven.repository.MusicianSongRepository;
import com.algerd.musicbookspringmaven.repository.SongGenreRepository;
import com.algerd.musicbookspringmaven.repository.SongRepository;

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
