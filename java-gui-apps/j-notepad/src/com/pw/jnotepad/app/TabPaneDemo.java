package com.pw.jnotepad.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TabPaneDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        tab.setText("New");
        TextArea textArea = new TextArea("This is tab");
        tab.setContent(textArea);
        tabPane.getTabs().add(tab);

        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
