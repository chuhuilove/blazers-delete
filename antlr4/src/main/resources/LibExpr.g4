grammar LibExpr;
import CommonLexerRules;


/**起始规则,语法分析的起点**/
prog: stat+;

stat: expr NEWLINE
    | ID '=' expr  NEWLINE
    | NEWLINE
    ;

expr: expr('*'|'/') expr
    | expr('+'|'-') expr
    | INT
    | ID
    | '(' expr ')'
    ;
