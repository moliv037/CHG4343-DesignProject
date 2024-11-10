public class AdiabaticPBR extends ReactorType implements ODERHS {

    //giving concrete definitions to parent methods

    //giving concrete definition to interface method

    public double returnODERHS (double w, double [] y, int odeIndex) {
        double X = y[0];
        double P = y[1];
        double T = y[2];

        switch (odeIndex) {
            case 0: // dX/dW
                return -1. * super.returnRateLaw(X) / super.getParameters().getFA_0();
            //parameter list in calculateRate will need to be updated once that method is defined
            case 1: // dP/dW
                return -1/2*super.getInput().getAlpha()*(super.getInput().getP0()/(P/super.getInput().getP0()))*(1+super.getParameters().getEpsilon()*X);
            case 2: // dT/dW
                return (-1. * super.returnRateLaw(X) * super.getInput().getDelH_rx()) / super.getParameters().FiCpi();
            default:
                throw new IllegalArgumentException("Invalid ODE index");

        }
}
