package mayton.neural.dl;

import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.deeplearning4j.nn.api.NeuralNetwork;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Collections;
import java.util.stream.IntStream;

public class Main {

    enum LayerType {I,H,O};

    public static final int INPUT_N = 2;
    public static final int HIDDEN_N = 2;

    private static Neuron[] neurons = new Neuron[INPUT_N + HIDDEN_N + 1];

    public static void main(String[] args) {

        NeuralNetConfiguration neuralNetConfiguration = new NeuralNetConfiguration();

        System.out.println(neuralNetConfiguration.toYaml());

        MultiLayerConfiguration multiLayerConfiguration = new MultiLayerConfiguration();
        multiLayerConfiguration.setBackprop(true);
        multiLayerConfiguration.setConfs(Collections.singletonList(neuralNetConfiguration));
        multiLayerConfiguration.setInputPreProcessors(Collections.EMPTY_MAP);

        System.out.println(multiLayerConfiguration.toYaml());

        NeuralNetwork neuralNetwork = new MultiLayerNetwork(multiLayerConfiguration);
        neuralNetwork.init();



        //IntStream.range(0, INPUT_N).forEach(i -> neurons[i] = neuralNetwork));
        //IntStream.range(INPUT_N, INPUT_N + HIDDEN_N).forEach(i -> neurons[i] = new Neuron(LayerType.H));
        //neurons[INPUT_N + HIDDEN_N] = new Neuron(LayerType.O);


    }

}
