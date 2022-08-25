package org.example;

public class Main {
    public static void main(String[] args) {
//        CoronaDesinfector desinfector = new CoronaDesinfector();
        CoronaDesinfector desinfector = ObjectFactory.getInstance().createObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}