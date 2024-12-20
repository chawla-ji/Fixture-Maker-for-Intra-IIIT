# FixtureMaker - Khel 2023 Football Tournament Fixture Generator

FixtureMaker is a robust Java-based application designed to create random and fair match fixtures for tournaments like the **Khel 2023 Football Tournament**. It is tailored to handle a variety of scenarios, ensuring seamless generation of schedules while incorporating effective error handling.

---

## **Features**

### 1. **Dynamic Fixture Generation**
- Supports both single-season and full-season formats.
- Handles **odd number of teams** by automatically adding a placeholder `"Bye"`. Teams paired with `"Bye"` skip that match.

### 2. **Randomized and Fair Scheduling**
- Utilizes `Collections.shuffle()` to ensure fair and randomized pairing of teams.
- Avoids repetition of matches in a single season.

### 3. **Error Handling**
- **Exception Handling**:
  - Validates input to ensure non-empty team names and a positive number of teams.
  - Catches invalid inputs gracefully, providing meaningful error messages to users.

### 4. **OOP Design**
- Follows a modular approach with classes for managing teams, matches, and fixtures:
  - **`FixtureBuilder`**: Core logic for generating fixtures, managing rounds, and tracking played matches.
  - **`FootballMatch`**: Represents a match between two teams with intuitive methods for access and representation.
  - **`Main`**: Entry point for user interaction and program execution.

---

## **Technical Overview**

### Core Classes and Responsibilities
1. **`FixtureBuilder`**:
   - Accepts a list of teams and a boolean indicating whether a full season is desired.
   - Manages the creation of rounds and tracks played matches to avoid repetition.
   - Incorporates logic to handle rematches for full-season fixtures.

2. **`FootballMatch`**:
   - Encapsulates the details of a single match (about both teams).
   - Provides methods for match representation and ensures immutability.

3. **`Main`**:
   - Handles user input for team names, number of teams, and season type.
   - Catches exceptions like invalid team counts or empty names and gracefully terminates execution if errors occur.

---

## **How It Works**

1. **User Input**:
   - Prompts for the number of teams and their names.
   - Offers an option for full-season scheduling.

2. **Fixture Generation**:
   - Randomly pairs teams for each round using the `FixtureBuilder`.
   - Ensures that matches are evenly distributed and non-repetitive within a season.

3. **Output**:
   - Displays fixtures round-wise, showing all scheduled matches.

4. **Error Handling**:
   - Invalid inputs (e.g., non-positive team counts, empty names) are caught, and appropriate error messages are displayed.

---
