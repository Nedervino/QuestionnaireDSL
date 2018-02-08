//grammar QL;
//WS          :	(' ' | '\t' | '\n' | '\r')+ -> skip;
//form        : 'form' ID block;
//ID          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
//block       : '{'(question)*'}';
//question    : declaration;
//declaration : STRINGLIT ID ':' type;
//type        : ('boolean');
//STRINGLIT   : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|',')+ '"';


grammar QL;
r  : 'hello' (ID | r) ;         // match keyword hello followed by an identifier
ID : [a-z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines


//grammar QL;
//
//
//
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
//question    : MULTILINE_COMMENT
//            ;
//
//computedQuestion
//            : MULTILINE_COMMENT
//            ;
//
//// Tokens
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
