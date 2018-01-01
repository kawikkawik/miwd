package run;

import backtest.Backtest;
import backtest.BacktestResolver;
import data.DataProvider;
import data.HistoricalDataProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import learn.GAControllerImpl;
import learn.GenethicAlgorithmController;
import scene.ApplicationController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class AplicationRunner extends Application {

    private GenethicAlgorithmController gaController;
    private GenethicAlgorithmController gaControllerTest;

    public void init() {
        DataProvider dataProvider = new HistoricalDataProvider();
        List<BigDecimal> training = dataProvider.getTrainigSet();
        List<BigDecimal> test = dataProvider.getTestSet();
        Backtest backtest = new BacktestResolver(training);
        Backtest forTest = new BacktestResolver(test);

        gaController = new GAControllerImpl();
        gaControllerTest = new GAControllerImpl();
        gaController.setBacktest(backtest);
        gaControllerTest.setBacktest(forTest);

    }

    public void run() {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/page.fxml"));
        Pane mainScene = null;
        try {
            mainScene = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApplicationController controller = loader.getController();
        controller.setGaController(gaController);
        controller.setTestGaController(gaControllerTest);
        primaryStage.setResizable(false);
        primaryStage.setTitle("MIWD");
        primaryStage.setScene(new Scene(mainScene));
        primaryStage.show();
    }
}
