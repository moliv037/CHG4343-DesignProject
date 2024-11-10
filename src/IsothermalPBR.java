public class IsothermalPBR extends ReactorType implements ODERHS {
    private double[] inputParameters;

    //constructor, copy constructor, clone, equals

    //giving concrete definitions to parent methods
    public double calculateX(RateLaw rateLaw, double[] inputParameters, double w) {
        super.setGlobalVariables (rateLaw);
        double delW = w/1000; //step size
        int maxIt = 1001;

        double conversion = ODESolver.euler(0.0, w, 0.0, delW, maxIt, this, 0);
        super.resetGlobalVariables();
        return conversion;
    }

    public double calculateT(RateLaw rateLaw, double[] inputParameters, double w) {
       return inputParameters[1];
    }

    public double calculateP(RateLaw ratelaw, double[] inputParameters, double w) {
        super.setGlobalVariables (rateLaw);
        double delW = w/1000; //step size
        int maxIt = 1001;

        double pressure = ODESolver.euler(0.0, w, 0.0, delW, maxIt, this, 1);
        super.resetGlobalVariables();
        return pressure;
    }

    //giving concrete definition to interface method
    public double returnODERHS(double w, double[] y, int odeIndex) {
        double X = y[0];
        double P = y[1];

        switch (odeIndex) {
            case 0: // dX/dW
                return -1. * super.returnRateLaw(X) / StaticParameters.getFA_0();
            //parameter list in calculateRate will need to be updated once that method is defined
            case 1: // dP/dW
                return -1/2*this.inputParameters[5]*(this.inputParameters[2]/(P/this.inputParameters[0]))*(1+StaticParameters.getEpsilon()*X);
            //doesn't need to be in StaticParameters but somewhere else needs to calculate Sum Fi*Cpi so it can be calld upon here
            default:
                throw new IllegalArgumentException("Invalid ODE index");
        }
    }
}