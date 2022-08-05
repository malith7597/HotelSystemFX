package com.example.hotelsystemfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ScreenLoader {
    public Pane view;

    public Pane getScreen(String filename){
        try{
            URL fileUrl= ApplicationFX.class.getResource("/hotelsystemfx/"+filename+".fxml");
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("FXML file can't be found!");
            }
            view = new FXMLLoader().load(fileUrl);

        }catch(Exception e){
            e.getStackTrace();
        }
        return view;
    }



}
