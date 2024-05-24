package jojikanabe.if2210_tb2_sks;

import javafx.application.Platform;
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
import java.util.concurrent.atomic.AtomicInteger;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Parent enemyroot;
    private boolean viewingOpponentField = false;
    private Kartu selectedKartu = null;
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
    private boolean isFromDeck = false;
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
    private int selectedCardRow = -1;
    private int selectedCardCol = -1;
    @FXML
    private Label turn, player1, player2;

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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void NextPlayer(ActionEvent event) throws IOException {
        if (viewingOpponentField) {
            // If we are viewing the opponent's field, don't change the background
            viewingOpponentField = false;
            if (GameState.getInstance().giliran == 1) {
                root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
            }
        } else {
            // Otherwise, proceed to the next turn and change the background
            GameState.getInstance().nextTurn();
            if (GameState.getInstance().giliran == 2) {
                root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
                enemyroot = FXMLLoader.load(getClass().getResource("Player1.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
                enemyroot = FXMLLoader.load(getClass().getResource("Player2.fxml"));
            }
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        showAlert();
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
            pemain.getDeckAktif().add(kartu1);
            pemain.getDeckAktif().add(kartu2);
            pemain.getDeckAktif().add(kartu3);
            pemain.getDeckAktif().add(kartu4);
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
        button.setPrefWidth(150);
        button.setPrefHeight(100);
        button.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        // Load the image
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // Add the image to the button
        button.setGraphic(imageView);

        return button;
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

            button.setOnAction(e -> {
                selectedKartu = kartu;
                isFromDeck = true;
            });
        }

    }

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

        for (Button button : ladangButtons) {
            button.setText("");
            button.setGraphic(null);
            button.setOnAction(null);
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

            int finalI = i;
            int x = -1;
            int y = -1;
            button.setOnAction(e -> {
                Pemain pemain = GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1);
                if (selectedKartu != null) {
                    if (pemain.getLadang().getKartu(finalI / 5, finalI % 5) == null) {
                        pemain.getLadang().addKartu(finalI / 5, finalI % 5, selectedKartu);
                        if (isFromDeck) {
                            pemain.removeKartu(selectedKartu);
                            selectedKartu = null;
                            isFromDeck = false;
                        } else {
                            selectedKartu = null;
                            pemain.getLadang().removeKartu(selectedCardRow, selectedCardCol);
                            selectedCardRow = -1;
                            selectedCardCol = -1;
                        }
                    }
                } else if (kartu != null) {
                    selectedKartu = kartu;
                    selectedCardRow = finalI / 5;
                    selectedCardCol = finalI % 5;
                }
                Platform.runLater(() -> {
                    addKartuToDeck();
                    addKartuToLadang();
                });
            });
        }
    }

    public void initialize() {
        if (GameState.getInstance().giliran != null) {
            addKartuToDeck();
            addKartuToLadang();
            turn.setText(GameState.getInstance().getTurn());
            player1.setText(GameState.getInstance().getGuldenPemain1());
            player2.setText(GameState.getInstance().getGuldenPemain2());
        }
    }

    public void showTokoDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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

    public void showLadangLawan(ActionEvent event) throws IOException {
        viewingOpponentField = true;
        if (GameState.getInstance().giliran == 1) {
            root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Platform.runLater(this::addOpponentKartuToLadang);
        Platform.runLater(this::addKartuToDeck);
    }

    private void addOpponentKartuToLadang() {
        List<Button> ladangButtons = new ArrayList<>();
        ladangButtons.add((Button) scene.lookup("#A01"));
        ladangButtons.add((Button) scene.lookup("#A02"));
        ladangButtons.add((Button) scene.lookup("#A03"));
        ladangButtons.add((Button) scene.lookup("#A04"));
        ladangButtons.add((Button) scene.lookup("#A05"));
        ladangButtons.add((Button) scene.lookup("#B01"));
        ladangButtons.add((Button) scene.lookup("#B02"));
        ladangButtons.add((Button) scene.lookup("#B03"));
        ladangButtons.add((Button) scene.lookup("#B04"));
        ladangButtons.add((Button) scene.lookup("#B05"));
        ladangButtons.add((Button) scene.lookup("#C01"));
        ladangButtons.add((Button) scene.lookup("#C02"));
        ladangButtons.add((Button) scene.lookup("#C03"));
        ladangButtons.add((Button) scene.lookup("#C04"));
        ladangButtons.add((Button) scene.lookup("#C05"));
        ladangButtons.add((Button) scene.lookup("#D01"));
        ladangButtons.add((Button) scene.lookup("#D02"));
        ladangButtons.add((Button) scene.lookup("#D03"));
        ladangButtons.add((Button) scene.lookup("#D04"));
        ladangButtons.add((Button) scene.lookup("#D05"));

        Ladang ladang;
        if (GameState.getInstance().giliran == 1) {
            ladang = GameState.getInstance().getPemain().get(1).getLadang();
        } else {
            ladang = GameState.getInstance().getPemain().get(0).getLadang();
        }

        for (int i = 0; i < 20; i++) {
            Button button = ladangButtons.get(i);
            Kartu kartu = ladang.getKartu(i / 5, i % 5);
            if (kartu != null) {
                button.setText(kartu.getNama());
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu.getImage())));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                button.setGraphic(imageView);
                button.setContentDisplay(ContentDisplay.TOP);
            } else {
                button.setText("");
                button.setGraphic(null);
            }
        }
    }

    public void showLadangku(ActionEvent event) throws IOException {
        viewingOpponentField = false;
        if (GameState.getInstance().giliran == 2) {
            root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
