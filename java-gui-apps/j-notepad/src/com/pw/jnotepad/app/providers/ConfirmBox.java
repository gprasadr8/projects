package com.pw.jnotepad.app.providers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    private static boolean response;

    public static boolean confirmAction(String title, String message){

        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(350);
        Label messageLabel = new Label(message);
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(event -> {
            response = true;
            window.close();
        });
        Button noButton = new Button("No");
        noButton.setOnAction(event -> {
            response = false;
            window.close();
        });
        VBox layout = new VBox(10,messageLabel,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene =  new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return response;
    }
}
