package com.moon.java.collectionFramework.singleLinkedList;

import com.moon.java.collectionFramework.arrayList.List;

import java.util.NoSuchElementException;

/* https://st-lab.tistory.com/167?category=856997 */
public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head; //노드의 첫 부분 , 리스트의 가장 첫 노드를 가리키는 변수.
    private Node<E> tail; //노드의 마지막 부분 , 리스트의 가장 마지막 노드를 가리키는 변수.
    private int size; //요소 개수 , 리스트에 있는 요소의 개수 (=연결 된 노드의 개수)


    //생성자
    //처음으로 단일 연결리스트를 생성 할 때에는 아무런 데이터가 없었으므로 당연히 head와 tail이 가리킬 노드가 없기에 null로 초기화 및 size는 0으로 초기화 해주도록 한다.
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    //단일 연결 리스트이다 보니 특정 위치의 데이터를 삽입, 삭제, 검색하기 위해서는 처음 노드(head)부터 next변수를 통해 특정 위치까지 찾아가야 하기 때문이다.
    //특정 위치의 노드를 반환하는 메소드

    private Node<E> search(int index) {
        //범위 밖 (잘못된 위치) 일 경우 예외 던지기
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;//head가 가리키는 노드부터 시작

        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    /*
     * add 메소드에는 ArrayList와 마찬가지로 크게 3가지로 분류 된다.
     * 1. 가장 앞부분에 추가 : addFirst(E value)
     * 2. 가장 마지막 부분에 추가(기본값) : addLast(E value)
     * 3. 특정 위치에 추가 : add(int index, E value)
     *
     * 자바에 내장되어 있는 LinkedList에서는 add() 역할을 addLast()메서드가 하고,
     * 특정 위치에 추가는 add(int index, E element)메서드, 가장 첫 부분에 추가는 addFirst()가 한다.
     * */

    /*
     * addFirst(E value)
     * 먼저 기본 값인 add() 및 addLast()를 구현하기 전에 먼저 addFirst()를 구현하고자 한다.
     * 이유는 addLast를 구현 할 때 확인 할 수 있다 .
     * 앞서 언급했듯이 연결리스트는 말 그대로 "링크"로 연결 된 리스트다. 즉, 가장 앞에 추가한다면 다음과 같은 과정을 거친다.
     *
     * Step. 1 make New Node.  (reference Data = null);
     * Step. 2 linking. (reference Data = nextNode);
     * Step. 3 update head. (head = new Node);
     *
     * */

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value); // 새노드 생성 Step 1
        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결 Step 2
        head = newNode; //step 3
        size++;

        /*
         * 다음에 가리킬 노드가 없는 경우 (=데이터가 새 노드밖에 없는경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
         * 마지막 노드다. 즉 tail = head 다.
         *
         * 새로운 노드의 next가 다음 노드인 head가 되는 것이다.
         *
         * */
        if (head.next == null) {
            tail = head;
        }

        /*
         * 위 스텝대로 새 노드(newNode)를 하나 만들어준 다음 '가장 앞에 추가'해야 하므로 기존에 있던 head가 가리키는 노드 앞에 존재해야 한다는 것이다.
         * 즉, 새로운 노드의 next가 다음 노드인 head가 되는 것이다.
         * 그렇게 링크를 연결해주었다면, head가 가리키는 첫번째 노드를 새로운 노드로 변경해주고 사이즈를 1증가시켜주면 된다.
         * 그리고 예외적으로 아무런 노드가 없는 상태에서 처음으로 추가하는 노드일 경우는 결국 head가 가리키는 노드는 없다.
         * 이는 head노드 (새로운노드)가 처음이자 마지막 노드가 된다는 말이기 때문에 마지막을 가리키는 변수 tail은 곧 head와 같은 노드를 가리키게 된다.
         *
         * */

    }


    /*
     *
     * 기본 삽입 : (E value) & addLast(E value) 메서드
     *
     * add()의 기본 값은 addLast()라고 했다. 그리고 LinkedList API를 보면 add 메서드를 호출하면 add() 메서드는 addLast()를 호출한다.
     * 즉, 구현 자체는 addLast를 중점적으로 구현하면 되는 것이다.
     * Step 1. make new Node.
     * Step 2. linking
     * Step 3. update tail
     *
     * addFirst를 먼저 구현했다면, size가 0일 경우(=아무런 노드가 없는 경우)는 결국 처음으로 데이터를 추가한다는 뜻이기 때문에, addFirst() 호출하면 된다.
     * 데이터를 이동 시키는 것이 아닌 새로운 노드를 생성하고 이전 노드의 레퍼런스 변수(next)가 새로운 노드를 가리키도록 하면 된다.
     *
     * */
    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value); // 새 노드 생성

        if (size == 0) {
            addFirst(value);
            return;
        }

        /*
         * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         * tail이 가리키는 노드를 새 노드로 바꿔준다.
         * */

        tail.next = newNode;
        tail = newNode;
        size++;
        /*
         * 기존에 있던 tail 노드가 다음 노드를 가리키는 변수(next)를 새 노드를 가리키도록 변경해주고 tail이 가리키는 노드를 새로운 노드로 변경만 해주면 된다.
         * */
    }
    /*
     * 1. 삽입하려는 위치의 노드, 그리고 그 이전 노드를 찾아야 한다.
     * 2. 넣으려는 위치의 이전 노드를 prevNode , 넣으려는 위치의 기존 노드를nextNode라고 할 때,
     * search()를 사용해서 넣으려는 위치의 -1 위치의 노드(prevNode)를 찾아내고,
     * nextNode는 prevNode.next를 통해 찾는다.
     *
     * 그리고 prevNode의 링크를 새로 추가하려는 노드로 변경, 새로 추가하려는 노드의 링크는 nextNode로 변경해준다.
     * 다만, index 변수가 잘못된 위치를 참조할 수 있으니, 이에 대한 예외처리로 IndexOutOfBoundsException을 한다.
     *
     * */

    @Override
    public void add(int index, E value) {

        //잘못된 인덱스를 참조할 경우 예외 발생
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출
        if (index == 0) {
            addFirst(value);
            return;
        }
        //추가하려는 index가 마지막 위치일 경우 addLast 호출
        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prevNode = search(index - 1);
        //추가하려는 위치의 노드
        Node<E> nextNode = prevNode.next;

        //추가하려는 노드
        Node<E> newNode = new Node<E>(value);

        /*
         * 이전 노드가 가리키는 노드를 끊은 뒤
         * 새 노드로 변경해준다.
         * 또한 새노드가 가리키는 노드는 nextNode로 설정해준다.
         * */
        prevNode.next = null;
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }
    /*
     * remove 메소드 구현
     *
     * add로 요소를 추가했다면 반대로 삭제할 수도 있어야 한다.
     * remove() 메소드 또한 그리 어렵지는 않다. 쉽게 생각해서 add()메소드의 메커니즘을 반대로 생각하면 된다.
     *
     * remove 메서드의 경우 크게 3가지로 나눌 수 있다.
     *
     * - 가장 앞의 요소(head)를 삭제 - remove()
     * - 특정 index의 요소를 삭제 - remove(int index)
     * - 특정 요소를 삭제 - remove(Object value)
     *
     * 기본적으로 삭제 연산의 기초는 remove()메서드로 head가 가리키는 요소, 즉 첫 번째 요소를 삭제하는 것이다.
     * 인덱스로 생각한다면 0 위치에 있는 요소를 말한다.
     *  그리고 다른 remove() 메서드들을 구현할 때 자칫 null을 참조하거나 잘못된 참조를 하는 경우도 있으니 주의가 필요.
     * */

    /*
     * remove()는 가장 앞에 있는 요소를 제거하는 것.
     * 즉 head가 가리키는 요소만 없애주면 된다.
     *
     * Step 1. Search head Node
     * Step 2. unlinking
     * Step 3. update head
     *
     * 쉽게 생각해서 head가 가리키는 노드의 링크와 데이터를 null로 지워준 뒤 head를 다음 노드로 업데이트를 해주는 것이다.
     * 그리고 삭제하려는 노드가 리스트에서의 유일한 노드였을 경우 해당 노드를 삭제하면 tail이 가리키는 노드 또한 없어지게 된다.
     * (요소가 한 개일 경우 head와 tail이 가리키는 노드가 같기 때문) 이에 대해서도 정확하게 처리를 해주어야 한다.
     *
     * */

    public E remove() {

        Node<E> headNode = head;
        if (headNode == null) {
            throw new NoSuchElementException();
        }
        //삭제된 노드를 반환하기 위한 임시변수
        E element = headNode.data;
        //head의 다음 노드
        Node<E> nextNode = head.next;
        //head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;
        // head 가 다음 노드를 가리키도록 업데이트
        head = nextNode;
        size--;

        /*
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail이었으므로
         * 삭제되면서 tail도 가리킬 요소가 없기 때문에
         * size가 0일 경우 tail도 null로 변환
         * */
        if (size == 0) {
            tail = null;
        }
        return element;
    }// 참고로 리스트에 아무런 요소가 없는데 삭제를 시도하려는 경우에는 요소를 찾을 수 없기 때문에 NoSuchElementException()예외를 던져준다.

    /*
     * remove(int index)메서드는 사용자가 원하는 특정 위치 (index)를 리스트에서 찾아서 삭제하는 것이다.
     * add(int index, E value)와 정반대로 구현해주면된다.
     * Step 1. serach Node
     * Step 2. unlinking
     * Step 3. linking
     *
     * 결과적으로 우리가 알아야 할 노드는 총 3개다.
     * 그리고 index를 범위 밖으로 입력했을 경우의 예외 또한 던져주도록 하자.
     * */

    @Override
    public E remove(int index) {

        //삭제하려는 노드가 첫번쨰 원소일 경우
        if (index == 0) {
            return remove();
        }

        //잘못된 범위에 대한 예외
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = search(index - 1); // 삭제할 노드의 이전 노드
        Node<E> removedNode = prevNode.next;  // 삭제할 노드 
        Node<E> nextNode = removedNode.next;  // 삭제할 노드의 다음 노드

        E element = removedNode.data; // 삭제되는 노드의 데이터를 반환하기 위한 임시 변수

        //이전 노드가 가리키는 노드를 삭제하라는 노드의 다음 노드로 변경
        prevNode.next = nextNode;
        //만약 삭제했던 노드가 마지막 노드라면 tail을 prevNode로 갱신
        if (prevNode.next == null) {
            tail = prevNode;
        }
        // 데이터 삭제
        removedNode.next = null;
        removedNode.data = null;
        size--;

        return element;
    }

    /*
     * remove(Object value) 메소드
     *
     * remove (Object value)메소드는 사용자가 원하는 특정 요소 (value)를 리스트에서 찾아서 삭제한다.
     * remove (int index) 메소드하고 동일한 메커티즘으로 작동한다.
     * 다만 고려해야 할 점은 '삭제하려는 요소가 존재하는지'를 염두해두어야 한다.
     * 예를 들어서, 삭제하려는 요소를 찾지 못 했을 경우 false를 반환, 찾았을 경우 remove(int index)와 동일한 삭제 방식을 사용한다.
     * Step 1. Search Node
     * Step 2. Unlinking
     * Step 3. linking
     *
     * */
    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> x = head; // removeNode

        //value와 일치하는 노드를 갖는다.
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        //일치하는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }
        //만약 삭제 하려는 노드가 head라면 기존 remove()를 사용
        if (x.equals(head)) {
            remove();
            return true;
        } else {
            // 이전 노드의 링크를 삭제 하려는 노드의 다음 노드로 연결
            prevNode.next = x.next;
            //만약 삭제했던 노드가 마지막 노드라면 tail을 prevNode로 갱신
            if (prevNode.next == null) {
                tail = prevNode;
            }
            x.data = null;
            x.next = null;
            size--;
            return true;
        }
    }

    /*
     * get()
     * index로 들어오는 값을 인덱스 삼아 해당 위치에 있는 요소를 반환하는 메소드다.
     * 앞의 search는 노드를 반환하고, get()메소드는 '노드의 데이터'를 반환한다.
     * */
    @Override
    public E get(int index) {

        return search(index).data;
    }

    /*
     * set(int index, E value)
     * set 메서드는 기존에 index에 위치한 데이터를 새로운 데이터(value)로 '교체'하는 것이다.
     * add 메서드는 데이터 '추가'인 반면에 set은 '교체'라는 점을 기억해두도록 하자.
     * 결과적으로 index에 위치한 데이터를 교체하는 것이기 때문에 마찬가지로 search() 메서드를 사용하여 노드를 찾고,
     * 해당 노드의 데이터만 새로운 데이터로 변경해준다.
     *
     * */
    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;

    }

    /*
     * indexOf 메서드가 사용자가 찾고자 하는 요소(value)의 위치(index)를 반환하는 메서드였다면,
     * contains는 사용자가 찾고자 하는 요소(value)가 존재하는지 안하는지를 반환하는 메서드이다.
     * 찾고자 하는 요소가 존재 한다면 true, 존재하지 않는다면 false를 반환한다.
     *
     * indexOf와 기능이 비슷함으로 해당 요소가 존재하는지를 검사한다는 기능이 같기에
     * indexOf 메서드를 이용하여 만약 음수가 아닌 수가 반환되었다면 요소가 존재한다는 뜻이고,
     * 음수 (-1)이 나왔다면 요소가 존재하지 않는다는 뜻이다.
     * i
     * */
    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    /*
     * indexOf메서드는 사용자가 찾고자 하는 요소(value)의 위치(index)를 반환하는 메서드다.
     * 만약, 찾고자하는 요소고 중복되는 상황이라면 가장 먼저 마주치는 요소의 인덱스를 반환한다.
     * ( 실제 자바 제공 indexOf에서도 동일)
     * 찾고자 하는 요소가 없다면 -1을 반환한다.
     * 그리고 중요한점은 객체끼리 비교할 때는 동등연산자가 아닌 반드시.equals로 비교해야 한다.
     * 객체까리 비교할 때 동등연산자를 쓰면 값을 비교하는 것이 아닌 주소를 비교하기 때문에 결과가 바르지 못하다.
     *
     *
     * */
    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next) {

            if (value.equals(x.data)) {
                if (value.equals(x.data)) {
                    return index;
                }
                index++;
            }
        }
        //찾고자 하는 요소를 찾지 못했을 경우 -1 반환
        return -1;
    }

    /*
     * ArrayList와 같이 모든 리스트 자료구조는 기본적으로 동적 할당을 전제로 한다.
     * 즉 그만큼 요소들을 삽입, 삭제가 많아지면 사용자가 리스트에 담긴 요소의 개수가 몇 개인지 기억하기 힘들다.
     * 더군다나 리스트의 변수들은 기본적으로 private 접근제한자로 size 또한 마찬가지다.
     * (왜냐하면 size를 접근할 수 있게 될 경우 size에 사용자가 고의적으로 데이터를 조작할 수 있다.)
     * 그렇게이 size변수의 값을 반환해주는 메소드인 size()를 만들어줄 필요가 있다. size만 반환해주면 되기 때문에 간단하다.
     * */
    @Override
    public int size() {
        return size;
    }

    /*
     * isEmpty() 메서드는 현재 arrayList에 요소가 단하나도 존재하지 않고 비어있는지를 알려준다.
     * 리스트가 비어있을 경우 true를, 비어있지 않고 단 하나라도 요소가 존재한다면 false를 반화해주면 된다.
     * 즉, 이 말은 size가 요소의 개수였으므로 size ==0 이면 true, 0이 아니면 false라는 것이다.
     * 굳이 배열을 모두 순회하여 데이터가 존재하는지 검사해줄 필요가 없다.
     * */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * clear()메서드
     * clear()는 단어에서 짐작할 수 있듯 모슨 요소들을 비워버리는 작업이다.
     * 예로, 리스트에 요소를 담아두었다가 초기화가 필요할 때 쓸 수 있기에 유용하다.
     * 모든 요소를 비워버린다는 것은 요ㅅ소가 0개라는 말로 size 또한 0으로 초기화해준다.
     *
     * 이 때 그냥 객체 자체를 null해주기 보다는 모든 노드를 하나하나 null 해주는 것이
     * 자바의 가비지 컬렉터가 명시적으로 해당 메모리를 안쓴다고 인지하기 때문에 메모리 관리 효율 측면에서 조금 더 좋다.
     * */
    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }
