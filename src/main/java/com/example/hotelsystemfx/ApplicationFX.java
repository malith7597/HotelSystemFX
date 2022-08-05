package com.example.hotelsystemfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class ApplicationFX extends javafx.application.Application {



    public static void main(String[] args) {
    launch(args);
    }
    double x,y=0;
    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed( event->{
                    x=event.getSceneX();
                    y=event.getSceneY();
                    });
            root.setOnMouseDragged(event->{
                primaryStage.setX(event.getSceneX()-x);
                primaryStage.setY(event.getSceneY()-y);

            });
            primaryStage.setScene(new Scene(root,800,500));
            primaryStage.show();



    }
}