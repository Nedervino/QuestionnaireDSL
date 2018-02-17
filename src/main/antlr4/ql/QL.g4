grammar QL;

/* Requirements

- grouping construct (for assigning characteristics to a group of questions, like style)
- question
    - id,
    - label (actual question)
    - type
    - (optional) associated to an expression, which makes it computed

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

exprIf          : 'if' '(' exprBool ')' block;


expr            : exprBool
                | exprNum
                | exprStr
                ;

exprBool        : exprBool '&&' exprBool
                | exprBool '||' exprBool
                | exprBool '==' exprBool
                | exprBool '!=' exprBool
                | '(' exprBool ')'
                | '!' exprBool
                | compNum
                | compStr
                | valBool
                ;

compNum         : exprNum compNumSym exprNum;
compNumSym      : ('<'|'<='|'>'|'>='|'=='|'!=');
compStr         : exprStr '==' exprStr
                | exprStr '!=' exprStr
                ;
valBool         : BOOLEAN | ID;

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




//Types
TYPE            : ('boolean' | 'money' | 'int' | 'float' | 'string');
BOOLEAN         : ('true' | 'false');
STRINGLIT       : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|','|':')* '"';
INT             : ('0'..'9')+;

//Other terms
ID              : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
WHITESPACE      : (' ' | '\t' | '\n' | '\r')+ -> skip;

MULTI_COMMENT   : '/*' .*? '*/' -> skip;

SINGLE_COMMENT  : '//' ~[\r\n]* '\r'? '\n' -> skip;








/*
    OLD
*/

//
//grammar QL;
//form        : 'form'  ID  '{' (formField)*  '}'
//            ;
//
//formField   : condition
//            | question
//            | computedQuestion
//            ;
//
//condition   : MULTILINE_COMMENT
//            ;
//
//question    :
//            ;
//
//computedQuestion
//            : MULTILINE_COMMENT
//            ;
//
//// Tokens
//ID          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
//
//
//WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
//    ;
//
//MULTILINE_COMMENT
//    : '/*' .* '*/' -> channel(HIDDEN)
//    ;
//
////?
//SINGLELINE_COMMENT
//    :   '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN)
//    ;
//
//Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
//
//Int: ('0'..'9')+;
//
//Str: '"' .* '"';
