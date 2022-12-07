package com.pw.jnotepad.app.providers;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class SearchMenuProvider {

    private Stage window;

    public SearchMenuProvider(Stage window) {
        this.window = window;
    }

    public Menu createSearchMenu() {
        Menu searchMenu = new Menu("Search");
        MenuItem find = new MenuItem("Find");
        MenuItem replace = new MenuItem("Replace");
        MenuItem gotoLine = new MenuItem("Goto");
        KeyCombination gotoKeyCombination = new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN);
        gotoLine.setAccelerator(gotoKeyCombination);
        gotoLine.setOnAction(event -> {
            System.out.println("CTRL+G event triggered");
            System.out.println(event.toString());
        });
        searchMenu.getItems().addAll(find,replace,gotoLine);
        return searchMenu;
    }
}
