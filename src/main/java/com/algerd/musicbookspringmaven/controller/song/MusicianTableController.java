package com.algerd.musicbookspringmaven.controller.song;

import com.algerd.musicbookspringmaven.controller.BaseIncludeController;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.algerd.musicbookspringmaven.utils.Helper;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongEntity;
import static com.algerd.musicbookspringmaven.service.ContextMenuItemType.ADD_MUSICIAN_SONG;
import static com.algerd.musicbookspringmaven.service.ContextMenuItemType.DELETE_MUSICIAN_SONG;
import static com.algerd.musicbookspringmaven.service.ContextMenuItemType.EDIT_MUSICIAN_SONG;

public class MusicianTableController extends BaseIncludeController<SongPaneController> {
    
    private MusicianSongEntity selectedItem;   
    
    @FXML
    private AnchorPane musicianTable;
    @FXML
    private TableView<MusicianSongEntity> musicianTableView;
    @FXML
    private TableColumn<MusicianSongEntity, String> musicianColumn;
    @FXML
    private TableColumn<MusicianSongEntity, MusicianSongEntity> instrumentColumn;   
    @FXML
    private TableColumn<MusicianSongEntity, Integer> ratingColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        musicianColumn.setCellValueFactory(cellData -> cellData.getValue().getMusician().nameProperty());
        ratingColumn.setCellValueFactory(cellData -> cellData.getValue().getMusician().ratingProperty().asObject());
        
        instrumentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        instrumentColumn.setCellFactory(col -> {
            TableCell<MusicianSongEntity, MusicianSongEntity> cell = new TableCell<MusicianSongEntity, MusicianSongEntity>() {
                @Override
                public void updateItem(MusicianSongEntity item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    if (!empty) {
                        List<MusicianInstrumentEntity> musicianInstruments = 
                            repositoryService.getMusicianInstrumentRepository().selectMusicianInstrumentByMusician(item.getMusician());
                        String str = "";
                        for (MusicianInstrumentEntity musicianInstrument :  musicianInstruments){
                            str += (!str.equals("")) ? ", " : "";
                            str += musicianInstrument.getInstrument().getName();
                        }
                        this.setText(str);                                         
                    }
                }
            };
			return cell;
        });
    }  
    
    public void bootstrap() {    
        setTableValue();
        initListeners();
        initRepositoryListeners();
    }
    
    private void setTableValue() {
        ObservableList<MusicianSongEntity> musicianSongs = 
            FXCollections.observableArrayList(repositoryService.getMusicianSongRepository().selectMusicianSongBySong(paneController.getSong()));
        clearSelectionTable();
        musicianTableView.getItems().clear();
        musicianTableView.setItems(musicianSongs);
        sort();
        Helper.setHeightTable(musicianTableView, 10);
    }
    
    private void initListeners() {
        musicianTableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selectedItem = musicianTableView.getSelectionModel().getSelectedItem()
        );
    }
    
    private void initRepositoryListeners() {
        //clear listeners
        repositoryService.getMusicianSongRepository().clearChangeListeners(this);                          
        repositoryService.getMusicianRepository().clearDeleteListeners(this);           
        repositoryService.getMusicianRepository().clearUpdateListeners(this);       
        repositoryService.getSongRepository().clearDeleteListeners(this);           
        repositoryService.getSongRepository().clearUpdateListeners(this);       
        repositoryService.getAlbumRepository().clearDeleteListeners(this);
        repositoryService.getArtistRepository().clearDeleteListeners(this);
        
        //add listeners
        repositoryService.getMusicianSongRepository().addChangeListener(this::changed, this);                          
        repositoryService.getMusicianRepository().addDeleteListener(this::changed, this);           
        repositoryService.getMusicianRepository().addUpdateListener(this::changed, this);       
        repositoryService.getSongRepository().addDeleteListener(this::changed, this);           
        repositoryService.getSongRepository().addUpdateListener(this::changed, this);       
        repositoryService.getAlbumRepository().addDeleteListener(this::changed, this);
        repositoryService.getArtistRepository().addDeleteListener(this::changed, this);
    }
    
    private void changed(ObservableValue observable, Object oldVal, Object newVal) {
        setTableValue();
    }
    
    private void sort() {
        clearSelectionTable();
        musicianTableView.getItems().sort(Comparator.comparing((MusicianSongEntity musicianSong) -> musicianSong.getMusician().getName()));
    }
    
    private void clearSelectionTable() {
        musicianTableView.getSelectionModel().clearSelection();
        selectedItem = null;
    }
    
    @FXML
    private void onMouseClickTable(MouseEvent mouseEvent) {
        boolean isShowingContextMenu = contextMenuService.getContextMenu().isShowing();
        contextMenuService.clear();  
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            // если контекстное меню выбрано, то лкм сбрасывает контекстное меню и выбор в таблице
            if (isShowingContextMenu) {
                clearSelectionTable();
            }
            // если лкм выбрана запись - показать её
            if (selectedItem != null) {
                MusicianEntity musician = repositoryService.getMusicianRepository().selectById(selectedItem.getMusician().getId());
                requestPageService.musicianPane(musician);
            }
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) { 
            MusicianSongEntity newMusicianSong = new MusicianSongEntity();
            newMusicianSong.setId_song(paneController.getSong().getId());
            contextMenuService.add(ADD_MUSICIAN_SONG, newMusicianSong);                    
            if (selectedItem != null) {
                contextMenuService.add(EDIT_MUSICIAN_SONG, selectedItem);
                contextMenuService.add(DELETE_MUSICIAN_SONG, selectedItem);     
            }
            contextMenuService.show(paneController.getView(), mouseEvent);            
        }
    }  
    
}
