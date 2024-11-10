public abstract class RateLaw {
    private InputParameters input;// object to access T0, P0, k_T0, E, alpha, V_0 and type
    private StaticParameters parameters;// object to access numberReactants,numberProducts, numberInerts,
    // [] reactantCoefficients,[] productCoefficients,[] inertCoefficients,[] reactantMoleFracs,[] productMoleFracs,[] inertMoleFracs
    // [] reactantHeatCapacities, [] productHeatCapacities, [] inertHeatCapacities;
    //CA_0, FA_0, epsilon, [] theta_reactants, [] theta_products;

    //instance variable
    //constructors, copy constructors, clone, accessor, mutator, equals
    //methods

    public abstract double calculateRateConstant (double [] inputParameters);
    //I created getters for all the variables in the inputParameters array in the InputParameters class.
    // If you make an object of that class.get(Variable you want)() it works

    public abstract double calculateRate (double k, double X);

}
