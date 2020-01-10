package kantor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import kantor.helper.DecimalCustomFormat;
import kantor.model.CurrencyApplicationModel;
import kantor.office.Currency;
import kantor.office.ExchangeOffice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeController {

    @FXML
    public TextField currencyInputTextFild;
    @FXML
    public Button calculateButton;
    @FXML
    public TextArea resultViewTextArea;
    @FXML
    public MenuButton currencyMenuButtonFrom;
    @FXML
    public MenuButton currencyMenuButtonTo;
    @FXML
    public DatePicker datePicker;

    @FXML
    public void initialize() {
        getConfigCurrencyButtons();
    }

    public void getConfigCurrencyButtons() {
        for (Currency c : Currency.values()) {
            currencyMenuButtonFrom.getItems().add(new MenuItem(c.getName()));
            currencyMenuButtonTo.getItems().add(new MenuItem(c.getName()));
        }

        currencyMenuButtonFrom.getItems().forEach(i -> i.setOnAction(a -> {
            currencyMenuButtonFrom.setText(i.getText());
        }));

        currencyMenuButtonTo.getItems().forEach(i -> i.setOnAction(a -> {
            currencyMenuButtonTo.setText(i.getText());
        }));

        datePicker.setValue(LocalDate.now());
    }

    @FXML
    public void performResult() {
        ExchangeOffice office = new ExchangeOffice(1000);
        CurrencyApplicationModel applicationModel = office.exchange(
                new BigDecimal(currencyInputTextFild.getText()),
                Currency.valueOf(currencyMenuButtonFrom.getText()),
                Currency.valueOf(currencyMenuButtonTo.getText())
        );
        applicationModel.setDate(datePicker.getValue());
        resultViewTextArea.setText(
                applicationModel.getDate() + "\n"
                        + "Waluta wymiany: " + applicationModel.getFrom() + "\n"
                        + "Waluta wymiany: " + applicationModel.getTo() + "\n"
                        + "aktualny kurs: " + DecimalCustomFormat.decimalToString(applicationModel.getRate()) + "\n"
                        + "wydaj w " + applicationModel.getTo() + "\n" + "kwotę: "
                        + DecimalCustomFormat.decimalToString(applicationModel.getResult())
        );
    }

    public void calculate() {
        performResult();
    }
}