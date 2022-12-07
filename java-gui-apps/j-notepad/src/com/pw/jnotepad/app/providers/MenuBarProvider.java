package com.pw.jnotepad.app.providers;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MenuBarProvider {

    private FileMenuProvider fileMenuProvider;

    private EditMenuProvider editMenuProvider;

    private SearchMenuProvider searchMenuProvider;

    private CommonUtilProvider commonUtilProvider;

    public MenuBarProvider(Stage window){
        this.commonUtilProvider = CommonUtilProvider.getInstance();
        this.fileMenuProvider = new FileMenuProvider(window, commonUtilProvider);
        this.editMenuProvider = new EditMenuProvider();
        this.searchMenuProvider = new SearchMenuProvider(window);
    }


    public MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = this.fileMenuProvider.createFileMenu();
        Menu editMenu = this.editMenuProvider.createEditMenu();
        Menu searchMenu = this.searchMenuProvider.createSearchMenu();
        menuBar.getMenus().addAll(fileMenu,editMenu,searchMenu);
        return menuBar;
    }
}
