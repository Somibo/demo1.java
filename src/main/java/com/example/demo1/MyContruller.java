package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MyContruller implements Initializable {


    private static GasStation gasStation = new GasStation();
    public void setGasStation(GasStation gasStation) {
        this.gasStation = gasStation;
    }

    @FXML
    private  TextArea transactionHistoryText;

    public static TextArea textArea;

    @FXML
    private Label prise;

    public static Label totalPrice;
    public static String type;

    public static String name_client;

    public static String liters_client;


    @FXML
    private TextField name;

    @FXML
    private TextField liters;

    @FXML
    private Button myButton13;

    @FXML
    private Button myButton23;

    @FXML
    void transactions(ActionEvent event) {

        actibioButtonClicked(event);
        textArea.setText(gasStation.printTransactions());


    }


    @FXML
    void Union(ActionEvent event) {
        actibioButtonClicked(event);
        // Получить выбранный тип топлива
        Fuel selectedFuel = new Fuel(type);
        double priceperlitter = selectedFuel.getPricePerLiter();
        String oil = selectedFuel.getType();
        String customerName = name_client;
        double fuelLiters = Double.parseDouble(liters_client);

        // Вызвать метод refuel класса GasStation
        gasStation.refuel(customerName, oil, priceperlitter, fuelLiters);
        setGasStation(gasStation);
    }



    @FXML
    void textField1(ActionEvent event) {
        System.out.println(name.getText().trim());
        name_client = name.getText().trim();
        actibioButtonClicked(event);
    }

    @FXML
    void buttonText1(ActionEvent event) {
        String buttonText = myButton13.getText();
        type=buttonText;
        System.out.println(buttonText);
        actibioButtonClicked(event);
    }


    @FXML
    void buttonText2(ActionEvent event) {
        String buttonText = myButton23.getText();
        type=buttonText;
        System.out.println(buttonText);
        actibioButtonClicked(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalPrice=prise;
        textArea=transactionHistoryText;

    }
    @FXML
    void textField2(ActionEvent event) {
        System.out.println(liters.getText().trim());
        liters_client=liters.getText().trim();
        actibioButtonClicked(event);
        Fuel selectedFuel = new Fuel(type);
        double priceperlitter = selectedFuel.getPricePerLiter();
        double fuelLiters = Double.parseDouble(liters_client);
        totalPrice.setText("цена: "+ priceperlitter * fuelLiters);

    }


    @FXML
    void actibioButtonClicked(ActionEvent event) {
        try {
            // Получаем информацию из userData
            Button clickedButton = (Button) event.getSource();

            // Извлекаем подстроку из id для определения типа кнопки
            String buttonId = clickedButton.getId();

            // Проверяем, если в конце написано "main"
            if (buttonId.endsWith("main")) {
                // Если "main", загружаем main.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) clickedButton.getScene().getWindow();

                // Устанавливаем новую сцену
                stage.setScene(new Scene(root));
            } else {
                // Иначе, предполагаем, что в конце цифра и загружаем соответствующий файл
                int digit = Integer.parseInt(buttonId.substring(buttonId.length() - 1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource(digit + ".fxml"));
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) clickedButton.getScene().getWindow();

                // Устанавливаем новую сцену
                stage.setScene(new Scene(root));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


}