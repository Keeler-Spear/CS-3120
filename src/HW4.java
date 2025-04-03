import java.util.function.Function;

public class HW4 {

    public static void main(String[] args) {
        double[][] xVals = {
                {0, 0, 0},
                {0, 0, 1},
                {0, 1, 0},
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 1},
                {1, 1, 0}
        };
        Matrix x = new Matrix(xVals);

        double[][] yVals = {
                {0, 1},
                {1, 0},
                {0, 1},
                {1, 0},
                {0, 1},
                {1, 0},
                {0, 1}
        };
        Matrix y = new Matrix(yVals);


    }

}
