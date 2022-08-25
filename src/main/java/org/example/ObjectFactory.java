package org.example;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * створює об'єкт
 * якщо передано інтерфейс - робить запит у Config
 * на клас який імлементує
 */
public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    private Config config;

    @SneakyThrows
    private ObjectFactory() {
//        this.config = new JavaConfig("org.example", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));
        this.config = new JavaConfig("org.example", new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class)));
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();
//        виклик BeanPostProcessor
        configurators.forEach(objectConfigurator -> objectConfigurator.configur(t));
        return t;
    }
}
