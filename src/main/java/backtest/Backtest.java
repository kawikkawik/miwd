package backtest;

import java.math.BigDecimal;

public interface Backtest {
    BigDecimal getValueOfProfit(Integer E);
    public void setPs(BigDecimal ps);
    public void setPw(BigDecimal pw);
}
