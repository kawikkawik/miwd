package data;

import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class HistoricalDataProvider implements DataProvider {

    private static final String APPLE_STOCK = "AAPL";
    private static final String RAD_STOCK = "RAD";
    private static final String ORCL_STOCK = "ORCL";
    private static final String BAC_STOCK = "BAC";
    private static final String CMCSA_STOCK = "CMCSA";
    private static final String AMD_STOCK = "AMD";
    private static final String RAI_STOCK = "RAI";
    private static final String GE_STOCK = "GE";
    private static final Calendar trainingFrom = new GregorianCalendar(2014, 1, 1);
    private static final Calendar testFrom = new GregorianCalendar(2016, 1, 1);
    private static final Calendar trainingTo = new GregorianCalendar(2016, 1, 1);
    private static final Calendar testTo = new GregorianCalendar(2017, 1, 1);
    private static final String[] stocks = {APPLE_STOCK,
            RAD_STOCK, ORCL_STOCK, BAC_STOCK, CMCSA_STOCK,
//            AMD_STOCK, RAI_STOCK, GE_STOCK
    };

    private static final DataWriter dataWriter = new DataWriterImpl();

    public List<BigDecimal> getTrainigSet() {
        List<BigDecimal> trainingSet = new LinkedList<BigDecimal>();
        for (String stock : stocks) {
            try {
                List<HistoricalQuote> historicalQuotes = YahooFinance.get(stock).getHistory(trainingFrom, trainingTo, Interval.DAILY);
                List<BigDecimal> toAdd = new LinkedList<BigDecimal>();
                for (HistoricalQuote quote : historicalQuotes) {
                    if (quote.getClose() != null) {
                        toAdd.add(quote.getClose());
                    }
                }
                dataWriter.writeCsv(toAdd, stock, "train");
                trainingSet.addAll(toAdd);
            } catch (IOException e) {
            }
        }


        return trainingSet;
    }

    public List<BigDecimal> getTestSet() {
        List<BigDecimal> testSet = new LinkedList<BigDecimal>();
        for (String stock : stocks) {
            try {
                List<BigDecimal> toAdd = new LinkedList<BigDecimal>();
                List<HistoricalQuote> historicalQuotes = YahooFinance.get(stock).getHistory(testFrom, testTo, Interval.DAILY);
                for (HistoricalQuote quote : historicalQuotes) {
                    if (quote.getClose() != null) {
                        toAdd.add(quote.getClose());
                    }
                }
                dataWriter.writeCsv(toAdd, stock, "test");
                testSet.addAll(toAdd);
            } catch (IOException e) {
            }
        }


        return testSet;
    }
}
