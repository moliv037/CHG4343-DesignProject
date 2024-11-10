public class AdiabaticPBR extends ReactorType implements ODERHS {

    //giving concrete definitions to parent methods

    //giving concrete definition to interface method

    public double returnODERHS (double w, double [] y, int odeIndex) {
        double X = y[0];
        double P = y[1];
        double T = y[2];

        switch (odeIndex) {
            case 0: // dX/dW
                return -1.*super.returnRateLaw(X)/StaticParameters.getFA_0();
            //parameter list in calculateRate will need to be updated once that method is defined
            case 1: // dP/dW
                return -1/2*this.getInputParameters[5]*(this.inputParameters[2]/(P/this.inputParameters[0]))*(1+StaticParameters.getEpsilon()*X);
            case 2: // dT/dW
                return (-1. * super.returnRateLaw() * this.inputParameters[0]) / StaticParameters.sumFiCpi();
            default:
                throw new IllegalArgumentException("Invalid ODE index");

        }
}
