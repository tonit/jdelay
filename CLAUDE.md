# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

jdelay is a lightweight Java utility that delays the start of another Java program by a specified duration. It's designed to help with debugging and profiling scenarios where you need to attach tools to a JVM before the main application starts.

## Architecture

- **Single-class application**: The entire functionality is contained in `JDelay.java`
- **Delegation pattern**: JDelay acts as a proxy main class that sleeps then invokes the real main class via reflection
- **System property configuration**: Uses `jdelay.realmain` to specify the target main class and `jdelay.wait` for delay duration

## Build System

This project uses Maven with a simple configuration:
- **Java version**: 1.8 (source and target)
- **Main class**: `com.github.tonit.jdelay.JDelay`
- **Packaging**: JAR with manifest containing main class
- **Distribution**: GitHub Packages

## Common Commands

```bash
# Build the project
mvn clean compile

# Package into JAR
mvn clean package

# Install to local repository
mvn clean install

# Deploy to GitHub Packages
mvn clean deploy
```

## Usage Pattern

The built JAR is meant to be used as a classpath addition:
```bash
java -cp path/to/your.jar:target/jdelay-1.0-SNAPSHOT.jar -Djdelay.realmain=YourMain -Djdelay.wait=1000 com.github.tonit.jdelay.JDelay
```

Note: The README.md uses property name `jdelay.delay` but the code uses `jdelay.wait` - this is a discrepancy in the documentation.

## File Structure

```
src/main/java/com/github/tonit/jdelay/
└── JDelay.java                 # Main application class
```

The codebase is intentionally minimal with no external dependencies beyond Java standard library.