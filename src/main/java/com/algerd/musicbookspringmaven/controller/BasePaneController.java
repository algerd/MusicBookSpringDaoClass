
package com.algerd.musicbookspringmaven.controller;

import com.algerd.musicbookspringmaven.spring.SpringFxmlLoader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BasePaneController extends BaseAwareController implements PaneController {
    
    private static final Logger LOG = LogManager.getLogger(BasePaneController.class);
    
    @FXML
    protected AnchorPane view;
    @Autowired
    private SpringFxmlLoader springFxmlLoader;
    
    @Override
    public void loadFxml() {      
        try {
            FXMLLoader loader = springFxmlLoader.loadController(getClass()); 
            view = loader.<AnchorPane>load();
        } catch (IOException e) {
            LOG.error("Error: ", e);
            //e.printStackTrace();
        }
    }
   
    @Override
    public AnchorPane getView() {
        if (view == null) {
            loadFxml();
        }       
        return view;
    }
   
}
