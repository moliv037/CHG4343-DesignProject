//This class will pass the necessary checks to the input parameters instead of doing it in the driver class.
//To access these parameters, create an object of the InputParameter class and use .getVariable().

public class InputParameters {

    private double delH_rx;
    private double T0;
    private double P0;
    private double k_T0;
    private double E;
    private double alpha;
    private double v_0;
    private String type;

    public InputParameters(double[] params, String type) {
        //[0]=Enthalpy of reaction delH_rx [J/mol]
        //[1]=initial temperature T_0 [K]
        //[2]=initial pressure P_0 [atm]
        //[3]=reaction rate constant at initial temperature k_T0 [L/mol/s]
        //[4]=Activation energy E [kJ/mol]
        //[5]=Pressure drop parameter alpha [1/kg]
        //[6]=Initial volumetric flow rate v_0 [L/s]

        if (params==null || params.length!=7) System.exit(0);//eventually replaced by thrown exception
        for (int i=0; i<params.length; i++) {if (params[i]<0.) System.exit(0);} //all values have to bigger than 0
        if (!type.equals("adiabatic")||!type.equals("isothermal")) System.exit(0);
        //check that the type is either adiabatic or isothermal
        this.delH_rx = params[0];
        this.T0 = params[1];
        this.P0 = params[2];
        this.k_T0 = params[3];
        this.E = params[4];
        this.alpha = params[5];
        this.v_0 = params[6];
        this.type = type;
    }//end of constructor

    public InputParameters(InputParameters source) {
        if(source==null) System.exit(0);
        this.delH_rx = source.delH_rx;
        this.T0 = source.T0;
        this.P0 = source.P0;
        this.k_T0 = source.k_T0;
        this.E = source.E;
        this.alpha = source.alpha;
        this.v_0 = source.v_0;
        this.type = source.type;

    }//end of copy constructor

    public InputParameters clone() {return new InputParameters(this);}//clone

    public boolean setParameters(double[] params, String type) {
        if (params==null || params.length!=7) return false;
        for (int i=0; i<params.length; i++) {if (params[i]<0.) return false;} //all values have to bigger than 0
        if (!type.equals("adiabatic")||!type.equals("isothermal")) return false;
        this.delH_rx = params[0];
        this.T0 = params[1];
        this.P0 = params[2];
        this.k_T0 = params[3];
        this.E = params[4];
        this.alpha = params[5];
        this.v_0 = params[6];
        this.type = type;
        return true;
    }

    public double getDelH_rx() {return delH_rx;}
    public double getT0() {return T0;}
    public double getP0() {return P0;}
    public double getK_T0() {return k_T0;}
    public double getE() {return E;}
    public double getAlpha() {return alpha;}
    public double getV_0() {return v_0;}
    public String getType() {return type;}

    public boolean equals (Object comparator){
        if (comparator==null) return false;
        if(comparator.getClass()!=this.getClass()) return false;
        boolean isEquals=true;
        if(((InputParameters)comparator).delH_rx!=this.delH_rx) isEquals=false;
        if(((InputParameters)comparator).T0!=this.T0) isEquals=false;
        if(((InputParameters)comparator).P0!=this.P0) isEquals=false;
        if(((InputParameters)comparator).k_T0!=this.k_T0) isEquals=false;
        if(((InputParameters)comparator).E!=this.E) isEquals= false;
        if(((InputParameters)comparator).alpha!=this.alpha) isEquals= false;
        if(((InputParameters)comparator).v_0!=this.v_0) isEquals=false;


    }

}

