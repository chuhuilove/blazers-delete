grammar Hello;

r: 'hello' ID;   //定义一个规则r,该规则是匹配关键字hello和紧随其后的标志符

ID:[a-z]+;

WS  :   [ \t\r\n]+ -> skip ; // 定义词法规则 “空白符号”，丢弃之

