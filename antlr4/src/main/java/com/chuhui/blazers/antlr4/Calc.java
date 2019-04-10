package com.chuhui.blazers.antlr4;

import com.chuhui.blazers.antlr4.labeledexpr.LabeledExprBaseVisitor;
import com.chuhui.blazers.antlr4.labeledexpr.LabeledExprLexer;
import com.chuhui.blazers.antlr4.labeledexpr.LabeledExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Cala
 *
 * @author: 纯阳子
 * @Date: 2019/4/9
 * @Description:TODO
 */
public class Calc {

    public static void main(String[] args) {



        CharStream charStream= CharStreams.fromString("a=10+13*13;");


        LabeledExprLexer lexer = new LabeledExprLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.expr();


        LabeledExprBaseVisitor visitor=new LabeledExprBaseVisitor();

        visitor.visit(tree);

    }


}
