package jojikanabe.if2210_tb2_sks;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private TextField folderTextField;

    public void NewGame(ActionEvent event) throws IOException {
        GameState.getInstance().NewGame();
        root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void showLoadGameDialog(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("Load Game");

        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(40);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457; -fx-border-color: #DBCF72; -fx-border-width: 2;");

        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 10 0 10 0;");

        Label formatLabel = new Label("FORMAT:");
        formatLabel.setId("formatLabel");
        Label folderLabel = new Label("FOLDER:");
        folderLabel.setId("folderLabel");

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
        loadButton.setId("loadButton");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
                GameState.getInstance().LoadGame();
                showLoadGameResultDialog("STATE LOADED SUCCESSFULLY", event);
            } catch (Exception ex) {
                showLoadGameResultDialog("FAILED TO LOAD STATE", event);
                ex.printStackTrace();
            }
            dialogStage.close();
        });

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(loadButton);

        pane.getChildren().add(vbox);

        Scene dialogScene = new Scene(pane, 400, 200);

        dialogScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    private void showLoadGameResultDialog(String message, ActionEvent originalEvent) {
        Stage resultDialogStage = new Stage();
        resultDialogStage.initStyle(StageStyle.UNDECORATED);

        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(30);
        vbox.setSpacing(10);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457; -fx-border-color: #DBCF72; -fx-border-width: 2;");

        Label messageLabel = new Label(message);
        messageLabel.setTranslateX(50);
        messageLabel.setId("messageLabel");

        Button okButton = new Button("OK");
        okButton.setId("okButton");
        okButton.setTranslateX(100);
        okButton.setOnAction(e -> {
            resultDialogStage.close();
            try {
                loadGameAndSwitchScene(originalEvent);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        vbox.getChildren().addAll(messageLabel, okButton);
        pane.getChildren().add(vbox);

        Scene resultScene = new Scene(pane, 400, 130);

        resultScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        resultDialogStage.setScene(resultScene);
        resultDialogStage.showAndWait();
    }

    public void getLoad() {
        String text = folderTextField.getText();
    }

    private void loadGameAndSwitchScene(ActionEvent event) throws IOException {
        if (GameState.getInstance().giliran % 2 == 1) {
            root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setTranslateX(20);
        gridPane.setTranslateY(10);

        Button button1 = new Button("");
        button1.setPrefWidth(150);
        button1.setPrefHeight(250);

        Button button2 = new Button("");
        button2.setPrefWidth(150);
        button2.setPrefHeight(250);

        Button button3 = new Button("");
        button3.setPrefWidth(150);
        button3.setPrefHeight(250);

        Button button4 = new Button("");
        button4.setPrefWidth(150);
        button4.setPrefHeight(250);

        gridPane.add(button1, 0, 0);
        gridPane.add(button2, 1, 0);
        gridPane.add(button3, 0, 1);
        gridPane.add(button4, 1, 1);

        Button regenerateButton = new Button("Regenerate");
        regenerateButton.setPrefWidth(100);
        regenerateButton.setTranslateX(-5);
        regenerateButton.setTranslateY(10);
        regenerateButton.setId("regenerateButton");

        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefWidth(100);
        confirmButton.setTranslateX(-5);
        confirmButton.setTranslateY(10);
        confirmButton.setId("confirmButton");
        confirmButton.setOnAction(e -> {
            alert.setResult(ButtonType.OK); // Set the result to OK (or any other type if needed)
            alert.hide(); // Close the alert dialog
        });

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER); // Center align vertically
        vbox.getChildren().addAll(gridPane, regenerateButton, confirmButton);

        // Center align gridPane horizontally
        GridPane.setHalignment(gridPane, HPos.CENTER);

        alert.getDialogPane().setContent(vbox);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialogPane.setPrefSize(400, 500);

        ImageView imageView = new ImageView();
        imageView.setImage(null);
        alert.setGraphic(imageView);

        alert.getButtonTypes().clear();

        alert.showAndWait();
    }
}
