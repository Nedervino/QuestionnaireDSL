grammar QLS;



stylesheet      : 'stylesheet' IDENTIFIER LEFTBRACKET page* RIGHTBRACKET;

page            : 'page' IDENTIFIER LEFTBRACKET (section | defaultRule)* RIGHTBRACKET;

section         : 'section' STRINGLITERAL LEFTBRACKET segment* RIGHTBRACKET
                | 'section' STRINGLITERAL segment
                ;

segment         : section
                | question
                | defaultRule
                ;

question        : 'question' IDENTIFIER widget?
//              | 'question' IDENTIFIER styleRule?
                ;

defaultRule     : 'default' type (widget | widgetStyle);

widget          : 'widget' widgetType;

type            : 'boolean'                                                                 #booleanType
                | 'integer'                                                                 #integerType
                | 'string'                                                                  #stringType
                | 'money'                                                                   #moneyType
                | 'decimal'                                                                 #decimalType
                | 'date'                                                                    #dateType
                ;

widgetType      : 'slider' sliderMap                                                        #sliderWidget
                | 'spinbox'                                                                 #spinboxWidget
                | 'text'                                                                    #textWidget
                | 'radio' choiceMap?                                                        #radioWidget
                | 'checkbox'  (LEFTPARENTHESES yes=STRINGLITERAL RIGHTPARENTHESES)?         #checkboxWidget
                | 'dropdown' choiceMap?                                                     #dropdownWidget
                ;

sliderMap       : LEFTPARENTHESES start=INTEGERLITERAL COMMA end=INTEGERLITERAL COMMA step=INTEGERLITERAL RIGHTPARENTHESES;

choiceMap       : LEFTPARENTHESES yes=STRINGLITERAL COMMA no=STRINGLITERAL RIGHTPARENTHESES;

widgetStyle     : LEFTBRACKET styleProperty+ widget? RIGHTBRACKET;

styleProperty   : 'width' COLON INTEGERLITERAL                                              #widthProperty
                | 'font' COLON STRINGLITERAL                                                #fontProperty
                | 'fontsize' COLON INTEGERLITERAL                                           #fontSizeProperty
                | 'color' COLON HEXCOLOR                                                    #colorProperty
                ;

value           : INTEGERLITERAL
                | STRINGLITERAL
                | HEXCOLOR
                ;



//Literals
//HEXCOLOR            : '#' ('0'..'9' | 'a'..'f'){6};
HEXCOLOR            : '#' ('0'..'9' | 'a'..'f')+;
INTEGERLITERAL      : DIGIT+;
STRINGLITERAL       : '"' (~('"' | '\\' | '\r' | '\n'))* '"';

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