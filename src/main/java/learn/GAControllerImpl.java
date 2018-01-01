package learn;

import backtest.Backtest;
import javafx.scene.control.TextArea;


import java.util.Random;

public class GAControllerImpl implements GenethicAlgorithmController {

    private int MAX_ITERATIONS = 100;

    private Backtest backtest;
    private TextArea output;

    public int performGenethicAlgorithm() {



        Random random = new Random();
        CreaturesPopulation population = new CreaturesPopulation(backtest);

        Creature[] creatures = new Creature[CreaturesPopulation.POPULATION_SIZE];
        Creature[] selectedCreatures = new Creature[2];

        output.appendText("Amount on Beginning: " + population.getTotalAmount() + "\n");
        output.appendText("Best one: " + population.findBest().getBacktestValue() + "\n");

        int count;
        for (int index = 0; index < MAX_ITERATIONS; index++) {
            count = 0;

            for (int i = 0; i < CreaturesPopulation.ELITE_CREATURES_NUMBER; ++i, count++) {
                creatures[count] = population.findBest();
            }

            while(count < CreaturesPopulation.POPULATION_SIZE){
                selectedCreatures[0] = population.getRandomCreature();
                selectedCreatures[1] = population.getRandomCreature();

                if(random.nextDouble() < CreaturesPopulation.CROSS_PROPABILITY){
                    selectedCreatures = Util.crossOver(selectedCreatures[0], selectedCreatures[1], backtest);
                }
                if(random.nextDouble() < CreaturesPopulation.MUTATION_PROPABILITY){
                    selectedCreatures[0].serialMutate();
                }
                if(random.nextDouble() < CreaturesPopulation.MUTATION_PROPABILITY){
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

    public void setBacktest(Backtest backtest) {
        this.backtest = backtest;
    }

    public void setOutput(TextArea output) {
        this.output = output;
    }

    public void setMAX_ITERATIONS(int MAX_ITERATIONS) {
        this.MAX_ITERATIONS = MAX_ITERATIONS;
    }

    public Backtest getBacktest() {
        return backtest;
    }
}
