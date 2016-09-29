package com.algerd.musicbookspringmaven.controller.artist;

import com.algerd.musicbookspringmaven.controller.BaseIncludeController;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentEntity;
import com.algerd.musicbookspringmaven.utils.Helper;
import static com.algerd.musicbookspringmaven.service.impl.ContextMenuItemType.ADD_MUSICIAN_GROUP;
import static com.algerd.musicbookspringmaven.service.impl.ContextMenuItemType.DELETE_MUSICIAN_GROUP;
import static com.algerd.musicbookspringmaven.service.impl.ContextMenuItemType.EDIT_MUSICIAN_GROUP;

public class MusicianTableController extends BaseIncludeController<ArtistPaneController> {
      
    private MusicianGroupEntity selectedItem;
    
    @FXML
    private AnchorPane musicianTable;
    @FXML
    private TableView<MusicianGroupEntity> musicianTableView;
    @FXML
    private TableColumn<MusicianGroupEntity, String> musicianNameColumn;
    @FXML
    private TableColumn<MusicianGroupEntity, MusicianGroupEntity> musicianInstrumentColumn;
    @FXML
    private TableColumn<MusicianGroupEntity, String> musicianStartDateColumn;
    @FXML
    private TableColumn<MusicianGroupEntity, String> musicianEndDateColumn;
    @FXML
    private TableColumn<MusicianGroupEntity, Integer> musicianRatingColumn; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        musicianNameColumn.setCellValueFactory(cellData -> cellData.getValue().getMusician().nameProperty());       
        musicianStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().start_dateProperty());
        musicianEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().end_dateProperty());
        musicianRatingColumn.setCellValueFactory(cellData -> cellData.getValue().getMusician().ratingProperty().asObject());
        
        musicianInstrumentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        musicianInstrumentColumn.setCellFactory(col -> {
            TableCell<MusicianGroupEntity, MusicianGroupEntity> cell = new TableCell<MusicianGroupEntity, MusicianGroupEntity>() {
                @Override
                public void updateItem(MusicianGroupEntity item, boolean empty) {
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
    
    @Override
    public void bootstrap() {
        setTableValue();
        initListeners();
        initRepositoryListeners();    
    } 
    
    private void initRepositoryListeners() {
        repositoryService.getMusicianGroupRepository().clearChangeListeners(this);                
        repositoryService.getMusicianRepository().clearDeleteListeners(this);           
        repositoryService.getMusicianRepository().clearUpdateListeners(this);   
        repositoryService.getMusicianInstrumentRepository().clearChangeListeners(this);          
        
        repositoryService.getMusicianGroupRepository().addChangeListener(this::changed, this);                
        repositoryService.getMusicianRepository().addDeleteListener(this::changed, this);           
        repositoryService.getMusicianRepository().addUpdateListener(this::changed, this);   
        repositoryService.getMusicianInstrumentRepository().addChangeListener(this::changed, this);          
    }
    
    private void setTableValue() { 
        List<MusicianGroupEntity> musicianGroups = repositoryService.getMusicianGroupRepository().selectMusicianGroupByArtist(paneController.getArtist());
        clearSelectionTable();
        musicianTableView.getItems().clear();
        musicianTableView.setItems(FXCollections.observableArrayList(musicianGroups));  
        sort();
        Helper.setHeightTable(musicianTableView, 10);
    }
    
    private void initListeners() {
        // Добавить слушателя на выбор элемента в таблице.
        musicianTableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selectedItem = musicianTableView.getSelectionModel().getSelectedItem()
        );       
    }
 
    private void changed(ObservableValue observable, Object oldVal, Object newVal) {
        setTableValue();
    }
     
    private void sort() {
        clearSelectionTable();
        musicianTableView.getItems().sort(Comparator.comparing(musicianGroup -> musicianGroup.getMusician().getName()));
    } 
    
    void clearSelectionTable() {
        musicianTableView.getSelectionModel().clearSelection();
        selectedItem = null;
    }
    
    /**
     * ЛКМ - зызов окна выбранного музыканта;
     * ПКМ - вызов контекстного меню для add, edit, delete выбранного музыканта.
     */
    @FXML
    private void onMouseClickMusicianTable(MouseEvent mouseEvent) { 
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
            MusicianGroupEntity newMusicianGroup = new MusicianGroupEntity();
            newMusicianGroup.setId_artist(paneController.getArtist().getId());       
            contextMenuService.add(ADD_MUSICIAN_GROUP, newMusicianGroup);

            if (selectedItem != null) {
                contextMenuService.add(EDIT_MUSICIAN_GROUP, selectedItem);
                contextMenuService.add(DELETE_MUSICIAN_GROUP, selectedItem);                       
            }
            contextMenuService.show(paneController.getView(), mouseEvent);   
        }
    }
    
}
