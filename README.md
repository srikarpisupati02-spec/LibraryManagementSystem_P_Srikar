# Library Management System

A Java-based Library Management System developed as a capstone project at IIIT Bangalore. The application manages library items, members, borrowing, returns, reservations, overdue loans and fines using Object-Oriented Programming concepts.

## Project Overview

The system simulates a college library where users can:
- Add books, magazines and reference books.
- Register students, staff and faculty members.
- Borrow and return library items.
- Calculate overdue fines.
- Reserve items that are currently on loan.
- Search for library items.
- View member loans, overdue loans and inventory status.

The application reads commands from standard input and displays the corresponding results.

## Project Structure

```text
LibraryManagementSystem/
│
├── src/
│   ├── items/
│   │   ├── LibraryItem.java
│   │   ├── Borrowable.java
│   │   ├── Book.java
│   │   ├── Magazine.java
│   │   └── ReferenceBook.java
│   │
│   ├── members/
│   │   ├── Member.java
│   │   ├── Student.java
│   │   ├── Staff.java
│   │   └── Faculty.java
│   │
│   ├── loans/
│   │   ├── Loan.java
│   │   └── ReturnResult.java
│   │
│   ├── exceptions/
│   │   ├── LibraryException.java
│   │   ├── ItemNotFoundException.java
│   │   ├── MemberNotFoundException.java
│   │   ├── ItemNotAvailableException.java
│   │   ├── BorrowLimitExceededException.java
│   │   └── ItemNotOnLoanException.java
│   │
│   ├── Library.java
│   └── Main.java
│
├── .gitignore
└── README.md
```

## Implemented Features

### 1. Library Item Management

- Abstract `LibraryItem` base class.
- `Book`, `Magazine` and `ReferenceBook` subclasses.
- `Borrowable` interface implemented by Book and Magazine.
- Different fine rates for different borrowable item types.
- Reference books cannot be borrowed.

### 2. Member Management

- Abstract `Member` base class.
- `Student`, `Staff` and `Faculty` subclasses.
- Individual borrowing limits and loan periods.
- Tracking of active loans for each member.

| Member Type | Maximum Items | Loan Period |
|---|---:|---:|
| Student | 3 | 14 days |
| Staff | 4 | 21 days |
| Faculty | 5 | 30 days |

### 3. Loan Management

- Creation and tracking of loans.
- Automatic due-day calculation.
- Overdue detection.
- Fine calculation based on overdue days and item fine rate.
- Return processing using `ReturnResult`.

### 4. Library Management

The `Library` class coordinates the main operations:

- Adding library items and members.
- Borrowing and returning items.
- Managing reservations using queues.
- Advancing simulated library time.
- Searching items by title.
- Retrieving member loans.
- Retrieving overdue loans.
- Displaying inventory and item status.

### 5. Exception Handling

Custom exception hierarchy based on `LibraryException`:

- `ItemNotFoundException`
- `MemberNotFoundException`
- `ItemNotAvailableException`
- `BorrowLimitExceededException`
- `ItemNotOnLoanException`

## Commands Supported

| Command | Description |
|---|---|
| `ADD_BOOK` | Add a book |
| `ADD_MAGAZINE` | Add a magazine |
| `ADD_REFERENCE` | Add a reference book |
| `ADD_MEMBER` | Register a member |
| `BORROW` | Borrow an item |
| `RETURN` | Return an item and calculate fine |
| `RESERVE` | Reserve an item currently on loan |
| `ADVANCE` | Advance simulated time |
| `SEARCH` | Search items by title |
| `LOANS` | Display a member's active loans |
| `OVERDUE` | Display overdue loans |
| `INVENTORY` | Display all items and their status |

## Technologies Used

- Java
- Java Collections Framework
- Object-Oriented Programming
- Git and GitHub
- Visual Studio Code

## OOP Concepts Used

- **Encapsulation:** Private attributes with public getter methods.
- **Inheritance:** Specialized item and member classes extend abstract parent classes.
- **Abstraction:** Abstract classes define common structures and behaviour.
- **Polymorphism:** Different member and item types are handled through parent-class references.
- **Interfaces:** The `Borrowable` interface represents borrowing capability.
- **Exception Handling:** Custom exceptions handle library rule violations.

## Compilation and Execution

Compile the complete project from the project root directory:

```bash
javac -d out src/items/*.java src/exceptions/*.java src/members/*.java src/loans/*.java src/*.java
```

Run the application:

```bash
java -cp out Main
```

Enter one command per line. The application continues until the input ends (EOF).

## Testing

The project is being tested against the sample input and expected output provided in the capstone specification.

Testing includes:
- Item and member creation.
- Borrowing limits.
- Item availability checks.
- Loan due dates.
- Returns and fine calculations.
- Overdue reports.
- Inventory status.
- Search functionality.
- Reservation processing.

## Author

**P. Srikar**  
IIIT Bangalore

---

Developed as part of the Library Management System Capstone Project.