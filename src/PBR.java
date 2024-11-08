public class PBR
{
    private ReactorType reactortype;
    public PBR (ReactorType reactortype)
    {
        if (reactortype == null) System.exit(0);
        this.reactortype=reactortype.clone;
    }
    public PBR (PBR source)
    {
        if (source==null) System.exit(0);
        this.reactortype=source.reactortype.clone();
    }
    public PBR clone ()
    {
        return new PBR (this);
    }
    public Boolean setReactorType (ReactorType reactortype)
    {
        if (reactortype==null) return false;
        this.reactortype=reactortype.clone();
        return true;
    }
    public ReactorType getReactorType ()
    {
        return this.reactortype.clone();
    }
    public public boolean equals (Object comparator)
{
    if (comparator ==null) return false;
    if (comparator.getClass()!=this.getClass()) return false;
    boolean isEquals =true;
    if (((PBR)comparator).reactortype!=this.reactortype)isEquals=false;
    return isEquals;
}
