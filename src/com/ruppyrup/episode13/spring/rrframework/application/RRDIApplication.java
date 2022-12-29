package com.ruppyrup.episode13.spring.rrframework.application;



import com.ruppyrup.episode13.spring.rrframework.rrannotations.RRAutoWire;
import com.ruppyrup.episode13.spring.rrframework.rrannotations.RRComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class RRDIApplication {

    private Map<String, Bean> beanRegistry = new TreeMap<>();

    protected void start(final Class<?> clazz) {
        updateBeanRegistry(clazz.getPackageName());
        autowireDependencies();
        run();
        cleanup();
    }

    private void autowireDependencies() {
        beanRegistry.entrySet()
                .forEach(obj -> Arrays.stream(obj.getValue().getClazz().getClass().getDeclaredFields())
                        .filter(field -> field.isAnnotationPresent(RRAutoWire.class))
                        .forEach(field -> {
                            try {
                                field.setAccessible(true);

                                String requiredProfile = field.getAnnotation(RRAutoWire.class).profile();

                                Bean foundBean = beanRegistry.values().stream()
                                        .filter(bean -> bean.hasInterface(field.getType()))
                                        .filter(bean -> {
                                            if (requiredProfile.isEmpty())
                                                return true;
                                            else
                                                return requiredProfile.equals(bean.getProfile());
                                        })
                                        .findFirst()
                                        .orElse(null);

                                field.set(obj.getValue().getClazz(), foundBean.getClazz());
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }));
    }

    private void updateBeanRegistry(String packageName) {

        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            reader.lines()
                .forEach(line -> {
                    if (line.endsWith(".class")) {
                        Class<?> foundClass = getClass(line, packageName);
                        String className = line.substring(0, line.lastIndexOf('.'));
                        try {
                            if (!foundClass.isInterface() && foundClass.getAnnotation(RRComponent.class) != null) {
                                String profile = foundClass.getAnnotation(RRComponent.class).profile();
                                Object classInstance = foundClass.getDeclaredConstructor().newInstance();
                                List<Class<?>> interfaces = Arrays.asList(foundClass.getInterfaces());
                                beanRegistry.put(className, new Bean(className, interfaces, classInstance, profile));
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if (!line.contains(".")){
                        System.out.println("line = " + packageName + "." + line);
                        updateBeanRegistry(packageName + "." + line);
                    } else {
                        System.out.println("Found something :: " + line);
                    }
                });

        } catch (IOException e ) {
            throw new RuntimeException(e);
        }

    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    protected Object getBean(String beanName) {
        return beanRegistry.get(beanName).getClazz();
    }
    protected abstract void run();

    protected abstract void cleanup();

}
