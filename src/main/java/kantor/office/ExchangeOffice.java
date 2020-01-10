package kantor.office;

import kantor.model.CurrencyApplicationModel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static kantor.library.Constans.MARGIN_VALUE;

public class ExchangeOffice {
    public static final Logger LOGGER = Logger.getLogger(ExchangeOffice.class.getName());
    private Map<Currency, BigDecimal> availableFounds;

    public ExchangeOffice(double startFounds) {
        this.availableFounds = new HashMap<>();
        for (Currency c : Currency.values()) {
            this.availableFounds.put(c, new BigDecimal(startFounds));
        }
    }

    //todo addMoney?!
    public ExchangeOffice(Currency currency, double startFounds) {
        this.availableFounds = new HashMap<>();
        this.availableFounds.put(currency, new BigDecimal(startFounds));
    }

    public void showFounds() {
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        for (Map.Entry<Currency, BigDecimal> entry : availableFounds.entrySet()) {
            LOGGER.info("Waluta: " + decimalFormat.format(entry.getValue() + " " + entry.getKey().getName() + "\n"));
        }
    }

    public CurrencyApplicationModel exchange(BigDecimal amount, Currency from, Currency to) {
        BigDecimal available = availableFounds.get(to);
        BigDecimal course = CurrencyConverter.convert(from, to);
        BigDecimal margineValue = new BigDecimal(MARGIN_VALUE);
        BigDecimal needed = amount.multiply(margineValue).multiply(course);

        DecimalFormat decimalFormat = new DecimalFormat("##.00");

        if (available.intValue() >= needed.intValue()) {
            availableFounds.put(from, availableFounds.get(from).add(amount));
            availableFounds.put(to, availableFounds.get(to).add(needed));

            LOGGER.info(
                    "Exchanging"
                            + decimalFormat.format(amount) + " "
                            + from.getName()
                            + " to"
                            + decimalFormat.format(needed) + " "
                            + to.getName());
        } else {
            LOGGER.info(
                    "Cannot exchange "
                    + decimalFormat.format(amount) + " to "
                    + from.getName() + " "
                    + decimalFormat.format(needed) + " "
                    +to.getName());
        }
        return new CurrencyApplicationModel.Builder()
                .amount(amount)
                .from(from)
                .to(to)
                .margin(margineValue)
                .rate(course)
                .result(needed)
                .build();
    }
}
