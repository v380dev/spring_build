package org.example;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * створює об'єкт
 */
public class ObjectFactory {
    private final ApplicationContext context;
    private static ObjectFactory objectFactory;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
//        this.config = new JavaConfig("org.example", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = implClass.getDeclaredConstructor().newInstance();
//        виклик BeanPostProcessor
        configurators.forEach(objectConfigurator -> objectConfigurator.configur(t, context));
        return t;
    }
}
