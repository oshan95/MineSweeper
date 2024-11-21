# Minesweeper Game

A simple command-line Minesweeper game implemented in Java, adhering to OOP principles and the SOLID design pattern. The game features customizable grid sizes and mine counts and offers a fun and interactive experience in the console.

---

## Features

- Generate a Minesweeper grid of any size.
- Place mines randomly on the grid.
- Reveal squares and calculate adjacent mines.
- Automatically uncover adjacent safe squares.
- Win the game by uncovering all non-mine squares.
- Command-line interface for user input.

---

## Prerequisites

Before you begin, ensure you have the following installed:

1. **Java JDK 17 or higher**  
   Verify installation:
   ```bash
   java -version

2. **Apache Maven**  
   Verify installation:
   ```bash
   mvn -version

---

## Installation and Setup

**Build the Project** - use Maven to build and test the project:

1. **Compile the source code**
    ```bash 
    mvn compile

2. **Run tests**
   ```bash
   mvn test
   
3. **Package the application**
   ```bash
   mvn package
   //This creates a JAR file in the target directory, e.g., target/MineSweeper-1.0-SNAPSHOT.jar
   
---

## Run the Application

1. **Run the packaged JAR file:**
   ```bash
   java -jar target/MineSweeper-1.0-SNAPSHOT.jar

---

## Dependencies
**The application uses the following dependencies, managed through Maven:**
   - Log4j2 (for logging)
   - JUnit (for testing)
**Dependencies are defined in pom.xml. Maven automatically downloads them when you build the project.**

Run the following command to ensure all dependencies are resolved:

bash
Copy code
mvn dependency:resolve

---

## Project Structure

1. The project follows the standard Maven directory structure:

   ```bash
   MineSweeper/
   ├── src/
   │   ├── main/
   │   │   ├── java/                # Source code
   │   │   │   └── com/allegro/minesweeper/
   │   │   │       ├── config/
   │   │   │       │   ├── MessageLoader.java
   │   │   │       │   
   │   │   │       ├── controller/
   │   │   │       │   ├── impl/
   │   │   │       │       ├── MineSweepControllerImpl.java
   │   │   │       │   ├── MineSweepController.java
   │   │   │       ├── ...
   │   │   │       └── Main.java
   │   │   └── resources/           # Resources (e.g., messages, configurations)
   │   │       └── log4j2.xml
   │   │       └── messages.properties
   │   └── test/                    # Test code
   │       └── java/
   │           └── com/allegro/minesweeper/
   │               ├── config/
   │               │   ├── MessageLoaderTest.java
   │               │   
   │               ├── controller/
   │               │   ├── MineSweepControllerImplTest.java
   │               └── ...
   ├── pom.xml                      # Maven configuration
   └── README.md                    # Documentation

