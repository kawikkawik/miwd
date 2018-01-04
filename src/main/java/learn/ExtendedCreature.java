package learn;

import backtest.Backtest;

import java.util.Random;

public class ExtendedCreature extends Creature {
    private int psGenotype[] = new int[SIZE];
    private int pwGenotype[] = new int[SIZE];
    int Ps;
    int Pw;

    public ExtendedCreature(Backtest backtest) {
        super(backtest);

        randExtendedGeneotype();
    }

    private void randExtendedGeneotype() {
        Random rand = new Random();
        for (int i = 0; i < SIZE; ++i) {
            this.psGenotype[i] = rand.nextInt(2);
        }
        for (int i = 0; i < SIZE; ++i) {
            this.pwGenotype[i] = rand.nextInt(2);
        }
        extendedSerialMutate();
    }

    @Override
    public void mutateGenotype() {
        super.mutateGenotype();
        Random rand = new Random();
        int index = rand.nextInt(SIZE);
        this.psGenotype[index] = 1 - this.psGenotype[index];
        this.pwGenotype[index] = 1 - this.pwGenotype[index];

    }


    public void extendedSerialMutate() {
        super.serialMutate();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int index = rand.nextInt(SIZE);
            this.psGenotype[index] = 1 - this.psGenotype[index];
            if (index < SIZE - 1) {
                this.psGenotype[index + 1] = this.psGenotype[index];
            }
            this.pwGenotype[index] = 1 - this.pwGenotype[index];
            if (index < SIZE - 1) {
                this.pwGenotype[index + 1] = this.pwGenotype[index];
            }
        }
    }

    @Override
    public int evaluate() {
        int fitness = 0;
        int psFitness = 0;
        int pwFitness = 0;
        for (int i = 0; i < SIZE; ++i) {
            fitness += this.getGene(i);
            psFitness += psGenotype[i];
            pwFitness += pwGenotype[i];
        }
        this.E = fitness;
        this.Ps = psFitness;
        this.Pw = pwFitness;
        this.backtestValue = backtest.getValueOfProfit(fitness, psFitness / 100, pwFitness / 100).doubleValue();
        return fitness + psFitness + pwFitness;
    }

    @Override
    public String toString() {
        return ": \nE: " + E + "\n" + "Ps: " + ((double) Ps) / 100 + "\n" + "Pw: " + ((double) Pw) / 100 + "\n";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        ExtendedCreature toReturn = new ExtendedCreature(backtest);
        for (int i = 0; i < SIZE; i++) {
            toReturn.psGenotype[i] = psGenotype[i];
            toReturn.pwGenotype[i] = pwGenotype[i];
        }
        for (int i = 0; i < SIZE; i++) {
            toReturn.setGene(i, toReturn.getGene(i));
        }
        toReturn.E = E;
        toReturn.backtestValue = backtestValue;
        toReturn.Ps = Ps;
        toReturn.Pw = Pw;

        return toReturn;
    }

    public int getPsGenotype(int index) {
        return psGenotype[index];
    }

    public void setPsGenotype(int psGenotype, int index) {
        this.psGenotype[index] = psGenotype;
    }

    public int getPwGenotype(int index) {
        return pwGenotype[index];
    }

    public void setPwGenotype(int pwGenotype, int index) {
        this.pwGenotype[index] = pwGenotype;
    }
}
