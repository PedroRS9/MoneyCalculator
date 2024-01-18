package software.ulpgc.moneycalculator.fixerapi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.CurrencyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class FixerCurrencyLoader implements CurrencyLoader {

    @Override
    public List<Currency> load() {
        try {
            return load(readCurrencies());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> load(String json) {
        return load(symbolsIn(json));
    }

    private List<Currency> load(Map<String, JsonElement> symbols) {
        return symbols.keySet().stream()
                .map(k -> toCurrency(k, symbols.get(k).getAsString()))
                .collect(toList());
    }

    private static Map<String, JsonElement> symbolsIn(String json) {
        return new Gson()
                .fromJson(json, JsonObject.class)
                .get("symbols")
                .getAsJsonObject()
                .asMap();
    }

    private Currency toCurrency(String code, String name) {
        return new Currency(code, name);
    }

    private String readCurrencies() throws IOException {
        try (InputStream is = new URL(FixerConfig.CurrencyUrl
                .replace(":API_KEY:", FixerConfig.API_KEY)
                )
                .openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
