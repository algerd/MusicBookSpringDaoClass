
package com.algerd.musicbookspringmaven.controller;

public abstract class BaseIncludeController<T extends PaneController> extends BaseAwareController implements IncludeController<T> {

    protected T paneController;
    
    @Override
    public void setPaneController(T paneController) {
        this.paneController = paneController;
        bootstrap();
    }
    
    protected abstract void bootstrap();
    
}
