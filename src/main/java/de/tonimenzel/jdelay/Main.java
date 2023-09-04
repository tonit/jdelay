package de.tonimenzel.jdelay;

public class Main {
    public static void main(String[] args) {
        String realmain = System.getProperty("jdelay.realmain");
        String delay = System.getProperty("jdelay.delay");
        if (realmain == null || delay == null) {
            System.err.println("jdelay: missing system properties");
            System.exit(1);
        }
        try {
            System.out.println("jdelay: delaying start for " + delay + "ms");
            Thread.sleep(Long.parseLong(delay));
        } catch (InterruptedException e) {
            System.err.println("jdelay: sleep interrupted");
            System.exit(1);
        }
        try {
            Class<?> clazz = Class.forName(realmain);
            clazz.getMethod("main", String[].class).invoke(null, (Object) args);
        } catch (Exception e) {
            System.err.println("jdelay: failed to invoke real main");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
