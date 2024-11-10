public class Reaction {
  private InputParameters input;
  private StaticParameters parameters;

  private double [] reactantConcentrations; //dont want in my constructor

  public Reaction(InputParameters input, StaticParameters parameters) {
    this.input = input.clone();
    this.parameters = parameters.clone(); //when we create reaction in drive we add input, parameters and reactantConcetrations
  }
  /*private static final double R=8.314; //in J/mol*K
  input : object to access T0, P0, k_T0, E, alpha, V_0 and type
  parameters : object to access numberReactants,numberProducts, numberInerts,
  [] reactantCoefficients,[] productCoefficients,[] inertCoefficients,[] reactantMoleFracs,[] productMoleFracs,[] inertMoleFracs
  [] reactantHeatCapacities, [] productHeatCapacities, [] inertHeatCapacities;
  CA_0, FA_0, epsilon, [] theta_reactants, [] theta_products; */

  //The goal of this class is to create methods that return arrays that calculate concentrations for reactants and products, and for FiCpi.

  /*public double [] calculateInitalConcentrations(InputParameters params){
    double[] InitialConcentrations=new double[numberReactants]; 
    double P0=params.getP0(); 
    double T0-params.getT0();
    //number of reactants? 
    for (int i=0; I < numberReactants; i++) {
      InitialConcentrations[i]= */

  public double [] getReactantConcentrations (double P, double T) {
    for (int i=0; i< parameters.getNumberReactants(); i++){

    }
  }



}//end of class
