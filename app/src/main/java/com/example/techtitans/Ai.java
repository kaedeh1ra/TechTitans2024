package com.example.techtitans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.random.Random;

public class Ai {
    public static int len;
    public static int numWords;

    public Ai(int len, int numWords) {
        this.len = len;
        this.numWords = numWords;
    }

    public static Double trainAndPredict(int l, int n) {
        List<List<Integer>> data = new ArrayList<List<Integer>>();
        data.add(Arrays.asList(30, 3));
        data.add(Arrays.asList(15, 0));
        data.add(Arrays.asList(100, 1));
        data.add(Arrays.asList(20, 7));
        List<Double> answers = Arrays.asList(1.0,0.0,0.0,1.0);

        Network network500 = new Network(500);
        network500.train(data, answers);

        Network network1000 = new Network(1000);
        network1000.train(data, answers);

        return network1000.predict(l, n);
    }


    static class Network {
        int epochs = 0; //1000;
        Double learnFactor = null;
        List<Neuron> neurons = Arrays.asList(
                new Neuron(), new Neuron(), new Neuron(),
                new Neuron(), new Neuron(),
                new Neuron());

        public Network(int epochs){
            this.epochs = epochs;
        }
        public Network(int epochs, Double learnFactor) {
            this.epochs = epochs;
            this.learnFactor = learnFactor;
        }

        public Double predict(Integer input1, Integer input2){
            return neurons.get(5).compute(
                    neurons.get(4).compute(
                            neurons.get(2).compute(input1, input2),
                            neurons.get(1).compute(input1, input2)
                    ),
                    neurons.get(3).compute(
                            neurons.get(1).compute(input1, input2),
                            neurons.get(0).compute(input1, input2)
                    )
            );
        }
        public void train(List<List<Integer>> data, List<Double> answers){
            Double bestEpochLoss = null;
            for (int epoch = 0; epoch < epochs; epoch++){
                // adapt neuron
                Neuron epochNeuron = neurons.get(epoch % 6);
                epochNeuron.mutate(this.learnFactor);

                List<Double> predictions = new ArrayList<Double>();
                for (int i = 0; i < data.size(); i++){
                    predictions.add(i, this.predict(data.get(i).get(0), data.get(i).get(1)));
                }
                Double thisEpochLoss = Util.meanSquareLoss(answers, predictions);

                if (epoch % 10 == 0) System.out.println(String.format("Epoch: %s | bestEpochLoss: %.15f | thisEpochLoss: %.15f", epoch, bestEpochLoss, thisEpochLoss));

                if (bestEpochLoss == null){
                    bestEpochLoss = thisEpochLoss;
                    epochNeuron.remember();
                } else {
                    if (thisEpochLoss < bestEpochLoss){
                        bestEpochLoss = thisEpochLoss;
                        epochNeuron.remember();
                    } else {
                        epochNeuron.forget();
                    }
                }
            }
        }
    }

    static class Neuron {
        Random random;
        private Double oldBias = random.nextDouble(-1, 1), bias = random.nextDouble(-1, 1);
        public Double oldWeight1 = random.nextDouble(-1, 1), weight1 = random.nextDouble(-1, 1);
        private Double oldWeight2 = random.nextDouble(-1, 1), weight2 = random.nextDouble(-1, 1);

        public String toString(){
            return String.format("oldBias: %.15f | bias: %.15f | oldWeight1: %.15f | weight1: %.15f | oldWeight2: %.15f | weight2: %.15f", this.oldBias, this.bias, this.oldWeight1, this.weight1, this.oldWeight2, this.weight2);
        }

        public void mutate(Double learnFactor){
            int propertyToChange = random.nextInt(0, 3);
            Double changeFactor = (learnFactor == null) ? random.nextDouble(-1, 1) : (learnFactor * random.nextDouble(-1, 1));
            if (propertyToChange == 0){
                this.bias += changeFactor;
            } else if (propertyToChange == 1){
                this.weight1 += changeFactor;
            } else {
                this.weight2 += changeFactor;
            };
        }
        public void forget(){
            bias = oldBias;
            weight1 = oldWeight1;
            weight2 = oldWeight2;
        }
        public void remember(){
            oldBias = bias;
            oldWeight1 = weight1;
            oldWeight2 = weight2;
        }
        public double compute(double input1, double input2){
//      this.input1 = input1;  this.input2 = input2;
            double preActivation = (this.weight1 * input1) + (this.weight2 * input2) + this.bias;
            double output = Util.sigmoid(preActivation);
            return output;
        }
    }

    static class Util {
        public static double sigmoid(double in){
            return 1 / (1 + Math.exp(-in));
        }
        public static double sigmoidDeriv(double in){
            double sigmoid = Util.sigmoid(in);
            return sigmoid * (1 - in);
        }
        /** Assumes array args are same length */
        public static Double meanSquareLoss(List<Double> correctAnswers, List<Double> predictedAnswers){
            double sumSquare = 0;
            for (int i = 0; i < correctAnswers.size(); i++){
                double error = correctAnswers.get(i) - predictedAnswers.get(i);
                sumSquare += (error * error);
            }
            return sumSquare / (correctAnswers.size());
        }
    }
}
