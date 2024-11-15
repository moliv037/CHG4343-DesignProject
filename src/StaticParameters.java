//The StaticParameters class calculates all the variables that are used in other classes that do not change, notably the initial conditions.
//This class will pass the necessary checks to the "static" parameters instead of doing it in the driver class.
//To access these parameters, create an object of the StaticParameter class and use .getVariable().

public class StaticParameters {

    //from driver inputs"
    private int numberReactants;
    private int numberProducts;
    private int numberInerts;
    private int [] reactantCoefficients;
    private int [] productCoefficients;
    private int [] inertCoefficients;
    private double [] reactantMoleFracs;
    private double [] productMoleFracs;
    private double [] inertMoleFracs;
    private double [] reactantHeatCapacities;
    private double [] productHeatCapacities;
    private double [] inertHeatCapacities;
    private InputParameters input;
    /* public double getDelH_rx() {return delH_rx;}
    public double getT0() {return T0;}
    public double getP0() {return P0;}
    public double getK_T0() {return k_T0;}
    public double getE() {return E;}
    public double getAlpha() {return alpha;}
    public double getV_0() {return v_0;}
    public String getType() {return type;}*/


    //calculated each call, for short calc do in constructor if not do setter like epsilon
    private double CA_0;
    private double FA_0;
    private double epsilon;
    private double [] theta_reactants;
    private double [] theta_products;


