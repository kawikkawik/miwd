package learn;

import backtest.Backtest;
import javafx.scene.control.TextArea;

public interface GenethicAlgorithmController {
    int performGenethicAlgorithm();

    int performExtendedGenethicAlgorithm();

    void setBacktest(Backtest backtest);

    void setOutput(TextArea output);

    Backtest getBacktest();

    void setMAX_ITERATIONS(int MAX_ITERATIONS);

    void setELITE_CREATURES_NUMBER(int ELITE_CREATURES_NUMBER);

    void setPOPULATION_SIZE(int POPULATION_SIZE);

    void setMUTATION_PROPABILITY(double MUTATION_PROPABILITY);

    void setCROSS_PROPABILITY(double CROSS_PROPABILITY);
}
