public abstract class ReactorType {
    private RateLaw g_rateLaw;
    private double [] inputParameters;
    private double k;

    //constructor, copy constructor, abstract clone, accessor, mutator, equals, global variable stuff

    //abstract method headers to be overridden in the children
    public abstract double calculateX (RateLaw rateLaw, double [] inputParameters, double w);
    public abstract double calculateT (RateLaw rateLaw, double [] inputParameters, double w);
    public abstract double calculateP (RateLaw ratelaw, double [] inputParameters, double w);

    //parent method
    protected double returnRateLaw (double X) {
        if (this.g_rateLaw == null) System.exit(0); //this will eventually be replaced with a thrown exception
        return this.g_rateLaw.calculateRate(this.k, X);
    }
}
