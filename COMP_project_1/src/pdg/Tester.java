package pdg;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Tester {
	private static FileInputStream in;
	
	public Tester() {	}
	
	public static void addFile(FileInputStream inArg) throws ParseException, IOException {
		in = inArg;
		CompilationUnit cu;
		try {
		// parse the file
			cu = JavaParser.parse(in);
		} finally {
		    in.close();
		}
        // visit method nodes and print the methods names
       // new MethodVisitor().visit(cu, null);
        // visit expressions,variabledeclarationexpr and variabledeclarators nodes
        //print the above nodes using toString() 
        //new MyVisitor().visit(cu,null);
    
		new MethodVisitor().processNode(cu);
	}
	
}


    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    class MethodVisitor extends VoidVisitorAdapter<Object> {
    	void processNode(Node child2){
    		
    		if(relevant(child2)) {
    			System.out.println("------------------------------------------------------------");
    			System.out.println(child2.getClass());
	    		System.out.println(child2.toString());
	    		//checkIfMethodAndPrintModifiers(child2);
    		}
    		
    		for(Node child: child2.getChildrenNodes()){	
    			processNode(child);
    		}
    	}

		private boolean relevant(Node child2) {
			if(child2.getClass().equals(com.github.javaparser.ast.body.ClassOrInterfaceDeclaration.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.body.VariableDeclaratorId.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.expr.IntegerLiteralExpr.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.stmt.ExpressionStmt.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.expr.FieldAccessExpr.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.expr.NameExpr.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.expr.CharLiteralExpr.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.stmt.BlockStmt.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.expr.BinaryExpr.class))
				return false;
			if(child2.getClass().equals(com.github.javaparser.ast.type.PrimitiveType.class))
				return false;
			return true;
		}

		private void checkIfMethodAndPrintModifiers(Node child2) {
			if(child2.getClass().equals(com.github.javaparser.ast.body.MethodDeclaration.class)) {
				if(ModifierSet.isPrivate(((MethodDeclaration) child2).getModifiers())){
					System.out.println("------------------------------------------------------------");
					System.out.print("ast.Modifier\nprivate\n");
				}
				if(ModifierSet.isPublic(((MethodDeclaration) child2).getModifiers())){
					System.out.println("------------------------------------------------------------");
					System.out.print("ast.Modifier\npublic\n");
				}
				if(ModifierSet.isStatic(((MethodDeclaration) child2).getModifiers())){
					System.out.println("------------------------------------------------------------");
					System.out.print("ast.Modifier\nstatic\n");    			
				}
    			if(ModifierSet.isStrictfp(((MethodDeclaration) child2).getModifiers())){
    				System.out.println("------------------------------------------------------------");
    				System.out.print("ast.Modifier\nstrictfp\n");
				}
    			if(ModifierSet.isSynchronized(((MethodDeclaration) child2).getModifiers())){
    				System.out.println("------------------------------------------------------------");
    				System.out.print("ast.Modifier\nsyncronized\n");
				}
    			if(ModifierSet.isTransient(((MethodDeclaration) child2).getModifiers())){
    				System.out.println("------------------------------------------------------------");
    				System.out.print("ast.Modifier\ntransient\n");
    			}
    			if(ModifierSet.isVolatile(((MethodDeclaration) child2).getModifiers())){
    				System.out.println("------------------------------------------------------------");
    				System.out.print("ast.Modifier\nvolatile\n");
    			}
    		}
		}	
    }
    
    /*
     * 
	class MyVisitor extends VoidVisitorAdapter
	{
		@Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
           
			System.out.print(ModifierSet.getAccessSpecifier(n.getModifiers()) + " ");	
			System.out.println(n.getName());
            
            super.visit(n, arg);
        }
	
	    @Override
	    public void visit (ExpressionStmt stmt, Object args)
	    {
	        System.out.println(stmt.toString()+" THIS IS AN EXPRESSION NODE");
	    	super.visit(stmt,args);
	    }
	
	    @Override
	    public void visit (VariableDeclarationExpr declarationExpr, Object args)
	    {
	    	System.out.println(declarationExpr.toString() + " THIS IS A VARIABLE DECLARATION NODE");
	    	super.visit(declarationExpr, args); 
	        
	    }
	
	    @Override
	    public void visit(VariableDeclarator declarator, Object args)
	    {
	    	System.out.println(declarator.toString() + " THIS IS A DECLARATOR NODE");
	    	super.visit(declarator,args);
	    }
	
	}
	*
	*/