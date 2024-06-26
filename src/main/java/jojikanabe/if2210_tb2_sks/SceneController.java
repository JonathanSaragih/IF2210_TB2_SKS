package jojikanabe.if2210_tb2_sks;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jojikanabe.if2210_tb2_sks.classes.*;
import jojikanabe.if2210_tb2_sks.classes.kartu.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Parent enemyroot;
    private boolean viewingOpponentField = false;
    private Kartu selectedKartu = null;
    @FXML
    private Label deckP;
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
    private BearAttack bearAttack;
    private Button buttonShuffle1, buttonShuffle2, buttonShuffle3, buttonShuffle4;
    private Kartu kartu1, kartu2, kartu3, kartu4;
    @FXML
    private Label bearAttackTimer;
    private Kartu kartuToko;

    public void NewGame(ActionEvent event) throws IOException {
        GameState.getInstance().NewGame();
        root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void bearAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bear Alert");
        alert.setHeaderText(null);
        alert.setContentText("Awasssss diserang beruang!");
        

        // Customize the dialog pane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-dialog");

        // Show the alert dialog
        alert.showAndWait();
    }

    public void NextPlayer(ActionEvent event) throws IOException {
        if (bearAttack.isBearAttackOngoing()) {
            return;
        }
        if (GameState.getInstance().getTurnInt() >= 20) {
            showWinner();
            return;
        }
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

    public void Refresh(ActionEvent event) throws IOException {
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

    }

    private void showWinner() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setTitle("Winner");

        String player1Gulden = String.valueOf(GameState.getInstance().getPemain().get(0).getGulden());
        String player2Gulden = String.valueOf(GameState.getInstance().getPemain().get(1).getGulden());

        String contentText = "Player 1 Gulden: " + player1Gulden + "\n" +
                "Player 2 Gulden: " + player2Gulden + "\n" +
                GameState.getInstance().getWinner();

        ImageView imageView = new ImageView();
        imageView.setImage(null);
        alert.setGraphic(imageView);

        Button exitButton = new Button("Exit");

        exitButton.setOnAction(event -> {

        });

        HBox buttonBox = new HBox(exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateX(-10);

        alert.getButtonTypes().clear();

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-dialog");
        dialogPane.setPrefSize(300, 200);

        Label contentLabel = new Label(contentText);
        contentLabel.getStyleClass().add("content");
        dialogPane.setContent(new VBox(10, contentLabel, buttonBox));

        alert.showAndWait();
    }

    private void showAlert() {
        Pemain pemain;
        if (GameState.getInstance().giliran == 1) {
            pemain = GameState.getInstance().getPemain().get(0);
        } else {
            pemain = GameState.getInstance().getPemain().get(1);
        }

//        System.out.println(pemain.getDeck().getSize());
//        System.out.println(pemain.getDeckAktif().size());

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
//            System.out.println(pemain.getDeckAktif().size());
//            System.out.println(pemain.getDeck().getSize());
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
                if (selectedKartu instanceof Produk) {
                    showJualDialog(e, selectedKartu);
                }
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
                        if (selectedKartu instanceof Hewan || selectedKartu instanceof Tanaman) {
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
                    } else {
                        if (selectedKartu instanceof Produk && pemain.getLadang().getKartu(finalI / 5, finalI % 5) instanceof Hewan) {
                            // TODO Hewan makan
                            try {
                                ((Hewan) pemain.getLadang().getKartu(finalI / 5, finalI % 5)).makan(selectedKartu);
                                pemain.removeKartu(selectedKartu);
                                selectedKartu = null;
                                isFromDeck = false;
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        } else if (selectedKartu instanceof Item) {
                            // TODO Item digunakan
                        } else {
                            // Bukan produk atau item
                        }
                    }
                } else if (kartu != null) {
                    selectedKartu = kartu;
                    selectedCardRow = finalI / 5;
                    selectedCardCol = finalI % 5;
                    showCardStatus(selectedKartu, selectedCardRow, selectedCardCol);
                }
                Platform.runLater(() -> {
                    addKartuToDeck();
                    addKartuToLadang();
                });
            });
        }
    }

    public void initialize() {
        bearAttack = new BearAttack();
        if (GameState.getInstance().giliran != null) {
            addKartuToDeck();
            addKartuToLadang();
            turn.setText(GameState.getInstance().getTurn());
            player1.setText(GameState.getInstance().getGuldenPemain1());
            player2.setText(GameState.getInstance().getGuldenPemain2());
            if (GameState.getInstance().giliran == 1) {
                deckP.setText(GameState.getInstance().getDeckStatusPemain1());
            } else if (GameState.getInstance().giliran == 2) {
                deckP.setText(GameState.getInstance().getDeckStatusPemain2());
            }
            bearAttack.startBearAttack(this);

            if (bearAttack.isBearAttackOngoing()) {
                bearAlert();
            }

        }

    }

    public void refreshGame() {
        try {
            Refresh(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTimerUI(double timeLeft) {
        // Update the timer display in the UI
        bearAttackTimer.setText("Bear Attack: " + timeLeft + "s");
    }

    public void showTokoDialog() {
        if (bearAttack.isBearAttackOngoing()) {
            // Show warning that visiting store is not allowed during bear attack
            return;
        }
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
                    button.setUserData(entry.getKey());
                    grid.add(button, counter % 3, counter / 3);
                    GridPane.setHalignment(button, HPos.CENTER);
                    GridPane.setValignment(button, VPos.CENTER);
                    button.setOnAction(e -> {
                        kartuToko = (Kartu) button.getUserData();
                        showBeliDialog(e);
                    });
                } else if (counter == 3 || counter == 4 || counter == 5) {
                    Button button = createButton(entry.getKey().getNama(), entry.getKey().getHarga().toString(), entry.getValue().toString(), entry.getKey().getImage());
                    button.setUserData(entry.getKey());
                    grid2.add(button, counter % 3, counter / 3);
                    GridPane.setHalignment(button, HPos.CENTER);
                    GridPane.setValignment(button, VPos.CENTER);
                    button.setOnAction(e -> {
                        kartuToko = (Kartu) button.getUserData();
                        showBeliDialog(e);
                    });
                } else {
                    Button button = createButton(entry.getKey().getNama(), entry.getKey().getHarga().toString(), entry.getValue().toString(), entry.getKey().getImage());
                    button.setUserData(entry.getKey());
                    grid3.add(button, counter % 3, counter / 3);
                    GridPane.setHalignment(button, HPos.CENTER);
                    GridPane.setValignment(button, VPos.CENTER);
                    button.setOnAction(e -> {
                        kartuToko = (Kartu) button.getUserData();
                        showBeliDialog(e);
                    });
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

    public void showLoadPluginDialog(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("Load Plugin");

        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(20);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457; -fx-border-color: yellow; -fx-border-width: 2;");

        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 40 0 10 0;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 50; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        backButton.setOnAction(e -> dialogStage.close());

        // Create a BorderPane
        BorderPane borderPane = new BorderPane();

        // Set backButton to the left of the BorderPane
        borderPane.setLeft(backButton);

        // Add vbox to the center of the BorderPane
        borderPane.setCenter(vbox);
        // Create an Insets object with top and left margins
        Insets margin = new Insets(10, 0, 0, 30); // 5 units for top and left margins
        // Apply the margin to backButton
        borderPane.setMargin(backButton, margin);

        // Add the BorderPane to the pane
        pane.getChildren().add(borderPane);

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

        Button loadButton = new Button("LOAD PLUGIN");
        loadButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 300; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
                GameState.getInstance().LoadGame(folder);
                showResultDialog("PLUGIN LOADED SUCCESSFULLY", event);
            } catch (Exception ex) {
                showResultDialog("FAILED TO LOAD PLUGIN", event);
                ex.printStackTrace();
            }
            dialogStage.close();
        });

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(loadButton);

        pane.getChildren().add(vbox);

        Scene dialogScene = new Scene(pane, 400, 200);

        // Add an event filter to close the dialog when clicking outside of it
        dialogScene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!pane.contains(mouseEvent.getX(), mouseEvent.getY())) {
                dialogStage.close();
            }
        });

        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    public void showLoadStateDialog(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("Load State");

        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(20);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457; -fx-border-color: yellow; -fx-border-width: 2;");

        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 40 0 10 0;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 50; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        backButton.setOnAction(e -> dialogStage.close());

        // Create a BorderPane
        BorderPane borderPane = new BorderPane();

        // Set backButton to the left of the BorderPane
        borderPane.setLeft(backButton);

        // Add vbox to the center of the BorderPane
        borderPane.setCenter(vbox);
        // Create an Insets object with top and left margins
        Insets margin = new Insets(10, 0, 0, 30); // 5 units for top and left margins
        // Apply the margin to backButton
        borderPane.setMargin(backButton, margin);

        // Add the BorderPane to the pane
        pane.getChildren().add(borderPane);

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

        Button loadButton = new Button("LOAD STATE");
        loadButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 300; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
                GameState.getInstance().LoadGame(folder);
                showResultDialog("STATE LOADED SUCCESSFULLY", event);
            } catch (Exception ex) {
                showResultDialog("FAILED TO LOAD STATE", event);
                ex.printStackTrace();
            }
            dialogStage.close();
        });

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(loadButton);

        pane.getChildren().add(vbox);

        Scene dialogScene = new Scene(pane, 400, 200);

        // Add an event filter to close the dialog when clicking outside of it
        dialogScene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!pane.contains(mouseEvent.getX(), mouseEvent.getY())) {
                dialogStage.close();
            }
        });

        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    public void showSaveStateDialog(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("Save State");

        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(20);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457; -fx-border-color: yellow; -fx-border-width: 2;");

        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 40 0 10 0;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 50; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        backButton.setOnAction(e -> dialogStage.close());

        // Create a BorderPane
        BorderPane borderPane = new BorderPane();

        // Set backButton to the left of the BorderPane
        borderPane.setLeft(backButton);

        // Add vbox to the center of the BorderPane
        borderPane.setCenter(vbox);
        // Create an Insets object with top and left margins
        Insets margin = new Insets(10, 0, 0, 30); // 5 units for top and left margins
        // Apply the margin to backButton
        borderPane.setMargin(backButton, margin);

        // Add the BorderPane to the pane
        pane.getChildren().add(borderPane);

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

        Button loadButton = new Button("SAVE STATE");
        loadButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 300; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
                GameState.getInstance().saveConfig(folder);
                showResultDialog("STATE SAVED SUCCESSFULLY", event);
            } catch (Exception ex) {
                showResultDialog("FAILED TO SAVE STATE", event);
                ex.printStackTrace();
            }
            dialogStage.close();
        });

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(loadButton);

        pane.getChildren().add(vbox);

        Scene dialogScene = new Scene(pane, 400, 200);

        // Add an event filter to close the dialog when clicking outside of it
        dialogScene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!pane.contains(mouseEvent.getX(), mouseEvent.getY())) {
                dialogStage.close();
            }
        });

        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    private void showResultDialog(String message, ActionEvent originalEvent) {
        Stage resultDialogStage = new Stage();
        resultDialogStage.initStyle(StageStyle.UNDECORATED);

        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(20);
        vbox.setSpacing(10);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457; -fx-border-color: yellow; -fx-border-width: 2;");

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14;");

        Button okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 100; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        okButton.setOnAction(e -> {
            resultDialogStage.close();
            try {
                SwitchScene(originalEvent);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        vbox.getChildren().addAll(messageLabel, okButton);
        pane.getChildren().add(vbox);

        Scene resultScene = new Scene(pane, 400, 200);

        // Add an event filter to close the dialog when clicking outside of it
        resultScene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!pane.contains(mouseEvent.getX(), mouseEvent.getY())) {
                resultDialogStage.close();
            }
        });

        resultDialogStage.setScene(resultScene);
        resultDialogStage.showAndWait();
    }

    private void SwitchScene(ActionEvent event) throws IOException {
        if (GameState.getInstance().giliran % 2 == 1) {
            root = FXMLLoader.load(getClass().getResource("Player1.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("Player2.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showLadangLawan(ActionEvent event) throws IOException {
        if (bearAttack.isBearAttackOngoing()) {
            // Show warning that switching field is not allowed during bear attack
            return;
        }
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

    public void showCardStatus(Kartu kartu, int row, int col) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Card Status");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);

        if (kartu instanceof Hewan) {
            Hewan hewan = (Hewan) kartu;
            alert.setContentText("Nama Hewan: " + hewan.getNama() + "\nBerat Badan: " + hewan.getBeratBadan());
            System.out.println(hewan.isSiapPanen());
        } else if (kartu instanceof Tanaman) {
            Tanaman tanaman = (Tanaman) kartu;
            alert.setContentText("Nama Tanaman: " + tanaman.getNama() + "\nUmur: " + tanaman.getUmur());
            System.out.println(tanaman.isSiapPanen());
        } else {
            alert.setContentText("Kartu:\nNama: " + kartu.getNama());
        }

        ImageView imageView = new ImageView();
        imageView.setImage(null); // Set an image if needed
        alert.setGraphic(imageView);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-dialog-pane");

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButtonType);

        Node okButton = dialogPane.lookupButton(okButtonType);
        okButton.setTranslateX(-15);

        Button panenButton = new Button("Panen");
        panenButton.setTranslateX(-10);
        panenButton.setOnMouseClicked(event -> {
            if (kartu instanceof Hewan) {
                try {
                    Hewan hewan = (Hewan) kartu;
                    if (hewan.isSiapPanen()) {
                        System.out.println("berhasil");
                        ((Hewan) kartu).panen(row, col, GameState.getInstance().giliran);
                    } else {
                        System.out.println("belum panen ni cok");
                    }
                } catch (Exception e) {
                    System.out.println("Lah rusak");
                }
            } else if (kartu instanceof Tanaman) {
                try {
                    Tanaman tanaman = (Tanaman) kartu;
                    if (tanaman.isSiapPanen()) {
                        System.out.println("berhasil");
                        ((Tanaman) kartu).panen(row, col, GameState.getInstance().giliran);
                    } else {
                        System.out.println("belum panen ni cok");
                    }
                } catch (Exception e) {
                    System.out.println("Lah rusak");
                }
            }
        });

        ButtonBar buttonBar = (ButtonBar) dialogPane.lookup(".button-bar");
        buttonBar.getButtons().add(panenButton);

        alert.showAndWait();
    }

    public void showBeliDialog(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("BELI");

        // Create a VBox for the central content
        VBox centerVBox = new VBox();
        centerVBox.setSpacing(10);
        centerVBox.setPadding(new Insets(-15, 30, 20, 30));
        centerVBox.setAlignment(Pos.CENTER);

        // Pane for the dialog with styling
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #970220; -fx-border-color: yellow; -fx-border-width: 2;");

        // GridPane for the number input
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 40 0 10 0;");
        gridPane.setAlignment(Pos.CENTER);

        Label numberLabel = new Label("NUMBER:");
        numberLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-pref-width: 70");

        Spinner<Integer> numberSpinner = new Spinner<>();
        numberSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
        numberSpinner.setPrefWidth(100);

        gridPane.add(numberLabel, 0, 0);
        gridPane.add(numberSpinner, 1, 0);

        Produk temp = (Produk) kartuToko;

        // Button for loading the plugin
        Button loadButton = new Button("BELI");
        loadButton.setStyle("-fx-background-color: #0000ff; -fx-text-fill: white; -fx-pref-width: 50;-fx-pref-height: 10 ; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        loadButton.setOnAction(e -> {
            int number = numberSpinner.getValue();
            int banyak = 0;
            if ((6 - GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).getDeckAktif().size()) >= number) {
                for (int i = 0; i < number; i++) {
                    GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).addKartu(GameState.getInstance().getToko().jualProduk(temp, 1), 6 - GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).getDeckAktif().size());
                    banyak++;
                }
            } else {
                for (int i = 0; i < (6 - GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).getDeckAktif().size()); i++) {
                    GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).addKartu(GameState.getInstance().getToko().jualProduk(temp, 1), 6 - GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).getDeckAktif().size());
                    banyak++;
                }
            }
            GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).addGulden(-temp.getHarga() * banyak);
            dialogStage.close();
        });

        numberLabel.setPadding(new Insets(0, 0, 0, 0));
        numberSpinner.setPadding(new Insets(0, 0, 0, 0));
        loadButton.setPadding(new Insets(0, 0, 0, 0));

        // Add the gridPane and loadButton to the centerVBox
        centerVBox.getChildren().addAll(gridPane, loadButton);

        // Button for going back, placed at the top-left
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #0000ff; -fx-text-fill: white; -fx-pref-width: 50; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        backButton.setOnAction(e -> dialogStage.close());

        // Create a BorderPane for the layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(backButton);
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(10, 0, 0, 10));

        // Set the central VBox in the center of the BorderPane
        borderPane.setCenter(centerVBox);

        // Add the BorderPane to the main pane
        pane.getChildren().add(borderPane);

        // Create a scene with the pane and set it to the stage
        Scene scene = new Scene(pane, 350, 150);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    public void showJualDialog(ActionEvent event, Kartu kartu) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("JUAL");

        // Create a VBox for the central content
        VBox centerVBox = new VBox();
        centerVBox.setSpacing(10);
        centerVBox.setPadding(new Insets(-15, 30, 20, 30));
        centerVBox.setAlignment(Pos.CENTER);

        // Pane for the dialog with styling
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #4b0d1a; -fx-border-color: #DBCF72; -fx-border-width: 2;");

        Produk selectedKartu = (Produk) kartu;
        Label nameLabel = new Label("Name: " + selectedKartu.getNama());
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14;-fx-translate-x: 100;");
        Label priceLabel = new Label("Price: " + selectedKartu.getHarga());
        priceLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-translate-x: 100;");

        // Load the image
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedKartu.getImage())));
        ImageView imageView = new ImageView(image);
        imageView.setStyle("-fx-translate-x: 100;");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // GridPane for the number input
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 40 0 10 0;");
        gridPane.setAlignment(Pos.CENTER);

        // Button for loading the plugin
        Button loadButton = new Button("JUAL");
        loadButton.setStyle("-fx-background-color: #3b0712; -fx-text-fill: #DBCF72; -fx-pref-width: 50;-fx-pref-height: 10 ; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10; -fx-translate-x: 100; -fx-pref-width: 70");

        loadButton.setPadding(new Insets(0, 0, 0, 0));

        loadButton.setOnAction(e -> {
            int gulden = GameState.getInstance().getToko().beliProduk(selectedKartu, 1);
            GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).addGulden(gulden);
            showResultDialog("Produk berhasil dijual", event);
            GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).removeKartu(selectedKartu);
            dialogStage.close();
        });

        // Add the gridPane and loadButton to the centerVBox
        centerVBox.getChildren().addAll(nameLabel, priceLabel, imageView, loadButton);

        // Button for going back, placed at the top-left
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #3b0712; -fx-text-fill: #DBCF72; -fx-pref-width: 50; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");
        backButton.setOnAction(e -> dialogStage.close());

        // Create a BorderPane for the layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(backButton);
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(10, 0, 0, 10));

        // Set the central VBox in the center of the BorderPane
        borderPane.setCenter(centerVBox);

        // Add the BorderPane to the main pane
        pane.getChildren().add(borderPane);

        // Create a scene with the pane and set it to the stage
        Scene scene = new Scene(pane, 350, 200);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
}
