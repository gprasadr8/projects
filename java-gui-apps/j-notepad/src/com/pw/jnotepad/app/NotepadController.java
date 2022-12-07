package com.pw.jnotepad.app;

import com.pw.jnotepad.app.providers.CommonUtilProvider;
import com.pw.jnotepad.app.providers.MenuBarProvider;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NotepadController {

    private CommonUtilProvider commonUtilProvider;

    private MenuBarProvider menuBarProvider;


    public NotepadController(Stage window){
        this.menuBarProvider = new MenuBarProvider(window);
        this.commonUtilProvider = CommonUtilProvider.getInstance();
        this.commonUtilProvider.setWindow(window);
        initJNotepad(window);
    }

    private void initJNotepad(Stage window) {
        window.setTitle("j-notepad");
        window.setOnCloseRequest(event ->{
            event.consume();
            this.commonUtilProvider.destroy();
        });

        Scene scene = this.commonUtilProvider.createBorderPaneScene(menuBarProvider.createMenuBar());

        window.setScene(scene);
        window.show();

    }






}
