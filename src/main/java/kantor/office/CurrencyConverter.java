package kantor.office;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static kantor.library.Constans.*;

public class CurrencyConverter {

    public static final Logger LOGGER = Logger.getLogger(CurrencyConverter.class.getName());


    public static BigDecimal convert(Currency from, Currency to) {
        try {
            URL url = new URL(CURRENCY_SERVICE
                    + from.getName()
                    + NODE
                    + to.getName()
                    + INFO_TYPE
                    + API_KEY
            );
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            LOGGER.log(Level.INFO, "reader get line: " + line);

            if (!StringUtils.isBlank(line)) {
                int startIndex = line.indexOf(":") +1;
                int endIndex = line.indexOf("}");
                String value = line.substring(startIndex, endIndex);
                return new BigDecimal(value);
            }
            reader.close();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return new BigDecimal(0);
    }
}
