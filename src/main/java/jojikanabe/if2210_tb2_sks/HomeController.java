package jojikanabe.if2210_tb2_sks;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jojikanabe.if2210_tb2_sks.classes.GameState;

import java.io.IOException;

public class HomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void NewGame(ActionEvent event) throws IOException {
        GameState.getInstance().NewGame();
        root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void showLoadGameDialog(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setTitle("Load Game");

        // Create a pane and set the background color
        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(20);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457;");

        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 10 0 10 0;");

        Label formatLabel = new Label("FORMAT:");
        formatLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 14; -fx-pref-width: 70");
        Label folderLabel = new Label("FOLDER:");
        folderLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 14; -fx-pref-width: 70");

        ChoiceBox<String> formatChoiceBox = new ChoiceBox<>();
        formatChoiceBox.getItems().add("txt");
        formatChoiceBox.setValue("txt");
        formatChoiceBox.setStyle("-fx-pref-width: 230");

        TextField folderTextField = new TextField();
        folderTextField.setPrefWidth(230);

        gridPane.add(formatLabel, 0, 0);
        gridPane.add(formatChoiceBox, 1, 0);
        gridPane.add(folderLabel, 0, 1);
        gridPane.add(folderTextField, 1, 1);

        Button loadButton = new Button("LOAD");
        loadButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 300; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
//                GameState.getInstance().LoadGame();
                showLoadGameResultDialog("STATE LOADED SUCCESSFULLY", dialogStage, event);
            } catch (Exception ex) {
                showLoadGameResultDialog("FAILED TO LOAD STATE", dialogStage, event);
                ex.printStackTrace();
            }
        });

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(loadButton);

        pane.getChildren().add(vbox);

        Scene dialogScene = new Scene(pane, 400, 200);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    private void showLoadGameResultDialog(String message, Stage parentStage, ActionEvent originalEvent) {
        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(20);
        vbox.setSpacing(10);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457;");

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14;");

        Button okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 100; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        okButton.setOnAction(e -> {
            parentStage.close();
            try {
                loadGameAndSwitchScene(e);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 100; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        backButton.setOnAction(e -> parentStage.close());

        vbox.getChildren().addAll(messageLabel, okButton, backButton);
        pane.getChildren().add(vbox);

        Scene resultScene = new Scene(pane, 400, 200);
        parentStage.setScene(resultScene);
    }

    private void loadGameAndSwitchScene(ActionEvent event) throws IOException {
        GameState.getInstance().LoadGame();
        if (GameState.getInstance().giliran % 2 == 1) {
            root = FXMLLoader.load(getClass().getResource("player1.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("player2.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
