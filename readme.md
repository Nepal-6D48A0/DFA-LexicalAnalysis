# Deterministic Finite Automata for Lexical Analysis
Final Project for CSCI 3236, Spring 2019
 
## Running the project
1. Download the zip file or clone the repo.
2. Start the server:
        
       ./mvnw spring-boot:run
        
3.  Visit `http://localhost:8080/` to access the project.

## Description
The language I selected is Structure Query Language (SQL). The DFA is limited to certain keywords
as including all syntax and characters is beyond the scope of this project. It does not perform any arithmetic operation
and checking the grammar of the statement is beyond the scope of this project.
It is case insensitive so 'SELECT' is equivalent to 'select'. <br>

A sample statement for this language is: 

    select order_id, status from tn_orders 
    where (weight<=10 and priority<>3) 
    or type ='Overnight' and
    status is not null;

The minimized DFA diagram can be seen after running th project or under `src/main/resources/static/dfa.html`

#### Keyword
The keywords are reserved and cannot be used as an identifier. These are special commands that are used by
the compiler whose purpose is beyond the scope of the project. The keywords used in this project are:
 
* OR
* IS
* AND
* FROM
* SELECT
* NOT
* NULL
* WHERE

#### Identifier
Identifiers are variables or labels. They cannot be a keyword. <br>
A valid identifier is a combination of letters, digits, and underscore. It must start with a letter or if it
starts with underscore, it must have at least one digit or letter succeeding it. It cannot begin with digit
and any other characters used will be invalid.


#### Number
Number represents numeric values. It is the combination of digits, period (.) for decimals, and - for
negative numbers.

#### Symbol
Symbol refers to some special characters used in this project. They are:
* (
* )
* &lt;
* &gt;
* . (period)
* , (comma)
* ' (quote)
* =
* \*

All these symbols except * can be used as delimiters.
 
