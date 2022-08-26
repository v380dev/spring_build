package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* аналог IoC Container
* */
public class ApplicationContext {
    @Setter
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject (Class<T> type) {
//        якщо в кеші вже існує потрібний об'єкт - достаємо
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }
//        якщо об'єкта в кеші не існує:
//        знаходимо необхідну імплементацію інтерфейсу:
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
//        створюємо об'єкт:
        T t = factory.createObject(implClass);
//        якщо створений об'єкт належить до singleton, додаємо в кеш:
        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }
        return t;
    }
}
