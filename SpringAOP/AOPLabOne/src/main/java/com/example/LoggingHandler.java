package com.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

// Write an InvocationHandler called LoggingHandler
// that logs the method name and arguments before calling the real method,
// and the return value after
public class LoggingHandler implements InvocationHandler {

    public final Object target;

    public LoggingHandler(Object target) {
        this.target = target; //Constructor
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // log method name + arguments
        System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(args));

        long start = System.nanoTime();

        try {
            Object result = method.invoke(target, args); //delegating

            System.out.println("Called " + method.getName() + " returned " + result);
            return result;

        } catch (InvocationTargetException e) {
            Throwable realEx = e.getCause(); //unwrap
            System.out.println(method.getName() + " Threw " + realEx);
            throw realEx;
        }
        finally { //total time taken
            long end = System.nanoTime();
            System.out.println("Time taken: " + (end - start));
        }
    }
}
