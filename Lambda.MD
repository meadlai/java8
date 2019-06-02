# java8

## Lambda Expressions
 - Syntax of Lambda
    * anonymous classes / function callback, block of code, access effectively final variables from the enclosing scope
    * add default and static methods to interfaces that provide concrete implementations
    
    `
    interface Demo {
    
		default void defaultMethod() {
		
			System.out.println("I am a default method!!!");
			
		}
		
		static void staticMethod() {
		
			System.out.println("I am a static method!!!");
			
		}
		
	}
    `
   
    * no the argument
    
   `
                  new Thread(() -> {
		  
			    System.out.println("this is a function lambda");
			    
		  }).start();
    `
    * optional type declaration
    
    ` 
               Arrays.sort(array, (v1, v2) -> {
	       
			return v1.compareTo(v2);
			
		});
    `
    * optional the keyword **return** and curly braces
    
    `
    		Arrays.sort(array, (v1, v2) -> v1.compareTo(v2));
		
    `
    
	> https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
	
 - Functional Interfaces
 - Method References
 - Constructor References
 - Variable Scope
 - Default Methods
 - Static Methods

