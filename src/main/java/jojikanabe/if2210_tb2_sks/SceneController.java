package jojikanabe.if2210_tb2_sks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jojikanabe.if2210_tb2_sks.classes.GameState;
import jojikanabe.if2210_tb2_sks.classes.Ladang;
import jojikanabe.if2210_tb2_sks.classes.Pemain;
import jojikanabe.if2210_tb2_sks.classes.Toko;
import jojikanabe.if2210_tb2_sks.classes.kartu.Kartu;
import jojikanabe.if2210_tb2_sks.classes.kartu.Produk;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;

public class SceneController {
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

    public void LoadGame(ActionEvent event) throws IOException {
        GameState.getInstance().LoadGame();
        if (GameState.getInstance().giliran == 1) {
            root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
        }
        GameState.getInstance().LoadGame();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void NextPlayer(ActionEvent event) throws IOException {
        GameState.getInstance().nextTurn();
        if (GameState.getInstance().giliran == 2) {
            root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        showAlert();
    }

    private void showAlert() {
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


    private Button createButton(String nama, String harga, String value, String imagePath) {
        Button button = new Button(nama + "\n" + harga + "\n" + value);
        button.setPrefWidth(150); // Set the width of the button
        button.setPrefHeight(100); // Set the height of the button
        button.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        // Load the image
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50); // Set the width of the image
        imageView.setFitHeight(50); // Set the height of the image

        // Add the image to the button
        button.setGraphic(imageView);

        return button;
    }

    @FXML
    private Button deck0;

    @FXML
    private Button deck1;

    @FXML
    private Button deck2;

    @FXML
    private Button deck3;

    @FXML
    private Button deck4;

    @FXML
    private Button deck5;

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

    @FXML
    private Button A01;

    @FXML
    private Button A02;

    @FXML
    private Button A03;

    @FXML
    private Button A04;

    @FXML
    private Button A05;

    @FXML
    private Button B01;

    @FXML
    private Button B02;

    @FXML
    private Button B03;

    @FXML
    private Button B04;

    @FXML
    private Button B05;

    @FXML
    private Button C01;

    @FXML
    private Button C02;

    @FXML
    private Button C03;

    @FXML
    private Button C04;

    @FXML
    private Button C05;

    @FXML
    private Button D01;

    @FXML
    private Button D02;

    @FXML
    private Button D03;

    @FXML
    private Button D04;

    @FXML
    private Button D05;

    public void addKartuToLadang() {
        List<Button> ladangButtons = new ArrayList<>();
        ladangButtons.add(A01);
        ladangButtons.add(A02);
        ladangButtons.add(A03);
        ladangButtons.add(A04);
        ladangButtons.add(A05);
        ladangButtons.add(B01);
        ladangButtons.add(B02);
        ladangButtons.add(B03);
        ladangButtons.add(B04);
        ladangButtons.add(B05);
        ladangButtons.add(C01);
        ladangButtons.add(C02);
        ladangButtons.add(C03);
        ladangButtons.add(C04);
        ladangButtons.add(C05);
        ladangButtons.add(D01);
        ladangButtons.add(D02);
        ladangButtons.add(D03);
        ladangButtons.add(D04);
        ladangButtons.add(D05);

        Ladang ladang;
        if (GameState.getInstance().giliran == 1) {
            ladang = GameState.getInstance().getPemain().get(0).getLadang();
        } else {
            ladang = GameState.getInstance().getPemain().get(1).getLadang();
        }

        for (int i = 0; i < 20; i++) {
            Button button = ladangButtons.get(i);
            Kartu kartu = ladang.getKartu(i / 5, i % 5);
            if (kartu != null) {
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
    }

    @FXML
    private Label turn;

    public void initialize() {
        if (GameState.getInstance().giliran != null) {
            addKartuToDeck();
            addKartuToLadang();
            turn.setText(GameState.getInstance().getTurn());
        }
    }

    public void showTokoDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Catalog");
        alert.setHeaderText(null);
        alert.setContentText(null);

        Pane pane = new Pane();
        VBox vbox = new VBox();
        vbox.setLayoutY(-10);
        vbox.setSpacing(10);

        Image image = new Image(getClass().getResourceAsStream("/assets/header_toko.png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(500);
        imageView.setLayoutX(-10);

        vbox.getChildren().add(imageView);
        vbox.setLayoutX(-10);

        GridPane grid = new GridPane();

        // Define three column constraints
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33.33);
            grid.getColumnConstraints().add(column);
        }

        GridPane grid2 = new GridPane();

        // Define three column constraints
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33.33);
            grid2.getColumnConstraints().add(column);
        }

        GridPane grid3 = new GridPane();

        // Define three column constraints
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33.33);
            grid3.getColumnConstraints().add(column);
        }

        Toko toko = GameState.getInstance().getToko();
        Map<Produk, Integer> daftarProduk = toko.getDaftarProduk();

        int counter = 0;
        for (Map.Entry<Produk, Integer> entry : daftarProduk.entrySet()) {

            if (entry.getValue() > 0) {
                if (counter == 0 || counter == 1 || counter == 2) {
                    Button button = createButton(entry.getKey().getNama(), entry.getKey().getHarga().toString(), entry.getValue().toString(), entry.getKey().getImage());
                    grid.add(button, counter % 3, counter / 3);
                    GridPane.setHalignment(button, HPos.CENTER);
                    GridPane.setValignment(button, VPos.CENTER);
                } else if (counter == 3 || counter == 4 || counter == 5) {
                    Button button = createButton(entry.getKey().getNama(), entry.getKey().getHarga().toString(), entry.getValue().toString(), entry.getKey().getImage());
                    grid2.add(button, counter % 3, counter / 3);
                    GridPane.setHalignment(button, HPos.CENTER);
                    GridPane.setValignment(button, VPos.CENTER);
                } else {
                    Button button = createButton(entry.getKey().getNama(), entry.getKey().getHarga().toString(), entry.getValue().toString(), entry.getKey().getImage());
                    grid3.add(button, counter % 3, counter / 3);
                    GridPane.setHalignment(button, HPos.CENTER);
                    GridPane.setValignment(button, VPos.CENTER);
                }

                counter++;
            }
        }

        vbox.getChildren().add(grid);

        Image image2 = new Image(getClass().getResourceAsStream("/assets/etalase.png"));
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(500);
        imageView2.setFitHeight(20);
        vbox.getChildren().add(imageView2);

        vbox.getChildren().add(grid2);

        Image image3 = new Image(getClass().getResourceAsStream("/assets/etalase.png"));
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(500);
        imageView3.setFitHeight(20);
        vbox.getChildren().add(imageView3);

        vbox.getChildren().add(grid3);

        Image image4 = new Image(getClass().getResourceAsStream("/assets/etalase.png"));
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(500);
        imageView4.setFitHeight(20);
        vbox.getChildren().add(imageView4);

        pane.getChildren().add(vbox);

        alert.getDialogPane().setGraphic(pane);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefSize(500, 500);
        dialogPane.setStyle("-fx-background-color: #D0D0D0;");

        alert.showAndWait();
    }
}
