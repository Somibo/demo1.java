package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 379, 610);
        stage.setResizable(false);
        stage.setTitle("ЗАПРАВКА");
        stage.setScene(scene);
        InputStream iconStream = getClass().getResourceAsStream("/images/1.png");
        stage.getIcons().add(new Image(iconStream));

        stage.show();
    }

}
class Fuel implements Serializable {
    private String type;
    private double pricePerLiter;

    public Fuel(String type) {
        this.type = type;
        if ("бензин".equalsIgnoreCase(type)) {
            pricePerLiter = 2.5;
        } else {
            pricePerLiter = 2.0;
        }
    }

    public String getType() {
        return type;
    }

    public double getPricePerLiter() {
        return pricePerLiter;
    }
}

class Transaction implements Serializable {
    private String customerName;
    private String fuel;
    private double liters;
    private double totalPrice;

    public Transaction(String customerName, String fuel, double Price, double liters) {
        this.customerName = customerName;
        this.fuel = fuel;
        this.liters = liters;
        this.totalPrice = Price * liters;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getFuel() {
        return fuel;
    }

    public double getLiters() {
        return liters;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

class GasStation implements Serializable {
    private List<Transaction> transactions;

    public GasStation() {
        this.transactions = new ArrayList<>();
    }

    public void refuel(String customerName, String namefuel, double priceperlitter, double liters) {
        transactions.add(new Transaction(customerName, namefuel, priceperlitter, liters));
        System.out.println(customerName);
        System.out.println(namefuel);
        System.out.println(liters);
        System.out.println("Заправка прошла успешно. Итоговая цена: " + priceperlitter * liters);
    }
    public String printTransactions() {
        StringBuilder historyText = new StringBuilder("История транзакций:\n");
        for (Transaction transaction : transactions) {
            historyText.append("Клиент: ").append(transaction.getCustomerName())
                    .append(", Тип топлива: ").append(transaction.getFuel())
                    .append(", Литры: ").append(transaction.getLiters())
                    .append(", Итоговая цена: ").append(transaction.getTotalPrice())
                    .append("\n");
        }
        return historyText.toString();
    }

}

