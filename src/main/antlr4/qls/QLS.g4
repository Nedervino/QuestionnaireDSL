grammar QLS;



stylesheet      : 'stylesheet' IDENTIFIER page*;

page            : 'page' IDENTIFIER block;

block           : '{'(component)*'}';

component       : section
                | defaultWidget
                ;

section         : 'section' IDENTIFIER block;

defaultWidget   : 'default' type (widget | widgetStyle);

widget          : 'widget' widgetType;

type            : 'boolean'                                     #booleanType
                | 'integer'                                     #integerType
                | 'string'                                      #stringType
                | 'money'                                       #moneyType
                | 'decimal'                                     #decimalType
                | 'date'                                        #dateType
                ;

widgetType      : 'slider'                                                    #sliderWidget
                | 'spinbox'                                                   #spinboxWidget
                | 'text'                                                      #textWidget
                | 'radio' '(' yes=STRINGLITERAL ',' no=STRINGLITERAL ')'      #radioWidget
                | 'checkbox'                                                  #checkboxWidget
                | 'dropdown' '(' yes=STRINGLITERAL ',' no=STRINGLITERAL ')'   #dropdownWidget
                ;

widgetStyle     : '{' styleRule+ widget? '}';

styleRule       : IDENTIFIER ':' value;

value           : INTEGERLITERAL
                | STRINGLITERAL
                | HEXCOLOR
                ;




//Literals
COLOR           : '#' ('0'..'9' | 'a'..'f'){6};
INTEGERLITERAL  : DIGIT+;
STRINGLITERAL   : '"' ('a'..'z'|'A'..'Z'|'0'..'9'|' '|'?'|'.'|','|':')* '"';

IDENTIFIER      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
DIGIT           : [0-9];

WHITESPACE      : (' ' | '\t' | '\n' | '\r')+ -> skip;
MULTICOMMENT    : '/*' .*? '*/' -> skip;
SINGLECOMMENT   : '//' ~[\r\n]* '\r'? '\n' -> skip;