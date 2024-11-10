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

  public InputParameters getInput(){ return this.input.clone();}
  public StaticParameters getParameters(){ return this.parameters.clone();}

  public boolean equals (Object comparator){
    if (comparator==null) return false;
    if (comparator.getClass() != this.getClass()) return false;
    if (!((Reaction)comparator).input.equals(this.input)) return false;
    if (!((Reaction)comparator).parameters.equals(this.parameters)) return false;
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

  public double calculate_FiCPi(double X){
    double FA_0= parameters.getFA_0();
    double a = parameters.getReactantCoefficients()[0];
    double yA_0 = parameters.getReactantMoleFracs()[0];
    double reactants =0.; //sum of Fi*Cpi of reactants
    double products = 0.; //sum of Fi*Cpi of products
    double inerts =0.; //sum of Fi*Cpi of inerts

    for (int i=0; i< parameters.getNumberReactants();i++){
      reactants += ((FA_0*(parameters.getTheta_reactants()[i])-((parameters.getReactantCoefficients()[i]/a)* X))* parameters.getProductHeatCapacities()[i]);}

    for (int i=0; i< parameters.getNumberProducts();i++){
      products += ((FA_0*(parameters.getTheta_products()[i])+((parameters.getProductCoefficients()[i]/a)* X))* parameters.getProductHeatCapacities()[i]);}

    for (int i=0; i< parameters.getNumberInerts();i++){
      inerts += ((parameters.getInertMoleFracs()[i]/yA_0)*parameters.getInertHeatCapacities()[i]);}

    return (reactants+products+inerts);
  }

}//end of class
