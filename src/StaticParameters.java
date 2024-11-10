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

    public StaticParameters(int numberReactants, int numberProducts, int numberInerts, double CA_0, double FA_0, double epsilon) {
        if (numberReactants <= 0 || numberProducts <= 0 || numberInerts < 0) System.exit(0);
        this.numberReactants = numberReactants;
        this.numberProducts = numberProducts;
        this.numberInerts = numberInerts;
        if (CA_0 <= 0. || FA_0 <= 0.) System.exit(0);
        this.CA_0 = CA_0;
        this.FA_0 = FA_0;

        this.epsilon = epsilon; //epsilon can be 0, negative, or positive
    }//constructor, need to add throw exceptions

    public StaticParameters(double [] reactantMoleFracs, double [] productMoleFracs, double [] inertMoleFracs) {
       //check array is not null
        if(reactantMoleFracs==null) System.exit(0);
        if(productMoleFracs==null) System.exit(0);
        if(inertMoleFracs==null) System.exit(0);

        //check that no mole fraction is smaller than 0
        for (int i = 0; i < reactantMoleFracs.length; i++) {
            if (reactantMoleFracs[i] < 0.) System.exit(0);}
        for (int i = 0; i < productMoleFracs.length; i++) {
            if (productMoleFracs[i] < 0.) System.exit(0);}
        for (int i = 0; i < inertMoleFracs.length; i++) {
            if (inertMoleFracs[i] < 0.) System.exit(0);}

        //check that the sum of mole fractions in and out are 1
        for (int i=0; i < reactantMoleFracs.length; i++) {
            double sum = 0.0;
            sum += reactantMoleFracs[i] + inertMoleFracs[i];
            if (sum != 1.0) System.exit(0);}
        for (int i = 0; i < productMoleFracs.length; i++) {
            double sum = 0.0;
            sum += productMoleFracs[i] + inertMoleFracs[i];
            if (sum != 1.0) System.exit(0);}

        for (int j=0; j < reactantMoleFracs.length; j++)
            this.reactantMoleFracs[j]=reactantMoleFracs[j];
        for (int i=0; i < productMoleFracs.length; i++)
            this.productMoleFracs[i]=productMoleFracs[i];
        for (int i=0; i < inertMoleFracs.length; i++)
            this.inertMoleFracs[i]=inertMoleFracs[i];

    }//constructor, need to add throw exceptions




public double calculateCA_0 (double [] reactantMoleFracs, double [] inputParameters){
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

