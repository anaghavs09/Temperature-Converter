package p3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String C_TO_F = "Celsius to Fahrenheit";
    private static final String F_TO_C = "Fahrenheit to Celsius";
    private  boolean isC_TO_F = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Temperature Converter");

        Button button = new Button("CONVERT");
        button.setStyle("-fx-font-weight: bold");

        Label l = new Label("Enter the temperature");
        l.setStyle("-fx-font-weight: bold");

        TextField t = new TextField();
        t.setPromptText("Enter temperature");
        t.setMaxWidth(200);

        ChoiceBox c = new ChoiceBox();
        c.getItems().add(C_TO_F);
        c.getItems().add(F_TO_C);
        c.setValue(C_TO_F);

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.add(c, 1, 3);
        g.add(l, 1, 5);
        g.add(t, 1, 6);
        g.add(button, 1, 8);
        g.setHgap(60);
        g.setVgap(10);


       EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               t.getText();
               convert();
           }

           public void convert() {
               try {

                   float input = Float.parseFloat(t.getText());
                   float result;

                   if (isC_TO_F) {
                       result = (input * 9 / 5) + 32;
                   } else {
                       result = (input - 32) * 5 / 9;
                   }
                   display(result);
               }

               catch (NumberFormatException e) {
                   error();
               }

               catch (Exception e) {
                   System.out.println(e);
               }

           }
       };

       button.setOnAction(event);


        c.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.equals(C_TO_F)) {
                isC_TO_F = true;
            } else {
                isC_TO_F = false;
            }
        });

        Scene scene = new Scene(g, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void error() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Enter a number");
        alert.show();
    }

    private void display(float result) {
        String unit = isC_TO_F ? " F" : " C";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RESULT");
        alert.setContentText("The result is : " + result + unit);
        alert.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}