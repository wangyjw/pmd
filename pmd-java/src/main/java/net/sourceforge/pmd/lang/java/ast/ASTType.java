/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import net.sourceforge.pmd.annotation.InternalApi;

/**
 * Represents a type reference.
 *
 * <pre>
 *
 * Type ::= {@linkplain ASTReferenceType ReferenceType} | {@linkplain ASTPrimitiveType PrimitiveType}
 *
 * </pre>
 *
 * Note: it is not exactly the same the "UnnanType" defined in JLS.
 */
public class ASTType extends AbstractJavaTypeNode {

    @InternalApi
    @Deprecated
    public ASTType(int id) {
        super(id);
    }

    @InternalApi
    @Deprecated
    public ASTType(JavaParser p, int id) {
        super(p, id);
    }

    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }


    public String getTypeImage() {
        ASTClassOrInterfaceType refType = getFirstDescendantOfType(ASTClassOrInterfaceType.class);
        if (refType != null) {
            return refType.getImage();
        }
        return getFirstDescendantOfType(ASTPrimitiveType.class).getImage();
    }


    @Deprecated
    public int getArrayDepth() {
        if (jjtGetNumChildren() != 0
            && (jjtGetChild(0) instanceof ASTReferenceType || jjtGetChild(0) instanceof ASTPrimitiveType)) {
            return ((Dimensionable) jjtGetChild(0)).getArrayDepth();
        }
        return 0; // this is not an array
    }


    /**
     * @deprecated Use {@link #isArrayType()}
     */
    @Deprecated
    public boolean isArray() {
        return isArrayType();
    }


    /**
     * Returns true if this type is an array type.
     */
    public boolean isArrayType() {
        return getArrayDepth() > 0;
    }
}
