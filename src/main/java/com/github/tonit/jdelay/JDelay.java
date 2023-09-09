/**
 * jdelay - a simple java program to delay the start of another java program
 *
 * Author: Toni Menzel (toni.menzel@rebaze.com)
 */
package com.github.tonit.jdelay;

import java.lang.reflect.InvocationTargetException;

public class JDelay {
    public static void main(String[] args) throws Exception {
        String realmain = System.getProperty("jdelay.realmain");
        String delay = System.getProperty("jdelay.wait");
        if (realmain == null || delay == null) {
            System.err.println("jdelay: missing system properties e.g.: -Djdelay.realmain=yourclass -Djdelay.wait=2000");
            System.exit(1);
        }
        try {
            System.out.println("jdelay: delaying start for " + delay + "ms");
            Thread.sleep(Long.parseLong(delay));
        } catch (InterruptedException e) {
            System.err.println("jdelay: sleep interrupted");
        }
        Class<?> clazz = Class.forName(realmain);
        clazz.getMethod("main", String[].class).invoke(null, (Object) args);
    }
}
