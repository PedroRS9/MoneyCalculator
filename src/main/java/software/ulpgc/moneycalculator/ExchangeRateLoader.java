package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
