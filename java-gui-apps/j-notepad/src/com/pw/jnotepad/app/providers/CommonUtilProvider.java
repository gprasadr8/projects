package com.pw.jnotepad.app.providers;

import com.pw.jnotepad.app.JNotePad;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommonUtilProvider {

    private static final CommonUtilProvider commonUtilProvider = new CommonUtilProvider();
    private  Stage window;

    private final Clipboard clipboard = Clipboard.getSystemClipboard();

    private static final TabPane tabPane = new TabPane();

    private static final String NEW_LINE = "\n";

    public static  CommonUtilProvider getInstance(){
        return commonUtilProvider;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void destroy(){
        boolean response = ConfirmBox.confirmAction("Title", "Are you sure?");
        if(response){
            System.out.println("All unsaved data saved in tmp files.");
            this.window.close();
        }
    }


    public Scene createBorderPaneScene(MenuBar menuBar) {
        BorderPane rootLayout = new BorderPane();
        VBox topLayout = new VBox(menuBar);
        rootLayout.setTop(topLayout);
        if(tabPane.getTabs().isEmpty()){
            JNotePad.notepad_tab_number = 0;
            addNewTab(new Tab("new*"+JNotePad.notepad_tab_number++, new TextArea()));
        }
        rootLayout.setCenter(tabPane);
        Scene scene = new Scene(rootLayout,1000,800);
        return scene;
    }

    public void addOpenFileTab(String title, TextArea content){
        Tab newTab = new Tab(title, content);
        addNewTab(newTab);
    }

    public void addNewTab(Tab newTab){
        newTab.setOnCloseRequest(event -> {
            JNotePad.notepad_tab_number--;
            System.out.println("new tab is called");
        });
        this.addEditShortcuts(newTab);
        this.addOnContentChangeEvent(newTab);
        this.tabPane.getTabs().add(newTab);
        this.tabPane.getSelectionModel().select(newTab);
    }

    private void addOnContentChangeEvent(Tab newTab) {
       TextArea textArea = (TextArea) newTab.getContent();
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Onchange listener is triggered.");
            }
        });
        newTab.getContent().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(!event.isControlDown() && !event.isAltDown()){
                if(event.getCode() == KeyCode.DELETE){
                    TextArea selectedTabArea  = getSelectedTabContent();
                    int startIndex = selectedTabArea.getCaretPosition();
                    doDeleteOperation(startIndex, selectedTabArea.getText(startIndex,startIndex+1),selectedTabArea);
                }else if(event.getCode() == KeyCode.BACK_SPACE){
                    TextArea selectedTabArea  = getSelectedTabContent();
                    int startIndex = selectedTabArea.getCaretPosition()-1;
                    doDeleteOperation(startIndex, selectedTabArea.getText(startIndex,startIndex+1),selectedTabArea);
                }else if(event.getCode() == KeyCode.ENTER){
                    TextArea selectedTabArea  = getSelectedTabContent();
                    doInsertionOperation(selectedTabArea.getCaretPosition(),NEW_LINE,selectedTabArea);
                }else {
                    TextArea selectedTabArea  = getSelectedTabContent();
                    if(event.getText().matches("^[a-z]*$")){
                        insertCharacter(event,selectedTabArea);
                    }
                }
                System.out.println("key code  "+event.getCode());
                System.out.println("User pressed "+event.getText());
                if(!event.isConsumed())
                    event.consume();
            }

        });
    }

    private void insertCharacter(KeyEvent event, TextArea selectedTabArea) {
        String enteredText = event.getText();
        if(event.isShiftDown()){
            enteredText = enteredText.toUpperCase();
        }
        doInsertionOperation(selectedTabArea.getCaretPosition(),enteredText,selectedTabArea);
        event.consume();
    }


    public void replaceSavedTabContent(String fileName, TextArea filledTextArea) {
        Tab selectedTab = this.tabPane.getSelectionModel().getSelectedItem();
        selectedTab.setText(fileName);
        selectedTab.setContent(filledTextArea);
    }

    /**
     * Use BufferedWriter when number of write operations are more
     * It uses internal buffer to reduce real IO operations and saves time
     */
    public File saveTextToFile(File file) {
         Node selectedTabContent = this.tabPane.getSelectionModel().getSelectedItem().getContent();
        if(selectedTabContent instanceof TextArea){
            try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file));) {
                ObservableList<CharSequence> paragraphs =  ((TextArea) selectedTabContent).getParagraphs();
                paragraphs.forEach(charSequence -> {
                    try {
                        buffer.append(charSequence);
                        buffer.newLine();
                    } catch (IOException e) {
                        System.out.println("failed to write to text file.");
                        AlertBox.display("File Save Error", "failed to write to text file.");
                        e.printStackTrace();
                    }
                });
                buffer.flush();
                return file;
            } catch (IOException e) {
                System.out.println("failed to write to text file.");
                AlertBox.display("File Save Error", "failed to write to text file.");
                e.printStackTrace();
            }
        }
       return null;
    }


    private void addEditShortcuts(Tab tab) {
        tab.getContent().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.isControlDown()){
                KeyCode keyCode = event.getCode();
                switch (keyCode){
                    case C:
                        doCopyOperation(event);
                        break;
                    case V:
                        doPasteOperation(event);
                        break;

                    case X:
                        doCutOperation(event);
                        break;
                    case Z:
                        doUndoOperation(event);
                        break;
                    case Y:
                        doRedoOperation(event);
                        break;
                }
            }
        });
    }

    public void doRedoOperation(Event event) {
        System.out.println("Ctrl+Y called");
        UndoRedo.redo();
        event.consume();
    }

    public void doUndoOperation(Event event) {
        System.out.println("Ctrl+Z is called");
        UndoRedo.undo();
        event.consume();
    }

    public void doPasteOperation(Event event) {
        System.out.println("Ctrl+V is triggered");
        if(clipboard.hasString()){
            String pastedText = clipboard.getString();
            TextArea selectedTabArea = getSelectedTabContent();
            doInsertionOperation(selectedTabArea.getCaretPosition(),pastedText,selectedTabArea);
        }
        event.consume();
    }

    private void doInsertionOperation(int startIndex, String content, TextArea selectedTabArea) {
        InsertCommand insertCommand = new InsertCommand(startIndex,content,selectedTabArea);
        insertCommand.execute();
        UndoRedo.insertIntoUndoStack(insertCommand);
    }

    public void doCopyOperation(Event event) {
        System.out.println("Ctrl+C event triggered");
        storeSelectedTextToClipboard();
        event.consume();
    }

    public void doCutOperation(Event event) {
        System.out.println("Ctrl+X is triggered");
        storeSelectedTextToClipboard();
        TextArea selectedTabArea = getSelectedTabContent();
        doDeleteOperation(selectedTabArea.getSelection().getStart(), selectedTabArea.getSelectedText(), selectedTabArea);
        event.consume();
    }

    private void doDeleteOperation(int startIndex, String content, TextArea selectedTabArea) {
        DeleteCommand deleteCommand = new DeleteCommand(startIndex, content, selectedTabArea);
        deleteCommand.execute();
        UndoRedo.insertIntoUndoStack(deleteCommand);
    }

    public void deleteSelectedContent(Event event){
        TextArea selectedTabArea = getSelectedTabContent();
        doDeleteOperation(selectedTabArea.getSelection().getStart(), selectedTabArea.getSelectedText(), selectedTabArea);
    }

    private void storeSelectedTextToClipboard() {
        String selectedText = getSelectedTabContent().getSelectedText();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(selectedText);
        clipboard.setContent(clipboardContent);
    }

    public TextArea getSelectedTabContent() {
        return (TextArea)this.tabPane.getSelectionModel().getSelectedItem().getContent();

    }
}
