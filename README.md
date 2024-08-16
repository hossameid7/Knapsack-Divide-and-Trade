
# Report on the Implementation of the "Divide and Trade" Algorithm in the Knapsack Problem

**Author:** Eid Hossam Mohamed, 11-108  
**Project Repository on GitHub:** [hossameid7/--HoSsaM-Technology](https://github.com/hossameid7/--HoSsaM-Technology)

## Introduction

The knapsack problem is a classic optimization problem where the goal is to maximize the value of a set of items that can be packed into a knapsack without exceeding a given weight limit. It is typically solved using dynamic programming, greedy algorithms, or branch and bound methods, but an interesting approach can also be implemented through "Divide and Conquer".

The "Divide and Conquer" method for the knapsack problem involves splitting the list of items into two equal parts, solving the knapsack problem for each part separately, and then combining the solutions. This may also require considering cases where items from the first part are combined with items from the second part to achieve the optimal result.

## Example Explanation

### Items:
We have a knapsack with a total weight of 9 kg and the following items:

### Groups:
- **Group 1:** Book, Pencil, 120 GB Flash Drive, Mobile Charger
- **Group 2:** Research Tools, USB Hard Drive (first), USB Hard Drive (second), Laptop

Using the "Divide and Conquer" method, we divided the items into two groups and calculated the maximum value that can be carried in the knapsack for each group, as well as considered the combination of these groups:

### Solution:
1. **Best Solution for Group 1 (Book, Pencil, 120 GB Flash Drive, Mobile Charger):**
   - Maximum Value: 7720 RUB
   - Combination of Items: Book, Pencil, 120 GB Flash Drive, Mobile Charger
   - Total Weight: 3.2 kg

2. **Best Solution for Group 2 (Research Tools, USB Hard Drive (first), USB Hard Drive (second), Laptop):**
   - Maximum Value: 92480 RUB
   - Combination of Items: Research Tools, USB Hard Drive (first), USB Hard Drive (second), Laptop
   - Total Weight: 9 kg

3. **Combined Solution for All Items:**
   - Maximum Value: 98906 RUB
   - Combination of Items: Mobile Charger, Research Tools, Laptop
   - Total Weight: 9 kg

### Result:
```python
(7720, ('Book', 'Pencil', '120 GB Flash Drive', 'Mobile Charger')),
(92480, ('Research Tools', 'USB Hard Drive (first)', 'USB Hard Drive (second)', 'Laptop')),
(98906, ('Mobile Charger', 'Research Tools', 'Laptop'))
```
As shown, the best strategy for packing the knapsack to maximize value while staying within the 9 kg weight limit is to take the Mobile Charger, Research Tools, and Laptop, achieving a maximum value of 98906 RUB.

## Algorithm and Code Description

### Idea of the "Divide and Trade" Algorithm
"Divide and Trade" is an approach to solving the knapsack problem by splitting all items into two parts, solving the knapsack problem for each part separately, and then combining the solutions. This reduces the spatial and temporal complexity of the algorithm by recursively breaking down the original problem into smaller sub-problems.

### Implementation

**Class `Item`:**
```java
class Item {
    String name;
    double weight;
    int price;

    public Item(String name, double weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }
}
```

**Class `Backpack`:**
```java
class Backpack {
    private final double maxWeight;
    private List<Item> bestItems = null;
    private double bestWeight = 0;
    private int bestPrice = 0;

    public Backpack(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void solveWithDivideAndTrade(List<Item> items) {
        // Implementation here
    }

    private void combineSolutions(List<Item> leftItems, List<Item> rightItems) {
        // Implementation here
    }

    private void solve(List<Item> items) {
        // Implementation here
    }

    private void solveRecursively(List<Item> items, int index, List<Item> currentItems, double currentWeight, int currentPrice) {
        // Implementation here
    }

    public List<Item> getBestItems() {
        return bestItems;
    }

    public int getBestPrice() {
        return bestPrice;
    }

    public double getBestWeight() {
        return bestWeight;
    }
}
```

### GUI Implementation
The GUI is implemented using Java Swing and includes fields for inputting item data and maximum weight, buttons for triggering calculations, and tables for displaying the entered items and results.

**Class `BackpackSolverGUI`:**
```java
public class BackpackSolverGUI extends JFrame {
    // GUI components and methods for user interaction with the program.
}
```

### Key Features:
- Input Panel for entering item data.
- Control Panel for setting the maximum weight and triggering the solution.
- List Panel for displaying entered items.
- Result Panel for showing the knapsack solution, including selected items, their weight, and value.

### Program Workflow:
1. Users input data into fields and click "Add Item". The data is added to the table and internal item list.
2. Users can select an item in the table and click "Delete" to remove it from the list and table.
3. When "Solve" is clicked, the program reads the maximum weight and uses the `solveWithDivideAndTrade` method of the `Backpack` class to find the optimal set of items.

### Visualization of Results:
```java
JOptionPane.showMessageDialog(this, "Best Price: " + backpack.getBestPrice() + "
Total Weight: " + backpack.getBestWeight());
```

The user sees an updated table with information about the items selected as part of the optimal knapsack solution. A pop-up window with the total value and weight gives a quick overview of the results.
