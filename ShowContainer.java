package sample;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * Created by zklhz9d on 29/01/2018.
 */
public class ShowContainer {

    Label showLabel;
    Label seasonLabel;
    Label episodeLabel;
    TextField showField;
    TextField seasonField;
    TextField episodeField;

    public ShowContainer(){
        this.showLabel = new Label("Show: ");
        this.seasonLabel = new Label("Season");
        this.episodeLabel = new Label("Episode: ");
        this.showField = new TextField();
        this.seasonField = new TextField();
        this.episodeField = new TextField();
        this.seasonField.setMaxWidth(30);
        this.episodeField.setMaxWidth(60);
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


}
