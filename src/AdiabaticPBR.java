public class AdiabaticPBR extends ReactorType implements ODERHS {

    private InputParameters input;// object to access T0, P0, k_T0, E, alpha, V_0 and type
    private StaticParameters parameters;// object to access numberReactants,numberProducts, numberInerts,
    // [] reactantCoefficients,[] productCoefficients,[] inertCoefficients,[] reactantMoleFracs,[] productMoleFracs,[] inertMoleFracs
    // [] reactantHeatCapacities, [] productHeatCapacities, [] inertHeatCapacities;
    //CA_0, FA_0, epsilon, [] theta_reactants, [] theta_products;

    //giving concrete definitions to parent methods

    //giving concrete definition to interface method

    public double returnODERHS (double w, double [] y, int odeIndex) {
        double X = y[0];
        double P = y[1];
        double T = y[2];

        switch (odeIndex) {
            case 0: // dX/dW
                return -1. * super.returnRateLaw(X) / super.getStaticParameters().getFA_0();
            //parameter list in calculateRate will need to be updated once that method is defined
            case 1: // dP/dW
                return -1/2*super.getInputParameters().getAlpha()*(super.getInputParameters().getP0()/(P/super.getInputParameters().getP0()))*(1+super.getStaticParameters().getEpsilon()*X);
            case 2: // dT/dW
                return (-1. * super.returnRateLaw(X) * super.getInputParameters().getDelH_rx) / super.getStaticParameters().sumFiCpi();
            default:
                throw new IllegalArgumentException("Invalid ODE index");

        }
}