/*

    */
/*
     * clone() 메서드
     *
     * 만약 사용자가 사용하고 있던 LinkedList를 새로 하난 복제하고 싶을 때 쓰는 메서드이다.
     * ArrayList에서도 언급이 되지만 단순히 = 연산자로 객체를 복사하게 되면 주소를 복사하는 것이기 때문에
     * 복사한 객체에서 데이터를 조작할 경우 원본 객체까지 영향을 미친다.
     * 즉 얕은 복사(shallow copy)가 된다는 것이다. (참고로 Object 클래스에 정의된 toString()
     * 메서드는 getClass.getName() + "@" + Integer.toHexString(hashCode());이다.)
     * Test 코드 중 testBeforeColoneMethod를 참고.
     * 객체의 주소가 복사되면 주소와 데이터가 모두 같아진다.
     *
     * 이러한 것을 방지하기 위해서 깊은 복사를 하는데, 이 때 필요한 것이 clone() 이다.
     * Object에 있는 메소드지만 접근제어자가 protected 로 되어 있어 우리가 만든 것처럼 사용자 클래스의 경우
     * Clonable 인터페이스를 implement 해야한다.
     * 즉, public class LinkedList<E> implements List<E> 에 Cloneable도 추가 해주어야 한다.
     * 만약 안하고서 구현하면 CloneNotSupportedException을 보게 된다.
     * 그리고 나서 clone()을 구현하면 되는데 아래와 같이 재정의 한다.
     * */



