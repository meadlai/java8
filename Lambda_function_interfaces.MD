# java8

## Lambda Expressions

 - Functional Interfaces
 
   - Predicate <T> interface is a functional interface with a method test(Object) to return a Boolean value
    
     ``` java
         Integer sumIf(List<Integer> list, Predicate<Integer> predicate) {
		     return list.stream().filter(predicate).mapToInt(Integer::intValue).sum();
	     }
        
   - Function<T, R> 
   
     ``` java
        public static String validInput(String name, Function<String, String> function) {
		    return function.apply(name);
	    }
   
   - Consumer<T>, Consumes a value of type T, no returns
    
        ``` java
          public static void validInput2(String name, Consumer<String> function) {
		        function.accept(name);
	      }
    
   - Supplier<T>, Supplies a value of type T
	
   - Runnable, Runs an action without arguments or return value

   - BiConsumer<T, U>, Consumes values of types T and U
   
   - BiFunction<T, U, R>, A function with arguments of types T and U
   
   - UnaryOperator<T>, A unary operator on the type T

   - BinaryOperator<T>
	
   - BiPredicate<T, U>
   
   
