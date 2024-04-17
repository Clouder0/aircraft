package edu.hitsz.basic;

public class Wrapper<T> {
    private T value;
    public Wrapper(T init) {
        this.value = init;
    }

    public T get() {
        return this.value;
    }

    public void set(T x) {
        this.value = x;
    }
}
