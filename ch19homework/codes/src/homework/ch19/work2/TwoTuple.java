package homework.ch19.work2;

import java.lang.Comparable;
import java.util.Objects;

public class TwoTuple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<TwoTuple<T1, T2>> {
    private T1 first;
    private T2 second;

    public TwoTuple() { }

    public TwoTuple(T1 _first, T2 _second){
        first = _first;
        second = _second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoTuple<?, ?> twoTuple = (TwoTuple<?, ?>) o;
        return Objects.equals(first, twoTuple.first) && Objects.equals(second, twoTuple.second);
    }

    @Override
    public String toString() {
        return "(" +
                first + ", " + second +
                ')';
    }

    @Override
    public int compareTo(TwoTuple<T1, T2> o) {
        if(first.equals(o.getFirst())){
            return second.compareTo(o.getSecond());
        }else{
            return first.compareTo(o.getFirst());
        }
    }
}
