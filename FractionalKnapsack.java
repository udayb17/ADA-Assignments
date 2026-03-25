class Item {
    int value, weight;

    Item(int v, int w) {
        value = v;
        weight = w;
    }
}

class FractionalKnapsack {

    static double getMaxValue(Item[] items, int capacity) {

        // Sort items by value/weight ratio (descending)
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = i + 1; j < items.length; j++) {

                double r1 = (double) items[i].value / items[i].weight;
                double r2 = (double) items[j].value / items[j].weight;

                if (r2 > r1) {
                    // swap
                    Item temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }

        double total = 0;

        for (Item i : items) {
            if (capacity >= i.weight) {
                total += i.value;
                capacity -= i.weight;
            } else {
                total += (double) i.value * capacity / i.weight;
                break;
            }
        }

        return total;
    }

    public static void main(String[] args) {

        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30),
                new Item(100,10)
        };

        int capacity = 70;

        double maxValue = getMaxValue(items, capacity);

        System.out.println("Maximum value = " + maxValue);
    }
}