    public StaticParameters (InputParameters input,int numberReactants, int numberProducts, int numberInerts, int [] reactantCoefficients, int [] productCoefficients, int [] inertCoefficients, double [] reactantMoleFracs, double [] productMoleFracs, double [] inertMoleFracs, double[] reactantHeatCapacities, double[] productHeatCapacities, double[] inertHeatCapacities) {
        if (input == null) System.exit(0);
                //check arrays are not null
        if (reactantCoefficients==null) System.exit(0);
        if (productCoefficients==null) System.exit(0);
        if (inertCoefficients==null) System.exit(0);
        if(reactantMoleFracs==null) System.exit(0);
        if(productMoleFracs==null) System.exit(0);
        if(inertMoleFracs==null) System.exit(0);
        if(reactantHeatCapacities==null) System.exit(0);
        if(productHeatCapacities==null) System.exit(0);
        if(inertHeatCapacities==null) System.exit(0);
        if (numberReactants <= 0 || numberProducts <= 0 || numberInerts < 0) System.exit(0);
        //check that the arrays cant have 0 elements
        if(reactantCoefficients.length == 0 || productCoefficients.length == 0||inertCoefficients.length==0) System.exit(0); //need coefficients
        if (reactantMoleFracs.length == 0 || productMoleFracs.length == 0 || inertMoleFracs.length==0) System.exit(0);
        if(reactantHeatCapacities.length == 0 || productHeatCapacities.length == 0 || inertHeatCapacities.length==0) System.exit(0);
        //check that coefficients are bigger than 0
        //this syntax was suggested by java when "cleaning up" the code, the result is the same as the syntax learned in class.
        //I kept the syntax learned in class for all the other lines.
        for (int reactantCoefficient : reactantCoefficients) if (reactantCoefficient <= 0) System.exit(0);
        for (int productCoefficient : productCoefficients) if (productCoefficient <= 0) System.exit(0);
        for (int inertCoefficient : inertCoefficients) if (inertCoefficient <= 0) System.exit(0);
        //check that no mole fraction or heat capacities are smaller than 0
        for (double reactantMoleFrac : reactantMoleFracs) if (reactantMoleFrac < 0.) System.exit(0);
        for (double productMoleFrac : productMoleFracs) if (productMoleFrac < 0.) System.exit(0);
        for (double inertMoleFrac : inertMoleFracs) if (inertMoleFrac < 0.) System.exit(0);
        for (double reactantHeatCapacity : reactantHeatCapacities) if (reactantHeatCapacity < 0.) System.exit(0);
        for (double productHeatCapacity : productHeatCapacities) if (productHeatCapacity < 0.) System.exit(0);
        for (double inertHeatCapacity : inertHeatCapacities) if (inertHeatCapacity < 0.) System.exit(0);
        //check that the sum of mole fractions in and out are 1
        for (int i=0; i < reactantMoleFracs.length; i++) {
            double sum = 0.0;
            sum += reactantMoleFracs[i] + inertMoleFracs[i];
            if (sum != 1.0) System.exit(0);}
        for (int i = 0; i < productMoleFracs.length; i++) {
            double sum = 0.0;
            sum += productMoleFracs[i] + inertMoleFracs[i];
            if (sum != 1.0) System.exit(0);}

        this.input = input.clone();
        this.numberReactants = numberReactants;
        this.numberProducts = numberProducts;
        this.numberInerts = numberInerts;
        this.reactantCoefficients=new int [reactantCoefficients.length];
        for (int i=0; i < reactantCoefficients.length; i++) this.reactantCoefficients[i]=reactantCoefficients[i];
        this.productCoefficients=new int [productCoefficients.length];
        for (int i=0; i<productCoefficients.length; i++) this.productCoefficients[i]=productCoefficients[i];
        this.inertCoefficients=new int [inertCoefficients.length];
        for (int i=0; i<inertCoefficients.length; i++) this.inertCoefficients[i]=inertCoefficients[i];
        this.reactantMoleFracs = new double[reactantMoleFracs.length];
        for (int j=0; j < reactantMoleFracs.length; j++) this.reactantMoleFracs[j]=reactantMoleFracs[j];
        this.productMoleFracs = new double[productMoleFracs.length];
        for (int i=0; i < productMoleFracs.length; i++) this.productMoleFracs[i]=productMoleFracs[i];
        this.inertMoleFracs = new double[inertMoleFracs.length];
        for (int i=0; i < inertMoleFracs.length; i++) this.inertMoleFracs[i]= inertMoleFracs[i];
        this.reactantHeatCapacities = new double[reactantHeatCapacities.length];
        for (int i=0; i < reactantHeatCapacities.length; i++) this.reactantHeatCapacities[i]=reactantHeatCapacities[i];
        this.productHeatCapacities = new double[productHeatCapacities.length];
        for (int i=0; i < productHeatCapacities.length; i++) this.productHeatCapacities[i]=productHeatCapacities[i];
        this.inertHeatCapacities = new double[inertHeatCapacities.length];
        for (int i=0; i<inertHeatCapacities.length; i++) this.inertHeatCapacities[i]=inertHeatCapacities[i];




        // these are calculated but stored as instance variables to access through an object by other classes
        //if all the checks above are correct, there should be there can be no error in the calculations
        //small calculations are done directly, larger ones are in a set method
        this.CA_0 = (this.reactantMoleFracs[0]*input.getP0())/(0.0826*input.getT0()); // here R = 0.08206 [L*atm/mol/K]
        this.FA_0 = (this.getCA_0()*input.getV_0());

        this.epsilon = setEpsilon(); //epsilon can be 0, negative, or positive

        if (theta_reactants==null) System.exit(0);
        if (theta_products==null) System.exit(0);

        //check that theta is not negative (they can be 0)
        for (int i = 0; i < theta_reactants.length; i++) if (theta_reactants[i] < 0.) System.exit(0);
        for (int i=0; i<theta_products.length; i++) if (theta_products[i] < 0.) System.exit(0);

        this.theta_reactants = new double[theta_reactants.length];
        for (int i = 0; i < theta_reactants.length; i++)  this.theta_reactants [i] = setTheta_reactants()[i];
        this.theta_products = new double[theta_products.length];
        for (int i = 0; i < theta_products.length; i++) this.theta_products[i] = setTheta_products()[i];

    }//end of constructor, need to add throw exceptions



