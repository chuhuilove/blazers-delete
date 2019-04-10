package com.chuhui.blazers.antlr4;

import com.chuhui.blazers.antlr4.labeledexpr.LabeledExprBaseVisitor;
import com.chuhui.blazers.antlr4.labeledexpr.LabeledExprParser;

import java.util.HashMap;
import java.util.Map;

/**
 * EvalVisitor
 *
 * @author: 纯阳子
 * @Date: 2019/4/9
 * @Description:TODO
 */
public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
    @Override
    public Integer visitStat(LabeledExprParser.StatContext ctx) {
        return super.visitStat(ctx);
    }

    @Override
    public Integer visitExpr(LabeledExprParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }


    //    /** "memory" for our calculator; variable/value pairs go here */
//    Map<String, Integer> memory = new HashMap<String, Integer>();
//
//    /** ID '=' expr NEWLINE */
//    @Override
//    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
//        String id = ctx.ID().getText();  // id is left-hand side of '='
//        int value = visit(ctx.expr());   // compute value of expression on right
//        memory.put(id, value);           // store it in our memory
//        return value;
//    }
//
//    /** expr NEWLINE */
//    @Override
//    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
//        Integer value = visit(ctx.expr()); // evaluate the expr child
//        System.out.println(value);         // print the result
//        return 0;                          // return dummy value
//    }
//
//    /** INT */
//    @Override
//    public Integer visitInt(LabeledExprParser.IntContext ctx) {
//        return Integer.valueOf(ctx.INT().getText());
//    }
//
//    /** ID */
//    @Override
//    public Integer visitId(LabeledExprParser.IdContext ctx) {
//        String id = ctx.ID().getText();
//        if ( memory.containsKey(id) ) return memory.get(id);
//        return 0;
//    }
//
//    /** expr op=('*'|'/') expr */
//    @Override
//    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
//        int left = visit(ctx.expr(0));  // get value of left subexpression
//        int right = visit(ctx.expr(1)); // get value of right subexpression
//        if ( ctx.op.getType() == LabeledExprParser.MUL ) return left * right;
//        return left / right; // must be DIV
//    }
//
//    /** expr op=('+'|'-') expr */
//    @Override
//    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
//        int left = visit(ctx.expr(0));  // get value of left subexpression
//        int right = visit(ctx.expr(1)); // get value of right subexpression
//        if ( ctx.op.getType() == LabeledExprParser.ADD ) return left + right;
//        return left - right; // must be SUB
//    }
//
//    /** '(' expr ')' */
//    @Override
//    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
//        return visit(ctx.expr()); // return child expr's value
//    }
//
}
