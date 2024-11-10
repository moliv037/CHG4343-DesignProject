public class ODESolver {

    public static double euler(double w_0, double w_f, double[] y_0, double delW, int maxIt, ODERHS f, int odeIndex) {
        double w = w_0;
        double y = y_0[odeIndex];  //start with the initial value for the specified ODE
        double[] y_i = y_0.clone();  //creates a copy of the initial values

        int i = 0;

        while (w < w_f && i < maxIt) {
            double dydw = f.returnODERHS(w, y_i, odeIndex);  //RHS for the specified ODE
            y = y + delW * dydw;  //euler step for the dependent variable
            y_i[odeIndex] = y; //update the value of the dependent variable for the specified ODE in the state array

            w = w + delW; //euler step for the independent variable
            i++;
        }

        if (i >= maxIt) {
            System.out.println("Warning: Maximum iterations reached before reaching w_f.");
        }

        return y;  //return the final value of the dependent variable for the specified ODE
    }
} //end of class