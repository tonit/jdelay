/**
 * jdelay - a simple java program to delay the start of another java program.
 * Author: Toni Menzel (toni.menzel@rebaze.com)
 */
package com.github.tonit.jdelay;

public class JDelay {
    
    private static long parseDelay(String delayStr) {
        if (delayStr == null || delayStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Delay cannot be null or empty");
        }
        
        delayStr = delayStr.trim().toLowerCase();
        
        if (delayStr.matches("\\d+")) {
            return Long.parseLong(delayStr);
        }
        
        if (delayStr.matches("\\d*\\.?\\d+[a-z]+")) {
            double value;
            String unit;
            
            int i = 0;
            while (i < delayStr.length() && (Character.isDigit(delayStr.charAt(i)) || delayStr.charAt(i) == '.')) {
                i++;
            }
            
            value = Double.parseDouble(delayStr.substring(0, i));
            unit = delayStr.substring(i);
            
            switch (unit) {
                case "ms": case "millis": case "milliseconds":
                    return (long) value;
                case "s": case "sec": case "seconds":
                    return (long) (value * 1000);
                case "m": case "min": case "minutes":
                    return (long) (value * 60 * 1000);
                case "h": case "hour": case "hours":
                    return (long) (value * 60 * 60 * 1000);
                default:
                    throw new IllegalArgumentException("Unknown time unit: " + unit + ". Supported units: ms, s, m, h");
            }
        }
        
        throw new IllegalArgumentException("Invalid delay format: " + delayStr + ". Use formats like: 1000, 5s, 2.5m, 1h");
    }
    
    public static void main(String[] args) throws Exception {
        String realmain = System.getProperty("jdelay.realmain");
        String delay = System.getProperty("jdelay.wait");
        if (realmain == null || delay == null) {
            System.err.println("jdelay: missing system properties e.g.: -Djdelay.realmain=yourclass -Djdelay.wait=5s");
            System.err.println("Supported delay formats: 1000 (ms), 5s, 2.5m, 1h, 500ms");
            System.exit(1);
        }
        try {
            long delayMs = parseDelay(delay);
            System.out.println("jdelay: delaying start for " + delayMs + "ms");
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            System.err.println("jdelay: sleep interrupted");
        } catch (IllegalArgumentException e) {
            System.err.println("jdelay: " + e.getMessage());
            System.exit(1);
        }
        Class<?> clazz = Class.forName(realmain);
        clazz.getMethod("main", String[].class).invoke(null, (Object) args);
    }
}
