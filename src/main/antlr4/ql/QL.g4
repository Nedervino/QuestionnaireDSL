grammar QL;


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
assignment      : declaration '=' expr;

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


//Lexer terms
//Types
TYPE            : ('boolean' | 'money' | 'int' | 'float' | 'string');
BOOLEAN         : ('true' | 'false');
STRINGLIT       : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|','|':')* '"';
INT             : ('0'..'9')+;

//Other terms
ID              : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
WS              : (' ' | '\t' | '\n' | '\r')+ -> skip;