    public StaticParameters (StaticParameters source){
        if (source == null) System.exit(0);
        this.input=source.input.clone(); //clone because it is an object *check this**
        this.numberReactants = source.numberReactants;
        this.numberProducts = source.numberProducts;
        this.numberInerts = source.numberInerts;
        this.CA_0 = source.CA_0;
        this.FA_0 = source.FA_0;
        this.epsilon = source.epsilon;

        this.reactantCoefficients=new int [source.reactantCoefficients.length];
        for (int i=0; i<reactantCoefficients.length; i++) this.reactantCoefficients[i]=source.reactantCoefficients[i];
        this.productCoefficients=new int [source.productCoefficients.length];
        for (int i=0; i<productCoefficients.length; i++) this.productCoefficients[i]=source.productCoefficients[i];
        this.inertCoefficients=new int [source.inertCoefficients.length];
        for (int i=0; i<inertCoefficients.length; i++) this.inertCoefficients[i]=source.inertCoefficients[i];

        this.reactantMoleFracs = new double [source.reactantMoleFracs.length];
        for (int i=0; i<reactantMoleFracs.length; i++) this.reactantMoleFracs[i]=source.reactantMoleFracs[i];
        this.productMoleFracs = new double [source.productMoleFracs.length];
        for (int i=0; i<productMoleFracs.length;i++) this.productMoleFracs[i]=source.productMoleFracs[i];
        this.inertMoleFracs = new double [source.inertMoleFracs.length];
        for (int i=0; i<inertMoleFracs.length;i++) this.inertMoleFracs[i]=source.inertMoleFracs[i];

        this.reactantHeatCapacities = new double[source.reactantHeatCapacities.length];
        for (int i=0; i<reactantHeatCapacities.length;i++) this.reactantHeatCapacities[i] = source.reactantHeatCapacities[i];
        this.productHeatCapacities = new double[source.productHeatCapacities.length];
        for (int i=0; i<productHeatCapacities.length;i++) this.productHeatCapacities[i]=source.productHeatCapacities[i];
        this.inertHeatCapacities = new double[source.inertHeatCapacities.length];
        for (int i=0; i<inertHeatCapacities.length;i++) this.inertHeatCapacities[i]=source.inertHeatCapacities[i];

        this.theta_reactants = new double [source.theta_reactants.length];
        for (int i=0; i<theta_reactants.length; i++) this.theta_reactants[i] = source.theta_reactants [i];
        this.theta_products = new double [source.theta_products.length];
        for (int i=0; i<theta_products.length;i++) this.theta_products [i] = source.theta_products [i];

    }// end of copy constructor


    public StaticParameters clone() {return new StaticParameters(this);} //clone

    //setter for calculations here for those too big for constructor
    public double setEpsilon() {
        double deltaProducts=0.;
        double deltaReactants = 0.;
        double a = this.reactantCoefficients[0]; //the coefficient of the limiting reactant is [0]

        for (int i=0; i<this.productCoefficients.length; i++){deltaProducts += this.productCoefficients[i]/a;}
        for (int i=0; i<this.reactantCoefficients.length; i++){deltaReactants -= this.reactantCoefficients[i]/a;}

        double delta= deltaProducts+deltaReactants;
        return (this.reactantMoleFracs[0]*delta);
    }

