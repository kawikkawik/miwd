package backtest;

import java.math.BigDecimal;
import java.util.List;

public class BacktestResolver implements Backtest {

    private List<BigDecimal> historyList;
    private BigDecimal Ps = new BigDecimal(0.05d);
    private BigDecimal Pw = new BigDecimal(0.2d);

    public BacktestResolver(List<BigDecimal> historyList) {
        this.historyList = historyList;
    }

    public BigDecimal getValueOfProfit(Integer E) {
        boolean isBuyed = false;
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < historyList.size(); i++) {
            if (i > E) {
                BigDecimal ratio = historyList.get(i).divide(historyList.get(i - E), BigDecimal.ROUND_DOWN);
                if ((BigDecimal.valueOf(1L).subtract(ratio)).compareTo(Ps) > 0 && isBuyed) {
                    //sell
                    result = result.subtract(historyList.get(i));
                    isBuyed = false;
                }
                if ((ratio.subtract(BigDecimal.valueOf(1L))).compareTo(Pw) > 0 && !isBuyed) {
                    //buy
                    result = result.add(historyList.get(i));
                    isBuyed = true;
                }
            }
        }

        return result;
    }

    public BigDecimal getValueOfProfit(Integer E, double ps, double pw) {
        Ps = BigDecimal.valueOf(ps);
        Pw = BigDecimal.valueOf(pw);
        return getValueOfProfit(E);
    }

    public void setPs(BigDecimal ps) {
        Ps = ps;
    }

    public void setPw(BigDecimal pw) {
        Pw = pw;
    }
}
