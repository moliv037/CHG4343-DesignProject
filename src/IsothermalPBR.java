public class IsothermalPBR extends ReactorType implements ODERHS {

    //constructor, copy constructor, clone, equals

    public IsothermalPBR(InputParameters input, StaticParameters parameters, double k)
    {
        super(input,parameters,k);
        this.resetGlobalVariables();
        }

    public IsothermalPBR (IsothermalPBR source){
    super(source);
            }

    public IsothermalPBR clone()
    {
        return new IsothermalPBR(this);
    }

    protected void resetGlobalVariables()
    {
        super.resetGlobalVariables();
    }

    protected void setGlobalVariables(RateLaw rateLaw, Reaction reaction)
    {
        super.setGlobalVariables((rateLaw), reaction);
    }

    public boolean equals (Object comparator) {
        if (!super.equals(comparator))return false;
        return true;
    }

    //giving concrete definitions to parent methods
    public double[] calculateX(RateLaw rateLaw, double[] inputParameters, double w) {
        super.setGlobalVariables (rateLaw, );
        double delW = w/1000; //step size
        int maxIt = 1001;
        double tolerance = 0.00001;

        double[] conversion = ODESolver.euler(0.0, w, new double[]{0.0}, delW, maxIt, this, 0, tolerance );
        super.resetGlobalVariables();
        return conversion;
    }

    public double[] calculateT(RateLaw rateLaw, double[] inputParameters, double w) {
        double temperature = super.getInput().getT0();
        return new double[] {w,temperature};
    }

    public double[] calculateP(RateLaw ratelaw, double[] inputParameters, double w) {
        super.setGlobalVariables (ratelaw, );
        double delW = w/1000; //step size
        int maxIt = 1001;
        double tolerance = 0.00001;

        double [] pressure = ODESolver.euler(0.0, w, new double[]{0.0}, delW, maxIt, this, 1, tolerance );
        super.resetGlobalVariables();
        return pressure;
    }

    //giving concrete definition to interface method
    public double returnODERHS(double w, double[] y, int odeIndex) {
        double X = y[0];
        double P = y[1];
        double T = super.getInput().getT0();

        switch (odeIndex) {
            case 0: // dX/dW
                return -1. * super.returnRateLaw(X,P,T) / super.getParameters().getFA_0();
            //parameter list in calculateRate will need to be updated once that method is defined
            case 1: // dP/dW
                return -1./2.*super.getInput().getAlpha()*(super.getInput().getP0()/(P/super.getInput().getP0()))*(1+super.getParameters().getEpsilon()*X);
            default:
                throw new IllegalArgumentException("Invalid ODE index");
        }
    }
}