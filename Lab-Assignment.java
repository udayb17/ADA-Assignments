//Fractional Knapsack
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





// JOB Sequence
class Job {
    int id, deadline, profit;

    Job(int i, int d, int p) {
        id = i;
        deadline = d;
        profit = p;
    }
}

class JobSequencing {
    static void jobSequence(Job[] jobs) {
        int n = jobs.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (jobs[j].profit > jobs[i].profit) {
                    Job temp = jobs[i];
                    jobs[i] = jobs[j];
                    jobs[j] = temp;
                }
            }
        }

        int maxDeadline = 0;
        for (Job j : jobs) {
            if (j.deadline > maxDeadline) {
                maxDeadline = j.deadline;
            }
        }

        int[] slot = new int[maxDeadline];
        for (int i = 0; i < maxDeadline; i++) {
            slot[i] = -1;
        }

        int totalProfit = 0;

        for (Job j : jobs) {
            for (int k = j.deadline - 1; k >= 0; k--) {
                if (slot[k] == -1) {
                    slot[k] = j.id;
                    totalProfit += j.profit;
                    break;
                }
            }
        }

        System.out.print("Selected Jobs: ");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i] != -1) {
                System.out.print("J" + slot[i] + " ");
            }
        }

        System.out.println("Total Profit = " + totalProfit);
    }

    public static void main(String[] args) {

        Job[] jobs = {
            new Job(1, 2, 100),
            new Job(2, 1, 50),
            new Job(3, 2, 10),
            new Job(4, 1, 20),
            new Job(5, 3, 30)
        };

        jobSequence(jobs);
    }
}
