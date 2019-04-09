package com.chuhui.blazers.antlr4;

import com.chuhui.blazers.antlr4.arrayinit.ArrayInitLexer;
import com.chuhui.blazers.antlr4.arrayinit.ArrayInitParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * BootStarup
 *
 * @author: 纯阳子
 * @Date: 2019/3/19
 * @Description:TODO
 */
public class ArrayInitStraup {

    public static void main(String[] args) {

        // create a CharStream that reads from standard input

        CharStream charStream= CharStreams.fromString("{1,2,3,4,5,6,1,2,3,4,5,6,7,8,9,123456,7,8,90}");

        // create a lexer that feeds off of input CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(charStream);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        ArrayInitParser parser = new ArrayInitParser(tokens);

        ParseTree tree = parser.init(); // begin parsing at init rule

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(new ShortToUnicodeString(),tree);

        System.out.println();

    }


}
