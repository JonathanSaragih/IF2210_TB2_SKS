package jojikanabe.if2210_tb2_sks;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ButtonType;

public class HelloController {
    public void showTokoDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Catalog");
        alert.setHeaderText(null);
        alert.setContentText(null);
        alert.getDialogPane().getButtonTypes().remove(ButtonType.OK);

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

        // Add some elements to the GridPane
        Button button1 = new Button("");
        button1.setPrefWidth(140); // Set the width of the button
        button1.setPrefHeight(100); // Set the height of the button
        button1.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        Button button2 = new Button("");
        button2.setPrefWidth(140); // Set the width of the button
        button2.setPrefHeight(100); // Set the height of the button
        button2.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        Button button3 = new Button("");
        button3.setPrefWidth(140); // Set the width of the button
        button3.setPrefHeight(100); // Set the height of the button
        button3.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        // Add the buttons to the GridPane at the desired column indices
        grid.add(button1, 0, 0); // Add Button 1 to column 0, row 0
        grid.add(button2, 1, 0); // Add Button 2 to column 1, row 0
        grid.add(button3, 2, 0); // Add Button 3 to column 2, row 0

        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setValignment(button1, VPos.CENTER);

        GridPane.setHalignment(button2, HPos.CENTER);
        GridPane.setValignment(button2, VPos.CENTER);

        GridPane.setHalignment(button3, HPos.CENTER);
        GridPane.setValignment(button3, VPos.CENTER);

        vbox.getChildren().add(grid);

        Image image2 = new Image(getClass().getResourceAsStream("/assets/etalase.png"));
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(500);
        imageView2.setFitHeight(20);
        vbox.getChildren().add(imageView2);

        GridPane grid2 = new GridPane();

        // Define three column constraints
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33.33);
            grid2.getColumnConstraints().add(column);
        }

        // Add some elements to the GridPane
        Button button4 = new Button("");
        button4.setPrefWidth(140); // Set the width of the button
        button4.setPrefHeight(100); // Set the height of the button
        button4.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        Button button5 = new Button("");
        button5.setPrefWidth(140); // Set the width of the button
        button5.setPrefHeight(100); // Set the height of the button
        button5.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        Button button6 = new Button("");
        button6.setPrefWidth(140); // Set the width of the button
        button6.setPrefHeight(100); // Set the height of the button
        button6.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        // Add the buttons to the GridPane at the desired column indices
        grid2.add(button4, 0, 0); // Add Button 1 to column 0, row 0
        grid2.add(button5, 1, 0); // Add Button 2 to column 1, row 0
        grid2.add(button6, 2, 0); // Add Button 3 to column 2, row 0

        GridPane.setHalignment(button4, HPos.CENTER);
        GridPane.setValignment(button4, VPos.CENTER);

        GridPane.setHalignment(button5, HPos.CENTER);
        GridPane.setValignment(button5, VPos.CENTER);

        GridPane.setHalignment(button6, HPos.CENTER);
        GridPane.setValignment(button6, VPos.CENTER);

        vbox.getChildren().add(grid2);

        Image image3 = new Image(getClass().getResourceAsStream("/assets/etalase.png"));
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(500);
        imageView3.setFitHeight(20);
        vbox.getChildren().add(imageView3);

        GridPane grid3 = new GridPane();

        // Define three column constraints
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33.33);
            grid3.getColumnConstraints().add(column);
        }

        // Add some elements to the GridPane
        Button button7 = new Button("");
        button7.setPrefWidth(140); // Set the width of the button
        button7.setPrefHeight(100); // Set the height of the button
        button7.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        Button button8 = new Button("");
        button8.setPrefWidth(140); // Set the width of the button
        button8.setPrefHeight(100); // Set the height of the button
        button8.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        Button button9 = new Button("");
        button9.setPrefWidth(140); // Set the width of the button
        button9.setPrefHeight(100); // Set the height of the button
        button9.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; -fx-border-width: 0;");

        // Add the buttons to the GridPane at the desired column indices
        grid3.add(button7, 0, 0); // Add Button 1 to column 0, row 0
        grid3.add(button8, 1, 0); // Add Button 2 to column 1, row 0
        grid3.add(button9, 2, 0); // Add Button 3 to column 2, row 0

        GridPane.setHalignment(button7, HPos.CENTER);
        GridPane.setValignment(button7, VPos.CENTER);

        GridPane.setHalignment(button8, HPos.CENTER);
        GridPane.setValignment(button8, VPos.CENTER);

        GridPane.setHalignment(button9, HPos.CENTER);
        GridPane.setValignment(button9, VPos.CENTER);

        vbox.getChildren().add(grid3);

        Image image4 = new Image(getClass().getResourceAsStream("/assets/etalase.png"));
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(500);
        imageView4.setFitHeight(20);
        vbox.getChildren().add(imageView4);

        pane.getChildren().add(vbox);

        alert.getDialogPane().setGraphic(pane);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefSize(500, 495);
        dialogPane.setStyle("-fx-background-color: #D0D0D0;");

        alert.showAndWait();
    }
}