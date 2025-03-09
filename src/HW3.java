import java.util.function.Function;

public class HW3 {

    private static int n = 5;
    private static double lr = 0.01;
    private static Function<Double, Double>[] bFncs = BasisFunctions.trig(n);

    public static void main(String[] args) {
        String file = "C:\\Users\\grand\\PersonalProjects\\CS-3120\\data\\pima-indians-diabetes-database.csv";
        Matrix[] data = Data.getBCData(file, "0", "1", 0, 60, false, true, true);
        Matrix xTrain = data[0];
        Matrix yTrain = data[1];
        Matrix xTest = data[2];
        Matrix yTest = data[3];

        Matrix w0 = LinearAlgebra.zeroMatrix(xTrain.getCols() * n + 1, 1);

        Matrix W = Regression.logisticReg(xTrain, yTrain, w0, lr, bFncs, false);

        Metrics.printClassificationReport(xTest, yTest, W, bFncs);
    }


}
