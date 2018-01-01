package data;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface DataProvider {
    List<BigDecimal> getTrainigSet();
    List<BigDecimal> getTestSet();
}
