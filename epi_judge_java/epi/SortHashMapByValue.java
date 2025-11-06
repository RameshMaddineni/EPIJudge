package epi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates how to sort a HashMap by its values using Java Streams.
 * 
 * This program shows multiple approaches to sort a HashMap<String, Integer> by values,
 * with a focus on using Java 8+ Streams API which provides a functional and declarative
 * approach to data processing.
 */
public class SortHashMapByValue {
    
    /**
     * Sorts a Map by its values in ascending order using Java Streams.
     * 
     * Detailed Explanation:
     * ---------------------
     * 1. map.entrySet().stream() - Converts the Map's entry set to a Stream.
     *    Each entry contains a key-value pair.
     * 
     * 2. sorted(Map.Entry.comparingByValue()) - Sorts the stream entries by their values.
     *    - Map.Entry.comparingByValue() creates a Comparator that compares entries based on values
     *    - This sorts in ascending order by default
     * 
     * 3. collect(Collectors.toMap(...)) - Collects the sorted entries back into a Map.
     *    - Map.Entry::getKey - Method reference to extract the key from each entry
     *    - Map.Entry::getValue - Method reference to extract the value from each entry
     *    - (e1, e2) -> e1 - Merge function (handles duplicate keys, though unlikely here)
     *    - LinkedHashMap::new - Uses LinkedHashMap to preserve insertion order (sorted order)
     * 
     * Why LinkedHashMap?
     * ------------------
     * LinkedHashMap maintains the insertion order of elements. Since we're inserting
     * sorted entries, the resulting map will maintain the sorted order.
     * Regular HashMap doesn't guarantee any order.
     * 
     * @param map The Map to be sorted (accepts any Map implementation)
     * @return A LinkedHashMap sorted by values in ascending order
     * @throws NullPointerException if map is null
     */
    public static Map<String, Integer> sortByValueAscending(Map<String, Integer> map) {
        if (map == null) {
            throw new NullPointerException("Input map cannot be null");
        }
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.comparingByValue())
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1,
                      LinkedHashMap::new
                  ));
    }
    
    /**
     * Sorts a Map by its values in descending order using Java Streams.
     * 
     * Additional Explanation:
     * -----------------------
     * This method is similar to sortByValueAscending, but uses:
     * - Map.Entry.comparingByValue(Comparator.reverseOrder())
     *   This creates a comparator that sorts in reverse (descending) order.
     * 
     * Alternatively, you could use:
     * - sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
     * 
     * @param map The Map to be sorted (accepts any Map implementation)
     * @return A LinkedHashMap sorted by values in descending order
     * @throws NullPointerException if map is null
     */
    public static Map<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        if (map == null) {
            throw new NullPointerException("Input map cannot be null");
        }
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1,
                      LinkedHashMap::new
                  ));
    }
    
    /**
     * Sorts a Map by its values and returns an immutable list of entries.
     * 
     * This alternative approach is useful when you just need to iterate
     * over sorted entries without creating a new Map.
     * 
     * @param map The Map to be sorted (accepts any Map implementation)
     * @return An immutable List of Map.Entry sorted by values in ascending order
     * @throws NullPointerException if map is null
     */
    public static List<Map.Entry<String, Integer>> sortByValueAsList(Map<String, Integer> map) {
        if (map == null) {
            throw new NullPointerException("Input map cannot be null");
        }
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.comparingByValue())
                  .collect(Collectors.toUnmodifiableList());
    }
    
    /**
     * Sorts a Map by its keys in ascending order using Java Streams.
     * 
     * Detailed Explanation:
     * ---------------------
     * This method sorts the Map entries by their keys (String) in alphabetical order.
     * 
     * 1. map.entrySet().stream() - Converts the Map's entry set to a Stream.
     * 
     * 2. sorted(Map.Entry.comparingByKey()) - Sorts the stream entries by their keys.
     *    - Map.Entry.comparingByKey() creates a Comparator that compares entries based on keys
     *    - For String keys, this sorts alphabetically (A-Z) by default
     * 
     * 3. collect(Collectors.toMap(...)) - Collects the sorted entries back into a Map.
     *    - LinkedHashMap::new - Uses LinkedHashMap to preserve the sorted order
     * 
     * Note: TreeMap could also be used here since it maintains keys in sorted order,
     * but LinkedHashMap is used for consistency with the value-sorting methods.
     * 
     * @param map The Map to be sorted (accepts any Map implementation)
     * @return A LinkedHashMap sorted by keys in ascending (alphabetical) order
     * @throws NullPointerException if map is null
     */
    public static Map<String, Integer> sortByKeyAscending(Map<String, Integer> map) {
        if (map == null) {
            throw new NullPointerException("Input map cannot be null");
        }
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.comparingByKey())
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1,
                      LinkedHashMap::new
                  ));
    }
    
    /**
     * Sorts a Map by its keys in descending order using Java Streams.
     * 
     * Additional Explanation:
     * -----------------------
     * This method is similar to sortByKeyAscending, but uses:
     * - Map.Entry.comparingByKey(Comparator.reverseOrder())
     *   This creates a comparator that sorts keys in reverse alphabetical order (Z-A).
     * 
     * Alternatively, you could use:
     * - sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
     * 
     * @param map The Map to be sorted (accepts any Map implementation)
     * @return A LinkedHashMap sorted by keys in descending (reverse alphabetical) order
     * @throws NullPointerException if map is null
     */
    public static Map<String, Integer> sortByKeyDescending(Map<String, Integer> map) {
        if (map == null) {
            throw new NullPointerException("Input map cannot be null");
        }
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1,
                      LinkedHashMap::new
                  ));
    }
    
    /**
     * Sorts a Map by its keys and returns an immutable list of entries.
     * 
     * This alternative approach is useful when you just need to iterate
     * over sorted entries by key without creating a new Map.
     * 
     * @param map The Map to be sorted (accepts any Map implementation)
     * @return An immutable List of Map.Entry sorted by keys in ascending order
     * @throws NullPointerException if map is null
     */
    public static List<Map.Entry<String, Integer>> sortByKeyAsList(Map<String, Integer> map) {
        if (map == null) {
            throw new NullPointerException("Input map cannot be null");
        }
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.comparingByKey())
                  .collect(Collectors.toUnmodifiableList());
    }
    
    /**
     * Main method demonstrating the HashMap sorting functionality.
     */
    public static void main(String[] args) {
        // Create and populate the Map as specified in the problem
        // Using Map interface with HashMap implementation (best practice)
        Map<String, Integer> hm = new HashMap<>();
        hm.put("Math", 98);
        hm.put("Data Structure", 85);
        hm.put("Database", 91);
        hm.put("Java", 95);
        hm.put("Operating System", 79);
        hm.put("Networking", 80);
        
        System.out.println("===== Original HashMap =====");
        System.out.println("(Note: HashMap doesn't guarantee any order)");
        hm.forEach((key, value) -> System.out.println(key + " : " + value));
        
        System.out.println("\n===== Sorted by Value (Ascending) =====");
        Map<String, Integer> sortedAsc = sortByValueAscending(hm);
        sortedAsc.forEach((key, value) -> System.out.println(key + " : " + value));
        
        System.out.println("\n===== Sorted by Value (Descending) =====");
        Map<String, Integer> sortedDesc = sortByValueDescending(hm);
        sortedDesc.forEach((key, value) -> System.out.println(key + " : " + value));
        
        System.out.println("\n===== Sorted by Key (Ascending - Alphabetical) =====");
        Map<String, Integer> sortedByKeyAsc = sortByKeyAscending(hm);
        sortedByKeyAsc.forEach((key, value) -> System.out.println(key + " : " + value));
        
        System.out.println("\n===== Sorted by Key (Descending - Reverse Alphabetical) =====");
        Map<String, Integer> sortedByKeyDesc = sortByKeyDescending(hm);
        sortedByKeyDesc.forEach((key, value) -> System.out.println(key + " : " + value));
        
        System.out.println("\n===== Sorted as List of Entries (by Value) =====");
        List<Map.Entry<String, Integer>> sortedList = sortByValueAsList(hm);
        sortedList.forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
        
        System.out.println("\n===== Sorted as List of Entries (by Key) =====");
        List<Map.Entry<String, Integer>> sortedByKeyList = sortByKeyAsList(hm);
        sortedByKeyList.forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
        
        System.out.println("\n===== DETAILED SOLUTION EXPLANATION =====");
        System.out.println("\nKey Concepts Used:");
        System.out.println("1. Streams API - Functional approach to data processing");
        System.out.println("2. Method References - Concise syntax for lambda expressions");
        System.out.println("3. Collectors - Terminal operations to collect stream results");
        System.out.println("4. LinkedHashMap - Maintains insertion order");
        System.out.println("\nWhy Use Streams?");
        System.out.println("- More readable and declarative code");
        System.out.println("- Less boilerplate compared to traditional loops");
        System.out.println("- Functional programming style");
        System.out.println("- Supports parallel processing (if needed)");
        System.out.println("- Easier to maintain and test");
        
        System.out.println("\nSorting Options:");
        System.out.println("- By Value: Use Map.Entry.comparingByValue()");
        System.out.println("- By Key: Use Map.Entry.comparingByKey()");
        System.out.println("- For descending: Add Comparator.reverseOrder()");
        
        System.out.println("\nStep-by-Step Breakdown:");
        System.out.println("1. map.entrySet().stream() -> Create a stream from map entries");
        System.out.println("2. sorted(Map.Entry.comparingByValue/Key()) -> Sort by values or keys");
        System.out.println("3. collect(Collectors.toMap(...)) -> Collect back to a Map");
        System.out.println("4. LinkedHashMap::new -> Preserve sorted order");
        
        System.out.println("\nTime Complexity: O(n log n) - due to sorting");
        System.out.println("Space Complexity: O(n) - for the new sorted map");
    }
}
