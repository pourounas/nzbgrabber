package com.company;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    private ArrayList<ShowContainer> shows = new ArrayList<ShowContainer>();
    private Downloader downloader = new Downloader();

    @Override
    public void start(Stage primaryStage) {
        VBox showLayout = new VBox(10);
        showLayout.getChildren().add(addShow());
        primaryStage.setTitle("Nzb Grabber");
        Button buttonGo = createGoButton();
        Button buttonAdd = createShowButton(showLayout);
        Button buttonClear = createClearButton(showLayout);
        VBox sceneLayout = new VBox(10);
        HBox buttonLayout = new HBox(5);
        buttonLayout.getChildren().addAll(buttonAdd, buttonGo, buttonClear);
        sceneLayout.getChildren().addAll(showLayout, buttonLayout);

        Scene scene = new Scene(sceneLayout,800,250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox addShow(){
        HBox hBox = new HBox(5);
        ShowContainer show = new ShowContainer();
        hBox.getChildren().addAll(show.showLabel, show.showField, show.categoryLabel,
                show.comboBox, show.seasonLabel, show.seasonField, show.episodeLabel,
                show.episodeField);
        shows.add(show);
        return hBox;
    }

    private Button createGoButton(){
        Button btn = new Button("Go!");
        btn.setOnAction(e ->{
            for (ShowContainer show : this.shows){
                try {
                    downloader.getEntry(show);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        return btn;
    }

    private Button createShowButton(VBox showLayout){
        Button btn = new Button("Add show");
        btn.setOnAction(e -> {
            //Button was clicked
            HBox show = addShow();
            showLayout.getChildren().add(show);
            System.out.println("Added show.");
        } );
        return btn;
    }

    private Button createClearButton(VBox showLayout){
        Button btn = new Button("Clear");
        btn.setOnAction(e ->{
            for (ShowContainer show : this.shows){
                show.episodeField.clear();
                show.showField.clear();
                show.categoryField.clear();
                show.seasonField.clear();
            }
        });
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}