// Generated from D:/MyLife/Code/person/java/github/blazers-parent/antlr4/src/main/resources\LabeledExpr.g4 by ANTLR 4.7.2
package com.chuhui.blazers.antlr4.labeledexpr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LabeledExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LabeledExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(LabeledExprParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(LabeledExprParser.ExprContext ctx);
}