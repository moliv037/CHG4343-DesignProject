public class InputParameters {

    private double delH_rx;
    private double T0;
    private double P0;
    private double k_T0;
    private double E;
    private double alpha;
    private double v_0;

    public InputParameters(double[] params) {
        //[0]=Enthalpy of reaction delH_rx [J/mol]
        //[1]=initial temperature T_0 [K]
        //[2]=initial pressure P_0 [atm]
        //[3]=reaction rate constant at initial temperature k_T0 [L/mol/s]
        //[4]=Activation energy E [kJ/mol]
        //[5]=Pressure drop parameter alpha [1/kg]
        //[6]=Initial volumetric flow rate v_0 [L/s]

        if (params==null || params.length!=7) System.exit(0);//eventually replaced by thrown exception
        for (int i=0; i<params.length; i++) {if (params[i]<0.) System.exit(0);} //all values have to bigger than 0

        this.delH_rx = params[0];
        this.T0 = params[1];
        this.P0 = params[2];
        this.k_T0 = params[3];
        this.E = params[4];
        this.alpha = params[5];
        this.v_0 = params[6];
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

    }//end of copy constructor


}

