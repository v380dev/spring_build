package org.example;

public interface Config {
    <T> Class<? extends T> getImplClass (Class<T> ifc);
}
