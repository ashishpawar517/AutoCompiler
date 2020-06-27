
//class for storing three values.
public class Tuple<T>
{
    private T a,b,c,d;
    
    Tuple(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    Tuple(T a, T b, T c,T d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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
    public T getForth()
    {
        return this.d;
    }
    @Override public String toString()
    {
        return "{"+this.a+","+this.b+","+this.c+""+this.d+"}";
    }
}