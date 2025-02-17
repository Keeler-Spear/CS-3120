public class HW1 {

    public static void main(String[] args) {

        //Data initialization
        double[] xData = {5., 38., 31., 20., 22., 25., 17., 60., 8., 60.};
        double[] yData = {29.54611622, 135.54611622, 121.54611622, 99.54611622, 103.54611622, 109.54611622, 93.54611622, 179.54611622, 75.54611622, 179.54611622};
        Matrix x = new Matrix(xData);
        Matrix y = new Matrix(yData);

        Matrix bb = LinearAlgebra.linSpace(0, 100, 1); //mx1 vector
        Matrix ww = LinearAlgebra.linSpace(-5, 5, 0.1); //nx1 vector
        double b;
        double w;

        double[][] zVals = new double[bb.getRows()][ww.getRows()];
        for (int i = 1; i <= bb.getRows(); i++) {
            for (int j = 1; j <= ww.getRows(); j++) {
                b = bb.getValue(i, 1);
                w = ww.getValue(j, 1);
                zVals[j - 1][i - 1] = 0;
                for (int k = 0; k < xData.length; k++) {
                    zVals[j - 1][i - 1] = zVals[j - 1][i - 1] + Math.pow((w * xData[k] + b - yData[k]), 2);
                }

                zVals[j - 1][i - 1] = zVals[j - 1][i - 1] / xData.length;
            }
        }

        bb = LinearAlgebra.transpose(bb);
        ww = LinearAlgebra.transpose(ww);
        Matrix z = new Matrix(zVals);



        //Running the gradDes algorithm
        Matrix[] temp = ML.gradDesTrack(x, y);
        Matrix min = temp[0];
        Matrix bPath = temp[1];
        Matrix wPath = temp[2];

        //Printing the minimum
        System.out.println(min);

        //Plot for Q3
        PyGraph.contour(bb, ww, z, min, "b", "w", "Loss Function");

        //Plot for Q6
        PyGraph.contourTrack(bb, ww, z, bPath, wPath, min, "b", "w", "Loss Function");
    }

}
