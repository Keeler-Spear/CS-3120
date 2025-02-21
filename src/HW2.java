import java.util.Random;

public class HW2 {

    private static double[][] gradEval(Matrix xTrain, Matrix yTrain, Matrix xTest, Matrix yTest, int n) {
        System.out.println("Polynomial Best Fit for Order " + n + ":");

        Matrix xLin = LinearAlgebra.linSpace(-3, 3, 0.1);

        Matrix w = LinearAlgebra.constantMatrix(n * xTrain.getCols() + 1,1, 0.0);

        double a;

        if (n <= 2) {
            a = 0.001;
        }
        else {
            a = Math.pow(10, 1 - n);
        }

        Matrix min = LinReg.polyGradDes(xTrain, yTrain, w, n, a);

        Matrix fnc = LinReg.buildPolyFunction(xLin, min);

        double errorTr = Error.meanSquared(xTrain, yTrain, min);

        System.out.println("MSE of Training Data: "  + errorTr);

        double errorTe = Error.meanSquared(xTest, yTest, min);

        System.out.println("MSE of Testing Data: "  + errorTe + "\n");

        String title = "Polynomial Best Fit for Order " + String.valueOf(n);
        PyGraph.scatterWFnc(xTest, yTest, xLin, fnc,"x", "y", title);

        double[][] errors = new double[2][1];

        errors[0][0] = errorTr;
        errors[1][0] = errorTe;

        return errors;
    }

    public static void main(String[] args) {
        long seed = 5;
        Random rand = new Random(seed);

        Matrix xTrain = LinearAlgebra.randMatrix(300, 1, -3, 3, seed);
        Matrix xTest = LinearAlgebra.randMatrix(200, 1, -3, 3, seed);
        double[] yValsTrain = new double[300];
        double[] yValsTest = new double[200];
        double xValTrain;
        double aTrain;
        double xValTest;
        double aTest;
        //y=0.5*X^5 - 5X^3 - X^2 +2 + a
        for (int i = 0; i < 300; i++) {
            xValTrain = xTrain.getValue(i + 1, 1);
            aTrain = 2 * (rand.nextDouble() - 0.5);
            yValsTrain[i] = 0.5 * Math.pow(xValTrain, 5) - 5 * Math.pow(xValTrain, 3) - Math.pow(xValTrain, 2) + 2 + aTrain;
        }

        Matrix yTrain = new Matrix(yValsTrain);

        for (int i = 0; i < 200; i++) {
            xValTest = xTest.getValue(i + 1, 1);
            aTest = 2 * (rand.nextDouble() - 0.5);
            yValsTest[i] = 0.5 * Math.pow(xValTest, 5) - 5 * Math.pow(xValTest, 3) - Math.pow(xValTest, 2) + 2 + aTest;
        }

        Matrix yTest = new Matrix(yValsTest);

//        double[] errorTr = new double[25];
//        double[] errorTe = new double[25];
//        double[] errorX = new double[25];
//
//        double[][] temp;
//
//        for (int i = 0; i <= 24; i++) {
//            temp = gradEval(xTrain, yTrain, xTest, yTest, i + 1);
//            errorTr[i] = temp[0][0];
//            errorTe[i] = temp[1][0];
//            errorX[i] = i + 1;
//        }
//
//        Matrix mErrorTrain = new Matrix(errorTr);
//        Matrix mErrorTest = new Matrix(errorTe);
//        Matrix mErrorX = new Matrix(errorX);

//        PyGraph.TwoFnc(mErrorX, mErrorTrain, mErrorTest, "Polynomial Order", "MSE", "Model Error");

//        gradEval(xTrain, yTrain, xTest, yTest, 2);

//        gradEval(xTrain, yTrain, xTest, yTest, 5);

//        gradEval(xTrain, yTrain, xTest, yTest, 8);

//        gradEval(xTrain, yTrain, xTest, yTest, 10);

        gradEval(xTrain, yTrain, xTest, yTest, 20);
    }
}
