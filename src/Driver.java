import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //number of reactants
        System.out.println("Enter the number of reactants:");
        int numberReactants = scanner.nextInt(); //the number of reactants is variable so the user inputs the reactions first

        //reactant names
        String [] reactantNames = new String[numberReactants];// array of strings for the reactant names
        System.out.println("Enter the reactants, the first reactant entered is the limiting reactant:");
        for (int i = 0; i < numberReactants; i++) {
            System.out.println("Name of reactant " + (i+1) +":"); //prompt for every other reactant
            reactantNames[i] = scanner.next(); //each i populates the reactant array where A = reactants [0], B=reactants[1]...
        }

        //reactant coefficients
        int[] reactantCoefficients = new int[numberReactants];//array of integers of reactant coefficients
        System.out.println("Enter the coefficients for the reactants:");
        for (int i = 0; i < numberReactants; i++) {
            System.out.println("Coefficient of reactant " + (i+1) +":");
            reactantCoefficients[i] = scanner.nextInt();
        }

        //mole fractions of each reactant
        double [] reactantMoleFracs = new double[numberReactants]; //to store the mole fractions of each reactant
        System.out.println("Enter the mole fractions for the reactants:");
        for (int i = 0; i < numberReactants; i++) {
            System.out.println("Mole fraction of reactant " + (i+1) +":");
            reactantMoleFracs[i] = scanner.nextDouble();
        }

        //heat capacities of reactants
        double [] reactantHeatCapacities = new double[numberReactants];
        System.out.println("Enter the heat capacity for the reactants [J/mol/K]:");
        for (int i = 0; i < numberReactants; i++) {
            System.out.println("Heat capacity of reactant " + (i+1) +":");
            reactantHeatCapacities[i] = scanner.nextDouble();
        }

        //number of products
        System.out.println("Enter the number of products:");
        int numberProducts = scanner.nextInt();

        //product names
        String [] productNames = new String[numberProducts];
        System.out.println("Enter the product:");
        for (int i = 0; i < numberProducts; i++) {
            System.out.println("Name of product " + (i+1) +":");
            productNames[i] = scanner.next();
        }

        //product coefficients
        int[] productCoefficients = new int[numberProducts];
        System.out.println("Enter the coefficients for the product:");
        for (int i = 0; i < numberProducts; i++) {
            System.out.println("Coefficient of product " + (i+1) +":");
            productCoefficients[i] = scanner.nextInt();
        }

        //mole fractions of product
        double [] productMoleFracs = new double[numberProducts];
        System.out.println("Enter the mole fractions for the product:");
        for (int i = 0; i < numberProducts; i++) {
            System.out.println("Mole fraction of product " + (i+1) +":");
            productMoleFracs[i] = scanner.nextDouble();
        }

        //heat capacities of product
        double [] productHeatCapacities = new double[numberProducts];
        System.out.println("Enter the heat capacity for the product [J/mol/K]:");
        for (int i = 0; i < numberProducts; i++) {
            System.out.println("Heat capacity of product " + (i+1) +":");
            productHeatCapacities[i] = scanner.nextDouble();
        }
//print chemical equation
        System.out.println("This is the entered chemical reaction:");
        StringBuilder chemicalReaction = new StringBuilder();
        for (int i = 0; i < numberReactants; i++) {
            chemicalReaction.append(reactantCoefficients[i]).append(reactantNames[i]);
                    if (i<numberReactants-1) { chemicalReaction.append(" + "); }
        }
        chemicalReaction.append(" → ");
        for (int i = 0; i < numberProducts; i++) {
            chemicalReaction.append(productCoefficients[i]).append(productNames[i]);
            if (i<numberProducts-1) { chemicalReaction.append(" + "); }
        }
        System.out.println(chemicalReaction.toString());

        //inerts
        System.out.println("Enter the number of inerts:");//number of inerts
        int numberInerts = scanner.nextInt();

        String [] inerts = new String[numberInerts]; //name of inerts
        System.out.println("Enter the inerts:");
        for (int i = 0; i < numberInerts; i++) {
            System.out.println("Name of inert " + (i+1) +":");
            inerts[i] = scanner.next();
        }

        //inert mole fractions
        double [] inertMoleFracs = new double[numberInerts];
        System.out.println("Enter the mole fractions for the inerts:");
        for (int i = 0; i < numberInerts; i++) {
            System.out.println("Mole fraction of inert " + (i+1) +":");
            inertMoleFracs[i] = scanner.nextDouble();
        }
        //inert heat capacities
        double [] inertHeatCapacities = new double[numberInerts];
        System.out.println("Enter the heat capacity for the inerts [J/mol/K]:");
        for (int i = 0; i < numberInerts; i++) {
            System.out.println("Heat capacity of inert " + (i+1) +":");
            inertHeatCapacities[i] = scanner.nextDouble();
        }
        double [] inputParameters = new double[7];
        //[0]=Enthalpy of reaction delH_rx [J/mol]
        //[1]=initial temperature T_0 [K]
        //[2]=initial pressure P_0 [atm]
        //[3]=reaction rate constant at initial temperature k_T0 [L/mol/s]
        //[4]=Activation energy E [kJ/mol]
        //[5]=Pressure drop parameter alpha [1/kg]
        //[6]=Initial volumetric flow rate v_0 [L/s]
        System.out.println("Enter the enthalpy of reaction [J/mol]:");
        inputParameters[0] = scanner.nextDouble();
        System.out.println("Enter the initial temperature [K]:");
        inputParameters[1] = scanner.nextDouble();
        System.out.println("Enter the initial pressure [atm]:");
        inputParameters[2] = scanner.nextDouble();
        System.out.println("Enter the reaction rate constant at initial temperature [L/mol/s]:");
        inputParameters[3] = scanner.nextDouble();
        System.out.println("Enter the activation energy [kJ/mol]:");
        inputParameters[4] = scanner.nextDouble();
        System.out.println("Enter the pressure drop parameter [1/kg]:");
        inputParameters[5] = scanner.nextDouble();
        System.out.println("Initial volumetric flow rate [L/s]:");
        inputParameters[6] = scanner.nextDouble();


        String type;
        System.out.println("Please enter either adiabatic or isothermal for the type of reactor:");
        type=scanner.next();


// Rest of code for output
        //code is missing value checks
    }//end of main

}//end of class
