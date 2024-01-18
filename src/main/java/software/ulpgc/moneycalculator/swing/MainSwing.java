package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.*;
import software.ulpgc.moneycalculator.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.fixerapi.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.fixerapi.FixerExchangeLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.swing.MainFrame;

import java.util.List;

public class MainSwing {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        CurrencyLoader currencyLoader = new FixerCurrencyLoader();
        List<Currency> currencies = currencyLoader.load();
        ExchangeRateLoader exchangeRateLoader = new FixerExchangeLoader();
        MoneyDialog moneyDialog = frame.getMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = frame.getCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = frame.getMoneyDisplay();
        frame.add("exchange", new ExchangeMoneyCommand(moneyDialog, currencyDialog, exchangeRateLoader, moneyDisplay));
        frame.setVisible(true);
    }
}
