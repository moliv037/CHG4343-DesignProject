public abstract class ReactorType
{
    private double T_0; // Initial Temperature [K]
    private double P_0;//initial pressure [atm]
    private double k_0; //rate constant at 450 K

    private Reaction reaction;

    public ReactorType (double T_0, double P_0, double k_0, Reaction reaction){
        if(T_0<=0.) System.exit(0); //has to be greater than 0 in K
        if(P_0<=0.) System.exit(0); //has to be greater than 0 in atm
        if(k_0<=0.) System.exit(0); //has to be greater than 0
        if(reaction==null)System.exit(0);

        this.T_0 = T_0;
        this.P_0 = P_0;
        this.k_0 = k_0;
        this.reaction = reaction;
    }// end of constructor

    public ReactorType (ReactorType source){
        if(source==null)System.exit(0);
        this.T_0 = source.T_0;
        this.P_0 = source.P_0;
        this.k_0 = source.k_0;
        this.reaction = source.reaction;
    } //end of copy constructor

    public abstract ReactorType clone ();

    public boolean setT_0 (double T_0){
        if(T_0<=0) return false;
        this.T_0 = T_0;
        return true;
    }//mutator for T_0

    public boolean setP_0 (double P_0){
        if(P_0<=0) return false;
        this.P_0 = P_0;
        return true;
    } //mutator for P_0

    public boolean setK_0 (double k_0){
        if(k_0<=0) return false;
        this.k_0 = k_0;
        return true;
    } //mutator for k_0

    public boolean setReaction (Reaction reaction){
        if(reaction==null)return false;
        this.reaction = reaction;
        return true;
    }// mutator for reaction object

    public double getT_0(){ return T_0;} //accessor for T_0
    public double getP_0(){ return P_0;} //accessor for P_0
    public double getK_0(){ return k_0;} //accessor for k_0
    public Reaction getReaction(){ return reaction;} //accessor for reaction object

    @Override
    public boolean equals(Object comparator) {
       if (comparator==null) return false;
       if (comparator.getClass() != this.getClass()) return false;
       boolean isEquals = true;
       if (((ReactorType)comparator).getT_0()!=this.getT_0()) isEquals = false;
       if (((ReactorType)comparator).getP_0()!=this.getP_0()) isEquals = false;
       if (((ReactorType)comparator).getK_0()!=this.getK_0()) isEquals = false;
       if (((ReactorType)comparator).getReaction()!=this.getReaction()) isEquals = false;
       return isEquals;
    }

    public abstract double calculateDeltaXDeltaW (double T_0, double P_0); // abstract for dX/dW
    public abstract double calculateDeltaPDeltaW (double T_0, double P_0);//abstract for dP/dW
    public abstract double calculateRateConstant(double k_0, double T_0); //abstract for rate constant equation


    /*public ReactorType (Reaction reaction)
    {
        if(reaction==null)System.exit(0);
        this.reaction=reaction.clone();
    }
    public ReactorType (ReactorType source)
    {
        if(source==null)System.exit(0);
        this.reaction=source.reaction.clone();
    }
    public abstract ReactorType clone ();
    public boolean setReaction (Reaction reaction)
    {
        if(reaction==null)return false;
        this.reaction=reaction.clone();
        return true;
    }
    public Reaction getReaction()
    {
        return this.reaction.clone();
    }
    public boolean equals (Object comparator)
    {
        if (comparator==null) return false;
        if (this.getClass()!=comparator.getClass()) return false;
        return this.reaction.equals(((Reaction)comparator.reaction);
    }*/


}//end of class