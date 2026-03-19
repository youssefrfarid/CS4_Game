# Zombie Survival Game Engine

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)
![Language: Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Build: CI](https://img.shields.io/badge/Build-CI-blue.svg)

A turn-based zombie survival game engine implemented in Java. Players choose a hero and navigate a procedurally generated 15 × 15 grid, collecting vaccines, fighting zombies, and recruiting allies — all governed by a strict action-point and visibility system.

The codebase is published as a portfolio project, with emphasis on architecture clarity and testable gameplay rules.

---

## Table of Contents

1. [Overview](#overview)
2. [Game Mechanics](#game-mechanics)
3. [Architecture](#architecture)
4. [Repository Structure](#repository-structure)
5. [Getting Started](#getting-started)
6. [Data Format](#data-format)
7. [Authors](#authors)
8. [Acknowledgments](#acknowledgments)
9. [License](#license)

---

## Overview

| Property        | Value                                         |
|-----------------|-----------------------------------------------|
| Language        | Java 11+                                      |
| Grid Size       | 15 × 15                                       |
| Initial Zombies | 10 (one new zombie spawns each end-turn)      |
| Vaccines on Map | 5                                             |
| Supplies on Map | 5                                             |
| Traps on Map    | 5                                             |
| Win Condition   | ≥ 5 heroes recruited, all vaccines used       |
| Lose Condition  | All heroes dead, or all vaccines gone before win |

---

## Game Mechanics

### Hero Classes

| Class    | Type Code | Role                                              |
|----------|-----------|---------------------------------------------------|
| Fighter  | `FIGH`    | High HP and attack; excels at direct combat        |
| Medic    | `MED`     | Lower attack; can heal and support allies          |
| Explorer | `EXP`     | Enhanced movement; best for map traversal          |

### Turn System

Each hero starts a turn with a fixed pool of **action points** (defined per hero in `heroes.csv`). Actions that consume points include:

- **Move** — walk to an adjacent or diagonal cell
- **Attack** — strike a target character
- **Use Supply** — restore HP
- **Cure** (Medic) — cure an adjacent zombie into a hero
- **Use Vaccine** — inject a vaccine on the map

At **End Turn**:
1. Every zombie attempts one attack on a random adjacent hero.
2. All heroes' action points are restored and special-action flags reset.
3. A new zombie spawns at a random empty cell (not adjacent to another zombie).
4. Map visibility is recalculated based on each hero's current position.

### Visibility

Only the hero's cell and all 8 adjacent cells are visible at any time. The rest of the map is hidden until explored.

---

## Architecture

```
engine.Game          ← static game state (map, heroes, zombies), turn logic
model.characters     ← Character, Hero (Fighter / Medic / Explorer), Zombie
model.world          ← Cell, CharacterCell, CollectibleCell, TrapCell
model.collectibles   ← Collectible, Vaccine, Supply
exceptions           ← InvalidTargetException, NotEnoughActionsException, …
tests                ← M1PublicTests, M2PublicTests (JUnit 4)
```

---

## Repository Structure

```
.
├── heroes.csv
├── LICENSE
├── README.md
└── src
    ├── engine
    │   └── Game.java
    ├── exceptions
    │   ├── GameActionException.java
    │   ├── InvalidTargetException.java
    │   ├── MovementException.java
    │   ├── NoAvailableResourcesException.java
    │   └── NotEnoughActionsException.java
    ├── model
    │   ├── characters
    │   │   ├── Character.java
    │   │   ├── Direction.java
    │   │   ├── Explorer.java
    │   │   ├── Fighter.java
    │   │   ├── Hero.java
    │   │   ├── Medic.java
    │   │   └── Zombie.java
    │   ├── collectibles
    │   │   ├── Collectible.java
    │   │   ├── Supply.java
    │   │   └── Vaccine.java
    │   └── world
    │       ├── Cell.java
    │       ├── CharacterCell.java
    │       ├── CollectibleCell.java
    │       └── TrapCell.java
    └── tests
        ├── M1PublicTests.java
        └── M2PublicTests.java
```

---

## Getting Started

### Prerequisites

- Java 11 or later
- JUnit 4 (for running tests)
- Any Java IDE (Eclipse, IntelliJ IDEA, VS Code with Java extension)

### Compile the Engine

```bash
javac -d bin \
  src/exceptions/*.java \
  src/model/collectibles/*.java \
  src/model/world/*.java \
  src/model/characters/*.java \
  src/engine/*.java
```

### Run Tests

Run `src/tests/M1PublicTests.java` and `src/tests/M2PublicTests.java` as JUnit 4 tests from your IDE, or add JUnit 4 to your classpath and execute with:

```bash
java -cp bin:junit.jar:hamcrest.jar org.junit.runner.JUnitCore tests.M1PublicTests
```

### Drive the Engine

The repository exposes the gameplay as an engine layer without a bundled CLI entry point. To run an interactive session, create a driver class that:

```java
Game.loadHeroes("heroes.csv");   // populate availableHeroes list
Game.startGame(selectedHero);    // initialise map, spawn zombies & collectibles
// … call hero actions then Game.endTurn() in a loop
```

---

## Data Format

`heroes.csv` uses the schema:

```
Name,Type,MaxHP,MaxActions,AttackDmg
```

Example rows from the default roster:

```
Joel Miller,FIGH,140,5,30
Ellie Williams,MED,110,6,15
Tess,EXP,80,6,20
```

Valid `Type` values: `FIGH`, `MED`, `EXP`

---

## Authors

| Name               | Affiliation                                          |
|--------------------|------------------------------------------------------|
| Andrew Abdelmalak  | Mechatronics Engineering, German University in Cairo |
| Youssef Ramy Farid | Computer Science, German University in Cairo         |

---

## Acknowledgments

Developed as part of the **CS4 (CSEN401) — Computer Programming Lab** course at the German University in Cairo. The milestone-based test suites (`M1PublicTests`, `M2PublicTests`) were provided by the course instructors.

---

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for details.
