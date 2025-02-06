package x03;

public interface MyList<E> {
    boolean add(E e);   // 요소를 맨 뒤에 추가
    void add(int idx, E e);   // 요소를 특정 위치에 추가

    boolean remove(Object o);   // 요소를 삭제
    E remove(int idx);  // 특정 위치에 있는 요소를 삭제

    E get(int idx); // 요소 가져오기
    E set(int idx, E e);  // 특정 위치에 있는 요소를 새 요소로 대체

    boolean contains(Object o); // 특정 요소 포함 여부
    int indexOf(Object o); // 특정 요소의 위치 (앞에서부터)

    int size(); // 요소의 개수
    boolean isEmpty();  // 빈 리스트 여부

    void clear();   // 비우기
}
