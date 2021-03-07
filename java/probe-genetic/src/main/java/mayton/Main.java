package mayton;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.OnePointCrossover;
import org.apache.commons.math3.genetics.RandomKeyMutation;
import org.apache.commons.math3.genetics.SelectionPolicy;
import org.apache.commons.math3.genetics.TournamentSelection;

public class Main {

    public static void main(String[] args) {

        CrossoverPolicy crossoverPolicy = new OnePointCrossover<>();
        double crossoverRate = 1.0;
        MutationPolicy mutationPolicy = new RandomKeyMutation();
        double mutationRate = 1.0;
        SelectionPolicy selectionPolicy = new TournamentSelection(10);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(crossoverPolicy, crossoverRate, mutationPolicy, mutationRate, selectionPolicy);


    }

}
