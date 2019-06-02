/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
/* Generated By:JJTree: Do not edit this line. ASTNullLiteral.java */

package net.sourceforge.pmd.lang.java.ast;

import net.sourceforge.pmd.lang.ast.Node;

/**
 * An array access expression.
 *
 * <pre class="grammar">
 *
 * ArrayAccess ::=  {@link ASTPrimaryExpression PrimaryExpression} "["  {@link ASTExpression Expression} "]"
 *
 * </pre>
 */
public final class ASTArrayAccess extends AbstractJavaTypeNode implements ASTPrimaryExpression, LeftRecursiveNode {
    ASTArrayAccess(int id) {
        super(id);
    }


    ASTArrayAccess(JavaParser p, int id) {
        super(p, id);
    }

    @Override
    public void jjtClose() {
        super.jjtClose();

        /* JLS:
         *  A name is syntactically classified as an ExpressionName in these contexts:
         *       ...
         *     - As the array reference expression in an array access expression (§15.10.3)
         */
        Node firstChild = jjtGetChild(0);

        if (firstChild instanceof ASTAmbiguousName) {
            replaceChildAt(0, ((ASTAmbiguousName) firstChild).forceExprContext());
        }
    }

    /**
     * Returns the expression to the left of the "[".
     */
    public ASTPrimaryExpression getLhsExpression() {
        return (ASTPrimaryExpression) jjtGetChild(0);
    }

    /**
     * Returns the expression within the brackets.
     */
    public ASTExpression getIndexExpression() {
        return (ASTPrimaryExpression) jjtGetChild(1);
    }


    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }

}
