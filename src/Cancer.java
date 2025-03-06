//Binary classification breast cancer

import java.util.function.Function;

public class Cancer {

    public static int n = 1; //93.8%
    public static Function[] bFnc = BasisFunctions.trig(n);
    public static double a = 0.001;

    public static void main(String[] args) {

        String file = "C:\\Users\\grand\\PersonalProjects\\CS-3120\\data\\data.csv"; //From https://www.kaggle.com/datasets/uciml/breast-cancer-wisconsin-data?resource=download

        Matrix[] data = Data.getBCData(file, "M", "B", 1,66.6,true, false, false);

        Matrix xTrain = data[0];
        Matrix yTrain = data[1];
        Matrix xTest = data[2];
        Matrix yTest = data[3];

        Matrix w0 = LinearAlgebra.zeroMatrix(n * xTrain.getCols() + 1, 1);

        Matrix w = Regression.logisticReg(xTrain, yTrain, w0, a, bFnc);

        Matrix CM = Metrics.confusionMatrix(xTest, yTest, w, bFnc);

        Metrics.printClassificationReport(CM);
    }
}