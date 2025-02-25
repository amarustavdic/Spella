# Spella: Custom Arithmetic Evaluator with REPL

Spella is an interactive **Read-Eval-Print Loop (REPL)** for evaluating
arithmetic expressions. The core of this project lies in parsing and 
evaluating arithmetic expressions, which are defined by a **Context-Free
Grammar (CFG)** using **Backus-Naur Form (BNF)**. Spella allows users to
input mathematical expressions and get immediate results, all while
showcasing hot to define adn parse a custom grammar.

---
## Table of Contents


---
## Project Overview


---
### Features


---
### How to Use


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

---
## Building the REPL
