//The StaticParameters class calculates all the variables that are used in other classes but do not change, notably the initial conditions.
//This class will pass the necessary checks to the "static" parameters instead of doing it in the driver class.
//To access these parameters, create an object of the StaticParameter class and use .getVariable().

public class StaticParameters {

    private int numberReactants;
    private int numberProducts;
    private int numberInerts;
    private int [] reactantCoefficients;
    private int [] productCoefficients;
    private int [] inertCoefficients;
    private double [] reactantMoleFracs;
    private double [] productMoleFracs;
    private double [] inertMoleFracs;
    private double [] reactantHeatCapacities;
    private double [] productHeatCapacities;
    private double [] inertHeatCapacities;

    private double CA_0;
    private double FA_0;
    private double epsilon;
    private double [] theta_reactants;
    private double [] theta_products;




public double getCA_0 (double [] reactantMoleFracs, double [] inputParameters){
   // here R = 0.08206 [L*atm/mol/K]
    return (reactantMoleFracs[0]*inputParameters[2])/(0.0826*inputParameters[1]);
}

public double getFA_0 (double [] reactantMoleFracs, double [] inputParameters){
    return (reactantMoleFracs[0]*inputParameters[2]*inputParameters[6])/(0.0826*inputParameters[1]);
}
public double getEpsilon (int[] reactantCoefficients, int[] productCoefficients, double [] reactantMoleFracs){
    double deltaProducts=0.;
    double deltaReactants = 0.;
    double a = reactantCoefficients[0]; //the coefficient of the limiting reactant is [0]
    for (int i=0; i<productCoefficients.length; i++){deltaProducts += productCoefficients[i]/a;}
    for (int i=0; i<reactantCoefficients.length; i++){deltaReactants -= reactantCoefficients[i]/a;}
   double delta= deltaProducts+deltaReactants;
    return (reactantMoleFracs[0]*delta);
}



}//end of class

