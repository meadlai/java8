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
   
   - Consumer<T>
    
        ``` java
          public static void validInput2(String name, Consumer<String> function) {
		        function.accept(name);
	      }
    
   - Supplier<T>
