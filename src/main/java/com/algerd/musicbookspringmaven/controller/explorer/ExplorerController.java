package com.algerd.musicbookspringmaven.controller.explorer;

import com.algerd.musicbookspringmaven.controller.BaseAwareController;
import com.algerd.musicbookspringmaven.service.RequestPageService;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import static com.algerd.musicbookspringmaven.service.impl.ContextMenuItemType.*;
import org.springframework.beans.factory.annotation.Autowired;

public class ExplorerController extends BaseAwareController implements Initializable {
    
    @Autowired
    private RequestPageService requestPageService;
            
    @FXML
    private AnchorPane explorer;
    @FXML
    private TreeView artistTree;

    @Override
    public void initialize(URL url, ResourceBundle rb) {                
        initTreeView();
    } 
    
    /**
     * Загрузить элементы в TreeView и задать CellFactory для требуемого отображения значений элементов.
     */
    private void initTreeView() {
        // Передать корневой элемент дереву 
        artistTree.setRoot(new ArtistTreeItem(null, repositoryService));        
        // Сделать невидимым корневой элемент       
        artistTree.setShowRoot(false);      
        //добавление элементов в дерево
        fillTreeItems();
        
        artistTree.setCellFactory(new Callback<TreeView, ArtistTreeCell>() {
            @Override
            public ArtistTreeCell call(TreeView tv) {
                return new ArtistTreeCell();
            }
        });               
        //  инициализировать слушателей таблиц
        new TreeViewTableListener(artistTree, repositoryService);
    }
       
    private void fillTreeItems() {      
        List<ArtistEntity> artists = repositoryService.getArtistRepository().selectAll();        
        artists.sort(Comparator.comparing(ArtistEntity::getName));       
        for (ArtistEntity artist : artists) {
            TreeItem artistItem = new ArtistTreeItem(artist, repositoryService);
            artistTree.getRoot().getChildren().add(artistItem); 
        }
    }
       
    /**
     * ЛКМ - зызов окна выбранной сущности
     * ПКМ - вызов контекстного меню.
     */
    @FXML
    private void onMouseClickTreeView(MouseEvent mouseEvent) {
        boolean isShowingContextMenu = contextMenuService.getContextMenu().isShowing();       
        contextMenuService.clear();
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            // если контекстное меню выбрано, то лкм сбрасывает контекстное меню и выбор в дереве
            if (isShowingContextMenu) {
                artistTree.getSelectionModel().clearSelection();
            }
            // если лкм выбрана запись - показать её
            ArtistTreeItem selectedItem = (ArtistTreeItem) artistTree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Entity entity = (Entity) selectedItem.getValue();
                if (entity instanceof ArtistEntity) {
                    requestPageService.artistPane(entity);
                }
                else if(entity instanceof AlbumEntity) {
                    requestPageService.albumPane(entity);
                }
                else if (entity instanceof SongEntity) {
                    requestPageService.songPane(entity);
                }               
            }            
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) { 
            showTreeContextMenu(mouseEvent);      
        }    
    }
    
    /**
     * При ПКМ по дереву показать контекстное меню.
     */
    private void showTreeContextMenu(MouseEvent mouseEvent) { 
        ArtistTreeItem selectedItem = (ArtistTreeItem) artistTree.getSelectionModel().getSelectedItem();
        if (selectedItem != null) { 
            Entity entity = (Entity) selectedItem.getValue();

            if (entity instanceof ArtistEntity) {
                ArtistEntity artist = (ArtistEntity) entity;
                contextMenuService.add(ADD_ARTIST, new ArtistEntity());
                // запретить удаление и редактирование записи с id = 1 (Unknown artist)
                if (artist.getId() != 1) {
                    contextMenuService.add(EDIT_ARTIST, artist);
                    contextMenuService.add(DELETE_ARTIST, artist);
                    contextMenuService.add(SEPARATOR);
                }
                AlbumEntity newAlbum = new AlbumEntity();
                newAlbum.setId_artist(artist.getId());  
                contextMenuService.add(ADD_ALBUM, newAlbum);           
            }
            else if(entity instanceof AlbumEntity) {   
                AlbumEntity album = (AlbumEntity) entity;
                AlbumEntity newAlbum = new AlbumEntity();
                newAlbum.setId_artist(album.getId_artist());
                contextMenuService.add(ADD_ALBUM, newAlbum);
                // запретить удаление и редактирование записи с id = 1 (Unknown album)
                if (album.getId() != 1) {
                    contextMenuService.add(EDIT_ALBUM, album);
                    contextMenuService.add(DELETE_ALBUM, album);
                    contextMenuService.add(SEPARATOR);
                }
                SongEntity song = new SongEntity();
                song.setId_album(album.getId());
                contextMenuService.add(ADD_SONG, song);
            } 
            else if(entity instanceof SongEntity) {
                SongEntity song = (SongEntity) entity;
                SongEntity newSong = new SongEntity();
                newSong.setId_album(song.getId_album());
                contextMenuService.add(ADD_SONG, newSong);
                contextMenuService.add(EDIT_SONG, song);
                contextMenuService.add(DELETE_SONG, song);
            }                         
        }
        //Если не выбран элемент в дереве - предоставить меню: add artist
        else {
            artistTree.getSelectionModel().clearSelection();
            contextMenuService.add(ADD_ARTIST, new ArtistEntity());
        }
        contextMenuService.show(explorer, mouseEvent); 
    } 
            
}
