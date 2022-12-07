package com.pw.jnotepad.app;

import com.pw.jnotepad.app.providers.AlertBox;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class StackOverflowAnswer extends Application {
    public static TabPane pane = new TabPane();
    public static TextArea area;
    public static ListView lines;
    public static VBox box;
    public static Tab tabs;
    public static BorderPane bps;
    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        area = new TextArea();
        lines = new ListView();

        box = new VBox();
        bps = new BorderPane();
        bps.setTop(createMenuBar());
        bps.setLeft(lines);
        bps.setRight(area);
        box.getChildren().addAll(bps);

        tabs = new Tab("tab-1");
        tabs.setContent(box);
        pane.getTabs().add(tabs);


        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static MenuBar createMenuBar(){
        Menu fileMenu = new Menu("File");
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(event -> {
            System.out.println("Save file action triggered");
            FileChooser fileChooser = createFileChooser("Save File");
            File selectedFile = fileChooser.showSaveDialog(window);
            if(selectedFile != null){
                File savedFile =  saveTextToFile(selectedFile);
                if(savedFile!= null){
                    System.out.println("file saved successfully");
                    updateTabTitle(savedFile.getName());
                }
            }


        });
        fileMenu.getItems().addAll(saveFile);
        return new MenuBar(fileMenu);
    }

    // to open save dialog window
    private static FileChooser createFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter onlyTextFilesFilter = new FileChooser.ExtensionFilter("Txt files(*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(onlyTextFilesFilter);
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(Paths.get("").toAbsolutePath().toFile());
        return fileChooser;
    }

    public static File saveTextToFile(File file) {
            TextArea selectedTabContent = getSelectedTabContent();
            if(selectedTabContent!=null){
                try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file));) {
                    ObservableList<CharSequence> paragraphs =  selectedTabContent.getParagraphs();
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

    private static TextArea getSelectedTabContent() {
        Node selectedTabContent = pane.getSelectionModel().getSelectedItem().getContent();
        if(selectedTabContent instanceof VBox){
            Node borderPane =  ((VBox) selectedTabContent).getChildren().get(0);
            if(borderPane instanceof BorderPane){
                Node textAreaNode = ((BorderPane) borderPane).getRight();
                if(textAreaNode instanceof  TextArea){
                    return (TextArea) textAreaNode;
                }
            }
        }
        return null;
    }

    private static void updateTabTitle(String name){
        pane.getSelectionModel().getSelectedItem().setText(name);
    }

}
