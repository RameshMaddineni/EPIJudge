# Sort HashMap by Value and Key Using Java Streams

> **Note:** This is a supplementary example demonstrating HashMap sorting techniques using Java Streams.
> It is not part of the standard EPI (Elements of Programming Interviews) problem set, but serves
> as an educational resource for learning modern Java collection manipulation.

This solution demonstrates how to sort a `HashMap<String, Integer>` by its values or keys using Java Streams API.

## Problem Statement

Given a HashMap with the following data:
- Math: 98
- Data Structure: 85
- Database: 91
- Java: 95
- Operating System: 79
- Networking: 80

Sort the HashMap by:
1. Its values (the scores) in both ascending and descending order
2. Its keys (the subject names) in both ascending (alphabetical) and descending (reverse alphabetical) order

## Solution Overview

The solution leverages Java 8+ Streams API to provide a clean, functional approach to sorting. 

### Sorting by Value (Main method):

```java
public static Map<String, Integer> sortByValueAscending(Map<String, Integer> map) {
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
```

### Sorting by Key:

```java
public static Map<String, Integer> sortByKeyAscending(Map<String, Integer> map) {
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
```

Note: The methods accept `Map<String, Integer>` rather than `HashMap<String, Integer>` to provide flexibility - they can work with any Map implementation (HashMap, TreeMap, LinkedHashMap, etc.).

## Detailed Explanation

### Step 1: Convert to Stream
```java
map.entrySet().stream()
```
- `entrySet()` returns a Set of Map.Entry objects, each containing a key-value pair
- `stream()` converts this set into a Stream for functional processing

### Step 2: Sort the Stream

**For sorting by value:**
```java
.sorted(Map.Entry.comparingByValue())
```
- `Map.Entry.comparingByValue()` creates a Comparator that compares entries by their values
- For descending order, use `Map.Entry.comparingByValue(Comparator.reverseOrder())`
- This sorts in ascending order by default (79, 80, 85, 91, 95, 98)

**For sorting by key:**
```java
.sorted(Map.Entry.comparingByKey())
```
- `Map.Entry.comparingByKey()` creates a Comparator that compares entries by their keys
- For String keys, this sorts alphabetically (A-Z) by default
- For descending order, use `Map.Entry.comparingByKey(Comparator.reverseOrder())`

### Step 3: Collect Back to Map
```java
.collect(Collectors.toMap(
    Map.Entry::getKey,      // Key mapper: extracts the key
    Map.Entry::getValue,    // Value mapper: extracts the value
    (e1, e2) -> e1,        // Merge function: handles duplicates
    LinkedHashMap::new      // Map supplier: creates LinkedHashMap
))
```

**Why these parameters?**
- `Map.Entry::getKey` - Method reference to extract the subject name (key)
- `Map.Entry::getValue` - Method reference to extract the score (value)
- `(e1, e2) -> e1` - Merge function in case of duplicate keys (unlikely but required)
- `LinkedHashMap::new` - **Critical**: Uses LinkedHashMap to preserve insertion order

### Why LinkedHashMap?

The standard `HashMap` does NOT guarantee any specific order. When we sort and collect the entries, we need a Map implementation that maintains insertion order:

- **HashMap**: No order guarantee (entries may appear randomly)
- **LinkedHashMap**: Maintains insertion order (perfect for sorted results)
- **TreeMap**: Maintains natural key ordering (useful for sorting by key, but not flexible for value sorting)

**Note:** While TreeMap could be used for key-based sorting (it maintains keys in sorted order automatically), we use LinkedHashMap for consistency across all sorting methods and to demonstrate the explicit sorting approach with Streams.

## Running the Code

### Compile:
```bash
cd epi_judge_java
javac -d java_build epi/SortHashMapByValue.java
```

### Run:
```bash
cd epi_judge_java
java -cp java_build epi.SortHashMapByValue
```

## Output Example

```
===== Original HashMap =====
(Note: HashMap doesn't guarantee any order)
Java : 95
Networking : 80
Database : 91
Operating System : 79
Math : 98
Data Structure : 85

===== Sorted by Value (Ascending) =====
Operating System : 79
Networking : 80
Data Structure : 85
Database : 91
Java : 95
Math : 98

===== Sorted by Value (Descending) =====
Math : 98
Java : 95
Database : 91
Data Structure : 85
Networking : 80
Operating System : 79

===== Sorted by Key (Ascending - Alphabetical) =====
Data Structure : 85
Database : 91
Java : 95
Math : 98
Networking : 80
Operating System : 79

===== Sorted by Key (Descending - Reverse Alphabetical) =====
Operating System : 79
Networking : 80
Math : 98
Java : 95
Database : 91
Data Structure : 85
```

## Available Methods

The solution provides six sorting methods:

### Sorting by Value:
1. `sortByValueAscending(Map<String, Integer> map)` - Returns Map sorted by values (low to high)
2. `sortByValueDescending(Map<String, Integer> map)` - Returns Map sorted by values (high to low)
3. `sortByValueAsList(Map<String, Integer> map)` - Returns immutable List sorted by values

### Sorting by Key:
4. `sortByKeyAscending(Map<String, Integer> map)` - Returns Map sorted by keys (A-Z)
5. `sortByKeyDescending(Map<String, Integer> map)` - Returns Map sorted by keys (Z-A)
6. `sortByKeyAsList(Map<String, Integer> map)` - Returns immutable List sorted by keys

## Key Concepts

1. **Streams API**: Functional programming approach for data processing
2. **Method References**: Concise syntax (`Map.Entry::getKey` instead of `e -> e.getKey()`)
3. **Collectors**: Terminal operations that transform streams into collections
4. **LinkedHashMap**: Maintains insertion order, crucial for preserving sorted order

## Advantages of Using Streams

- **Readable**: Declarative code that clearly expresses intent
- **Concise**: Less boilerplate compared to traditional loops
- **Functional**: Encourages immutability and functional programming style
- **Maintainable**: Easier to understand and modify
- **Parallel-ready**: Can be easily parallelized if needed

## Alternative Approach (Without Streams)

For comparison, here's the traditional approach:

```java
public static Map<String, Integer> sortByValueTraditional(Map<String, Integer> map) {
    List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
    
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
            return e1.getValue().compareTo(e2.getValue());
        }
    });
    
    LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : list) {
        sortedMap.put(entry.getKey(), entry.getValue());
    }
    
    return sortedMap;
}
```

As you can see, the Stream approach is much more concise and readable!

## Complexity Analysis

- **Time Complexity**: O(n log n) - Dominated by the sorting operation
- **Space Complexity**: O(n) - We create a new LinkedHashMap with all entries

## Additional Features

The solution also includes:
1. **Descending order sorting**: Using `Comparator.reverseOrder()`
2. **List conversion**: Alternative approach that returns a sorted list of entries
3. **Comprehensive examples**: Demonstrates all three approaches

## Summary

This solution demonstrates modern Java best practices for sorting a HashMap by value:
- Uses Java 8+ Streams API for clean, functional code
- Employs method references for conciseness
- Uses LinkedHashMap to preserve sorted order
- Provides both ascending and descending sorting options
- Includes detailed explanations and examples
