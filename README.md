# Software Construction 2017/2018
This repository, managed by team Kazan, contains work on two DSLs (QL and QLS) tailored for creating questionnaires. ANTLR4 is used for parser generation from created grammars.


## Collaborators:
* Tim Nederveen ([@nedervino](mailto:tim.nederveen@hotmail.com)) - 11198591
* Bram Oosterlee ([@bmoosterlee](mailto:bram.oosterlee@student.uva.nl)) - TODO 

## Setup requirements
* Java 1.8
* ANTLR4
* JUnit 4
* Maven >=3.0

## How does it work?
A .g4 file containing Lexer/Parser rules for the DSLs can be found in src/main/antlr4. src/input contains example input files used for generating actual forms in a GUI.