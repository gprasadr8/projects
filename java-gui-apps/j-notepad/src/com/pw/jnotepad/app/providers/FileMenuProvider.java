package com.pw.jnotepad.app.providers;

import com.pw.jnotepad.app.JNotePad;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;

public class FileMenuProvider {

    private Stage window;

    private CommonUtilProvider commonUtilProvider;

    public  FileMenuProvider(Stage window, CommonUtilProvider commonUtilProvider){
        this.window = window;
        this.commonUtilProvider = commonUtilProvider;
    }

    public Menu createFileMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem newFile = new MenuItem("New");
        newFile.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newFile.setOnAction(event -> {
            commonUtilProvider.addNewTab(new Tab("new*"+ JNotePad.notepad_tab_number++, new TextArea()));
        });
        MenuItem  openFile = new MenuItem("Open");
        openFile.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openFile.setOnAction(event -> {
            triggerOpenFile();
        });
        MenuItem  saveFile = new MenuItem("Save");
        saveFile.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveFile.setOnAction(event -> {
            System.out.println("Save file action triggered");
            File savedFile = triggerSaveOption(event);
            if(savedFile!= null){
                TextArea filledTextArea = writeFileContentToTextArea(savedFile);
                commonUtilProvider.replaceSavedTabContent(savedFile.getName(),filledTextArea);
            }

        });
        MenuItem  saveAs = new MenuItem("Save As");
        saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN));
        saveAs.setOnAction(event -> {
            System.out.println("Save file action triggered");
            triggerSaveOption(event);
        });
        MenuItem  exitApp = new MenuItem("Exit");
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
        exitApp.setAccelerator(keyCombination);

        exitApp.setOnAction(event -> {
            this.commonUtilProvider.destroy();
        });
        fileMenu.getItems().addAll(newFile,openFile,saveFile,saveAs,exitApp);
        return fileMenu;
    }

    private void triggerOpenFile() {
        FileChooser fileChooser = createFileChooser("Open File");
        File selectedFile = fileChooser.showOpenDialog(this.window);
        if(selectedFile!=null){
            TextArea filledTextArea = writeFileContentToTextArea(selectedFile);
            commonUtilProvider.addOpenFileTab(selectedFile.getName(), filledTextArea);
        }
    }

    private TextArea writeFileContentToTextArea(File selectedFile) {
        System.out.println(selectedFile.getName()+ " selected to open.");
        try(BufferedReader reader = new BufferedReader(new FileReader(selectedFile))){
            String line = reader.readLine();
            TextArea openTextArea = new TextArea();
            while (line!=null){
                openTextArea.appendText(line);
                if(!line.endsWith(System.getProperty("line.separator")))
                    openTextArea.appendText(System.getProperty("line.separator"));
                line = reader.readLine();
            }
            return openTextArea;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private File triggerSaveOption(ActionEvent event) {
        FileChooser fileChooser = createFileChooser("Save File");
        File selectedFile = fileChooser.showSaveDialog(this.window);
        if(selectedFile != null){
            return commonUtilProvider.saveTextToFile(selectedFile);
        }
        return null;
    }

    private FileChooser createFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter onlyTextFilesFilter = new FileChooser.ExtensionFilter("Txt files(*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(onlyTextFilesFilter);
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(Paths.get("").toAbsolutePath().toFile());
        return fileChooser;
    }



}
