package com.ruppyrup.episode13.spring.rrframework.application;

import java.util.List;

class Bean {
    private String name;
    private List<Class<?>> interfaces;
    private Object clazz;
    private String profile;

    public String getProfile() {
        return profile;
    }

    public Bean(final String name, final List<Class<?>> interfaces, final Object clazz, final String profile) {
        this.name = name;
        this.interfaces = interfaces;
        this.clazz = clazz;
        this.profile = profile;
    }

    public boolean hasInterface(Class<?> interfaceType) {
        return interfaces.contains(interfaceType);
    }

    public String getName() {
        return name;
    }

    public List<Class<?>> getInterfaces() {
        return interfaces;
    }

    public Object getClazz() {
        return clazz;
    }
}
