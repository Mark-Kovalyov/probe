package mayton.neural.neuroph;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.nnet.learning.BackPropagation;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork<BackPropagation> neuralNetwork = new NeuralNetwork();

        Layer layer1 = new Layer();
            layer1.addNeuron(new Neuron());
            layer1.addNeuron(new Neuron());

        neuralNetwork.addLayer(layer1);

        Layer layer2 = new Layer();
            layer2.addNeuron(new Neuron());
            layer2.addNeuron(new Neuron());

        neuralNetwork.addLayer(layer2);

        neuralNetwork.calculate();

        double[] out = neuralNetwork.getOutput();
    }

}
