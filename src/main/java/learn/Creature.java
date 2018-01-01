package learn;

import backtest.Backtest;

import java.math.BigDecimal;
import java.util.Random;

public class Creature {
    //wielkość genotypu
    public static final int SIZE = 100;
    //genotyp
    private int genotype[] = new int[SIZE];
    //wartość E
    private int E;
    //wartość backtestingu dla E
    private double backtestValue;
    //Obiekt do testowania
    private Backtest backtest;

    public Creature(Backtest backtest) {
        this.backtest = backtest;
        randGeneotype();
    }


    //ustawia genotyp na losowy z zakresu 0 - 1
    private void randGeneotype() {
        Random rand = new Random();
        for (int i = 0; i < SIZE; ++i) {
            this.genotype[i] = rand.nextInt(2);
        }
        serialMutate();
    }

    //mutuje genotyp poprzez przestawienie jednego genu
    public void mutateGenotype() {
        Random rand = new Random();
        int index = rand.nextInt(SIZE);
        this.genotype[index] = 1 - this.genotype[index];
    }

    //mutuje genotype poprzez przestawiwnie wielu genów
    public void serialMutate(){
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            int index = rand.nextInt(SIZE);
            this.genotype[index] = 1 - this.genotype[index];
            if(index < SIZE - 1){
                this.genotype[index + 1] = this.genotype[index];
            }
        }
    }

    //ustawia zdrowie creature i oblicza zysk
    public int evaluate() {
        int fitness = 0;
        for (int i = 0; i < SIZE; ++i) {
            fitness += this.genotype[i];
        }
        this.E = fitness;
        this.backtestValue = backtest.getValueOfProfit(fitness).doubleValue();
        return fitness;
    }


    public double getBacktestValue() {
        return this.backtestValue;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public int[] getGenes() {
        return this.genotype;
    }

    public int getE() {
        return this.E;
    }

    public Backtest getBacktest() {
        return this.backtest;
    }

    public void setGene(int index, int gene){
        genotype[index] = gene;
    }

    public int getGene(int index){
        return genotype[index];
    }

}
