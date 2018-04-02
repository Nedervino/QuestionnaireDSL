grammar QLS;



stylesheet      : 'stylesheet' IDENTIFIER LEFTBRACKET page* RIGHTBRACKET;

page            : 'page' IDENTIFIER LEFTBRACKET component+ defaultRule* RIGHTBRACKET;

section         : 'section' STRINGLITERAL LEFTBRACKET component+ defaultRule* RIGHTBRACKET
                | 'section' STRINGLITERAL component
                ;

component       : section
                | question
                ;

question        : 'question' IDENTIFIER widget?
//              | 'question' IDENTIFIER styleRule?
                ;

defaultRule     : 'default' type widget                                                     #widgetRule
                | 'default' type style                                                      #styleRule
                ;

widget          : 'widget' widgetType;

type            : 'boolean'                                                                 #booleanType
                | 'integer'                                                                 #integerType
                | 'string'                                                                  #stringType
                | 'money'                                                                   #moneyType
                | 'decimal'                                                                 #decimalType
                | 'date'                                                                    #dateType
                ;

widgetType      : 'slider' sliderMap                                                        #sliderType
                | 'spinbox'                                                                 #spinboxType
                | 'textfield'                                                               #textfieldType
                | 'radio' choiceMap?                                                        #radioType
                | 'checkbox'  (LEFTPARENTHESES yes=STRINGLITERAL RIGHTPARENTHESES)?         #checkboxType
                | 'dropdown' choiceMap?                                                     #dropdownType
                ;

sliderMap       : LEFTPARENTHESES start=INTEGERLITERAL COMMA end=INTEGERLITERAL COMMA step=INTEGERLITERAL RIGHTPARENTHESES;

choiceMap       : LEFTPARENTHESES yes=STRINGLITERAL COMMA no=STRINGLITERAL RIGHTPARENTHESES;

style           : LEFTBRACKET styleProperty+ widget? RIGHTBRACKET;

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