package com.pw.jnotepad.app.providers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static  void display(String title,String message){
        Stage window = new Stage();
        //This option blocks the window until user says ok
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label messageLabel = new Label(message);
        Button okButton = new Button("OK");
        VBox layout = new VBox(20,messageLabel,okButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        //This option blocks the window until user says ok
        window.showAndWait();
    }
}
