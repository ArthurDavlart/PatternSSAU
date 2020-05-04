package com.company.instruments.customeCollection;
import java.util.function.Function;

@FunctionalInterface
public interface SelectPredicate<T, E> {
    E condition(T x);
}
