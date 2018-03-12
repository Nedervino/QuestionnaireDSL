grammar QLS;



stylesheet      : 'stylesheet' IDENTIFIER page*;

page            : 'page' IDENTIFIER block;

block           : '{'(component)*'}';

component       : section
                | defaultWidget
                ;

section         : 'section' IDENTIFIER block;

defaultWidget   : 'default' type 'widget' widgetType
                | 'default' type widgetStyle;



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

styleRule       : ;