/*

    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        SinglyLinkedList.md<? super E> clone = (SinglyLinkedList.md<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }
        return clone;
    }
*/

/*
     * super.clone()을 해주면, 객체 자제는 생성되나 내부까지 데이터 복제가 이루어지는 것이 아닌 얕은 복사가 되어버린다.
     * 그렇기 때문에 새로 만들어진 객체의 내부에 데이터를 새로 설정해주어야 한다.
     * 즉, 각 노드를 일단 끊고, 처음부터 끝까지 현재 리스트의 데이터를 clone 리스트에 넣어주어야 한다.
     *
     * 설명을 덧붙이자면, super.clone() 자체가 생성자 비슷한 역할이고 shallow copy를 통해 사실상 new SinglyLinkedList.md()를 호출하는
     * 격이라 제대로 완벽하게 복제하려면 clone한 리스트의 array 또한 새로 생성해서 해당 배열에 copy를 해주어야 한다.

     * sort() 메서드
     * ArrayList에서는 따로 만들지는 않았따, ArrayList 자체가 내부에서 Object[]배열을 사용하고 있기 때문에
     * Arrays.sort()를 해주면 끝나기 때문이다.
     * 하지만 LinkedList 같은 객체로 연결 된 리스트들은 이와는 조금 다른 방법으로 정렬해야 한다.
     * 객체배열의 경우 Collections.sort()를 사용하게 되는데, 해당 메서드를 뜯어보았다면 알겠지만,
     * Collections.sort() 도 내부에서는 Arrays.sort()를 쓴다.
     * 어떻게 Arrays.sort()를 쓰지? 싶겠지만, 원리는 간단하다.
     * 해당 리스트를 Object[] 배열로 변환시켜 Arrays.sort()를 통해 정렬한 뒤,
     * 정렬된 데이터를 다시 리스트의 노드에서 세팅을 해주는 것이다.
     *
     * 만약 래퍼(Wrapper) 클래스 타입(ex. Integer, String, Double..) 이라면 따로 Comparator를 구현해주지 않아도 되지만,
     * 사용자 클래스, 예로 Student 클래스를 만든다거나.. 이러한 경우는 사용자가 따로 해당 객체에 Comparable를 구현해주거나 또는
     * Comparator를 구현해주어 파라미터로 넘겨주어야 한다.
     *
     * 즉,sort() 메소드를 만들 때, 기본적으로 두 가지 경우를 생각해야 한다는 것이다.
     * 첫번째는 객체에 Comparable이 구현이 되어있어 따로 파라미터로 COmparator를 넘겨주지 않는 경우이고 ,
     * 다른 하나는 Comparator를 넘겨주어 정의 된 정렬 방식을 사용하는 경우다.
     * */


