
package com.algerd.musicbookspringmaven.repository;

public interface Changeable<T> {
    
    T getOld();
    T getNew();
}
