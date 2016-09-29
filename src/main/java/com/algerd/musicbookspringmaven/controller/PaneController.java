package com.algerd.musicbookspringmaven.controller;

import com.algerd.musicbookspringmaven.repository.Entity;
import javafx.scene.layout.AnchorPane;

public interface PaneController {
    
    void show(Entity entity);
   
    AnchorPane getView();
                
    void loadFxml();
    
}
