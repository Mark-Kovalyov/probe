package mayton.probe.ignite;

import org.apache.ignite.ml.math.primitives.vector.Vector;
import org.apache.ignite.ml.math.primitives.vector.impl.DenseVector;
import org.apache.ignite.ml.regressions.linear.LinearRegressionModel;

public class LinearRegression {

    public static void main(String[] args) {

        Vector weights = new DenseVector(new double[]{1.0});

        LinearRegressionModel model = new LinearRegressionModel(weights, 0.1);



    }

}
