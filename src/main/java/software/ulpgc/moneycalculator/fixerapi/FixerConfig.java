package software.ulpgc.moneycalculator.fixerapi;

public class FixerConfig {
    protected final static String API_KEY = "f19d70262659d34a6578cc3c4f32566c";
    protected final static String CurrencyUrl = "http://data.fixer.io/api/symbols?access_key=:API_KEY:";
    protected final static String ExchangeRateUrl = "http://data.fixer.io/api/latest?access_key=:API_KEY:&base=:FROM:&symbols=:TO:";

}
