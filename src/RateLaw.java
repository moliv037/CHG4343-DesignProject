public abstract class RateLaw {
    //instance variable
    //constructors, copy constructors, clone, accessor, mutator, equals
    //methods

    public abstract double calculateRateConstant (double [] inputParameters);
    //I created getters for all the variables in the inputParameters array in the InputParameters class.
    // If you make an object of that class.get(Variable you want)() it works

    public abstract double calculateRate (double k);

}
