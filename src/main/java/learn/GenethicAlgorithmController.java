package learn;

import backtest.Backtest;
import javafx.scene.control.TextArea;

public interface GenethicAlgorithmController {
    int performGenethicAlgorithm();
    void setBacktest(Backtest backtest);
    void setOutput(TextArea output);
    Backtest getBacktest();
    void setMAX_ITERATIONS(int MAX_ITERATIONS);
}
