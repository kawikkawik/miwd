package data;

import java.math.BigDecimal;
import java.util.List;

public interface DataWriter {
    void writeCsv(List<BigDecimal> toWrite, String name, String kind);
}
