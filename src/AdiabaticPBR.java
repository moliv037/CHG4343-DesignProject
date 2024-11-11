public class AdiabaticPBR extends ReactorType implements ODERHS {

    //constructor, copy constructor, clone, equals
    public AdiabaticPBR(InputParameters input, StaticParameters parameters) {
        super(input, parameters);
        this.resetGlobalVariables();
    }

    public AdiabaticPBR(AdiabaticPBR source) {
        super(source);
    }

    public AdiabaticPBR clone() {
        return new AdiabaticPBR(this);
    }

    protected void resetGlobalVariables() {
        super.resetGlobalVariables();
    }

    protected void setGlobalVariables(RateLaw rateLaw, Reaction reaction) {
        super.setGlobalVariables(rateLaw, reaction);
    }

    public boolean equals(Object comparator) {
        if (!super.equals(comparator)) return false;
        return true;
    }

    //giving concrete definitions to parent methods
    public double[] calculateX(RateLaw rateLaw, Reaction reaction, double w) {
        super.setGlobalVariables(rateLaw, reaction);
        double delW = w / 1000; //step size
        int maxIt = 1001;
        double tolerance = 0.00001;

        double[] conversion = ODESolver.euler(0.0, w, new double[]{0.,0.,0.}, delW, maxIt, this, 0, tolerance);
        super.resetGlobalVariables();
        return conversion;
    }

    public double[] calculateT(RateLaw rateLaw, Reaction reaction, double w) {
        super.setGlobalVariables(rateLaw, reaction);
        double delW = w / 1000; //step size
        int maxIt = 1001;
        double tolerance = 0.00001;

        double[] temperature = ODESolver.euler(0.0, w, new double[]{0.,0.,0.}, delW, maxIt, this, 2, tolerance);
        super.resetGlobalVariables();
        return temperature;
    }

    public double[] calculateP(RateLaw ratelaw, Reaction reaction, double w) {
        super.setGlobalVariables(ratelaw, reaction);
        double delW = w / 1000; //step size
        int maxIt = 1001;
        double tolerance = 0.00001;

        double[] pressure = ODESolver.euler(0.0, w, new double[]{0.,0.,0.}, delW, maxIt, this, 1, tolerance);
        super.resetGlobalVariables();
        return pressure;
    }

    //giving concrete definition to interface method
    public double returnODERHS(double w, double[] y, int odeIndex) {
        double X = y[0];
        double P = y[1];
        double T = y[2];

        switch (odeIndex) {
            case 0: // dX/dW
                return -1. * super.returnRateLaw(X, P, T) / super.getParameters().getFA_0();
            //parameter list in calculateRate will need to be updated once that method is defined
            case 1: // dP/dW
                return -1. / 2. * super.getInput().getAlpha() * (super.getInput().getP0() / (P / super.getInput().getP0())) * (1 + super.getParameters().getEpsilon() * X);
            case 2: // dT/dW
                return (-1. * super.returnRateLaw(X, P, T) * super.getInput().getDelH_rx()) / super.returnFiCpi(X);
            default:
                throw new IllegalArgumentException("Invalid ODE index");

        }
    }
}
