package com.company;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class ShowContainer {

    Label showLabel;
    Label seasonLabel;
    Label episodeLabel;
    Label categoryLabel;
    TextField showField;
    TextField seasonField;
    TextField episodeField;
    TextField categoryField;
    ComboBox comboBox;

    final ObservableList<String> categories = createOptionsList();

    private static ObservableList<String> createOptionsList() {
        ObservableList<String> options =  FXCollections.observableArrayList();
        Downloader.categoryMap.forEach((k, v) -> options.add(k));
        return options;
    }

    public ShowContainer(){
        this.showLabel = new Label("Show: ");
        this.categoryLabel = new Label("Category: ");
        this.seasonLabel = new Label("Season");
        this.episodeLabel = new Label("Episode: ");
        this.showField = new TextField();
        //this.categoryField = new TextField();
        this.seasonField = new TextField("1");
        this.episodeField = new TextField();
        this.seasonField.setMaxWidth(30);
        //this.categoryField.setMaxWidth(100);
        this.episodeField.setMaxWidth(200);
        this.comboBox  = new ComboBox(categories);
    }

    public String getShowName(){
        return this.showField.getText();
    }

    public String getEpisode(){
        return this.episodeField.getText();
    }

    public String getSeason(){
        return this.seasonField.getText();
    }

    public String getCategory(){
        return this.comboBox.getValue().toString();
    }
}
