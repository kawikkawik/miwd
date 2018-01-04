package learn;

import backtest.Backtest;
import javafx.scene.control.TextArea;


import java.util.Random;

public class GAControllerImpl implements GenethicAlgorithmController {

    private int MAX_ITERATIONS = 100;
    private int ELITE_CREATURES_NUMBER = 5;
    private int POPULATION_SIZE = 200;
    private double MUTATION_PROPABILITY = 0.1;
    private double CROSS_PROPABILITY = 0.7;

    private Backtest backtest;
    private TextArea output;

    public int performGenethicAlgorithm() {

        Random random = new Random();
        CreaturesPopulation population = new CreaturesPopulation(backtest, POPULATION_SIZE + ELITE_CREATURES_NUMBER, ELITE_CREATURES_NUMBER, MUTATION_PROPABILITY, CROSS_PROPABILITY);

        Creature[] creatures = new Creature[population.getPOPULATION_SIZE()];
        Creature[] selectedCreatures = new Creature[2];

        output.appendText("Amount on Beginning: " + population.getTotalAmount() + "\n");
        output.appendText("Best one: " + population.findBest().getBacktestValue() + "\n");


        for (int index = 0; index < MAX_ITERATIONS; index++) {
            int count = 0;

            for (int i = 0; i < population.getELITE_CREATURES_NUMBER(); ++i, count++) {
                creatures[count] = population.findBest();
            }

            while (count < population.getPOPULATION_SIZE()) {
                selectedCreatures[0] = population.getRandomCreature();
                selectedCreatures[1] = population.getRandomCreature();

                if (random.nextDouble() < population.getCROSS_PROPABILITY()) {
                    selectedCreatures = Util.crossOver(selectedCreatures[0], selectedCreatures[1], backtest);
                }
                if (random.nextDouble() < population.getMUTATION_PROPABILITY()) {
                    selectedCreatures[0].serialMutate();
                }
                if (random.nextDouble() < population.getMUTATION_PROPABILITY()) {
                    selectedCreatures[1].serialMutate();
                }

                creatures[count++] = selectedCreatures[0];
                creatures[count++] = selectedCreatures[1];
            }

            population.setPopulation(creatures);
            population.evaluate();
            output.appendText(index + ": Amount: " + population.getTotalAmount() + "\n");
            output.appendText(index + ": Best one: " + population.findBest().getBacktestValue() + "\n");
            output.appendText(index + ": E: " + population.findBest().getE() + "\n");
        }
        return population.findBest().getE();
    }

    public int performExtendedGenethicAlgorithm() {
        Random random = new Random();
        ExtendedPopulation population = new ExtendedPopulation(backtest, POPULATION_SIZE + ELITE_CREATURES_NUMBER, ELITE_CREATURES_NUMBER, MUTATION_PROPABILITY, CROSS_PROPABILITY);

        ExtendedCreature[] creatures = new ExtendedCreature[population.getPOPULATION_SIZE()];
        ExtendedCreature[] selectedCreatures = new ExtendedCreature[2];

        output.appendText("Amount on Beginning: " + population.getTotalAmount() + "\n");
        output.appendText("Best one: " + population.findBest().getBacktestValue() + "\n");

        int count;
        for (int index = 0; index < MAX_ITERATIONS; index++) {
            count = 0;

            for (int i = 0; i < population.getELITE_CREATURES_NUMBER(); ++i, count++) {
                creatures[count] = population.findBest();
            }

            while (count < population.getPOPULATION_SIZE()) {
                selectedCreatures[0] = population.getRandomCreature();
                selectedCreatures[1] = population.getRandomCreature();

                if (random.nextDouble() < population.getCROSS_PROPABILITY()) {
                    selectedCreatures = Util.crossOver(selectedCreatures[0], selectedCreatures[1], backtest);
                }
                if (random.nextDouble() < population.getMUTATION_PROPABILITY()) {
                    selectedCreatures[0].serialMutate();
                }
                if (random.nextDouble() < population.getMUTATION_PROPABILITY()) {
                    selectedCreatures[1].serialMutate();
                }

                creatures[count++] = selectedCreatures[0];
                creatures[count++] = selectedCreatures[1];
            }

            population.setExtendedPopulation((ExtendedCreature[]) creatures);
            population.evaluate();
            output.appendText(index + ": Amount: " + population.getTotalAmount() + "\n");
            output.appendText(index + ": Best one: " + population.findBest().getBacktestValue() + "\n");
            output.appendText(index + population.findBest().toString());

        }
        return population.findBest().getE();
    }

    public void setBacktest(Backtest backtest) {
        this.backtest = backtest;
    }

    public void setOutput(TextArea output) {
        this.output = output;
    }

    public void setMAX_ITERATIONS(int MAX_ITERATIONS) {
        this.MAX_ITERATIONS = MAX_ITERATIONS;
    }

    public void setELITE_CREATURES_NUMBER(int ELITE_CREATURES_NUMBER) {
        this.ELITE_CREATURES_NUMBER = ELITE_CREATURES_NUMBER;
    }

    public void setPOPULATION_SIZE(int POPULATION_SIZE) {
        this.POPULATION_SIZE = POPULATION_SIZE;
    }

    public void setMUTATION_PROPABILITY(double MUTATION_PROPABILITY) {
        this.MUTATION_PROPABILITY = MUTATION_PROPABILITY;
    }

    public void setCROSS_PROPABILITY(double CROSS_PROPABILITY) {
        this.CROSS_PROPABILITY = CROSS_PROPABILITY;
    }

    public Backtest getBacktest() {
        return backtest;
    }
}
