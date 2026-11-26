# Library Management System

A Java-based Library Management System developed as a capstone project. The system is designed to manage library items, members, borrowing, returns, and overdue fines using object-oriented programming concepts.

## Project Structure

```text
LibraryManagement/
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
│   ├── exceptions/
│   │   ├── LibraryException.java
│   │   ├── ItemNotFoundException.java
│   │   ├── MemberNotFoundException.java
│   │   ├── ItemNotAvailableException.java
│   │   ├── BorrowLimitExceededException.java
│   │   └── ItemNotOnLoanException.java
│   │
│   ├── loans/
│   ├── Library.java
│   └── Main.java
│
├── .gitignore
└── README.md

```

## Implemented Features

### 1. Library Item Hierarchy
- Abstract `LibraryItem` base class.
- `Book`, `Magazine`, and `ReferenceBook` subclasses.
- `Borrowable` interface to represent items that can be borrowed.
- Fine rates defined for borrowable item types.
- Reference books cannot be borrowed.

### 2. Member Hierarchy
- Abstract `Member` base class.
- `Student`, `Staff`, and `Faculty` subclasses.
- Different borrowing limits and loan periods for each member type.
- Tracking of active loans.

| Member Type | Maximum Items | Loan Period |
|---|---:|---:|
| Student | 3 | 14 days |
| Staff | 4 | 21 days |
| Faculty | 5 | 30 days |

### 3. Exception Handling
- Custom abstract `LibraryException` base class.
- Specific exceptions for item and member lookup, item availability, borrowing limits, and loan status.

## Concepts Used
- Object-Oriented Programming
- Abstraction
- Inheritance
- Polymorphism
- Interfaces
- Encapsulation
- Exception Handling
- Java Collections Framework

## Upcoming Work
- Implement the Loan class and loan-related operations.
- Implement the Library class to manage items, members, borrowing, and returns.
- Implement command handling in Main.
- Add overdue fine calculation.
- Test the complete system against the capstone requirements.

## Compilation and Execution

Compile the Java source files:

```bash
javac -d out src/items/*.java src/exceptions/*.java src/members/*.java src/loans/*.java src/*.java
```

Run the application once `Main.java` is implemented:

```bash
java -cp out Main
```

## Author
**P. Srikar**  
IIIT Bangalore
