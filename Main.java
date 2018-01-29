package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main extends Application {

    private ArrayList<ShowContainer> shows = new ArrayList<ShowContainer>();


    @Override
    public void start(Stage primaryStage) {
        VBox showLayout = new VBox(10);
        showLayout.getChildren().add(addShow());
        primaryStage.setTitle("My First JavaFX GUI");
        Button buttonGo = createGoButton();
        Button buttonAdd = createShowButton(showLayout);
        Button buttonClear = createClearButton(showLayout);
        VBox sceneLayout = new VBox(10);
        HBox buttonLayout = new HBox(5);
        buttonLayout.getChildren().addAll(buttonAdd, buttonGo, buttonClear);
        sceneLayout.getChildren().addAll(showLayout, buttonLayout);

        Scene scene = new Scene(sceneLayout, 600, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox addShow(){
        HBox hBox = new HBox(5);
        ShowContainer show = new ShowContainer();
        hBox.getChildren().addAll(show.showLabel, show.showField, show.seasonLabel, show.seasonField, show.episodeLabel, show.episodeField);
        shows.add(show);
        return hBox;
    }

    private Button createGoButton(){
        Button btn = new Button("Go!");
        btn.setOnAction(e ->{
            for (ShowContainer show : this.shows){
                String s = show.getShowName();
                String episode = show.getEpisode();
                String season = show.getSeason();
                System.out.println(s);
                System.out.print(season);
                System.out.print(episode);
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
                show.seasonField.clear();
            }
        });
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
