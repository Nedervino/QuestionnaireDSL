grammar QL;


form                : 'form' IDENTIFIER block;

block               : LEFTBRACKET statement* RIGHTBRACKET;
statement           : question
                    | computedQuestion
                    | ifStatement
                    ;


question            : STRINGLITERAL declaration;
declaration         : IDENTIFIER COLON type;

computedQuestion    : STRINGLITERAL declaration ASSIGNMENT expression;

ifStatement         : 'if' LEFTPARENTHESES expression RIGHTPARENTHESES block elseBlock?;
elseBlock           : 'else' block;

expression          : LEFTPARENTHESES expression RIGHTPARENTHESES           #nestedExpression
                    | unaryOperator expression                              #unaryExpression
                    | left=expression arithmeticOperator right=expression   #arithmeticBinary
                    | left=expression relationalOperator right=expression   #relationalBinary
                    | left=expression logicalOperator right=expression      #logicalBinary
                    | value                                                 #expressionValue
                    ;

unaryOperation      : (MINUS | NEGATION) expression;

arithmeticOperator  : PLUS
                    | MINUS
                    | DIVISION
                    | MULTIPLICAITON
                    ;

relationalOperator  : LESSTHAN
                    | LESSTHANOREQUAL
                    | GREATERTHAN
                    | GREATERTHANOREQUAL
                    | EQUAL
                    | NOTEQUAL
                    ;

unaryOperator       : MINUS
                    | NEGATION
                    ;

logicalOperator     : AND
                    | OR
                    ;

value               : BOOLEANLITERAL                                        #booleanLiteral
                    | INTEGERLITERAL                                        #integerLiteral
                    | STRINGLITERAL                                         #stringLiteral
                    | MONEYLITERAL                                          #moneyLiteral
                    | DECIMALLITERAL                                        #decimalLiteral
                    | DATELITERAL                                           #dateLiteral
                    | IDENTIFIER                                            #variable
                    ;

type                : 'boolean'                                             #booleanType
                    | 'integer'                                             #integerType
                    | 'string'                                              #stringType
                    | 'money'                                               #moneyType
                    | 'decimal'                                             #decimalType
                    | 'date'                                                #dateType
                    ;



//Literals
BOOLEANLITERAL      : ('true' | 'false');
INTEGERLITERAL      : DIGIT+;
STRINGLITERAL       : '"' (~('"' | '\\' | '\r' | '\n'))* '"';
MONEYLITERAL        : DIGIT+ ',' DIGIT DIGIT;
DECIMALLITERAL      : DIGIT+ '.' DIGIT+;
DATELITERAL         : DIGIT DIGIT '-' DIGIT DIGIT '-' DIGIT DIGIT DIGIT DIGIT;

IDENTIFIER          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
DIGIT               : [0-9];


//Operators
//Relational
LESSTHAN            : '<';
LESSTHANOREQUAL     : '<=';
GREATERTHAN         : '>';
GREATERTHANOREQUAL  : '>=';
EQUAL               : '==';
NOTEQUAL            : '!=';

//Arithmetic
MINUS               : '-';
PLUS                : '+';
DIVISION            : '/';
MULTIPLICAITON      : '*';

//Logical
AND                 : '&&';
OR                  : '||';

NEGATION            : '!';

COLON               : ':';
ASSIGNMENT          : '=';

LEFTBRACKET         : '{';
RIGHTBRACKET        : '}';

LEFTPARENTHESES     : '(';
RIGHTPARENTHESES    : ')';

WHITESPACE          : (' ' | '\t' | '\n' | '\r')+ -> skip;
MULTICOMMENT        : '/*' .*? '*/' -> skip;
SINGLECOMMENT       : '//' ~[\r\n]* '\r'? '\n' -> skip;