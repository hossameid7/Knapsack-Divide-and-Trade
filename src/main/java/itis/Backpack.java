package itis;

import java.util.ArrayList;
import java.util.List;

class Backpack {
    private final double maxWeight;
    private List<Item> bestItems = null;
    private double bestWeight = 0;
    private int bestPrice = 0;

    public Backpack(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void solveWithDivideAndTrade(List<Item> items) {
        if (items.size() <= 1) {
            solve(items);
            return;
        }

        int mid = items.size() / 2;
        List<Item> leftItems = new ArrayList<>(items.subList(0, mid));
        List<Item> rightItems = new ArrayList<>(items.subList(mid, items.size()));

        solve(leftItems);
        List<Item> bestLeftItems = new ArrayList<>(bestItems);
        int bestLeftPrice = bestPrice;
        double bestLeftWeight = bestWeight;

        solve(rightItems);
        List<Item> bestRightItems = new ArrayList<>(bestItems);
        int bestRightPrice = bestPrice;
        double bestRightWeight = bestWeight;

        if (bestLeftPrice > bestRightPrice) {
            bestItems = bestLeftItems;
            bestPrice = bestLeftPrice;
            bestWeight = bestLeftWeight;
        } else {
            bestItems = bestRightItems;
            bestPrice = bestRightPrice;
            bestWeight = bestRightWeight;
        }

        combineSolutions(leftItems, rightItems);
    }

    private void combineSolutions(List<Item> leftItems, List<Item> rightItems) {
        List<Item> allItems = new ArrayList<>(leftItems);
        allItems.addAll(rightItems);
        solve(allItems);
    }

    private void solve(List<Item> items) {
        bestItems = new ArrayList<>();
        solveRecursively(items, 0, new ArrayList<>(), 0, 0);
    }

    private void solveRecursively(List<Item> items, int index, List<Item> currentItems, double currentWeight, int currentPrice) {
        if (index == items.size()) {
            if (currentWeight <= maxWeight && currentPrice > bestPrice) {
                bestItems = new ArrayList<>(currentItems);
                bestPrice = currentPrice;
                bestWeight = currentWeight;
            }
            return;
        }

        Item currentItem = items.get(index);
        if (currentWeight + currentItem.weight <= maxWeight) {
            currentItems.add(currentItem);
            solveRecursively(items, index + 1, currentItems, currentWeight + currentItem.weight, currentPrice + currentItem.price);
            currentItems.remove(currentItems.size() - 1);
        }
        solveRecursively(items, index + 1, currentItems, currentWeight, currentPrice);
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