    public double [] setTheta_reactants(){
        double yA0 = this.reactantMoleFracs[0];
        double [] thetas = new double [this.numberReactants];
        for (int i = 0; i<this.numberReactants; i++){
            thetas[i] = this.reactantMoleFracs[i]/yA0;
        }
        return thetas;
    }
    public double [] setTheta_products(){
        double yA0 = this.reactantMoleFracs[0];
        double [] thetas = new double [this.numberProducts];
        for (int i = 0; i<this.numberProducts; i++){
            thetas[i] = this.productMoleFracs[i]/yA0;
        }
        return thetas;
    }


//start of
    public boolean setStaticParameters(InputParameters input,int numberReactants, int numberProducts, int numberInerts, int [] reactantCoefficients, int [] productCoefficients, int [] inertCoefficients, double [] reactantMoleFracs, double [] productMoleFracs, double [] inertMoleFracs, double[] reactantHeatCapacities, double[] productHeatCapacities, double[] inertHeatCapacities) {
        if (input == null) return false;
        //check arrays are not null
        if (reactantCoefficients==null) return false;
        if (productCoefficients==null) return false;
        if (inertCoefficients==null) return false;
        if(reactantMoleFracs==null) return false;
        if(productMoleFracs==null) return false;
        if(inertMoleFracs==null) return false;
        if(reactantHeatCapacities==null) return false;
        if(productHeatCapacities==null) return false;
        if(inertHeatCapacities==null) return false;
        if (numberReactants <= 0 || numberProducts <= 0 || numberInerts < 0) return false;
        //check that the arrays cant have 0 elements
        if(reactantCoefficients.length == 0 || productCoefficients.length == 0||inertCoefficients.length==0) return false; //need coefficients
        if (reactantMoleFracs.length == 0 || productMoleFracs.length == 0 || inertMoleFracs.length==0) return false;
        if(reactantHeatCapacities.length == 0 || productHeatCapacities.length == 0 || inertHeatCapacities.length==0) return false;
        //check that coefficients are bigger than 0
        //this syntax was suggested by java when "cleaning up" the code, the result is the same as the syntax learned in class.
        //I kept the syntax learned in class for all the other lines.
        for (int reactantCoefficient : reactantCoefficients) if (reactantCoefficient <= 0) return false;
        for (int productCoefficient : productCoefficients) if (productCoefficient <= 0) return false;
        for (int inertCoefficient : inertCoefficients) if (inertCoefficient <= 0) return false;
        //check that no mole fraction or heat capacities are smaller than 0
        for (double reactantMoleFrac : reactantMoleFracs) if (reactantMoleFrac < 0.) return false;
        for (double productMoleFrac : productMoleFracs) if (productMoleFrac < 0.) return false;
        for (double inertMoleFrac : inertMoleFracs) if (inertMoleFrac < 0.) return false;
        for (double reactantHeatCapacity : reactantHeatCapacities) if (reactantHeatCapacity < 0.) return false;
        for (double productHeatCapacity : productHeatCapacities) if (productHeatCapacity < 0.) return false;
        for (double inertHeatCapacity : inertHeatCapacities) if (inertHeatCapacity < 0.)return false;
        //check that the sum of mole fractions in and out are 1
        for (int i=0; i < reactantMoleFracs.length; i++) {
            double sum = 0.0;
            sum += reactantMoleFracs[i] + inertMoleFracs[i];
            if (sum != 1.0) return false;}
        for (int i = 0; i < productMoleFracs.length; i++) {
            double sum = 0.0;
            sum += productMoleFracs[i] + inertMoleFracs[i];
            if (sum != 1.0) return false;}

        this.input = input.clone();
        this.numberReactants = numberReactants;
        this.numberProducts = numberProducts;
        this.numberInerts = numberInerts;
        this.reactantCoefficients=new int [reactantCoefficients.length];
        for (int i=0; i < reactantCoefficients.length; i++) this.reactantCoefficients[i]=reactantCoefficients[i];
        this.productCoefficients=new int [productCoefficients.length];
        for (int i=0; i<productCoefficients.length; i++) this.productCoefficients[i]=productCoefficients[i];
        this.inertCoefficients=new int [inertCoefficients.length];
        for (int i=0; i<inertCoefficients.length; i++) this.inertCoefficients[i]=inertCoefficients[i];
        this.reactantMoleFracs = new double[reactantMoleFracs.length];
        for (int j=0; j < reactantMoleFracs.length; j++) this.reactantMoleFracs[j]=reactantMoleFracs[j];
        this.productMoleFracs = new double[productMoleFracs.length];
        for (int i=0; i < productMoleFracs.length; i++) this.productMoleFracs[i]=productMoleFracs[i];
        this.inertMoleFracs = new double[inertMoleFracs.length];
        for (int i=0; i < inertMoleFracs.length; i++) this.inertMoleFracs[i]= inertMoleFracs[i];
        this.reactantHeatCapacities = new double[reactantHeatCapacities.length];
        for (int i=0; i < reactantHeatCapacities.length; i++) this.reactantHeatCapacities[i]=reactantHeatCapacities[i];
        this.productHeatCapacities = new double[productHeatCapacities.length];
        for (int i=0; i < productHeatCapacities.length; i++) this.productHeatCapacities[i]=productHeatCapacities[i];
        this.inertHeatCapacities = new double[inertHeatCapacities.length];
        for (int i=0; i<inertHeatCapacities.length; i++) this.inertHeatCapacities[i]=inertHeatCapacities[i];




        // these are calculated but stored as instance variables to access through an object by other classes
        //if all the checks above are correct, there should be there can be no error in the calculations
        //small calculations are done directly, larger ones are in a set method
        this.CA_0 = (this.reactantMoleFracs[0]*input.getP0())/(0.0826*input.getT0()); // here R = 0.08206 [L*atm/mol/K]
        this.FA_0 = (this.getCA_0()*input.getV_0());

        this.epsilon = setEpsilon(); //epsilon can be 0, negative, or positive

        if (theta_reactants==null) return false;
        if (theta_products==null) return false;

        //check that theta is not negative (they can be 0)
        for (int i = 0; i < theta_reactants.length; i++) if (theta_reactants[i] < 0.) return false;
        for (int i=0; i<theta_products.length; i++) if (theta_products[i] < 0.) return false;

        this.theta_reactants = new double[theta_reactants.length];
        for (int i = 0; i < theta_reactants.length; i++)  this.theta_reactants [i] = setTheta_reactants()[i];
        this.theta_products = new double[theta_products.length];
        for (int i = 0; i < theta_products.length; i++) this.theta_products[i] = setTheta_products()[i];

        return true;
    }//mutator, need to add throw exceptions

