package learn;

import backtest.Backtest;


import java.util.Random;

public class Util {

    //Bierze dwie istoty
    //tworzy nowe dwie
    //losuje punkt w genotypie
    //i do pierwszego nowego wsadza cześć genotypu z pierwszego i dopełnia gentypem drugiego
    //i do drugiego nowego na odwrót
    public static Creature[] crossOver(Creature one, Creature two, Backtest backtest) {
        Random rand = new Random();
        Creature[] newCreatures = new Creature[2];

        newCreatures[0] = new Creature(backtest);
        newCreatures[1] = new Creature(backtest);

        int randomPoint = rand.nextInt(Creature.SIZE);
        int index;
        for(index = 0; index < randomPoint; ++index){
            newCreatures[0].setGene(index, one.getGene(index));
            newCreatures[1].setGene(index, two.getGene(index));
        }
        for(; index < Creature.SIZE; ++index){
            newCreatures[0].setGene(index, two.getGene(index));
            newCreatures[1].setGene(index, one.getGene(index));
        }
        return newCreatures;
    }

    public static ExtendedCreature[] crossOver(ExtendedCreature one, ExtendedCreature two, Backtest backtest) {
        Random rand = new Random();
        ExtendedCreature[] newCreatures = new ExtendedCreature[2];

        newCreatures[0] = new ExtendedCreature(backtest);
        newCreatures[1] = new ExtendedCreature(backtest);

        int randomPoint = rand.nextInt(Creature.SIZE);
        int index;
        for(index = 0; index < randomPoint; ++index){
            newCreatures[0].setGene(index, one.getGene(index));
            newCreatures[0].setPsGenotype(index, one.getPsGenotype(index));
            newCreatures[0].setPwGenotype(index, one.getPwGenotype(index));
            newCreatures[1].setGene(index, two.getGene(index));
            newCreatures[1].setPsGenotype(index, two.getPsGenotype(index));
            newCreatures[1].setPwGenotype(index, two.getPwGenotype(index));
        }
        for(; index < Creature.SIZE; ++index){
            newCreatures[0].setGene(index, two.getGene(index));
            newCreatures[0].setPsGenotype(index, two.getPsGenotype(index));
            newCreatures[0].setPwGenotype(index, two.getPwGenotype(index));
            newCreatures[1].setGene(index, one.getGene(index));
            newCreatures[1].setPsGenotype(index, one.getPsGenotype(index));
            newCreatures[1].setPwGenotype(index, one.getPwGenotype(index));
        }

        return newCreatures;
    }
}
