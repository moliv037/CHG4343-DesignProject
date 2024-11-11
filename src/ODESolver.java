public class ODESolver {

    public static double [] euler(double w_0, double w_f, double[] y_0, double delW, int maxIt, ODERHS f, int odeIndex, double tolerance) {
        double w = w_0; //setting the initial value of the independent variable
        double[] y_i = y_0.clone();  //creates a copy of the initial dependent variables
        double[] y_prev = new double [y_0.length]; //previous state of dependent variables
        int numODEs = y_0.length; //number of odes

        int i = 0; //starting iteration count at zero

        while (w < w_f && i < maxIt) {
            y_prev = y_i.clone(); //at first iteration, inital state is equal to previous state

            //compute dy/dw for all odes simultaneously and update y_i using Euler's method
            for (int currentODE = 0; currentODE < numODEs; currentODE++) {
                double dydw = f.returnODERHS(w, y_prev, currentODE);
                y_i[currentODE] = y_prev[currentODE] + delW * dydw;
            }

            //check for convergence
            boolean converged = true;
            for (int currentODE = 0; currentODE < numODEs; currentODE++) {
                if (Math.abs(y_i[currentODE] - y_prev[currentODE]) > tolerance) {
                converged = false;
                break;
            }
            }
            if (converged) {
                System.out.println("Convergence achieved within tolerance after " + i + " iterations");
                break; //exit loop when convergence is achieved
            }

            w += delW; //step the independent variable forward
            i++;
        }

        if (i == maxIt) {
            System.out.println("Warning: Maximum iterations reached before reaching w_f");
        }

        // Return final w and y values for the last state
        return new double[]{w, y_i[odeIndex]};  //return ODE specified by ODE index
    }
} //end of class













// double dydw = f.returnODERHS(w, y_i, odeIndex);  //RHS for the specified ODE
// y = y + delW * dydw;  //euler step for the dependent variable
//y_i[odeIndex] = y; //update the value of the dependent variable for the specified ODE in the state array

//checks for convergence based on tolerance
//if (Math.abs(y-y_previous) < tolerance) {
// System.out.println("Convergence achieved within tolerance");
//break; //exit the loop when convergence is reached
//}
//y_previous = y; //update value
// w = w + delW; //euler step for the independent variable
// i++;
//}

//if (i >= maxIt) {
//  System.out.println("Warning: Maximum iterations reached before reaching w_f");
//}

//return new double[] {w,y};  //returns an array containing the final values of the dependent and independent variables