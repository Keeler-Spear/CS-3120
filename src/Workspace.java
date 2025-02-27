import java.util.Random;
import java.util.function.Function;

//Area for informal testing of my code
public class Workspace {
    static final int n = 5;
    static final Function[] bFnc = BasisFunctions.polynomials(n);
    static final double a = 0.00001;


    static Random rand = new Random();
    static final int trSize = 300;
    static final int teSize = 200;
    static final double lowerBound = -3;
    static final double upperBound = 3;
    static final double aMag = 1;

    //y=0.5*X^5 - 5X^3 - X^2 +2 + a
    private static double fnc(double x) {
        double a = aMag * 2 * (rand.nextDouble() - 1);
        return 0.5 * Math.pow(x, 5) - 5 * Math.pow(x, 3) - Math.pow(x, 2) + 2;
    }

    public static void main(String[] args) {

        Matrix xValsTrain = LinearAlgebra.randMatrix(trSize, 1, lowerBound, upperBound);
        Matrix xValsTest = LinearAlgebra.randMatrix(teSize, 1, lowerBound, upperBound);
        Matrix yValsTrain = new Matrix(trSize, 1);
        Matrix yValsTest = new Matrix(teSize, 1);

        //y=0.5*X^5 - 5X^3 - X^2 +2 + a
        for (int i = 1; i <= xValsTrain.getRows(); i++) {
            yValsTrain.setValue(i, 1, fnc(xValsTrain.getValue(i, 1)));
        }

        for (int i = 1; i <= xValsTest.getRows(); i++) {
            yValsTest.setValue(i, 1, fnc(xValsTest.getValue(i, 1)));
        }

        Matrix w = LinearAlgebra.zeroMatrix(n * xValsTrain.getCols() + 1,1);

        Matrix min = LinReg.gradDes(xValsTrain, yValsTrain, w, n, a, bFnc);

        Matrix xLin = LinearAlgebra.linSpace(lowerBound, upperBound, 0.1);

        Matrix fnc = LinReg.buildFunction(xLin, min, bFnc);

        double errorTr = Error.meanSquared(xValsTrain, yValsTrain, min, bFnc);

        double errorTe = Error.meanSquared(xValsTest, yValsTest, min, bFnc);


        System.out.println("Least Squares Fit for Order " + n + ":");
        System.out.println(min);
        System.out.println("------");
        System.out.println("MSE of Training Data: "  + errorTr);
        System.out.println("MSE of Testing Data: "  + errorTe);

        String title = "Best Fit for Order " + String.valueOf(n);
        PyChart.scatterWFnc(xValsTest, yValsTest, xLin, fnc,"x", "y", title);
    }
}
