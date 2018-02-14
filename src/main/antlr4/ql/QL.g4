grammar QL;
WS          :	(' ' | '\t' | '\n' | '\r')+ -> skip;
form        : 'form' ID block;
ID          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')*;
block       : '{'(question | ifexpr | assignment)*'}';
question    : declaration;
declaration : STRINGLIT ID ':' type;
type        : ('boolean' | 'money' | 'int' | 'float' | 'string');

STRING      : ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|',')+;
STRINGLIT   : '"' STRING '"';
BOOLEAN     : ('true' | 'false');
INT         : ('0'..'9')+;

ifexpr      : 'if' condition block;
condition   : '(' boolexpr ')';

boolval     : (BOOLEAN | ID | boolexpr | comparison);
boolexpr    : ((boolexpr '&&' boolexpr) | (boolexpr '||' boolexpr) | ('!' boolexpr) | ( '(' boolexpr ')' ) | boolval);

comparison  : (compbool | compnum | compstr);
compbool    : (boolexpr ('==' | '!=') boolexpr);
compnum     : (numexpr compsym numexpr);
compsym     : ('<'|'<='|'>'|'>='|'=='|'!=');
compstr     : (strexpr ('==' | '!=') strexpr);

numval	    : (INT | ID | numexpr);
numexpr	    : ((numexpr '+' numexpr) | (numexpr '-' numexpr) | (numexpr '/' numexpr) | (numexpr '*' numexpr) | ('-' numexpr) | ('(' numexpr ')') | numval);

strval	    : (STRINGLIT | ID | strexpr);
strexpr	    : ((strexpr '+' strexpr) | ('(' strexpr ')') | strval);

assignment  : declaration '=' val;
val	    : (boolval | numval | strval);
