package com.moon.java.collectionFramework.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> {
    private final Comparator<? super E> comparator;
    private static final int defaultCapacity = 10; // 최소(기본)용적 크기

    private int size; // 요소개수

    private Object[] array; // 요소를 담을 배열
    /*
     * comparator : 객체를 정렬하고자 할 때, 혹은 임의의 순서로 정렬하고 싶을 때 Cpmparator를 파라마터로 받아 설정할 수 있도록 한 변수다.
     * defaultCapacity : 배열의 기본 및 최소 용적이다. 요소를 담을 배열의 크기를 의미 한다.
     *                   배열을 동적으로 관리할 때 최소 크기가 10 미만으로 내려가지 않기 위한 변수다. 그리고 요소의 개수랑은 다른 의미이다.
     * size : 배열에 담긴 요소( 원소)의 개수 변수
     * array : 요소를 담을 배열이다.
     *
     *
     * 그리고 생성자는 크게 4개가지로 나누었다.
     *
     * 먼저 데이터(요소)의 개수를 예상할 수 있어 배열의 크기(용적)를 최적으로 하고 싶을 때 초기에 생성할 배열의 크기를 설정 해줄 수 있도록
     * 만든 방법과 사용자가 정렬 방법을 따로 넘겨주고자 할 때 쓸 수 있도록 Comparator를 받는 방법을 조합하여 4가지로 나누었다.
     *
     * 먼저 데이터(요소)의 개수를 예상할 수 있어 배열의 크기(용적)를 최적을 ㅗ학 ㅗ싶을때 초기에 생성할 배열의 크기를 설정해줄 수 있도록 만든 방법과
     * 사용자가 정렬방법을 따로 넘겨주고자 할 때 쓸 수 있도록 Comparator를 받는 방법을 조합하여 4가지로 나누었다.
     *
     * 그리고 힙 설명에서 힙을 배열로 구현하게 될 때의 성질에 관해 배웠다.
     *
     * [성질]
     * 1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 x2
     * 2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 x 2 + 1
     * 3. 부모 노드 인덱스 = 자식 노드 인덱스 /2
     * 위 세 가지 성질을 이용하기 위해 각가 부모 또는 자식의 인덱스를 반환해주는 메서드를 생성했다.
     *
     * */

    //생성자 Type 1 (초기 공간 할당 x)
    public Heap() {
        this(null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[defaultCapacity];
        this.size = 0;
        this.comparator = comparator;
    }

    //생성자 Type 2 (초기 공간할당 O)
    public Heap(int capacity) {
        this(capacity, null);
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[defaultCapacity];
        this.size = 0;
        this.comparator = comparator;
    }

    // 받은 인덱스의 부모 노드 인덱스를 반환
    private int getParent(int index) {
        return index / 2;
    }

    // 받은 인덱스의 왼쪽 자식 노드 인덱스를 변환
    private int getLeftChild(int index) {
        return index * 2;
    }

    //받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
    private int getRightChild(int index) {
        return index * 2 + 1;
    }

    /*
     * @param resize  메서드 구현
     *  모든 자료구조는 기본적으로 동적으로 만들 수 있어야 한다.
     *
     * 만약 배열에 요소들이 모두 차면 배열의 크기를 늘려야하고, 만약 요소가 배열 용적에 비해 현저히 적으면 낭비되는 메모리가 크므로 적절히 줄여줄 수 있어야 한다.
     * 그럴 때 배열의 크기를 재조정하기 위해 쓰는 메서드다.
     * */
    public void resize(int newCapacity) {

        //새로 만들 배열
        Object[] newArray = new Object[newCapacity];

        //새 배열에 기존에 있던 배열의 요소들을 모두 복사한다.
        for (int i = 1; i < -size; i++) {
            newArray[i] = array[i];
        }

        /*
         * 현재 배열은 GC 처리를 위해 null로 처리한 뒤,
         * 새 배열을 연결해준다.
         * */
        this.array = null;
        this.array = newArray;
        /*
         * 위 과정대로 새 배열을 생성하고 기존에 있던 배열의 요소들을 복사해준 뒤 , 새 배열을 가리키도록 새로 연결해주면 된다.
         * */
    }

    /*
    * Heap의 삽입은 크게 두 가지로 나뉜다.
    * 1. 사용자가 Compartor을 사용하여 정렬 방법을 Heap 생성단계에서 넘겨 받은 경우 (comparator가 null이 아닌 경우 )
    * 2. 클래스 내에 정렬 방식을 Comprable로 구현했거나 기본 정렬 방식을 따르는 경우 (comparator가 null인 경우)
    *
    * 이 두 가지로 나누어 봐야한다.
    *
    * 기본적으로 Heap에 원소가 추가되는 과정은 다음과 같다.
    * */
    public void add(E value){

        //배열 용적이 꽉 차 있을 경우 용적을 두 배로 늘려준다.
        if (size +1 == array .length){
            resize(array.length*2);
        }

    }
    /*
    * 상향선별
    *
    * @param index 추가할 노드의 인덱스
    * @param target 재배치 할 노드
    * */
    private void siftUp(int index, E target){
        // comparator가 존재할 경우 comparator를 파라미터로 넘겨준다.
        if(comparator != null) {
            siftUpComparator(index, target,comparator);
        }else {
            siftUpComparable(index,target);
        }
    }

    //Comparator를 이용한 sift Up
    @SuppressWarnings("unchekced")
    private void siftUpComparator(int index,E target, Comparator<? super E> comparator){
        //root 노드보다  클 떄까지 탐색한다.
        while (index > 1 ){
            int parent = getParent(index); //삽입 노드의 부모 노드 인덱스 구하기
            Object parentVal = array[parent]; // 부모 노드의 값

            //타겟 노드 값이 부모노드 보다 크면 반목문 종료
            if (comparator.compare(target, (E)parentVal) >= 0){
                break;
            }
            /*
            * 부모 노드가 타겟 노드보다 크므로
            * 현재 삽입 될 위치에 부모노드 값으로 교체해주고
            * 타겟 노드의 위치를 부모노드의 위치로 변경한다.
            * */
            array[index] = parentVal;
            index = parent;
        }
        //최종적으로 삽입될 위치에 타겟 노드 값을 저장해준다.
        array[index] = target;
    }

    //삽입 할 객체의 Comparable 을 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparable (int index , E target){
        //타겟 노드가 비교 될 수 있도록 변수를 만든다.
        Comparable <? super E > comparable = (Comparable<? super E>) target;

        while (index > 1){
            int parent = getParent(index);
            Object parentVal = array[parent];

            if (comparable.compareTo((E)parentVal) >= 0){
                break;
            }
            array[index]= parentVal;
            index = parent;
        }
        array[index] = comparable;
    }

    /*
    * remove
    * 삭제는 어떻게 해야할까?
    * add 경우 맨 마지막 노드에 추가하고 비교하면서 자리를 찾아갔다.
    * 이를 거꾸로 하면 삭제연산의 경우 root에 있는 노드를 삭제하고,
    * 마지막에 위치해있던 노드를 root Node로 가져와 add와는 반대로 자식 노드가 재배치하려는 노드보다 크거나
    * 자식노드가 없을 때 까지 자신의 위치를 찾아가면 된다.
    *
    * 마지막 노드를 root 노드로 가져온 뒤, 자식 노드와 비교하면서 자리를 찾아가면 된다. 즉 비교 대상이 되는 '분홍생 노드'는 타겟이 되는것이다.
    * 이 타겟을 다른 노드와 비교하면서 타겟 노드가 배치 될 자리를 찾아가야 한다.
    * 중요한 점은 왼쪽 자식 노드와 오른쪽 자식 노드 중 "값을 가진 노드"랑 재배치 할 노드와 비교해야한다.
    *
    * 그래야 최소 힙을 만족시킬 수 있다.  만약 반대로 된다면 첫 비교 교환 단계에서 35가 root 노드에 배치되어 버리는데 , 이는 왼쪽 자식 노드인 10보다 큰 값을 갖게 되면서 최소힙을 만족하지 못한다.
    * 이렇게 아래로 내려가면서 재배치 하는 과정을 sift-down( 하향 선별) 이라고도 한다.
    *
    * 그리고 삽입 과정과 마찬가지로 Comparator를 쓰느냐, Comparale을 쓰느냐를 나누어서 만들어 본다.
    * */

    @SuppressWarnings("unchecked")
    public E remove(){
    if (array[1] == null){ // 만약 root가 비어있을 경우 예외를 던지도록 함.
        throw new NoSuchElementException();
    }

    E result = (E) array[1]; //삭제된 요소를 반환하기 위한 임시 변수
    E target = (E) array[size]; // 타겟이 될 요소
    array[size ]= null; // 타겟 노드를 비운다.

        //삭제할 노드의 인덱스와 이후 재배치 할 타겟 노드를 넘겨준다.
        siftDown(1, target); //  루트 노드가 삭제되므로 1을 넘겨준다.

        return result;
    }
    
    /*
    * @param index 삭제할 노드의 인덱스
    * @param target 재배치 할 노드
    * */

    private void siftDown (int index, E target){
        //comparator 가 존재할 경우 comparator를 인자로 넘겨준다.
        if (comparator != null){
            siftDownComparator(index,target,comparator);
        }
        else{
            siftDownComparable(index, target);
        }
    }

    // Comparator를 이용한 sift -down
    @SuppressWarnings("unchecked")
    private void siftDownComparator(int index, E target, Comparator<? super E> comparator){

        array[index] = null; // 삭제 할 인덱스의 노드를 삭제
        size--;
        
        int parent = index; // 삭제노드부터 시작할 부모를 가리키는 변수
        int child; // 교환 될 자식을 가리키는 변수
        
        // 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 때 까지 반복
        while((child = getLeftChild(parent))<= size){
            int right = getRightChild(parent);//오른쪽 자식 인덱스
            Object childVal = array[child]; // 왼쪽 자식의 값 (교환될 값)
            /*
            * 오른쪽 자식 인덱스가 size가 넘지 안흥면서
            * 왼쪽 자식이 오른쪽 자식보다 큰 경우
            * 재배치 할 노드는 작은 자식과 비교해야하므로
            * child와 childVal을 오른쪽 자식으로 바꿔준다.
            * */
            if (right <= size && comparator.compare((E) childVal, (E)array[right])>0){
                child = right;
                childVal = array[child];
            }
            // 재배치 할 노드가 자식 노드보다 작을 경우 반목문을 종료한다.
            if(comparator.compare(target, (E) childVal )<=0){
                break;
            }
            /*
            * 현재 부모 인덱스에 자식 노드 값을 대체해주고
            * 부모 인덱스를 자식 인덱스로 교체.
            * */
            array[parent] = childVal;
            parent = child;
        }
        //최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어준다.
        array[parent] = target;
        /*
        * 용적의 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4일 경우
        * 용적을 반으로 줄임( 단, 최소 용적보단 커야함)
        * */
        if (array.length > defaultCapacity && size < array.length /4 ){
            resize(Math.max(defaultCapacity, array.length/2));
        }
    }

    //Comparable 을 이용한 sift-down
    @SuppressWarnings("unchecked")
    private void siftDownComparable(int index, E target){

        Comparable<? super E> comparable = (Comparable<? super E>) target;

        array[index] = null;
        size --;

        int parent = index;
        int child;

        while ((child = getLeftChild(parent)) <= size){
            int right = getRightChild(parent);

            Object childVal = array[child];

            if (right <= size && ((Comparable<?super E>) childVal).compareTo((E)array[right]) > 0){
                child = right;
                childVal = array[child];
            }

            if (comparable.compareTo((E) childVal) <= 0){
                break;
            }
            array[parent] = childVal;
            parent = child;
        }
        array[parent] = comparable;

        if(array.length > defaultCapacity && size < array.length / 4){
            resize(Math.max(defaultCapacity, array.length/2));
        }
    } //삭제 과정 최대 힙 구현하려한다면 비교과정을 반대로 해주면 된다.
    
    /*
    * size, peek, isEmpty, toArray 메서드 구현
    * 현재 Heap에 저장 된 요소의 개수를 알고 싶을 때 size값을 리턴하기 위한 메서드로 size()를 하나 만들고,
    * 또한 가장 우선순위가 높은 원소인 루트 노드의 값만 확인하고 싶을 때 쓰는 메서드를 위해 peek() 메서드를 만들 것이다.
    * 데이터를 삭제하지 않고 확인만 하고 싶을 때 쓰는 것이 peek() 메서드다. 한마디로 remove() 메서드에서 삭제과정만 없는 것이 peek() 이다.
    *
    * 그리고 의외로 자주 쓰이는 현재 힙(Heap)에 요소가 아무 것도 없는 경우를 판별하기 위한 메서드도 하나 만들어주자.
    * 이 때 힙에 아무 요소가 없다는 것은 size가 0이라는 소리이므로 size == 0 인지를 반환해주면 된다.
    *
    * 마지막으로 toArray로 현재 Heap의 배열에 요소가 어떻게 배치되어있는지 볼 수 있또록 아주 간단하게 구현해보자.
    * */
    public int size () {
        return this.size;
    }
    @SuppressWarnings("unchecked")
    public E peek(){
        if (array[1] == null){
            throw new NoSuchElementException();
        }
        return (E)array[1];
    }

    public boolean isEmpty() {
        return size == 0 ;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size +1);
    }
}
