package backtest;

import java.math.BigDecimal;

public interface Backtest {
    BigDecimal getValueOfProfit(Integer E);
    BigDecimal getValueOfProfit(Integer E, double ps, double pw);
    public void setPs(BigDecimal ps);
    public void setPw(BigDecimal pw);
}
