import java.util.function.Function;

//Area for informal testing of my code
public class Workspace {

    public static int n = 1;
    public static Function[] bFnc = BasisFunctions.poly(n);
    public static double a = 0.001;

    public static void main(String[] args) {

        double[][] X = {
                { 1.22, 4.95 },
                { 1.41, 8.02 },
                { 8.63, 6.23 },
                { 7.61, 5.61 },
                { 2.00, 5.14 },
                { 6.08, 1.71 },
                { 4.32, 2.91 },
                { 0.65, 9.49 },
                { 3.31, 0.64 },
                { 9.66, 8.08 },
                { 3.58, 1.16 },
                { 6.01, 7.08 },
                { 3.05, 0.98 },
                { 9.39, 8.95 },
                { 1.82, 1.83 },
                { 2.81, 5.43 },
                { 3.12, 5.20 },
                { 1.56, 1.56 },
                { 3.04, 5.25 },
                { 3.11, 3.25 },
                { 3.89, 2.71 },
                { 5.47, 1.85 },
                { 3.75, 9.51 },
                { 8.87, 4.72 },
                { 7.30, 6.38 },
                { 9.70, 7.75 },
                { 6.12, 1.39 },
                { 6.84, 4.40 },
                { 1.20, 7.13 },
                { 2.92, 3.66 },
                { 7.07, 7.29 },
                { 0.75, 9.87 },
                { 0.21, 9.70 },
                { 7.32, 5.99 },
                { 5.98, 9.22 },
                { 0.34, 9.09 },
                { 8.32, 2.12 },
                { 7.71, 4.94 },
                { 4.56, 7.85 },
                { 7.72, 1.99 },
                { 0.88, 1.96 },
                { 5.92, 0.46 },
                { 5.23, 4.28 },
                { 2.59, 6.63 },
                { 0.06, 8.15 },
                { 0.45, 3.25 },
                { 0.58, 8.66 },
                { 0.25, 1.08 },
                { 7.71, 0.74 },
                { 8.29, 3.57 }
        };

        double[] y = { 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };


        double[][] X_new = {
                { 5.11, 5.02 },
                { 7.02, 7.96 },
                { 6.97, 7.02 },
                { 4.39, 0.78 },
                { 8.90, 3.38 },
                { 8.36, 6.96 },
                { 2.87, 5.91 },
                { 1.56, 2.50 },
                { 0.25, 9.63 },
                { 5.78, 0.36 },
                { 3.76, 0.94 },
                { 6.23, 0.85 },
                { 8.09, 8.10 },
                { 7.70, 2.16 },
                { 7.26, 9.76 },
                { 8.77, 7.41 },
                { 8.23, 3.60 },
                { 5.41, 6.37 },
                { 0.31, 0.37 },
                { 4.09, 1.73 },
                { 0.52, 5.31 },
                { 6.60, 2.80 },
                { 4.66, 5.43 },
                { 3.59, 2.94 },
                { 7.95, 2.71 },
                { 5.16, 3.23 },
                { 8.67, 9.13 },
                { 7.98, 6.50 },
                { 5.49, 7.15 },
                { 1.27, 5.22 }
        };

        double[] y_new = { 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0,
                0, 0, 1, 0, 1, 0, 1, 1, 1, 0 };

        double[] w_Vals = {-10, 1, 1}; //bias + xC + yC

        Matrix xTrain = new Matrix(X);
        Matrix yTrain = new Matrix(y);

        Matrix xTest = new Matrix(X_new);
        Matrix yTest = new Matrix(y_new);

        Matrix w0 = LinearAlgebra.constantMatrix(3, 1, 2);

        Matrix w = Regression.logisticReg(xTrain, yTrain, w0, a, bFnc);

        System.out.println("Weights:");
        System.out.println(w); //Should be -10, 1, 1

        Matrix CM = Error.confusionMatrix(xTest, yTest, w, bFnc);

        Error.printClassificationReport(CM);




    }
}
