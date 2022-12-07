package com.pw.jnotepad.app.providers;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class EditMenuProvider {

    private TabPane tabPane;

    private CommonUtilProvider commonUtilProvider;

    public EditMenuProvider() {
        this.commonUtilProvider = CommonUtilProvider.getInstance();
        this.tabPane = this.commonUtilProvider.getTabPane();
    }

    public Menu createEditMenu() {
        Menu editMenu = new Menu("Edit");

        MenuItem undo = new MenuItem("Undo");
        undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        undo.setOnAction(event -> {
            commonUtilProvider.doUndoOperation(event);
        });

        MenuItem redo = new MenuItem("Redo");
        redo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        redo.setOnAction(event -> {
            commonUtilProvider.doRedoOperation(event);
        });

        MenuItem cut = new MenuItem("Cut");
        cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        cut.setOnAction(event -> {
            commonUtilProvider.doCutOperation(event);
        });

        MenuItem copy = new MenuItem("Copy");
        copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        copy.setOnAction(event -> {
            commonUtilProvider.doCopyOperation(event);
        });

        MenuItem paste = new MenuItem("Paste");
        paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        paste.setOnAction(event->{
            commonUtilProvider.doPasteOperation(event);
        });
        MenuItem delete = new MenuItem("Delete");
        KeyCombination deleteKeyCombination = new KeyCodeCombination(KeyCode.D,KeyCombination.CONTROL_DOWN);
        delete.setAccelerator(deleteKeyCombination);
        delete.setOnAction( event->{
            commonUtilProvider.deleteSelectedContent(event);
        });

        MenuItem selectAll = new MenuItem("SelectAll");
        selectAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        selectAll.setOnAction(event -> {
            TextArea textArea = commonUtilProvider.getSelectedTabContent();
            textArea.selectAll();
        });

        editMenu.getItems().addAll(undo,redo,cut,copy,paste,delete,selectAll);
        // KeyCombination is not working for platform default shortcuts
        return editMenu;
    }







}
