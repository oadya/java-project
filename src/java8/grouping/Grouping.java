package java8.grouping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grouping {

	public static void main(String[] args) {
		example1();
		
		example2();

	}

	
	private static void example1(){
		
		System.out.println("--- example1 ----");
		//3 apple, 2 banana, others 1
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        
        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        System.out.println(result);
        
        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);
        
	}
	

	private static void example2(){

		System.out.println("--- example2 ----");
	     List<Item> items = Arrays.asList(
	                new Item("apple", 10, new BigDecimal("9.99")),
	                new Item("banana", 20, new BigDecimal("19.99")),
	                new Item("orang", 10, new BigDecimal("29.99")),
	                new Item("watermelon", 10, new BigDecimal("29.99")),
	                new Item("papaya", 20, new BigDecimal("9.99")),
	                new Item("apple", 10, new BigDecimal("9.99")),
	                new Item("banana", 10, new BigDecimal("19.99")),
	                new Item("apple", 20, new BigDecimal("9.99"))
	        );
	     
	     System.out.println("--Group by + count");
		     
	     Map<String, Long> counting = items.stream().collect(
	                Collectors.groupingBy(Item::getName, Collectors.counting()));

	        System.out.println(counting);

	        System.out.println("--Group by + quantity");
	        
	        Map<String, Integer> sum = items.stream().collect(
	                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

	        System.out.println(sum);
	        
	        System.out.println("--Group by price");
	        
	        Map<BigDecimal, List<Item>> groupByPriceMap = 
				items.stream().collect(Collectors.groupingBy(Item::getPrice));
	        
	        System.out.println(groupByPriceMap);
	        
	        System.out.println("--Group by price collect to set og name");
	        
	        Map<BigDecimal, Set<String>> result =
	                items.stream().collect(
	                        Collectors.groupingBy(Item::getPrice,
	                                Collectors.mapping(Item::getName, Collectors.toSet())
	                        )
	                );

	        System.out.println(result);
	}
}
