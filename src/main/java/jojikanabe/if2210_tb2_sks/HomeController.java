package jojikanabe.if2210_tb2_sks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jojikanabe.if2210_tb2_sks.classes.GameState;
import jojikanabe.if2210_tb2_sks.classes.Pemain;
import jojikanabe.if2210_tb2_sks.classes.kartu.Kartu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private TextField folderTextField;

    @FXML
    private Button deck0, deck1, deck2, deck3, deck4, deck5, deckP;

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
                GameState.getInstance().LoadGame(folder);
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

    private Button createButtonShuffle(String nama, String imagePath) {
        Button button = new Button(nama);
        button.setPrefWidth(150);
        button.setPrefHeight(250);
        button.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        // Load the image
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // Add the image to the button
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.TOP);

        return button;
    }

    private Button buttonShuffle1, buttonShuffle2, buttonShuffle3, buttonShuffle4;

    private Kartu kartu1, kartu2, kartu3, kartu4;

    private void showAlert() {
        Pemain pemain;
        if (GameState.getInstance().giliran == 1) {
            pemain = GameState.getInstance().getPemain().get(0);
        } else {
            pemain = GameState.getInstance().getPemain().get(1);
        }

        System.out.println(pemain.getDeck().getSize());
        System.out.println(pemain.getDeckAktif().size());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setTranslateX(20);
        gridPane.setTranslateY(10);

        kartu1 = pemain.getDeck().getKartuRandom();
        kartu2 = pemain.getDeck().getKartuRandom();
        kartu3 = pemain.getDeck().getKartuRandom();
        kartu4 = pemain.getDeck().getKartuRandom();

        buttonShuffle1 = createButtonShuffle(kartu1.getNama(), kartu1.getImage());
        buttonShuffle2 = createButtonShuffle(kartu2.getNama(), kartu2.getImage());
        buttonShuffle3 = createButtonShuffle(kartu3.getNama(), kartu3.getImage());
        buttonShuffle4 = createButtonShuffle(kartu4.getNama(), kartu4.getImage());

        gridPane.add(buttonShuffle1, 0, 0);
        gridPane.add(buttonShuffle2, 1, 0);
        gridPane.add(buttonShuffle3, 0, 1);
        gridPane.add(buttonShuffle4, 1, 1);

        Button regenerateButton = new Button("Regenerate");
        regenerateButton.setPrefWidth(100);
        regenerateButton.setTranslateX(-5);
        regenerateButton.setTranslateY(10);
        regenerateButton.setId("regenerateButton");
        regenerateButton.setOnAction(e -> {
            pemain.getDeck().addKartu(kartu1);
            pemain.getDeck().addKartu(kartu2);
            pemain.getDeck().addKartu(kartu3);
            pemain.getDeck().addKartu(kartu4);
            kartu1 = pemain.getDeck().getKartuRandom();
            kartu2 = pemain.getDeck().getKartuRandom();
            kartu3 = pemain.getDeck().getKartuRandom();
            kartu4 = pemain.getDeck().getKartuRandom();
            buttonShuffle1.setText(kartu1.getNama());
            buttonShuffle2.setText(kartu2.getNama());
            buttonShuffle3.setText(kartu3.getNama());
            buttonShuffle4.setText(kartu4.getNama());
            Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu1.getImage())));
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitWidth(50);
            imageView1.setFitHeight(50);
            buttonShuffle1.setGraphic(imageView1);
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu2.getImage())));
            ImageView imageView2 = new ImageView(image2);
            imageView2.setFitWidth(50);
            imageView2.setFitHeight(50);
            buttonShuffle2.setGraphic(imageView2);
            Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu3.getImage())));
            ImageView imageView3 = new ImageView(image3);
            imageView3.setFitWidth(50);
            imageView3.setFitHeight(50);
            buttonShuffle3.setGraphic(imageView3);
            Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu4.getImage())));
            ImageView imageView4 = new ImageView(image4);
            imageView4.setFitWidth(50);
            imageView4.setFitHeight(50);
            buttonShuffle4.setGraphic(imageView4);
        });

        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefWidth(100);
        confirmButton.setTranslateX(-5);
        confirmButton.setTranslateY(10);
        confirmButton.setId("confirmButton");
        confirmButton.setOnAction(e -> {
            alert.setResult(ButtonType.OK); // Set the result to OK (or any other type if needed)
            alert.hide(); // Close the alert dialog
            if (6 - pemain.getDeckAktif().size() > 3) {
                pemain.getDeckAktif().add(kartu1);
                pemain.getDeckAktif().add(kartu2);
                pemain.getDeckAktif().add(kartu3);
                pemain.getDeckAktif().add(kartu4);
            } else if (6 - pemain.getDeckAktif().size() == 3) {
                pemain.getDeckAktif().add(kartu1);
                pemain.getDeckAktif().add(kartu2);
                pemain.getDeckAktif().add(kartu3);
                pemain.getDeck().addKartu(kartu4);
            } else if (6 - pemain.getDeckAktif().size() == 2) {
                pemain.getDeckAktif().add(kartu1);
                pemain.getDeckAktif().add(kartu2);
                pemain.getDeck().addKartu(kartu3);
                pemain.getDeck().addKartu(kartu4);
            } else if (6 - pemain.getDeckAktif().size() == 1) {
                pemain.getDeckAktif().add(kartu1);
                pemain.getDeck().addKartu(kartu2);
                pemain.getDeck().addKartu(kartu3);
                pemain.getDeck().addKartu(kartu4);
            } else {
                pemain.getDeck().addKartu(kartu1);
                pemain.getDeck().addKartu(kartu2);
                pemain.getDeck().addKartu(kartu3);
                pemain.getDeck().addKartu(kartu4);
            }
            System.out.println(pemain.getDeck().getSize());
            System.out.println(pemain.getDeckAktif().size());
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

        showAlert();
    }

    public void addKartuToDeck() {
        List<Kartu> deckAktif;
        if (GameState.getInstance().giliran == 1) {
            deckAktif = GameState.getInstance().getPemain().get(0).getDeckAktif();
        } else {
            deckAktif = GameState.getInstance().getPemain().get(1).getDeckAktif();
        }
        List<Button> deckButtons = new ArrayList<>();
        deckButtons.add(deck0);
        deckButtons.add(deck1);
        deckButtons.add(deck2);
        deckButtons.add(deck3);
        deckButtons.add(deck4);
        deckButtons.add(deck5);

        for (Button button : deckButtons) {
            button.setText("");
            button.setGraphic(null);
            button.setOnAction(null);
        }

        for (int i = 0; i < deckAktif.size(); i++) {
            Button button = deckButtons.get(i);
            Kartu kartu = deckAktif.get(i);
            button.setText(kartu.getNama());

            // Load the image
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu.getImage())));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(30); // Set the width of the image
            imageView.setFitHeight(30); // Set the height of the image

            // Add the image to the button
            button.setGraphic(imageView);

            button.setContentDisplay(ContentDisplay.TOP);
        }

    }

    public void initialize() {
        if (GameState.getInstance().giliran != null) {
            addKartuToDeck();
            if (GameState.getInstance().giliran == 1) {
                deckP.setText(GameState.getInstance().getDeckStatusPemain1());
            } else if (GameState.getInstance().giliran == 2) {
                deckP.setText(GameState.getInstance().getDeckStatusPemain2());
            }
        }
    }
}
