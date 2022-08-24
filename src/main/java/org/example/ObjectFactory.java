package org.example;

import lombok.SneakyThrows;

/**
 * створює об'єкт
 * якщо передано інтерфейс - робить запит у Config
 * на клас який імлементує
 *
 * */
public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    private Config config = new JavaConfig("org.example");

    private ObjectFactory() {
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();
// todo
        return t;
    }
}
