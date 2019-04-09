/**
* 将词法规则重构并抽取出来成为一个"模块"意味着可以将它应用于不同的语法分析器
*/
lexer grammar CommonLexerRules;  //注意区别,是lexer grammar

ID:[a-zA-Z]+;   //匹配标志符
INT:[0-9]+;     //匹配整数
NEWLINE:'\r'?'\n'; //告诉语法分析器一个新行的开始(即语句终止标志)
WS:[\t]+->skip;   //丢弃空白字符



