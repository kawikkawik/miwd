package learn;

import backtest.Backtest;

import java.util.Random;

public class CreaturesPopulation {
    //ilość istot elitarnych
    public static final int ELITE_CREATURES_NUMBER = 5;
    //wielkość populacji
    public static final int POPULATION_SIZE = 200 + ELITE_CREATURES_NUMBER;
    //prawdopodobieństwo mutacji
    public static final double MUTATION_PROPABILITY = 0.6;
    //prawdopodobieństwo skrzyżowania
    public static final double CROSS_PROPABILITY = 0.7;
    private final Random rand = new Random();
    private Creature[] population;
    private double totalAmount;

    public CreaturesPopulation(Backtest backtest){
        population  = new Creature[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++){
            population[i] = new Creature(backtest);
        }

        this.evaluate();
    }

    public void setPopulation(Creature[] creatures){
        System.arraycopy(creatures, 0, population, 0, POPULATION_SIZE);
    }

    public Creature[] getPopulation() {
        return population;
    }

    public double evaluate(){
        this.totalAmount = 0.0;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            this.totalAmount += population[i].evaluate();
        }
        return this.totalAmount;
    }

    public Creature findBest(){
        int indexMax = 0;
        double currentMax = 0.0;
        double currentVal;

        for(int index = 0; index < POPULATION_SIZE; ++index){

            currentVal = population[index].getBacktestValue();

            if (currentVal > currentMax) {
                currentMax = currentVal;
                indexMax = index;
            }
        }
        return population[indexMax];
    }

    public Creature getRandomCreature(){
        double randomNumber = rand.nextDouble() * this.totalAmount;
        int index;

        for(index = 0; index < POPULATION_SIZE && randomNumber > 0; index++){
            randomNumber -= population[index].getBacktestValue();
        }

        return population[index - 1];
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
