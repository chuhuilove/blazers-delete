package com.chuhui.blazers.antlr4;

import com.chuhui.blazers.antlr4.arrayinit.ArrayInitBaseListener;
import com.chuhui.blazers.antlr4.arrayinit.ArrayInitParser;

/**
 * ShortToUnicodeString
 *
 * @author: 纯阳子
 * @Date: 2019/3/19
 * @Description:将类似于{1,2,3}的short数组初始化语句翻译为"\u0001\u0002\u0003"
 */
public class ShortToUnicodeString extends ArrayInitBaseListener {
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {

        //将 { 翻译成 ""
        System.out.println("");
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        //将 } 翻译成 ""
        System.out.println("");
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {

        /**
         * 每个整数翻译成四位的十六进制形式，然后加前缀
         */

        int value = Integer.valueOf(ctx.INT().getText());
        System.out.printf( "\\u"+"%04x",value);
    }

}
