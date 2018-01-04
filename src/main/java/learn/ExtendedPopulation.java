package learn;

import backtest.Backtest;

public class ExtendedPopulation extends CreaturesPopulation {
    public ExtendedPopulation(Backtest backtest, int populationSize, int eliteCreaturesSize, double mutationP,
                              double crossP){
        this.ELITE_CREATURES_NUMBER = eliteCreaturesSize;
        this.POPULATION_SIZE = populationSize;
        this.MUTATION_PROPABILITY = mutationP;
        this.CROSS_PROPABILITY = crossP;

        population = new ExtendedCreature[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++){
            population[i] = new ExtendedCreature(backtest);
        }

        this.evaluate();
    }

    public void setExtendedPopulation(ExtendedCreature[] creatures){

        for(int i = 0; i < creatures.length; i++){
            try {
                population[i] = (ExtendedCreature) creatures[i].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public ExtendedCreature findBest(){
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
        return (ExtendedCreature) population[indexMax];
    }

    public ExtendedCreature getRandomCreature(){
        double randomNumber = rand.nextDouble() * this.totalAmount;
        int index;

        for(index = 0; index < POPULATION_SIZE && randomNumber > 0; index++){
            randomNumber -= population[index].getBacktestValue();
        }

        return (ExtendedCreature) population[index - 1];
    }
}
