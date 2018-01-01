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
    private static final Calendar trainingFrom = new GregorianCalendar(2015, 1, 1);
    private static final Calendar testFrom = new GregorianCalendar(2014, 1, 1);
    private static final Calendar trainingTo = new GregorianCalendar(2016, 1, 1);
    private static final Calendar testTo = new GregorianCalendar(2015, 1, 1);

    public List<BigDecimal> getTrainigSet() {
        List<HistoricalQuote> historicalQuotes = null;
        try {
            historicalQuotes = YahooFinance.get(APPLE_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY);
        } catch (IOException e) {
        }
        try {
            historicalQuotes.addAll(YahooFinance.get(RAD_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY));
        } catch (IOException e) {
        }
        try {
            historicalQuotes.addAll(YahooFinance.get(ORCL_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY));
        } catch (IOException e) {
        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(BAC_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY));
//        } catch (IOException e) {
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(CMCSA_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY));
//        } catch (IOException e) {
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(AMD_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY));
//        } catch (IOException e) {
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(RAI_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY));
//        } catch (IOException e) {
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(GE_STOCK).getHistory(trainingFrom, trainingTo, Interval.DAILY));
//        } catch (IOException e) {
//        }
        List<BigDecimal> trainingSet = new LinkedList<BigDecimal>();

        for (HistoricalQuote quote : historicalQuotes) {
            if (quote.getClose() != null) {
                trainingSet.add(quote.getClose());
            }
        }

        return trainingSet;
    }

    public List<BigDecimal> getTestSet() {
        List<HistoricalQuote> historicalQuotes = null;
        try {
            historicalQuotes = YahooFinance.get(APPLE_STOCK).getHistory(testFrom, testTo, Interval.DAILY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            historicalQuotes.addAll(YahooFinance.get(RAD_STOCK).getHistory(testFrom, testTo, Interval.DAILY));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            historicalQuotes.addAll(YahooFinance.get(ORCL_STOCK).getHistory(testFrom, testTo, Interval.DAILY));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(BAC_STOCK).getHistory(testFrom, testTo, Interval.DAILY));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(CMCSA_STOCK).getHistory(testFrom, testTo, Interval.DAILY));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(AMD_STOCK).getHistory(testFrom, testTo, Interval.DAILY));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(RAI_STOCK).getHistory(testFrom, testTo, Interval.DAILY));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            historicalQuotes.addAll(YahooFinance.get(GE_STOCK).getHistory(testFrom, testTo, Interval.DAILY));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        List<BigDecimal> testSet = new LinkedList<BigDecimal>();

        for (HistoricalQuote quote : historicalQuotes) {
            if (quote.getClose() != null) {
                testSet.add(quote.getClose());
            }
        }

        return testSet;
    }
}
