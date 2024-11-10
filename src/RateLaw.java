public class RateLaw {
    private InputParameters input;// object to access T0, P0, k_T0, E, alpha, V_0 and type
    private StaticParameters parameters;// object to access numberReactants,numberProducts, numberInerts,
    // [] reactantCoefficients,[] productCoefficients,[] inertCoefficients,[] reactantMoleFracs,[] productMoleFracs,[] inertMoleFracs
    // [] reactantHeatCapacities, [] productHeatCapacities, [] inertHeatCapacities;
    //CA_0, FA_0, epsilon, [] theta_reactants, [] theta_products;
    private Reaction reaction;


    public RateLaw(InputParameters input, StaticParameters parameters) {
        this.input = input.clone();
        this.parameters = parameters.clone();//when we create reaction in drive we add input, parameters
        this.reaction=reaction.clone();
    }// end of constructor, need to add thrown exceptions


    public RateLaw (RateLaw source){
        this.input = source.input.clone();
        this.parameters = source.parameters.clone();
        this.reaction=source.reaction.clone();
    }//end of copy constructor

    public RateLaw clone(){ return new RateLaw(this); }

    public boolean setInput(InputParameters input){ this.input = input.clone(); return true; }
    public boolean setParameters(StaticParameters parameters){ this.parameters = parameters.clone(); return true; }
    public boolean setReaction(Reaction reaction){ this.reaction = reaction.clone(); return true; }

    public InputParameters getInput(){ return this.input;}
    public StaticParameters getParameters(){ return this.parameters;}
    public Reaction getReaction(){ return this.reaction;}

    public boolean equals (Object comparator){
        if (comparator==null) return false;
        if (comparator.getClass() != this.getClass()) return false;
        if (((RateLaw)comparator).input != this.input) return false;
        if (((RateLaw)comparator).parameters != this.parameters) return false;
        if (((RateLaw)comparator).reaction != this.reaction) return false;
        return true;
    }//end of equals

    public double calculateRateLaw (double X, double P, double T){ //****returns ra not -ra
        double rate;
        double concProduct =1; //product of concentrations
        double k_rate=0; //k value used in rate law

        if (input.getType() == "isothermal"){
            k_rate = input.getK_T0();}
        else {
            k_rate = input.getK_T0()*Math.exp((input.getE()/8.3145)*((1/input.getT0())-(1/T)));
        }

        for (int i=0; i<parameters.getNumberReactants(); i++){
            concProduct *= Math.pow(reaction.reactantConcentrations(X,P,T)[i],parameters.getReactantCoefficients()[i]);
        }
        rate = -k_rate*concProduct;
        return rate;
    }
    
}//end of class
