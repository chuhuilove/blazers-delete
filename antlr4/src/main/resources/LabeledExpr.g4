grammar LabeledExpr;


stat: expr  NEWLINE
    | ID '=' expr ';' NEWLINE
    | NEWLINE
    ;

expr: expr op=('*'|'/') expr
    | expr op=('+'|'-') expr
    | INT
    | ID
    | '('expr')'
    ;


MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
ID  :   [a-zA-Z]+ ;      // match identifiers
INT :   [0-9]+ ;         // match integers
NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace