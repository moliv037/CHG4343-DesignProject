public class PBR {

    private ReactorType reactorType;

    //constructor, copy constructor, clone, accessor, mutator, and equals methods
    public PBR (ReactorType reactorType) {
        if (reactorType == null) System.exit(0);
        this.reactorType = reactorType.clone();
    }
    public PBR (PBR source) {
        if (source == null) System.exit(0);
        this.reactorType = source.reactorType.clone();
    }
    public PBR clone() {
        return new PBR(this);
    }
    public boolean setReactorType (ReactorType reactorType) {
        if (reactorType == null) return false;
        this.reactorType = reactorType.clone();
        return true;
    }
    public ReactorType getReactorType () {
        return reactorType.clone();
    }
    public boolean equals (Object comparator) {
        if (comparator == null) return false;
        if (comparator.getClass() != this.getClass()) return false;
        boolean isEquals=true;
        if (!((PBR)comparator).reactorType.equals(this.reactorType)) isEquals=false;
        return isEquals;
    }

}
