package com.github.x3rmination.solaris.common.scheduler;

import java.lang.reflect.Method;

public class Executable {
    private final Object object;
    private final Method method;

    private final Object[] args;
    private int delay;



    public Executable(Object object, Method method, Object[] args, int delay) {
        this.object = object;
        this.method = method;
        this.delay = delay;
        this.args = args;
    }

    public Method getMethod() {
        return method;
    }

    public Object getObject() {
        return object;
    }

    public Object[] getArgs() {
        return args;
    }

    public boolean shouldExecute() {
        return this.delay < 1;
    }
    public int getDelay() {
        return delay;
    }

    public void decreaseDelay() {
        delay--;
    }
}
