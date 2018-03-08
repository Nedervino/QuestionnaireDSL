# Software Construction 2017/2018
This repository, managed by team Kazan, contains work on two DSLs (QL and QLS) tailored for creating questionnaires. QL is used to define the functionality of the form, while QLS files can be used as stylesheets. ANTLR4 is used for parser generation from the specified grammars.


## Collaborators:
* Tim Nederveen ([@nedervino](mailto:tim.nederveen@hotmail.com)) - 11198591
* Bram Oosterlee ([@bmoosterlee](mailto:bram.oosterlee@student.uva.nl)) - TODO 

## Setup requirements
* Java 1.8
* ANTLR4
* JUnit 4
* Maven >=3.0

## How does it work?
A .g4 file containing Lexer/Parser rules for the DSLs can be found in src/main/antlr4. Before running, Maven will need to generate the required classes from this grammar using the included ANTLR v4 plugin.
Once this is done, you can define the form you wish to create in src/main/java/ql/Main. src/input contains example input questionnaires used for generating the form interface.

