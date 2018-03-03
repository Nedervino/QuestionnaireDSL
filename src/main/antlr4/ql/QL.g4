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
statement       : question
                | computedQuestion
                | ifStatement
                ;


question        : STRLIT declaration;
declaration     : ID ':' TYPE;

computedQuestion: STRLIT declaration '=' expr;

ifStatement     : 'if' '(' expr ')' block elseBlock?;
elseBlock       : 'else' block;

expr            : '('expr')'                        #nestedExpression
                | unOp                              #unaryOperation
                | left=expr ARITHMETIC right=expr   #arithMeticBinary
                | left=expr RELATIONAL right=expr   #relationalBinary
                | left=expr LOGICAL right=expr      #logicalBinary
                | val                               #value
                ;

unOp            : UNARY expr
                ;

val             : BOOLLIT                           #booleanLiteral
                | INTLIT                            #integerLiteral
                | STRLIT                            #stringLiteral
                | ID                                #identifier
                ;


TYPE            : ('boolean' | 'money' | 'int' | 'float' | 'string');
STRLIT          : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|','|':')* '"';
INTLIT          : ('0'..'9')+;
//TODO replace "INT" in the line valNum with INT | DECIMAL | MONEY_LITERAL. This will allow using numericals interchangeably. Test this thoroughly.

//TODO the line which defines MONLIT is incorrect. The two INTLIT terms would allow integers of any length at these positions. We could either reuse a DIGIT term, or inline this.
//MONLIT        : '-'? INTLIT+ '.' INTLIT INTLIT;
DECLIT          : '-'? INTLIT+ '.' INTLIT+;
BOOLLIT         : ('true' | 'false');

//Binary Operators
ARITHMETIC      : ('+'|'-'|'/'|'*');
RELATIONAL      : ('<'|'<='|'>'|'>='|'=='|'!=');
LOGICAL         : ('&&'|'||');

//Unary Operators
UNARY           : ('!'|'-');


ID              : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
WHITESPACE      : (' ' | '\t' | '\n' | '\r')+ -> skip;

MULTI_COMMENT   : '/*' .*? '*/' -> skip;

SINGLE_COMMENT  : '//' ~[\r\n]* '\r'? '\n' -> skip;