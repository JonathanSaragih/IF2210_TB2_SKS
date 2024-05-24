package jojikanabe.if2210_tb2_sks;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    public void showSaveGameDialog(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("Save Game");

        VBox vbox = new VBox();
        vbox.setLayoutX(50);
        vbox.setLayoutY(20);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #564457; -fx-border-color: yellow; -fx-border-width: 2;");

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

        Button loadButton = new Button("SAVE");
        loadButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 300; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
                GameState.getInstance().LoadGame();
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

        Button loadButton = new Button("LOAD STATE");
        loadButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 300; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
                GameState.getInstance().LoadGame();
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

        Button loadButton = new Button("LOAD PLUGIN");
        loadButton.setStyle("-fx-background-color: #1C2045; -fx-text-fill: #DBCF72; -fx-pref-width: 300; -fx-border-color: #DBCF72; -fx-border-width: 2; -fx-background-radius: 10;");

        loadButton.setOnAction(e -> {
            String format = formatChoiceBox.getValue();
            String folder = folderTextField.getText();
            try {
                GameState.getInstance().LoadGame();
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
}
