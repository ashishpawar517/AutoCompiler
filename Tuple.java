public class Tuple<T>
{
    private T a,b,c;
    Tuple(T a, T b, T c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public T getFirst()
    {
        return this.a;
    }
    public T getSecond()
    {
        return this.b;
    }
    public T getThird()
    {
        return this.c;
    }
    @Override public String toString()
    {
        return "{"+this.a+","+this.b+","+this.c+"}";
    }
}