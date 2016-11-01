
package com.algerd.musicbookspringmaven.service.impl;

import com.algerd.musicbookspringmaven.service.ContextMenuItemType;
import com.algerd.musicbookspringmaven.service.RequestDialogService;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongEntity;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.service.ContextMenuService;
import com.algerd.musicbookspringmaven.service.DeleteAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextMenuServiceImpl implements ContextMenuService {     
      
    @Autowired
    private DeleteAlertService  deleteAlertService;
    @Autowired
    private RequestDialogService requestDialogService;
    
    private final ContextMenu contextMenu = new ContextMenu();
    private final Map<ContextMenuItemType, EventHandler<ActionEvent>> menuMap = new HashMap<>();
    private Map<ContextMenuItemType, Entity> valueMap = new HashMap<>();
        
    public ContextMenuServiceImpl() {
        
        menuMap.put(ContextMenuItemType.ADD_ARTIST, e -> requestDialogService.artistDialog(valueMap.get(ContextMenuItemType.ADD_ARTIST)));      
        menuMap.put(ContextMenuItemType.EDIT_ARTIST, e -> requestDialogService.artistDialog(valueMap.get(ContextMenuItemType.EDIT_ARTIST)));            
        menuMap.put(ContextMenuItemType.DELETE_ARTIST, e -> deleteAlertService.show((ArtistEntity) valueMap.get(ContextMenuItemType.DELETE_ARTIST)));      
        
        menuMap.put(ContextMenuItemType.ADD_ALBUM, e -> requestDialogService.albumDialog(valueMap.get(ContextMenuItemType.ADD_ALBUM)));
        menuMap.put(ContextMenuItemType.EDIT_ALBUM, e -> requestDialogService.albumDialog(valueMap.get(ContextMenuItemType.EDIT_ALBUM)));
        menuMap.put(ContextMenuItemType.DELETE_ALBUM, e -> deleteAlertService.show((AlbumEntity) valueMap.get(ContextMenuItemType.DELETE_ALBUM)));             
        
        menuMap.put(ContextMenuItemType.ADD_SONG, e -> requestDialogService.songDialog(valueMap.get(ContextMenuItemType.ADD_SONG)));       
        menuMap.put(ContextMenuItemType.EDIT_SONG, e -> requestDialogService.songDialog(valueMap.get(ContextMenuItemType.EDIT_SONG)));        
        menuMap.put(ContextMenuItemType.DELETE_SONG, e -> deleteAlertService.show((SongEntity) valueMap.get(ContextMenuItemType.DELETE_SONG)));       
        
        menuMap.put(ContextMenuItemType.ADD_GENRE, e -> requestDialogService.genreDialog(valueMap.get(ContextMenuItemType.ADD_GENRE)));
        menuMap.put(ContextMenuItemType.EDIT_GENRE, e -> requestDialogService.genreDialog(valueMap.get(ContextMenuItemType.EDIT_GENRE)));
        menuMap.put(ContextMenuItemType.DELETE_GENRE, e -> deleteAlertService.show((GenreEntity) valueMap.get(ContextMenuItemType.DELETE_GENRE)));     
        
        menuMap.put(ContextMenuItemType.ADD_MUSICIAN, e -> requestDialogService.musicianDialog(valueMap.get(ContextMenuItemType.ADD_MUSICIAN)));
        menuMap.put(ContextMenuItemType.EDIT_MUSICIAN, e -> requestDialogService.musicianDialog(valueMap.get(ContextMenuItemType.EDIT_MUSICIAN)));
        menuMap.put(ContextMenuItemType.DELETE_MUSICIAN, e -> deleteAlertService.show((MusicianEntity) valueMap.get(ContextMenuItemType.DELETE_MUSICIAN)));       
       
        menuMap.put(ContextMenuItemType.ADD_MUSICIAN_ALBUM, e -> requestDialogService.musicianAlbumDialog(valueMap.get(ContextMenuItemType.ADD_MUSICIAN_ALBUM)));
        menuMap.put(ContextMenuItemType.EDIT_MUSICIAN_ALBUM, e -> requestDialogService.musicianAlbumDialog(valueMap.get(ContextMenuItemType.EDIT_MUSICIAN_ALBUM)));
        menuMap.put(ContextMenuItemType.DELETE_MUSICIAN_ALBUM, e -> deleteAlertService.show((MusicianAlbumEntity) valueMap.get(ContextMenuItemType.DELETE_MUSICIAN_ALBUM))); 
        
        menuMap.put(ContextMenuItemType.ADD_MUSICIAN_GROUP, e -> requestDialogService.musicianGroupDialog(valueMap.get(ContextMenuItemType.ADD_MUSICIAN_GROUP)));
        menuMap.put(ContextMenuItemType.EDIT_MUSICIAN_GROUP, e -> requestDialogService.musicianGroupDialog(valueMap.get(ContextMenuItemType.EDIT_MUSICIAN_GROUP)));
        menuMap.put(ContextMenuItemType.DELETE_MUSICIAN_GROUP, e -> deleteAlertService.show((MusicianGroupEntity) valueMap.get(ContextMenuItemType.DELETE_MUSICIAN_GROUP))); 
        
        menuMap.put(ContextMenuItemType.ADD_MUSICIAN_SONG, e -> requestDialogService.musicianSongDialog(valueMap.get(ContextMenuItemType.ADD_MUSICIAN_SONG)));
        menuMap.put(ContextMenuItemType.EDIT_MUSICIAN_SONG, e -> requestDialogService.musicianSongDialog(valueMap.get(ContextMenuItemType.EDIT_MUSICIAN_SONG)));
        menuMap.put(ContextMenuItemType.DELETE_MUSICIAN_SONG, e -> deleteAlertService.show((MusicianSongEntity) valueMap.get(ContextMenuItemType.DELETE_MUSICIAN_SONG))); 
        
        menuMap.put(ContextMenuItemType.ADD_ARTIST_REFERENCE, e -> requestDialogService.artistReferenceDialog(valueMap.get(ContextMenuItemType.ADD_ARTIST_REFERENCE)));
        menuMap.put(ContextMenuItemType.EDIT_ARTIST_REFERENCE, e -> requestDialogService.artistReferenceDialog(valueMap.get(ContextMenuItemType.EDIT_ARTIST_REFERENCE)));
        menuMap.put(ContextMenuItemType.DELETE_ARTIST_REFERENCE, e -> deleteAlertService.show((ArtistReferenceEntity) valueMap.get(ContextMenuItemType.DELETE_ARTIST_REFERENCE)));
    
        menuMap.put(ContextMenuItemType.ADD_INSTRUMENT, e -> requestDialogService.instrumentDialog(valueMap.get(ContextMenuItemType.ADD_INSTRUMENT)));
        menuMap.put(ContextMenuItemType.EDIT_INSTRUMENT, e -> requestDialogService.instrumentDialog(valueMap.get(ContextMenuItemType.EDIT_INSTRUMENT)));
        menuMap.put(ContextMenuItemType.DELETE_INSTRUMENT, e -> deleteAlertService.show((InstrumentEntity) valueMap.get(ContextMenuItemType.DELETE_INSTRUMENT)));
    }
    
    @Override
    public void add(ContextMenuItemType itemType, Entity entity) {
        // сохранить в карте переменных entity для элемента меню itemType
        valueMap.put(itemType, entity);
        // получить элемент меню
        MenuItem item = itemType.get();   
        // задать экшен элементу меню
        item.setOnAction(menuMap.get(itemType));
        // добавить в меню элемент
        contextMenu.getItems().add(item);   
    }
    
    @Override
    public void add(ContextMenuItemType itemType) {
        contextMenu.getItems().add(itemType.get());
    }
        
    @Override
    public void show(Pane pane, MouseEvent mouseEvent) {
        contextMenu.show(pane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }
      
    @Override
    public void clear() {
        contextMenu.hide();
        contextMenu.getItems().clear();
        valueMap.clear();
    }
    
    @Override
    public ContextMenu getContextMenu() {
        return contextMenu;
    }
    
}
