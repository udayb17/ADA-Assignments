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
