package java8.loop_sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.omg.Messaging.SyncScopeHelper;

public class LoopSort {

	public static void main(String[] args) {
		 example1();
		
		example2();
		
		example3();
		
		example4();
		

		example5();
	}

	private static void example1(){
		
		Map<String, String> map = new HashMap<>();
		
		map.put("1", "Jan");
		map.put("2", "Feb");
		map.put("3", "Mar");
		
		for(Map.Entry<String, String> e : map.entrySet()){
			System.out.println("key ="+e.getKey() + " value ="+e.getValue());
		}
		
		map.forEach((k,v) -> System.out.println("key ="+ k + " value ="+ v));
		
	}
	
	private static void example2(){
		System.out.println("/*-------- example2------------*/");
		 // initial a Map
        Map<String, String> map = new HashMap<>();
        map.put("1", "Jan");
        map.put("2", "Feb");
        map.put("3", "Mar");
        map.put("4", "Apr");
        map.put("5", "May");
        map.put("6", "Jun");
        
        map.forEach( (k,v)-> System.out.println("key ="+ k + "value "+v));
        
        System.out.println("/*-------Iterator-------*/");
        // use Iterator
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("Key : " + entry.getKey() + " Value :" + entry.getValue());
        }
        System.out.println("/*-------- stream ------*/");
        map.entrySet()
           .stream()
           .filter(x-> "Mar".equals(x.getValue()))
           .forEach( x -> System.out.println("Key : " + x.getKey() + " Value : " + x.getValue()));
		
	}
	
	
	private static void example3(){
		
		System.out.println("/*-------- example3 ------*/");
        
		List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
		
		List<String> sortedList= list.stream().sorted((a,b)->a.compareTo(b)).collect(Collectors.toList());
		
		sortedList.forEach(System.out::println);
		
		List<String> sortedList2= list.stream().sorted().collect(Collectors.toList());
		
		sortedList2.forEach(System.out::println);
		

		 System.out.println("/*- Comparator.reverseOrder() -*/");
        List<String> sortedList3= list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		
		sortedList3.forEach(System.out::println);

	}
	
	private static void example4(){
		
		System.out.println("/*-------- example4 ------*/");
        
		 List<User> users = Arrays.asList(
	            new User("C", 30),
	            new User("D", 40),
	            new User("A", 10),
	            new User("B", 20),
	            new User("E", 50));
		 

		 System.out.println("/*- sort by age -*/");
		 List<User> sortedByAge = users.stream().sorted((a,b) -> a.getAge() - b.getAge()).collect(Collectors.toList());
		 

		 sortedByAge.forEach(System.out::println);
		 
		 List<User> sortedByAge2 = users.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
		 
		 sortedByAge2.forEach(System.out::println);
		 
		 System.out.println("/*- sort by age reverse -*/");
	        
		 List<User> reversesortedByAge = users.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).collect(Collectors.toList());
		 
		 reversesortedByAge.forEach(System.out::println);
		 
		 System.out.println("/*- sort by name -*/");
List<User> sortedByName = users.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
		 
sortedByName.forEach(System.out::println);
		 
		 
	}
	
	private static void example5(){
		

		System.out.println("/*-------- example5 ------*/");
		
		 Map<String, Integer> unsortMap = new HashMap<>();
	        unsortMap.put("z", 10);
	        unsortMap.put("b", 5);
	        unsortMap.put("a", 6);
	        unsortMap.put("c", 20);
	        unsortMap.put("d", 1);
	        unsortMap.put("e", 7);
	        unsortMap.put("y", 8);
	        unsortMap.put("n", 99);
	        unsortMap.put("g", 50);
	        unsortMap.put("m", 2);
	        unsortMap.put("f", 9);
	        
	        System.out.println("Sorted by key...");
	        // sort by keys, a,b,c..., and return a new LinkedHashMap
	        // toMap() will returns HashMap by default, we need LinkedHashMap to keep the order.
	        Map<String, Integer> result = unsortMap.entrySet().stream()
	                .sorted(Map.Entry.comparingByKey())
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	        
	        System.out.println(result);
	        
	        // Not Recommend, but it works.
	        //Alternative way to sort a Map by keys, and put it into the "result" map
	        
	        Map<String, Integer> result2 = new LinkedHashMap<>();
	        
	        unsortMap.entrySet().stream()
	                .sorted(Map.Entry.comparingByKey())
	                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
	        
	        System.out.println(result);
	        
	        System.out.println("Sorted by value...");
	        
	        Map<String, Integer> result3 = unsortMap.entrySet().stream()
	                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	        
	        
	        System.out.println(result3);
							
	        //Alternative way
	        Map<String, Integer> result4 = new LinkedHashMap<>();
	        unsortMap.entrySet().stream()
	                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	                .forEachOrdered(x -> result4.put(x.getKey(), x.getValue()));

	      
	        System.out.println(result4);
	}
}
