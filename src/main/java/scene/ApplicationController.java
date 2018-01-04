package scene;

import backtest.Backtest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import learn.GenethicAlgorithmController;

import javax.swing.text.Document;
import java.io.PrintStream;
import java.math.BigDecimal;


public class ApplicationController {

    @FXML
    private TextArea output;
    @FXML
    private TextField iter;
    @FXML
    private TextField ps;
    @FXML
    private TextField pw;
    @FXML
    private TextField populationSize;
    @FXML
    private TextField eliteSize;
    @FXML
    private TextField mutationP;
    @FXML
    private TextField crossP;
    @FXML
    private CheckBox searchForPsPw;
    @FXML
    private Button start;

    private GenethicAlgorithmController controller;
    private GenethicAlgorithmController testController;

    @FXML
    private void initialize() {
        output.setEditable(false);
    }

    @FXML
    private void startAlgorithm() {
        block();
        try {
            validateAndSet();
        } catch (NumberFormatException e) {
            output.setText("Insert proper values");
//            e.printStackTrace();
            unblock();
            return;
        }

        output.setText("Algorithm started, please wait for end\n");
        Platform.runLater(new Runnable() {
            public void run() {
                int normal;
                int test;
                if (searchForPsPw.isSelected()) {
                    normal = controller.performExtendedGenethicAlgorithm();
                    test = testController.performExtendedGenethicAlgorithm();
                } else {
                    normal = controller.performGenethicAlgorithm();
                    test = testController.performGenethicAlgorithm();
                }
                output.appendText("E from normal data: " + normal + "\n");
                output.appendText("E from test data: " + test + "\n");
                output.appendText("Algorithm ended\n");
            }
        });
        unblock();
    }

    public void setGaController(GenethicAlgorithmController controller) {
        this.controller = controller;
        this.controller.setOutput(output);
    }

    public void setTestGaController(GenethicAlgorithmController controller) {
        this.testController = controller;
        this.testController.setOutput(output);
    }


    private void block() {
        start.setDisable(true);
        iter.setDisable(true);
        ps.setDisable(true);
        pw.setDisable(true);
        searchForPsPw.setDisable(true);

    }

    private void unblock() {
        start.setDisable(false);
        iter.setDisable(false);
        ps.setDisable(false);
        pw.setDisable(false);
        searchForPsPw.setDisable(false);
    }

    private void validateAndSet() throws NumberFormatException {
        if (!searchForPsPw.isSelected()) {
            if (Double.valueOf(ps.getText()) < 0 || Double.valueOf(pw.getText()) < 0)
                throw new NumberFormatException();
        }
        if (Integer.valueOf(iter.getText()) < 0)
            throw new NumberFormatException();
        if (Integer.valueOf(populationSize.getText()) < 0)
            throw new NumberFormatException();
        if (Integer.valueOf(eliteSize.getText()) < 0)
            throw new NumberFormatException();

        if (Double.valueOf(mutationP.getText()) < 0 || Double.valueOf(mutationP.getText()) > 1)
            throw new NumberFormatException();
        if (Double.valueOf(crossP.getText()) < 0 || Double.valueOf(crossP.getText()) > 1)
            throw new NumberFormatException();

        if (!searchForPsPw.isSelected()) {
            controller.getBacktest().setPs(BigDecimal.valueOf(Double.valueOf(ps.getText()) / 100d));
            controller.getBacktest().setPw(BigDecimal.valueOf(Double.valueOf(pw.getText()) / 100d));
            testController.getBacktest().setPs(BigDecimal.valueOf(Double.valueOf(ps.getText()) / 100d));
            testController.getBacktest().setPw(BigDecimal.valueOf(Double.valueOf(pw.getText()) / 100d));
        }
        controller.setMAX_ITERATIONS(Integer.valueOf(iter.getText()));
        controller.setPOPULATION_SIZE(Integer.valueOf(populationSize.getText()));
        controller.setELITE_CREATURES_NUMBER(Integer.valueOf(eliteSize.getText()));
        controller.setMUTATION_PROPABILITY(Double.valueOf(mutationP.getText()));
        controller.setCROSS_PROPABILITY(Double.valueOf(crossP.getText()));
        testController.setMAX_ITERATIONS(Integer.valueOf(iter.getText()));
        testController.setPOPULATION_SIZE(Integer.valueOf(populationSize.getText()));
        testController.setELITE_CREATURES_NUMBER(Integer.valueOf(eliteSize.getText()));
        testController.setMUTATION_PROPABILITY(Double.valueOf(mutationP.getText()));
        testController.setCROSS_PROPABILITY(Double.valueOf(crossP.getText()));

    }
}
