### Low-Level Design (LLD) of the Library Management System

#### 1. **Entities and Classes**

- **Book (Interface)**
  - The `Book` interface is the core abstraction for all book types.
  - It provides the common operations of borrowing and returning a book.
  
  **Methods**:
  - `void borrow()`: To borrow the book.
  - `void returnBook()`: To return the book.
  - `boolean isBorrowed()`: To check if the book is currently borrowed.

- **FictionBook (Class)**
  - This class implements the `Book` interface and represents fiction books.
  - It contains additional logic specific to fiction books, such as no specific return duration mentioned.

  **Attributes**:
  - `title (String)`: The title of the fiction book.
  - `borrowed (boolean)`: A flag indicating whether the book is borrowed or not.

  **Methods**:
  - `borrow()`: Sets the `borrowed` flag to `true` and prints a message.
  - `returnBook()`: Sets the `borrowed` flag to `false` and prints a message.
  - `isBorrowed()`: Returns whether the book is currently borrowed.

- **NonFictionBook (Class)**
  - This class implements the `Book` interface and represents non-fiction books.
  - It adds specific logic, such as emphasizing the return period.

  **Attributes**:
  - `title (String)`: The title of the non-fiction book.
  - `borrowed (boolean)`: A flag indicating whether the book is borrowed or not.

  **Methods**:
  - `borrow()`: Checks if the book is borrowed, sets the `borrowed` flag to `true`, and adds a message for the return duration.
  - `returnBook()`: Checks if the book was borrowed, sets the `borrowed` flag to `false`, and adds a message.
  - `isBorrowed()`: Returns whether the book is currently borrowed.

#### 2. **Key Design Concepts**

- **Interface Segregation**: The `Book` interface provides a standard contract for both fiction and non-fiction books. This approach allows the system to be extended easily if more types of books (e.g., `EBook`, `TextBook`) are added later. The system doesn't need to worry about the type of book it’s dealing with—just that the book implements the `Book` interface.
  
- **Encapsulation**: The state of whether a book is borrowed or not is encapsulated within each book class (`FictionBook` and `NonFictionBook`). The user of these classes does not need to know the internal implementation; they simply interact with methods like `borrow()` and `returnBook()`.

- **Polymorphism**: Both `FictionBook` and `NonFictionBook` classes implement the `Book` interface. This allows the `LibraryManagementSystem` to treat any type of book as a `Book`. For instance, the system can store both `FictionBook` and `NonFictionBook` objects in a single list of `Book`.

#### 3. **Component Interaction**

The flow of the system when borrowing and returning books can be described as:

1. **Borrowing a Book**:
   - The system (or user) calls the `borrow()` method of a `Book` object (either `FictionBook` or `NonFictionBook`).
   - The method first checks if the book is already borrowed by checking the `borrowed` flag.
   - If the book is available, the flag is set to `true`, and a relevant message is displayed (different messages for `FictionBook` and `NonFictionBook`).
   
   Example:
   ```java
   fiction.borrow(); // Output: The Great Gatsby has been borrowed.
   nonFiction.borrow(); // Output: Sapiens has been borrowed. Please return within 2 weeks.
   ```

2. **Returning a Book**:
   - The system calls the `returnBook()` method of the `Book` object.
   - The method checks if the book was borrowed by verifying the `borrowed` flag.
   - If it was borrowed, the flag is set to `false`, and a return message is shown.

   Example:
   ```java
   fiction.returnBook(); // Output: The Great Gatsby has been returned.
   nonFiction.returnBook(); // Output: Sapiens has been returned. Thank you for returning it on time.
   ```

#### 4. **Class Diagram**

Here’s the representation of the class relationships:

```
        +-----------------+
        |      Book        |    << Interface >>
        +-----------------+
        | + borrow()       |
        | + returnBook()   |
        | + isBorrowed()   |
        +-----------------+
                ^
                |
        +-------------------+
        | FictionBook        |    << Implements Book >>
        +-------------------+
        | - title: String    |
        | - borrowed: bool   |
        +-------------------+
        | + borrow()         |
        | + returnBook()     |
        | + isBorrowed()     |
        +-------------------+
                ^
                |
        +-------------------+
        | NonFictionBook     |    << Implements Book >>
        +-------------------+
        | - title: String    |
        | - borrowed: bool   |
        +-------------------+
        | + borrow()         |
        | + returnBook()     |
        | + isBorrowed()     |
        +-------------------+
```

#### 5. **Interaction Flow**

- The **LibraryManagementSystem** class orchestrates the borrowing and returning of books.
- The interaction flow between components:
  - A `FictionBook` or `NonFictionBook` object is created.
  - The system calls `borrow()` or `returnBook()` on the book object as needed.
  - The logic in the respective class (either `FictionBook` or `NonFictionBook`) handles borrowing and returning in its own way.
  - The system checks the `isBorrowed()` status to see if the book is available.

#### 6. **Scalability**

The system is designed to be **extensible**. If new types of books are introduced, such as:

- **EBook**: Implements additional methods like `download()`.
- **Magazine**: Implements its own borrowing logic (e.g., shorter borrowing period).

They can easily implement the `Book` interface without affecting existing code.

---

In conclusion, this low-level design ensures that:
- Each type of book (fiction or non-fiction) has specific behaviors while adhering to a shared interface (`Book`).
- The system can be extended with new book types without changing the core library system.
