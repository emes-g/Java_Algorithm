package x13;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 6, 13, 16, 19, 22, 23, 30, 32};
        int[] arr2 = {2, 4, 6, 10, 10, 16, 16, 16, 30, 32};
        int target = 10;

//        System.out.println(binarySearch(arr1, 19));
        System.out.println(lowerBound(arr2, target));
        System.out.println(upperBound(arr2, target));
        System.out.println(getCount(arr2, target));
    }

    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    // 1. end = arr.length
    // 2. 후보가 1개가 될 때까지 (start < end)
    // 3. arr[mid]가 target의 하한 후보일 때,
    // 즉 arr[mid]가 target 이상일 때, end = mid로 당김
    public static int lowerBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length;
        while (start < end) {   // 후보가 1개가 될 때까지
            int mid = (start + end) / 2;
            if (arr[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;   // 아무거나 반환
    }

    public static int upperBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static int getCount(int[] arr, int target) {
        return upperBound(arr, target) - lowerBound(arr, target);
    }
}
