package com.jakuboc.TennisCourtSystem.mappers;

/**
 * mapper between two selected classes
 * @param <A> primary class
 * @param <B> secondary class (f.e. DTO)
 */
public interface Mapper<A,B> {
    B mapTo(A a);

    A mapFrom(B b);
}
