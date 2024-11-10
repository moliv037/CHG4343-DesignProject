public class Reaction {
  private InputParameters input;
  private StaticParameters parameters;



  public Reaction(InputParameters input, StaticParameters parameters) {
    this.input = input.clone();
    this.parameters = parameters.clone(); //when we create reaction in drive we add input, parameters
  }// end of constructor, need to add thrown exceptions


  public Reaction (Reaction source){
    this.input = source.input.clone();
    this.parameters = source.parameters.clone();
  }//end of copy constructor

  public Reaction clone(){ return new Reaction(this); }

  public boolean setInput(InputParameters input){ this.input = input.clone(); return true; }
  public boolean setParameters(StaticParameters parameters){ this.parameters = parameters.clone(); return true; }

  public InputParameters getInput(){ return this.input;}
  public StaticParameters getParameters(){ return this.parameters;}

  public boolean equals (Object comparator){
    if (comparator==null) return false;
    if (comparator.getClass() != this.getClass()) return false;
    if (((Reaction)comparator).input != this.input) return false;
    if (((Reaction)comparator).parameters != this.parameters) return false;
    return true;
  }//end of equals

public double [] reactantConcentrations (double X, double P, double T){
    double CA_0= parameters.getCA_0();
    double epsilon= parameters.getEpsilon();
    double P_0= input.getP0();
    double T_0= input.getT0();
    double a = parameters.getReactantCoefficients()[0];
    double [] rxnConc = new double [parameters.getNumberReactants()];

    for (int i=0; i< parameters.getNumberReactants();i++){
        rxnConc[i] = ((CA_0*(parameters.getTheta_reactants()[i]-((parameters.getReactantCoefficients()[i]/a)* X)))/(1 + (epsilon * X)) * (P / P_0) * (T_0 / T));
    }
    return rxnConc;
}
  public double [] productConcentrations (double X, double P, double T){
    double CA_0= parameters.getCA_0();
    double epsilon= parameters.getEpsilon();
    double P_0= input.getP0();
    double T_0= input.getT0();
    double a = parameters.getReactantCoefficients()[0];
    double [] rxnConc = new double [parameters.getNumberProducts()];

    for (int i=0; i< parameters.getNumberProducts();i++){
      rxnConc[i] = ((CA_0*(parameters.getTheta_products()[i])+((parameters.getProductCoefficients()[i]/a)* X)))/(1 + (epsilon * X)) * (P / P_0) * (T_0 / T);
    }
    return rxnConc;
  }

  private double


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


}//end of class
