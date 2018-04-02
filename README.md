# QL/QLS - A DSL Questionnaire Generator
This repository contains work on two DSLs (QL and QLS) tailored for creating questionnaires and their interfaces. QL is used to define the functionality of the form, while QLS files can be used as stylesheets. ANTLR4 is used for parser generation from the specified grammars.
The project is based on the [Language Workbench Challenge](https://homepages.cwi.nl/~storm/publications/lwc13paper.pdf) issued by the Dutch national research institute for mathematics and computer science, CWI. More info on the requirements for the challenge can be found [here](https://github.com/software-engineering-amsterdam/software-construction/blob/master/2017-2018/QL.pdf).



## Collaborators:
* Tim Nederveen ([@nedervino](mailto:tim.nederveen@hotmail.com))
* Bram Oosterlee ([@bmoosterlee](mailto:bram.oosterlee@student.uva.nl))

## Stack
* Java 1.8
* Swing
* ANTLR4
* JUnit 4
* Maven

## How to run
* ```mvn antlr4:antlr4``` to generate parsers from grammar
* ```mvn exec:java``` to run
* Alternatively, instead of directly running generate a JAR with ```mvn package``` and run with ```java -cp target/querylanguage-1.0-jar-with-dependencies.jar ql.Main``` 

## Setup instructions for IntelliJ
* File -> New -> Project from existing sources -> Kazan
* Make sure to run the antlr4 maven plugin to generate required parsers from the grammar
* Run /endless-ql/Kazan/src/main/java/ql/Main

## Running Tests
```mvn test```

## How does it work?
.g4 files containing Lexer/Parser rules for both the QL and QLS DSLs can be found in src/main/antlr4. Before running, Maven will need to generate the required classes from this grammar using the included ANTLR v4 plugin.
Once this is done, you can define the form you wish to create in src/main/java/ql/Main. src/input contains example input files used for generating the form and its corresponding styling.
The parser of the DSL is implemented using a grammar-based parser generator, and the internal DSL program structure is represented using abstract syntax trees. The DSL programs are interpreted.

# QL DSL Specification
The program accepts a .ql input file which specifies the questions to be contained in the form, including their label, excepted answer type (one of integer, decimal, boolean, text, date, or money),
and computed output fields (computed questions) based on answers to other questions.  

## Syntax
The following is an example of a valid QL program:
```
form taxOfficeExample
{ 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    
  if (hasSoldHouse)
  {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money = 
        (sellingPrice - privateDebt)
  }
  
}
```

## Validation
Input QL forms are checked for the following violations:
   * reference to undefined questions
   * duplicate question declarations with different types
   * conditions that are not of the type boolean
   * operands of invalid type to operators
   * cyclic dependencies between questions
   * duplicate labels (warning)


# QLS DSL Specification

Optionally, the program can be passed a second input file with .qls extension, used for specifying the form's styling. 

QLS allows you to place questions of a base QL program in pages and sections. Furthermore, you can define default 
widget types and styles for questions of a particular type (e.g. boolean questions). Such default styles can be overridden on a per widget basis.

## Syntax
The following is an example of a valid QLS program to accompany the previous QL example:
```
stylesheet taxOfficeExample 
{
  page Housing
  {
    section "Buying"
    {
      question hasBoughtHouse  
        widget checkbox 
    }
    section "Loaning"  
      question hasMaintLoan
  }

  page Selling
  { 
    section "Selling"
    {
      question hasSoldHouse
        widget radio("Yes", "No") 
      section "You sold a house"
      {
        question sellingPrice
          widget spinbox(0, 100)
        question privateDebt
          widget slider(4, 20)
        question valueResidue
        default money
        {
          width: 400
          font: "Arial" 
          fontsize: 14
          color: #999999
          widget spinbox(3, 30)
        }        
      }
    }
    default boolean widget radio("Yes", "No")
  }  
}
```

The following widgets are supported: slider, spinbox (for numbers), text, yesno-radios, checkbox, yesno-dropdown (for booleans)

## Validation

Input QLS stylesheets are checked for the following violations:

  * no references to questions that are not in the QL program
  * all questions of the QL program are placed by the QLS program.
  * (default) widget assignments are compatible with question types (e.g. no radio button for integer widgets).
  * you cannot place a single question multiple times.




