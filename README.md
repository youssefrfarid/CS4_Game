# Zombie Survival Game

A turn-based zombie survival game implemented in Java. Players select a hero and navigate a 15×15 grid map, fighting zombies, collecting vaccines and supplies, and recruiting new heroes. The objective is to cure zombies using vaccines while keeping at least five heroes alive.

## Features

- **Three hero classes** — Fighter (high damage, free attacks on special), Medic (heals adjacent heroes), Explorer (reveals the entire map)
- **Procedurally generated map** — 15×15 grid with randomised zombie, vaccine, supply, and trap placement each game
- **Turn-based combat** — heroes spend action points to move, attack, and use abilities; zombies retaliate at end of turn
- **Win/loss conditions** — win by curing all 5 zombies with 5+ active heroes; lose if all heroes die or vaccines are exhausted with too few heroes
- **Full JUnit test suite** — comprehensive unit and integration tests covering all game mechanics

## Directory Structure

```
.
├── heroes.csv               Hero roster loaded at game start
├── test_Exp.csv             Test data — Explorer heroes
├── test_Fighters.csv        Test data — Fighter heroes
├── test_MEDs.csv            Test data — Medic heroes
├── test_heros.csv           Test data — mixed hero roster
├── src/
│   ├── engine/
│   │   └── Game.java        Core game loop, map initialisation, win/loss logic
│   ├── exceptions/
│   │   ├── GameActionException.java
│   │   ├── InvalidTargetException.java
│   │   ├── MovementException.java
│   │   ├── NoAvailableResourcesException.java
│   │   └── NotEnoughActionsException.java
│   ├── model/
│   │   ├── characters/
│   │   │   ├── Character.java
│   │   │   ├── Direction.java
│   │   │   ├── Explorer.java
│   │   │   ├── Fighter.java
│   │   │   ├── Hero.java
│   │   │   ├── Medic.java
│   │   │   └── Zombie.java
│   │   ├── collectibles/
│   │   │   ├── Collectible.java
│   │   │   ├── Supply.java
│   │   │   └── Vaccine.java
│   │   └── world/
│   │       ├── Cell.java
│   │       ├── CharacterCell.java
│   │       ├── CollectibleCell.java
│   │       └── TrapCell.java
│   └── tests/
│       ├── GameTests.java
│       ├── GamePublicTests.java
│       └── GameExtendedTests.java
└── .classpath
```

## Prerequisites

- Java 11 or later
- Eclipse IDE (recommended) **or** any Java build tool that supports the standard `src`/`bin` layout
- JUnit 5 (included via Eclipse's built-in JUnit container)

## Quick Start

### Eclipse

1. Clone the repository:
   ```bash
   git clone https://github.com/youssefrfarid/CS4_Game.git
   ```
2. Open Eclipse → **File → Open Projects from File System** → select the cloned folder.
3. Eclipse will auto-detect `.classpath` and configure the build path, including JUnit 5.
4. Right-click `src/engine/Game.java` → **Run As → Java Application** to start the game engine.
5. To run tests: right-click `src/tests/` → **Run As → JUnit Test**.

### Command Line

```bash
# Compile
javac -d bin $(find src -name "*.java")

# Run (pass path to hero roster)
java -cp bin engine.Game
```

> **Note:** `Game.loadHeroes(String filePath)` expects the path to a hero CSV file as an argument. Pass `heroes.csv` (included in the root) when integrating with a UI or driver class.

## Hero Roster Format

The `heroes.csv` file (and test CSVs) follow this format:

```
Name,Type,MaxHP,MaxActions,AttackDamage
```

Valid types: `FIGH` (Fighter), `MED` (Medic), `EXP` (Explorer).

Example:
```
Joel Miller,FIGH,140,5,30
Ellie Williams,MED,110,6,15
Tess,EXP,80,6,20
```

## Authors

- **Andrew Abdelmalak** — Mechatronics Engineering, The German University in Cairo
- **Youssef Ramy Farid** — [github.com/youssefrfarid](https://github.com/youssefrfarid)
