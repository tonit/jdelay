# jdelay

jdelay - a simple java program to delay the start of another java program.

## Usage:
Add it to the classpath of your java program:
```
java -cp path/to/your.jar:jdelay.jar -Djdelay.realmain=YourMain -Djdelay.delay=1000 com.github.tonit.jdelay.JDelay
```

## Why?
The JVM will exist now for 1000ms longer before the main application will start.
This might help with debugging or profiling e.g. when attaching an Instana Agent to a short lived JVM.