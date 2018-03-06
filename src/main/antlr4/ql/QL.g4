grammar QL;

/* Requirements

TODO:
- Refactor lexer/parser division
- Remove MONEY / DECIMAL ambiguity (add money-specific symbols in front such as $ ?)
- replace "INT" in the line valNum with INT | DECIMAL | MONEY_LITERAL to allow using numericals interchangeably (?)

*/



form            : 'form' IDENTIFIER block;

block           : '{'(statement)*'}';
statement       : question
                | computedQuestion
                | ifStatement
                ;


question        : STRINGLITERAL declaration;
declaration     : IDENTIFIER ':' type;

computedQuestion: STRINGLITERAL declaration '=' expression;

ifStatement     : 'if' '(' expression ')' block elseBlock?;
elseBlock       : 'else' block;

expression      : '('expression')'                              #nestedExpression
                | unaryOperation                                #unaryExpression
                | left=expression ARITHMETIC right=expression   #arithMeticBinary
                | left=expression RELATIONAL right=expression   #relationalBinary
                | left=expression LOGICAL right=expression      #logicalBinary
                | value                                         #expressionValue
                ;

unaryOperation  : UNARY expression
                ;

value           : BOOLEANLITERAL                                #booleanLiteral
                | INTEGERLITERAL                                #integerLiteral
                | STRINGLITERAL                                 #stringLiteral
                | MONEYLITERAL                                  #moneyLiteral
                | DECIMALLITERAL                                #decimalLiteral
                | DATELITERAL                                   #dateLiteral
                | IDENTIFIER                                    #variable
                ;


type            : 'boolean'                                     #booleanType
                | 'integer'                                     #integerType
                | 'string'                                      #stringType
                | 'money'                                       #moneyType
                | 'decimal'                                     #decimalType
                | 'date'                                        #dateType
                ;



//Literals
BOOLEANLITERAL  : ('true' | 'false');
INTEGERLITERAL  : DIGIT+;
STRINGLITERAL   : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|','|':')* '"';
MONEYLITERAL    : '-'? DIGIT+ ',' DIGIT DIGIT;
DECIMALLITERAL  : '-'? DIGIT+ '.' DIGIT+;
DATELITERAL     : DIGIT DIGIT '-' DIGIT DIGIT '-' DIGIT DIGIT DIGIT DIGIT;

IDENTIFIER      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
DIGIT           : [0-9];

//Binary Operators
ARITHMETIC      : ('+'|'-'|'/'|'*');
RELATIONAL      : ('<'|'<='|'>'|'>='|'=='|'!=');
LOGICAL         : ('&&'|'||');

//Unary Operators
UNARY           : ('!'|'-');


WHITESPACE      : (' ' | '\t' | '\n' | '\r')+ -> skip;

MULTICOMMENT    : '/*' .*? '*/' -> skip;

SINGLECOMMENT   : '//' ~[\r\n]* '\r'? '\n' -> skip;