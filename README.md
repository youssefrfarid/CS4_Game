# Zombie Survival Game Engine

A turn-based zombie survival game engine implemented in Java. This project focuses on clean object-oriented modeling for combat, movement, collectibles, and win/loss state transitions on a 15x15 grid.

The codebase is published as a portfolio project, with emphasis on architecture clarity and testable gameplay rules.

## Highlights

- Three hero classes with distinct abilities: Fighter, Medic, Explorer
- Procedural map generation with zombies, traps, supplies, and vaccines
- Rule-driven turn system with action points and end-turn zombie retaliation
- Deterministic win/loss conditions implemented in the game engine
- Public test suites for core mechanics coverage

## Project Structure

```
.
|-- heroes.csv
|-- LICENSE
|-- README.md
`-- src
   |-- engine
   |   `-- Game.java
   |-- exceptions
   |   |-- GameActionException.java
   |   |-- InvalidTargetException.java
   |   |-- MovementException.java
   |   |-- NoAvailableResourcesException.java
   |   `-- NotEnoughActionsException.java
   |-- model
   |   |-- characters
   |   |   |-- Character.java
   |   |   |-- Direction.java
   |   |   |-- Explorer.java
   |   |   |-- Fighter.java
   |   |   |-- Hero.java
   |   |   |-- Medic.java
   |   |   `-- Zombie.java
   |   |-- collectibles
   |   |   |-- Collectible.java
   |   |   |-- Supply.java
   |   |   `-- Vaccine.java
   |   `-- world
   |       |-- Cell.java
   |       |-- CharacterCell.java
   |       |-- CollectibleCell.java
   |       `-- TrapCell.java
   `-- tests
      |-- M1PublicTests.java
      `-- M2PublicTests.java
```

## Prerequisites

- Java 11 or later
- JUnit 5 (for running tests)
- Eclipse IDE or another Java IDE/toolchain

## Quick Start

### Compile the Engine

```bash
javac -d bin src/exceptions/*.java src/model/collectibles/*.java src/model/world/*.java src/model/characters/*.java src/engine/*.java
```

### Run Tests

Run the classes in src/tests as JUnit tests from your IDE (or a JUnit-compatible build setup).

### Run Gameplay

This repository currently exposes the gameplay as an engine layer and does not include a CLI main entrypoint in Game.java.

To run an interactive session, add or use an external driver class that initializes the engine and calls Game.loadHeroes("heroes.csv") before starting turn actions.

## Data Format

heroes.csv uses this schema:

```
Name,Type,MaxHP,MaxActions,AttackDmg
```

Valid Type values:

- FIGH
- MED
- EXP

## Authors

- Andrew Abdelmalak - Mechatronics Engineering, The German University in Cairo
- Youssef Ramy Farid - github.com/youssefrfarid
