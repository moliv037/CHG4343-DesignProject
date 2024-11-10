public class Reaction {
  private static final double R=8.314; //in J/mol*K
  private InputParameters input; // object to access T0, P0, k_T0, E, alpha, V_0 and type
  private StaticParameters parameters; // object to access numberReactants,numberProducts, numberInerts,
  // [] reactantCoefficients,[] productCoefficients,[] inertCoefficients,[] reactantMoleFracs,[] productMoleFracs,[] inertMoleFracs
  // [] reactantHeatCapacities, [] productHeatCapacities, [] inertHeatCapacities;
  //CA_0, FA_0, epsilon, [] theta_reactants, [] theta_products;


  public double [] calculateInitalConcentrations(InputParameters params){
    double[] InitialConcentrations=new double[numberReactants]; 
    double P0=params.getP0(); 
    double T0-params.getT0();
    //number of reactants? 
    for (int i=0; I < numberReactants; i++) {
      InitialConcentrations[i]=

    
}
