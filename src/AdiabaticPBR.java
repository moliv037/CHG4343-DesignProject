public class AdiabaticPBR extends ReactorType {

    private double Cp_A; // [J/mol/K]
    private double Cp_B; // [J/mol/K]
    private double Cp_C; //[J/mol/K]
    private double Cp_I; //[J/mol/K] for inert

    public AdiabaticPBR (double T_0, double P_0, double k_0, Reaction reaction,double Cp_A, double Cp_B, double Cp_C, double Cp_I) {
        super(T_0, P_0, k_0, reaction); //from parent class

        //Assume no phase change and reaction does not reach absolute 0 so specific heat capacity should always be greater than 0
        if (Cp_A <= 0.) System.exit(0);
        if (Cp_B <= 0.) System.exit(0);
        if (Cp_C <= 0.) System.exit(0);
        if (Cp_I <= 0.) System.exit(0);

        this.Cp_A = Cp_A;
        this.Cp_B = Cp_B;
        this.Cp_C = Cp_C;
        this.Cp_I = Cp_I;

    }//end of constructor

    public AdiabaticPBR (AdiabaticPBR source){
        super(source);
        this.Cp_A = source.Cp_A;
        this.Cp_B = source.Cp_B;
        this.Cp_C = source.Cp_C;
        this.Cp_I = source.Cp_I;
    }//end of copy constructor

    public AdiabaticPBR clone () { return new AdiabaticPBR(this); } //clone

    public boolean setCp_A(double Cp_A) {
        if (Cp_A <= 0.) return false;
        this.Cp_A = Cp_A;
        return true;
    }
    public boolean setCp_B(double Cp_B) {
        if (Cp_B <= 0.) return false;
        this.Cp_B = Cp_B;
        return true;
    }
    public boolean setCp_C(double Cp_C) {
        if (Cp_C <= 0.) return false;
        this.Cp_C = Cp_C;
        return true;
    }
    public boolean setCp_I(double Cp_I) {
        if (Cp_I <= 0.) return false;
        this.Cp_I = Cp_I;
        return true;
    }
    public double getCp_A() { return Cp_A; }
    public double getCp_B() { return Cp_B; }
    public double getCp_C() { return Cp_C; }
    public double getCp_I() { return Cp_I; }


    @Override
    public boolean equals(Object comparator) {
        return super.equals(comparator);
        if (((AdiabaticPBR)comparator).Cp_A != this.Cp_A) return false;
        if (((AdiabaticPBR)comparator).Cp_B != this.Cp_B) return false;
        if (((AdiabaticPBR)comparator).Cp_C != this.Cp_C) return false;
        if (((AdiabaticPBR)comparator).Cp_I != this.Cp_I) return false;
        return true;
    }//end of equals method

    @Override
    public double calculateRateConstant(double k_0, double T_0, double T) {
        double E = 31.4; //[kJ/mol]
        double R = 8.3145; // [J/mol/K]

        return (super.getK_0()*Math.exp((E*1000/R)*(1.0/super.getT_0()-1.0/T)));
    }// for the rate constant

    public double calculateDeltaXDeltaW (double T_0, double P_0){

    }


}//end of class

