package org.example;

import java.util.Map;

/**
 * аналог SprigBoot-Run
 * запускає класи інфраструктурного коду у необхідній послідовності
 */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);
//        todo homework - init all singletons witch are not lazy
        context.setFactory(factory);
        return context;
    }
}
