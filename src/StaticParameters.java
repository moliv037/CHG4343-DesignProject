//The StaticParameters class calculates all the variables that are used in other classes but do not change, notably the initial conditions.
//This class will pass the necessary checks to the "static" parameters instead of doing it in the driver class.
//To access these parameters, create an object of the StaticParameter class and use .getVariable().

public class StaticParameters {

    private int numberReactants;
    private int [] reactantCoefficients;
    private double [] reactantHeatCapacities;

    private double [] reactantMoleFracs;
 private double [] productMoleFracs;




public static double calculateCA_0 (double [] reactantMoleFracs, double [] inputParameters){
   // here R = 0.08206 [L*atm/mol/K]
    return (reactantMoleFracs[0]*inputParameters[2])/(0.0826*inputParameters[1]);
}

public static double getFA_0 (double [] reactantMoleFracs, double [] inputParameters){
    return (reactantMoleFracs[0]*inputParameters[2]*inputParameters[6])/(0.0826*inputParameters[1]);
}
public static double getEpsilon (int[] reactantCoefficients, int[] productCoefficients, double [] reactantMoleFracs){
    double deltaProducts=0.;
    double deltaReactants = 0.;
    double a = reactantCoefficients[0]; //the coefficient of the limiting reactant is [0]
    for (int i=0; i<productCoefficients.length; i++){deltaProducts += productCoefficients[i]/a;}
    for (int i=0; i<reactantCoefficients.length; i++){deltaReactants -= reactantCoefficients[i]/a;}
   double delta= deltaProducts+deltaReactants;
    return (reactantMoleFracs[0]*delta);
}



}//end of class

