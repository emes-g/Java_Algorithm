package x03;

public class Main {
    public static void main(String[] args) {
        System.out.println(func2(new int[]{1, 52, 48}, 3));
        System.out.println(func2(new int[]{50, 42}, 2));
        System.out.println(func2(new int[]{4, 13, 63, 87}, 4));
    }

    public static int func2(int[] arr, int N) {
        int[] freq = new int[101];
        for (int num : arr) {
            freq[num]++;
        }
        for (int num : arr) {
            if (num == 50) {
                continue;
            }
            if (freq[num] + freq[100 - num] == 2) {
                return 1;
            }
        }
        return 0;
    }
}
