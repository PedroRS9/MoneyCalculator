package software.ulpgc.moneycalculator.fixerapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;


public class FixerExchangeLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return new ExchangeRate(from, to, LocalDate.now(), downloadRate(from,to));
    }

    private double downloadRate(Currency from, Currency to) {
        try(InputStream is = new URL(FixerConfig.ExchangeRateUrl
                .replace(":API_KEY:", FixerConfig.API_KEY)
                .replace(":FROM:", from.code())
                .replace(":TO:", to.code())
        ).openStream()) {
            String json = new String(is.readAllBytes());
            JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
            return jsonObject.getAsJsonObject("rates").get(to.code()).getAsDouble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
