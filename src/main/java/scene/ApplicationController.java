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
            if (!searchForPsPw.isSelected()) {
                controller.getBacktest().setPs(BigDecimal.valueOf(Double.valueOf(ps.getText())));
                controller.getBacktest().setPw(BigDecimal.valueOf(Double.valueOf(pw.getText())));
            }
            controller.setMAX_ITERATIONS(Integer.valueOf(iter.getText()));
        } catch (NumberFormatException e) {
            output.setText("Insert proper values");
            return;
        }

        output.setText("Algorithm started, please wait for end\n");
        if (searchForPsPw.isSelected()) {

        } else {
            int normal = controller.performGenethicAlgorithm();
            int test = testController.performGenethicAlgorithm();

            output.appendText("From normal data: " + normal + "\n");
            output.appendText("From test data: " + test + "\n");
        }
        output.appendText("Algorithm ended\n");
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
}
