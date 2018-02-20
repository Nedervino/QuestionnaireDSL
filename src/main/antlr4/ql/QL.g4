grammar QL;

/* Requirements

- grouping construct (for assigning characteristics to a group of questions, like style)
- question
    - id,
    - label (actual question)
    - type
    - (optional) associated to an expression

// Boolean expressions, e.g.
&&
||
!

// Comparisons
<
>
>=
<=
!=
==

// Required types
BOOL
STRING
INTEGER
DATE
DECIMAL
MONEY

// Additional options. Only requirement is the data type can be automatically mapped to a widget
ENUMERATION //(e.g. good, bad, don't know)
INTEGER_RANGE // (e.g. 1..5)



TODO:
- Refactor lexer/parser divisiion
- Reconsider money / decimal separation
- Implement DATE type
*/


form            : 'form'  ID  block;

block           : '{'(statement)*'}';
statement       : input
                | output
                | exprIf
                | assignment
                ;


input           : STRINGLIT declaration;
declaration     : ID ':' TYPE;

output          : STRINGLIT assignment;
assignment      : (declaration | ID) '=' expr;


exprIf          : 'if' '(' exprBool ')' block elseBlock?;
elseBlock       : 'else' block;


expr            : exprBool
                | exprNum
                | exprStr
                ;

exprBool        : '(' exprBool ')'
                | '!' exprBool
                | exprBool '&&' exprBool
                | exprBool '||' exprBool
                | exprBool '==' exprBool
                | exprBool '!=' exprBool
                | compNum
                | compStr
                | valBool
                ;

// Compare Numerical
compNum         : exprNum compNumSym exprNum;
compNumSym      : ('<'|'<='|'>'|'>='|'=='|'!=');
compStr         : exprStr '==' exprStr
                | exprStr '!=' exprStr
                ;
valBool         : BOOLEAN_LITERAL | ID;

exprNum	        : exprNum '+' exprNum
                | exprNum '-' exprNum
                | exprNum '/' exprNum
                | exprNum '*' exprNum
                | '-' exprNum
                | '(' exprNum ')'
                | valNum
                ;
valNum	        : INT | ID;

exprStr	        : exprStr '+' exprStr
                | '(' exprStr ')'
                | valStr
                ;

valStr	        : STRINGLIT | ID;

//Literals
//TODO replace "INT" in the line valNum with INT | DECIMAL | MONEY_LITERAL and test this.
//MONEY_LITERAL   : '-'? INT+ '.' INT INT;
DECIMAL_LITERAL : '-'? INT+ '.' INT+;
BOOLEAN_LITERAL : ('true' | 'false');


//Types
TYPE            : ('boolean' | 'money' | 'int' | 'float' | 'string');
STRINGLIT       : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|','|':')* '"';
INT             : ('0'..'9')+;

//Other terms
ID              : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
WHITESPACE      : (' ' | '\t' | '\n' | '\r')+ -> skip;

MULTI_COMMENT   : '/*' .*? '*/' -> skip;

SINGLE_COMMENT  : '//' ~[\r\n]* '\r'? '\n' -> skip;