//Binary classification breast cancer

import java.util.function.Function;

public class Cancer {

    public static int n = 1;
    public static Function[] bFnc = BasisFunctions.poly(n);
    public static double a = 0.001;

    public static void main(String[] args) {


        String file = "C:\\Users\\grand\\PersonalProjects\\CS-3120\\data\\data.csv"; //From https://www.kaggle.com/datasets/uciml/breast-cancer-wisconsin-data?resource=download
        double[][] dataVals = ReadFile.csvToArray(file, "M", "B", 1);

        double[][] trData = new double[376][dataVals[0].length];
        double[][] teData = new double[193][dataVals[0].length];
        //Splitting data
        for (int i = 0; i < trData.length; i++) {
            trData[i] = dataVals[i];
        }
        for (int i = 0; i < teData.length; i++) {
            teData[i] = dataVals[i + trData.length];
        }

        Matrix xTrain = new Matrix(trData);
        Matrix yTrain = LinearAlgebra.vectorFromColumn(xTrain, 1);
        xTrain.removeCol(1); //Removing labels

        Matrix xTest = new Matrix(trData);
        Matrix yTest = LinearAlgebra.vectorFromColumn(xTest, 1);
        xTest.removeCol(1); //Removing labels

        Matrix w0 = LinearAlgebra.zeroMatrix(xTrain.getCols() + 1, 1);

        Matrix w = Regression.logisticReg(xTrain, yTrain, w0, a, bFnc);

        Matrix CM = Error.confusionMatrix(xTest, yTest, w, bFnc);

        Error.printClassificationReport(CM);
    }
}
