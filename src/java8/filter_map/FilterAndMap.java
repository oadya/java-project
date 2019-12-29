package java8.filter_map;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterAndMap {

	public static void main(String[] args) {
		example1();
		
		example2();
		
		example3();
	
		example4();
		
		example5();
		
		example6();
		
		example7();
		
		example8();
		
		example9();
		
		example10();
		
		
	}
	
	private static  void  example1(){

		System.out.println(/************* example1 *************/);
		
		List<String> lines = Arrays.asList("spring", "node", "java");
		
		List<String> finalLists = lines.stream().filter(e-> !"java".equals(e)).collect(Collectors.toList());
		
		finalLists.forEach(System.out::println);
	}
	
	private static  void  example2(){
		
		System.out.println(/************* example2 *************/);
		
	    List<Person> persons = Arrays.asList(
                new Person("jane", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
		
		List<Person> finalLists = persons.stream().filter(e-> !"jane".equals(e.getName())).collect(Collectors.toList());
		
		finalLists.forEach(System.out::println);
	}
	
	
    private static  void  example3(){
		
		System.out.println(/************* example3 *************/);
		
	    List<Person> persons = Arrays.asList(
                new Person("jane", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
		
	    Person p = persons.stream().filter(e-> "jane".equals(e.getName())).findAny().orElse(null);

	    Person p2 = persons.stream().filter(e-> "jack".equals(e.getName()) && e.getAge() == 20).findAny().orElse(null);
		
	    Person p3 = persons.stream().filter(e-> { 
	       return "lawrence".equals(e.getName()) && e.getAge() == 40 ? true :false; 
	    }
	    ).findAny()
	      .orElse(null);
		
	     System.out.println(p);
	     System.out.println(p2);
	     System.out.println(p3);
	}
	

    
private static  void  example4(){
		
		System.out.println(/************* example4 *************/);
		
	    List<Person> persons = Arrays.asList(
                new Person("jane", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
		
	    String name = persons.stream().filter(e-> "jane".equals(e.getName())).map(Person::getName).findAny().orElse(null);

	    String name2 = persons.stream().filter(e-> "jack".equals(e.getName())).map(e-> e.getName()).findAny().orElse(null);

		
	     System.out.println(name);
	     System.out.println(name2);
	}


private static  void  example5(){
	
	System.out.println(/************* example5 *************/);
	
    List<Person> persons = Arrays.asList(
            new Person("jane", 30),
            new Person("jack", 20),
            new Person("lawrence", 40),
            new Person(null, 40),
            null
    );
	
    List<String> names = persons.stream().filter(e-> e != null).map(Person::getName).collect(Collectors.toList());

    List<Integer> ages = persons.stream().filter(Objects::nonNull).map(e-> e.getAge()).collect(Collectors.toList());

     System.out.println(names);
     System.out.println(ages);
}

private static  void  example6(){
	
	System.out.println(/************* example6 *************/);
	
    List<Person> persons = Arrays.asList(
            new Person("jane", 30),
            new Person("jack", 20),
            new Person("lawrence", 40),
            new Person(null, 40),
            null
    );
	
    List<String> names = persons.stream().filter(e-> e != null).map(Person::getName).filter(Objects::nonNull).map(String::toUpperCase).collect(Collectors.toList());

    List<Integer> ages = persons.stream().filter(Objects::nonNull).map(e-> e.getAge()).map(Math::negateExact).collect(Collectors.toList());

     System.out.println(names);
     System.out.println(ages);
}



private static  void  example7(){
	
	System.out.println(/************* example7 *************/);
	  
	List<Staff> staff = Arrays.asList(
              new Staff("jane", 30, new BigDecimal(10000)),
              new Staff("jack", 27, new BigDecimal(20000)),
              new Staff("lawrence", 33, new BigDecimal(30000))
      );
	
	List<StaffPublic> staffPublic = staff.stream().map( elt -> {
    	 StaffPublic res = new StaffPublic();
    	 
    	 res.setName(elt.getName());
    	 res.setAge(elt.getAge());
    	 res.setExtra("test");
    	 
    	 return res;
     } 
     ).collect(Collectors.toList());

     System.out.println(staffPublic);
}

private static void example8(){
	 System.out.println("/*-------- example8 -----------*/"); 
	 Map<Integer, String> HOSTING = new HashMap<>();
     HOSTING.put(1, "linode.com");
     HOSTING.put(2, "heroku.com");
     HOSTING.put(3, "digitalocean.com");
     HOSTING.put(4, "aws.amazon.com");
     
   //Map -> Stream -> Filter -> Map
     Map<Integer, String> collect = HOSTING.entrySet().stream()
             .filter(map -> map.getKey() == 2)
             .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

     System.out.println(collect); 

     Map<Integer, String> collect2 = HOSTING.entrySet().stream()
             .filter(map -> map.getKey() <= 3)
             .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

     System.out.println(collect2); 
	
}


private static void example9(){
	

	 System.out.println("/*-------- example9 -----------*/"); 
	 
	Map<Integer, String> HOSTING = new HashMap<>();
    HOSTING.put(1, "linode.com");
    HOSTING.put(2, "heroku.com");
    HOSTING.put(3, "digitalocean.com");
    HOSTING.put(4, "aws.amazon.com");
	
    
    //Map -> Stream -> Filter -> String
    String result = HOSTING.entrySet().stream()
            .filter(map -> "aws.amazon.com".equals(map.getValue()))
            .map(map -> map.getValue())
            .collect(Collectors.joining());

    System.out.println("filterd map: " + result);

    // filter more values
   String  result2 = HOSTING.entrySet().stream()
            .filter(x -> {
                if (!x.getValue().contains("amazon") && !x.getValue().contains("digital")) {
                    return true;
                }
                return false;
            })
            .map(map -> map.getValue())
            .collect(Collectors.joining(","));

    System.out.println("filtered map : " + result2);
}


private static void example10(){
	

	 System.out.println("/*-------- example10 -----------*/"); 
	 
	 Map<Integer, String> HOSTING = new HashMap<>();
     HOSTING.put(1, "linode.com");
     HOSTING.put(2, "heroku.com");
     HOSTING.put(3, "digitalocean.com");
     HOSTING.put(4, "aws.amazon.com");
     HOSTING.put(5, "aws2.amazon.com");
	
   
  
   Map<Integer, String> filteredMap = filterByValue(HOSTING, x -> x.contains("linode"));
   System.out.println(filteredMap);


   Map<Integer, String> filteredMap2 = filterByValue(HOSTING, x -> (x.contains("aws") || x.contains("linode")));
   System.out.println(filteredMap2);

   
   Map<Integer, String> filteredMap3 = filterByValue(HOSTING, x -> (x.contains("aws") && !x.contains("aws2")));
   System.out.println(filteredMap3);

   
   Map<Integer, String> filteredMap4 = filterByValue(HOSTING, x -> (x.length() <= 10));
   System.out.println(filteredMap4);
   }

//Generic Map filterbyvalue, with predicate
public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
    return map.entrySet()
            .stream()
            .filter(x -> predicate.test(x.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
}

}
