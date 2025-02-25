# Spella: Custom Arithmetic Evaluator with REPL

Spella is an interactive **Read-Eval-Print Loop (REPL)** for evaluating 
arithmetic expressions. It parses and evaluates expressions using a 
**recursive descent parser** based on a well-defined **Context-Free Grammar
(CFG)** in **Backus-Naur Form (BNF)**.

This project demonstrates **how to define and parse a custom arithmetic 
grammar**, making it an excellent educational tool for **parsing theory**,
**language design**, and **compiler construction**.

---
## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [How to Use](#how-to-use)
- [Grammar Definition in BNF](#grammar-definition-in-bnf)
- [Building the REPL](#building-the-repl)
- [REPL Workflow](#building-the-repl)
- [Installation & Running](#installation--running) #TBD
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

---
## Project Overview

Spella is a fun project that began with the goal of building something simpler before diving into the complexities of a full-fledged **compiler** or **interpreter**. It’s designed as a **stepping stone** for understanding core concepts in parsing and evaluation without the overhead of building a full toy programming language.

By starting with **natural language arithmetic expressions**, Spella allows us to focus on key topics like **tokenization**, **parsing**, and **evaluation** in a manageable way. It supports basic mathematical operations, including addition, subtraction, multiplication, and division, along with **unary operations** and **grouping with parentheses**.

The project uses a **recursive descent parsing** approach, which is simple and effective for this level of complexity.

So, in short: we wanted to build a small, manageable project first to lay the foundation before jumping into something more complex! 🚀

---
## Features

✔️ **Interactive REPL** for evaluating expressions in real time  
✔️ **Natural language operators** (`plus`, `minus`, `times`, `divided by`)  
✔️ **Unary operations** (e.g., `minus 2` → `-2.0`)  
✔️ **Parentheses grouping** for complex expressions  
✔️ **Floating point support** (e.g., `3.5 times 2`)  
✔️ **Recursive descent parser** for structured syntax processing  
✔️ **Unit-tested Lexer and Parser** for correctness

---
## How to Use

Once the program is running, enter expressions in the REPL to evaluate them. 
Spella supports the following syntax:

```
> 2 plus 3
5.0
> 10 divided by 2
5.0
> minus (2 times 2)
-4.0
> 3 times (4 plus 5)
27.0
```

To exit the REPL, `Ctrl+C`.

---
## Grammar Definition in BNF

The **Context-Free Grammar (CFG)** for Spella is defined using **Backus-Naur 
Form (BNF)**. This defines the syntax for valid arithmetic expressions.
Bellow is the grammar for the supported expressions:

```bnf
<expression> ::= <term> ( ("plus" | "minus") <term> )*

<term> ::= <factor> ( ("times" | "divided by") <factor> )*

<factor> ::= <unary> | <grouping> | <number>

<unary> ::= "-" <factor>

<grouping> ::= "(" <expression> ")"

<operator> ::= "plus" | "minus" | "times" | "divided by"

<number> ::= <digit>+ ("." <digit>+)?

<digit> ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
```

This grammar ensures Spella correctly interprets arithmetic expressions 
while allowing **prefix notation for unary operations**.

---
## Building the REPL

The REPL (Read-Eval-Print Loop) is the core interactive feature of Spella. 
It continuously reads user input, **parses the expression**, **evaluates the 
result**, and **prints the output**.

### REPL Workflow

1. **Read** input from the user
2. **Tokenize** input using a **Lexer**
3. **Parse** tokens into an **Abstract Syntax Tree (AST)** using a **recursive descent parser**
4. **Evaluate** the AST to compute the result
5. **Print** the result

---
## Installation & Running

### Prerequisites

- **Java 23+**
- **Maven**

### Clone the Repository

```sh
git clone https://github.com/yourusername/spella.git
cd spella
```
#TBD

---
## Examples

### Basic Arithmetic

```sh
> 3 plus 2
5.0
> 10 minus 4
6.0
> 5 times 3
15.0
> 8 divided by 2
4.0
```

### Unary Operators

```sh
> minus 5
-5.0
> minus (3 plus 2)
-5.0
```

### Parentheses Grouping

```sh
> (2 plus 3) times 4
20.0
> 10 minus (2 times 3)
4.0
```

---
## Contributing
Want to contribute? Go for it! 🚀

1. **Fork** the repo
2. **Create a new branch**
3. **Commit** your changes
4. **Push and open a Pull Request**

No rules, no stress—just have fun! 😎

---
## License

This project is licensed under the **MIT License**.
