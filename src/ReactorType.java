public abstract class ReactorType {
    private RateLaw g_rateLaw;
    private Reaction g_reaction;
    private InputParameters input;// object to access T0, P0, k_T0, E, alpha, V_0 and type
    private StaticParameters parameters;// object to access numberReactants,numberProducts, numberInerts,
    // [] reactantCoefficients,[] productCoefficients,[] inertCoefficients,[] reactantMoleFracs,[] productMoleFracs,[] inertMoleFracs
    // [] reactantHeatCapacities, [] productHeatCapacities, [] inertHeatCapacities;
    //CA_0, FA_0, epsilon, [] theta_reactants, [] theta_products;

    //constructor, copy constructor, abstract clone, accessor, mutator, equals, global variable methods
    public ReactorType (InputParameters input, StaticParameters parameters, Reaction reaction, double k){
        if (input==null)System.exit(0);
        this.input = input.clone();

        if (parameters==null)System.exit(0);
        this.parameters = parameters.clone();

        this.resetGlobalVariables();

    }
    public ReactorType (ReactorType source){
        if(source==null)System.exit(0);
        this.input=source.input.clone();
        this.parameters=source.parameters.clone();
        
        if(source.g_rateLaw==null)this.g_rateLaw=null;
        else this.g_rateLaw=source.g_rateLaw.clone();

        if (source.g_reaction==null)this.g_reaction=null;
        else this.g_reaction=source.g_reaction.clone();

    }
    public abstract ReactorType clone();

    protected void resetGlobalVariables()
    {
        this.g_rateLaw=null;
        this.g_reaction=null;
    }

    protected void setGlobalVariables (RateLaw rateLaw, Reaction reaction)
    {
        this.g_rateLaw=rateLaw.clone();
        this.g_reaction=reaction.clone();
    }

    public InputParameters getInput()
    {
        return this.input.clone();
    }

    public StaticParameters getParameters()
    {
        return this.parameters.clone();
    }

    public boolean setInput (InputParameters input)
    {
        if(input==null)return false;
        this.input=input.clone();
        return true;
    }
    
    public boolean setParameters (StaticParameters parameters)
    {
        if(parameters==null)return false;
        this.parameters=parameters.clone();
        return true;
    }

    public boolean equals (Object comparator)
    {
        if (comparator==null) return false;
        if (comparator.getClass()!=this.getClass()) return false;
        boolean isEquals=true;
        if (!(((ReactorType)comparator).input.equals(this.input))) isEquals=false;
        if (!(((ReactorType)comparator).parameters.equals(this.parameters))) isEquals=false;
        if (this.g_rateLaw!=null && ((ReactorType)comparator).g_rateLaw==null) isEquals=false;
        if (this.g_rateLaw==null && ((ReactorType)comparator).g_rateLaw!=null) isEquals=false;
        if (this.g_rateLaw!=null)
            if (!((ReactorType)comparator).g_rateLaw.equals(this.g_rateLaw)) isEquals=false;
        if (this.g_reaction!=null && ((ReactorType)comparator).g_reaction==null) isEquals=false;
        if (this.g_reaction==null && ((ReactorType)comparator).g_reaction!=null) isEquals=false;
        if (this.g_reaction!=null)
            if (!((ReactorType)comparator).g_reaction.equals(this.g_reaction)) isEquals=false;
        return isEquals;
    }

    //abstract method headers to be overridden in the children
    public abstract double[] calculateX (RateLaw rateLaw, double [] inputParameters, double w);
    public abstract double[] calculateT (RateLaw rateLaw, double [] inputParameters, double w);
    public abstract double[] calculateP (RateLaw ratelaw, double [] inputParameters, double w);

    //parent methods
    protected double returnRateLaw (double X, double P, double T) {
        if (this.g_rateLaw == null) System.exit(0); //this will eventually be replaced with a thrown exception
        return this.g_rateLaw.calculateRateLaw(X,P,T);
    }
    protected double returnFiCpi(double X) {
        if (this.g_reaction == null) System.exit(0);
        return this.g_reaction.calculate_FiCPi(X);
    }
}
