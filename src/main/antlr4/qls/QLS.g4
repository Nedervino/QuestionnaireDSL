grammar QLS;



stylesheet      : 'stylesheet' IDENTIFIER LEFTBRACKET page* RIGHTBRACKET;

page            : 'page' IDENTIFIER block;

block           : LEFTBRACKET component* RIGHTBRACKET;

component       : section
                | defaultWidget
                ;

section         : 'section' IDENTIFIER block;

defaultWidget   : 'default' type (widget | widgetStyle);

widget          : 'widget' widgetType;

type            : 'boolean'                                                                 #booleanType
                | 'integer'                                                                 #integerType
                | 'string'                                                                  #stringType
                | 'money'                                                                   #moneyType
                | 'decimal'                                                                 #decimalType
                | 'date'                                                                    #dateType
                ;

widgetType      : 'slider' sliderMap                                                        #sliderWidget
                | 'spinbox' (LEFTPARENTHESES yes=STRINGLITERAL RIGHTPARENTHESES)?           #spinboxWidget
                | 'text'                                                                    #textWidget
                | 'radio' choiceMap?                                                        #radioWidget
                | 'checkbox'                                                                #checkboxWidget
                | 'dropdown' choiceMap?                                                     #dropdownWidget
                ;

sliderMap       : LEFTPARENTHESES start=INTEGERLITERAL',' end=INTEGERLITERAL',' step=INTEGERLITERAL RIGHTPARENTHESES;

choiceMap       : LEFTPARENTHESES yes=STRINGLITERAL ',' no=STRINGLITERAL RIGHTPARENTHESES;

widgetStyle     : LEFTBRACKET styleRule+ widget? RIGHTBRACKET;

styleRule       : IDENTIFIER COLON value;

value           : INTEGERLITERAL
                | STRINGLITERAL
                | HEXCOLOR
                ;







//Literals
//HEXCOLOR            : '#' ('0'..'9' | 'a'..'f'){6};
HEXCOLOR            : '#' ('0'..'9' | 'a'..'f')+;
INTEGERLITERAL      : DIGIT+;
STRINGLITERAL       : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|','|':')* '"';
//DECIMALLITERAL      : DIGIT+ '.' DIGIT+;

IDENTIFIER          : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
DIGIT               : [0-9];

COLON               : ':';
COMMA               : ',';

LEFTBRACKET         : '{';
RIGHTBRACKET        : '}';

LEFTPARENTHESES     : '(';
RIGHTPARENTHESES    : ')';

WHITESPACE          : (' ' | '\t' | '\n' | '\r')+ -> skip;
MULTICOMMENT        : '/*' .*? '*/' -> skip;
SINGLECOMMENT       : '//' ~[\r\n]* '\r'? '\n' -> skip;