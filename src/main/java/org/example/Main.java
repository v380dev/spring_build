package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        CoronaDesinfector desinfector = new CoronaDesinfector();
        ApplicationContext context = Application.run("org.example", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}