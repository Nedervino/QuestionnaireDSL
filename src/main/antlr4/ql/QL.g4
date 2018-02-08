// Define a grammar called Hello
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
