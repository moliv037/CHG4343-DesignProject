public class ODESolver {

    public static double euler(double w_0, double w_f, double[] y_0, double delW, int maxIt, ODERHS f, int odeIndex, double tolerance) {
        double w = w_0;
        double y = y_0[odeIndex];  //start with the initial value for the specified ODE
        double[] y_i = y_0.clone();  //creates a copy of the initial values

        int i = 0;
        double y_previous = y; //stores previous value of y to calculate convergence

        while (w < w_f && i < maxIt) {
            double dydw = f.returnODERHS(w, y_i, odeIndex);  //RHS for the specified ODE
            y = y + delW * dydw;  //euler step for the dependent variable
            y_i[odeIndex] = y; //update the value of the dependent variable for the specified ODE in the state array

            //checks for convergence based on tolerance
            if (Math.abs(y-y_previous) < tolerance) {
                System.out.println("Convergence achieved within tolerance.");
                break;
            }
            y_previous = y; //update value
            w = w + delW; //euler step for the independent variable
            i++;
        }

        if (i >= maxIt) {
            System.out.println("Warning: Maximum iterations reached before reaching w_f.");
        }

        return y;  //return the final value of the dependent variable for the specified ODE
    }
} //end of class