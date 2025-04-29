package x17;

import java.util.NoSuchElementException;

public class MyMinHeap {
    private static final int DEFAULT_CAPACITY = 10;
    private int[] arr;
    private int size;   // 실제 개수

    public MyMinHeap() {
        this.arr = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void push(int x) {
        if (arr.length == size + 1) {   // 배열에 원소가 가득찬 경우
            resize(arr.length * 2);
        }
        arr[++size] = x;
        int childIdx = size;
        while (childIdx > 1 && arr[childIdx / 2] > arr[childIdx]) { // 부모가 자식보다 클 동안
            swap(childIdx / 2, childIdx);
            childIdx /= 2;
        }
    }

    public int top() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[1];
    }

    public void pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        arr[1] = arr[size--];
        if (arr.length > DEFAULT_CAPACITY && size < arr.length / 4) {   // 용적의 1/4도 채우지 못하고 있는 경우
            resize(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }
        int parentIdx = 1;
        while (size >= parentIdx * 2) { // 자식이 있는 동안
            int childIdx = parentIdx * 2;   // 디폴트를 왼쪽 자식으로 선정
            if (size > childIdx && arr[childIdx] > arr[childIdx + 1]) { // 오른쪽 자식이 존재하고, 값이 더 작다면 오른쪽 자식으로 변경
                childIdx++;
            }
            if (arr[parentIdx] < arr[childIdx]) {   // 최소 힙 조건을 만족하면 탈출
                break;
            }
            swap(parentIdx, childIdx);
            parentIdx = childIdx;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCapacity) {
        int[] newArr = new int[newCapacity];
        for (int i = 1; i <= size; i++) {
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }

    private void swap(int idx1, int idx2) {
        if (idx1 > size || idx2 > size) {
            throw new IllegalArgumentException("인덱스 설정 오류");
        }
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1; i < size; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[size]).append("]");
        return sb.toString();
    }
}
