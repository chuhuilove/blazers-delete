package com.chuhui.blazers.antlr4;

import com.chuhui.blazers.antlr4.arrayinit.ArrayInitLexer;
import com.chuhui.blazers.antlr4.arrayinit.ArrayInitParser;
import com.chuhui.blazers.antlr4.expr.ExprLexer;
import com.chuhui.blazers.antlr4.expr.ExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * ExprStraup
 *
 * @author: 纯阳子
 * @Date: 2019/4/9
 * @Description:TODO
 */
public class ExprStraup {


    public static void main(String[] args) {

        // create a CharStream that reads from standard input

        CharStream charStream= CharStreams.fromString("a=10+13*13");

        // create a lexer that feeds off of input CharStream
        ExprLexer lexer = new ExprLexer(charStream);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        ExprParser parser = new ExprParser(tokens);

        ParseTree tree = parser.expr(); // begin parsing at init rule

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(new ShortToUnicodeString(),tree);

        System.out.println();





    }

}
