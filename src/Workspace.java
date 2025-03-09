import java.util.function.Function;

public class Workspace {

    private static int numTests = 10;
    private static double lr = 0.01;

    /*
    Polynomial Order 10
    Without Rounding:  Accuracy: 0.8279 | AUC: 0.8737
    With Rounding:  Accuracy: 0.6299 | AUC: 0.7148

    Trig Order 5
    Without Rounding:  Accuracy: 0.8214 | AUC: 0.8789
     */
    public static void main(String[] args) {
        String file = "C:\\Users\\grand\\PersonalProjects\\CS-3120\\data\\pima-indians-diabetes-database.csv";
        Matrix[] data = Data.getBCData(file, "0", "1", 0, 60, false, true, true);
        Matrix xTrain  = data[0];
        Matrix yTrain  = data[1];
        Matrix xTest  = data[2];
        Matrix yTest = data[3];
        Function<Double, Double>[] bFnc;
        Matrix w0;

        double[][] noRoundingPoly = new double[numTests][2];
        double[][] roundingPoly = new double[numTests][2];
        double[][] noRoundingTrig = new double[numTests][2];
        double[][] roundingTrig = new double[numTests][2];

        for (int i = 1; i <= numTests; i++) {
            w0 = LinearAlgebra.zeroMatrix(i * xTrain.getCols() + 1, 1);
            bFnc = BasisFunctions.poly(i);

            System.out.println("Polynomial Order " + i);
            System.out.print("Without Rounding:  ");
            noRoundingPoly[i - 1] = Metrics.quickModelEval(xTest, yTest, Regression.logisticReg(xTrain, yTrain, w0, lr, bFnc, false), bFnc);
            System.out.print("With Rounding:  ");
            roundingPoly[i - 1] = Metrics.quickModelEval(xTest, yTest, Regression.logisticReg(xTrain, yTrain, w0, lr, bFnc, true), bFnc);
            System.out.println();
        }

        for (int i = 1; i <= numTests; i++) {
            w0 = LinearAlgebra.zeroMatrix(i * xTrain.getCols() + 1, 1);
            bFnc = BasisFunctions.trig(i);

            System.out.println("Trig Order " + i);
            System.out.print("Without Rounding:  ");
            noRoundingTrig[i - 1] = Metrics.quickModelEval(xTest, yTest, Regression.logisticReg(xTrain, yTrain, w0, lr, bFnc, false), bFnc);
            System.out.print("With Rounding:  ");
            roundingTrig[i - 1] = Metrics.quickModelEval(xTest, yTest, Regression.logisticReg(xTrain, yTrain, w0, lr, bFnc, true), bFnc);
            System.out.println();
        }

    }

}