    public InputParameters getInput () {return this.input.clone();}
    public int getNumberReactants (){return this.numberReactants;}
    public int getNumberProducts (){return this.numberProducts;}
    public int getNumberInerts (){return this.numberInerts;}

    public int[] getReactantCoefficients() {
        int [] copy = new int [reactantCoefficients.length];
        for (int i = 0; i < reactantCoefficients.length; i++) copy [i]= this.reactantCoefficients[i];
        return copy;}
    public int[] getProductCoefficients() {
        int [] copy = new int [productCoefficients.length];
        for (int i = 0; i < productCoefficients.length; i++) copy [i]= this.productCoefficients[i];
        return copy;}
    public int[] getInertCoefficients() {
        int [] copy = new int [inertCoefficients.length];
        for (int i=0; i<inertCoefficients.length; i++) copy [i]= this.inertCoefficients[i];
        return copy;    }

    public double [] getReactantMoleFracs(){
        double [] copy = new double [reactantMoleFracs.length];
        for (int i=0; i<reactantMoleFracs.length; i++) copy [i]= this.reactantMoleFracs[i];
        return copy;}
    public double [] getProductMoleFracs(){
        double [] copy = new double [productMoleFracs.length];
        for (int i=0; i<productMoleFracs.length; i++) copy [i]= this.productMoleFracs[i];
        return copy;}
    public double[] getInertMoleFracs(){
        double [] copy = new double [inertMoleFracs.length];
        for (int i=0; i<inertMoleFracs.length; i++) copy [i]= this.inertMoleFracs[i];
        return copy;}

