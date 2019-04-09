
/** Grammars always start with a grammar header. This grammar is called
 *  ArrayInit and must match the filename: ArrayInit.g4
 */
grammar ArrayInit;

/** 一条名为init的规则，它匹配一对花括号，逗号分隔的value*/
init  : '{' value (',' value)* '}';  //必须匹配至少一个 value

/** 一个value可以是嵌套的花括号结构，也可以是一个简单的整数，即INT词法符号*/
value : init
      | INT
      ;

// 词法分析器的规则必须以小写字母开头，词法分析器的规则必须用大写字母开头
INT :   [0-9]+ ;             //  定义词法符号INT,它由一个或多个数字组成
WS  :   [ \t\r\n]+ -> skip ; // 定义词法规则 “空白符号”，丢弃之

//输入的是一个由一对花括号包裹的三个值组成的初始化语句
// this is test






