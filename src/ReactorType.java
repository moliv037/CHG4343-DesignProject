public abstract class ReactorType {
    private RateLaw g_rateLaw;
    private InputParameters input;// object to access T0, P0, k_T0, E, alpha, V_0 and type
    private StaticParameters parameters;// object to access numberReactants,numberProducts, numberInerts,
    // [] reactantCoefficients,[] productCoefficients,[] inertCoefficients,[] reactantMoleFracs,[] productMoleFracs,[] inertMoleFracs
    // [] reactantHeatCapacities, [] productHeatCapacities, [] inertHeatCapacities;
    //CA_0, FA_0, epsilon, [] theta_reactants, [] theta_products;
    private Reaction reaction;
    private double k;


    //constructor, copy constructor, abstract clone, accessor, mutator, equals, global variable methods
    public ReactorType (InputParameters input, StaticParameters parameters, Reaction reaction, double k){
        if (input==null)System.exit(0);
        this.input = input.clone();

        if (parameters==null)System.exit(0);
        this.parameters = parameters.clone();

        if(reaction==null)System.exit(0);
        this.reaction = reaction.clone();

        if (k<0.)System.exit(0);
        this.k = k;

        this.resetGlobalVariables();

    }
    public ReactorType (ReactorType source){
        if(source==null)System.exit(0);
        this.input=source.input.clone();
        this.parameters=source.parameters.clone();
        this.reaction=source.reaction.clone();
        this.k=source.k;
        
        if(source.g_rateLaw==null)this.g_rateLaw=null;
        else
            this.g_rateLaw=source.g_rateLaw.clone();

    }
    public abstract ReactorType clone();

    protected void resetGlobalVariables()
    {
        this.g_rateLaw=null;
    }

    protected void setGlobalVariables (RateLaw rateLaw)
    {
        this.g_rateLaw=rateLaw.clone();
    }

    public InputParameters getInput()
    {
        return this.input.clone();
    }

    public StaticParameters getParameters()
    {
        return this.parameters.clone();
    }

    public Reaction getReaction() {
        return this.reaction.clone();
    }

    public double getK()
    {
        return this.k;
    }

public boolean setK(double k)
    {
    if(k<0.)
        return false;
    this.k=k;
    return true;
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

    public boolean setReaction (Reaction reaction) {
        if(reaction==null)return false;
        this.reaction=reaction.clone();
        return true;
    }

    public boolean equals (Object comparator)
    {
        if (comparator==null) return false;
        if (comparator.getClass()!=this.getClass()) return false;
        boolean isEquals=true;
        if (!(((ReactorType)comparator).input.equals(this.input)))
            isEquals=false;
        if (!(((ReactorType)comparator).parameters.equals(this.parameters)))
        isEquals=false;
        if (((ReactorType) comparator).k!=this.k)isEquals=false;
        return isEquals;
    }

    //abstract method headers to be overridden in the children
    public abstract double[] calculateX (RateLaw rateLaw, double [] inputParameters, double w);
    public abstract double[] calculateT (RateLaw rateLaw, double [] inputParameters, double w);
    public abstract double[] calculateP (RateLaw ratelaw, double [] inputParameters, double w);

    //parent method
    public double returnRateLaw (double X) {
        if (this.g_rateLaw == null) System.exit(0); //this will eventually be replaced with a thrown exception
        return this.g_rateLaw.calculateRate(this.k, X);
    }
    public double returnFiCpi(double X) {
        //fill in code
    }
}
