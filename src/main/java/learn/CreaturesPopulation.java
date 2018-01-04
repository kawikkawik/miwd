package learn;

import backtest.Backtest;

import java.util.Random;

public class CreaturesPopulation {
    //ilość istot elitarnych
    protected int ELITE_CREATURES_NUMBER;
    //wielkość populacji
    protected int POPULATION_SIZE;
    //prawdopodobieństwo mutacji
    protected double MUTATION_PROPABILITY;
    //prawdopodobieństwo skrzyżowania
    protected double CROSS_PROPABILITY;
    protected final Random rand = new Random();
    protected Creature[] population;
    protected double totalAmount;

    public CreaturesPopulation() {
    }

    public CreaturesPopulation(Backtest backtest, int populationSize, int eliteCreaturesSize, double mutationP,
                               double crossP) {
        this.ELITE_CREATURES_NUMBER = eliteCreaturesSize;
        this.POPULATION_SIZE = populationSize;
        this.MUTATION_PROPABILITY = mutationP;
        this.CROSS_PROPABILITY = crossP;

        population = new Creature[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population[i] = new Creature(backtest);
        }

        this.evaluate();
    }

    public void setPopulation(Creature[] creatures) {

        for (int i = 0; i < creatures.length; i++) {
            try {
                population[i] = (Creature) creatures[i].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public Creature[] getPopulation() {
        return population;
    }

    public double evaluate() {
        this.totalAmount = 0.0;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            this.totalAmount += population[i].evaluate();
        }
        return this.totalAmount;
    }

    public Creature findBest() {
        int indexMax = 0;
        double currentMax = 0.0;
        double currentVal;

        for (int index = 0; index < POPULATION_SIZE; ++index) {

            currentVal = population[index].getBacktestValue();

            if (currentVal > currentMax) {
                currentMax = currentVal;
                indexMax = index;
            }
        }
        return population[indexMax];
    }

    public Creature getRandomCreature() {
        double randomNumber = rand.nextDouble() * this.totalAmount;
        int index;

        for (index = 0; index < POPULATION_SIZE && randomNumber > 0; index++) {
            randomNumber -= population[index].getBacktestValue();
        }

        return population[index - 1];
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getELITE_CREATURES_NUMBER() {
        return ELITE_CREATURES_NUMBER;
    }

    public int getPOPULATION_SIZE() {
        return POPULATION_SIZE;
    }

    public double getMUTATION_PROPABILITY() {
        return MUTATION_PROPABILITY;
    }

    public double getCROSS_PROPABILITY() {
        return CROSS_PROPABILITY;
    }
}