    public double[] getReactantHeatCapacities(){
        double [] copy = new double [reactantHeatCapacities.length];
        for (int i=0; i<reactantHeatCapacities.length; i++) copy [i]= this.reactantHeatCapacities[i];
        return copy;}
    public double[] getProductHeatCapacities(){
        double [] copy = new double [productHeatCapacities.length];
        for (int i=0; i<productHeatCapacities.length; i++) copy [i]= this.productHeatCapacities[i];
        return copy;}
    public double[] getInertHeatCapacities(){
        double [] copy = new double [inertHeatCapacities.length];
        for (int i=0; i<inertHeatCapacities.length; i++) copy [i]= this.inertHeatCapacities[i];
        return copy;}

    public double getCA_0 (){return this.CA_0;}
    public double getFA_0 (){return this.FA_0;}
    public double getEpsilon (){return this.epsilon;} //gets the number from the setter

    public double [] getTheta_reactants(){
        double [] copy = new double [theta_reactants.length];
        for (int i=0; i<theta_reactants.length; i++) copy [i] = this.theta_reactants[i];
    return copy;}
    public double [] getTheta_products(){
        double [] copy = new double [theta_products.length];
        for (int i=0; i<theta_products.length; i++) copy [i] = this.theta_products[i];
    return copy;}

    @Override
    public boolean equals(Object comparator) {
        if(comparator ==null) return false;
        if (comparator.getClass() != this.getClass()) return false;
        if (((StaticParameters)comparator).reactantCoefficients.length != this.reactantCoefficients.length) return false;
        if (((StaticParameters)comparator).productCoefficients.length != this.productCoefficients.length) return false;
        if (((StaticParameters)comparator).inertCoefficients.length != this.inertCoefficients.length) return false;
        if (((StaticParameters)comparator).reactantMoleFracs.length != this.reactantMoleFracs.length) return false;
        if (((StaticParameters)comparator).productMoleFracs.length != this.productMoleFracs.length) return false;
        if (((StaticParameters)comparator).inertMoleFracs.length != this.inertMoleFracs.length) return false;
        if (((StaticParameters)comparator).reactantHeatCapacities.length != this.reactantHeatCapacities.length) return false;
        if (((StaticParameters)comparator).productHeatCapacities.length != this.productHeatCapacities.length) return false;
        if (((StaticParameters)comparator).inertHeatCapacities.length != this.inertHeatCapacities.length) return false;
        if (((StaticParameters)comparator).theta_reactants.length != this.theta_reactants.length) return false;
        if (((StaticParameters)comparator).theta_products.length != this.theta_products.length) return false;


        if (((StaticParameters)comparator).input != this.input) return false;
        if (((StaticParameters)comparator).numberReactants != this.numberReactants) return false;
        if (((StaticParameters)comparator).numberProducts != this.numberProducts) return false;
        if (((StaticParameters)comparator).numberInerts != this.numberInerts) return false;

        if (!(((StaticParameters)comparator).reactantCoefficients.equals(this.reactantCoefficients))) return false;
        if (!(((StaticParameters)comparator).productCoefficients.equals(this.productCoefficients))) return false;
        if (!(((StaticParameters)comparator).inertCoefficients.equals(this.inertCoefficients))) return false;

        if (!(((StaticParameters)comparator).reactantMoleFracs.equals(this.reactantMoleFracs))) return false;
        if(!(((StaticParameters)comparator).productMoleFracs.equals(this.productMoleFracs))) return false;
        if(!(((StaticParameters)comparator).inertMoleFracs.equals(this.inertMoleFracs))) return false;

        if(!(((StaticParameters)comparator).reactantHeatCapacities.equals(this.reactantHeatCapacities))) return false;
        if(!(((StaticParameters)comparator).productHeatCapacities.equals(this.productHeatCapacities))) return false;
        if(!(((StaticParameters)comparator).inertHeatCapacities.equals(this.inertHeatCapacities))) return false;

        if(!(((StaticParameters)comparator).theta_reactants.equals(this.theta_reactants))) return false;
        if(!(((StaticParameters)comparator).theta_products.equals(this.theta_products))) return false;

        return true;
    }//end of equals

    /*fix related problems */

}//end of class