/*

    public void sort() {

    */
/*
     * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된
     * 정렬 방식을 사용한다.
     * 만약 구현되어 있지 않으면 cannot be cast to class java.lang.Comparable
     * 에러가 발생한다.
     * 만약 구현되어 있을 경우 null로 파라미터를 넘기면
     * arrays.sort()가 객체의 compareTO 메소드에 정의된 방식대로 정렬한다.
     * *//*



        sort(null);
    }
*/
/*

    @SuppressWarnings({"unchecked","rawtypes"})
    public void sort(Comparator<? super E> c){

        Object[] a = this.toArray();
        Arrays.sort(a,(Comparator) c);

        int i = 0;
        for (Node<E> x = head; x != null; x = x.next, i++){
            x.data = (E) a[i];
        }
    }
    */
/*

     * 어떠한 형태로든 결국 sort((Comparator<? super E> c)로 간다.
     * (참고로 상속관계이면서 부모 클래스에서 정렬 방식을 따르는 경우도 생각하여
     * <? super E>로하였다, 이러한 경우를 고려하지 않는다면 Comparator<E>로 해주어도
     * 종속관계 영향을 안받는 객체의 경우는 괜찮다.
     *
     * 그런 다음 Object[] 배열로 우리가 만들었던 toArray()를 통해 만들어 주어 Arrays.sort() 메서드를 통해 정렬해준다.
     * 실제로도 Collections.sort() 또한 Arrays.sort() 로 보낸다.  이 내부 구현으 들여다보면
     *
     * *//*


    // Collections.sort() - Comparator() 파라미터가 없는 경우
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void sort(List<T> list){
        list.sort(null);
    }
    //Collections.sort() - Comparator() 파라미터가 주어지는 경우
    @SuppressWarnings({"unchecked","rawtypes"})
    public static <T> void sort(List<T> list, Comparator<? super T> c){
        list.sort(c);
    }
    // 그리고 두 메소드 모두 List 인터페이스의 default 메소드인 sort()메소드로 보낸다. 해당 내부 구현은은 이렇다.
    //[List.sort()]
    @SuppressWarnings({"unchecked","rawtypes"})
    default void sort(Comparator<? super E> c){
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e: a) {
            i.next();
            i.set((E) e);
            // Iterator를 구현하지 않았기 때문에 대신 반복문을 통해 현재 클래스의 노드 데이터를 직접 바꾼다.
        }
    }
*/


}
