package com.moon.java.collectionFramework.doubleLinkedList;

import com.moon.java.collectionFramework.List;

public class DoublyLinkedList<E> implements List<E> {

    private Node<E> head; //첫 노드
    private Node<E> tail; //마지막 노드
    private int size; // 요소 개수
    /*
    * Node<E> head : 리스트의 가장 첫 노드를 가리키는 변수
    * Node<E> tail : 리스트의 가장 마지막 노드를 가리키는 변수
    * size         : 리스트에 있는 요소의 개수(=연결 된 노드의 개수)
    *
    * 처음 양방향 연결리스트를 생성할 때에는 아무런 데이터가 없으므로
    * 당연히 head와 tail이 가리킬 노드가 없기에 null로 초기화 및 size는 0으로 초기화 한다.
    * */


    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /*
    * Search 메서드
    *
    * 단일 연결리스트와 마찬가지로 연결리스트는 특정 위치의 데이터를 삽입, 삭제, 검색하기 위해서는
    * next 변수를 통해 특정 위치까지 찾아가야 할 일이 많기 때문이다.
    *
    * 앞으로 구현할 대부분의 자료구조들도 만약 가능하다면 검색(탐색) 메소드부터 구현해놓는 것이 매우 편리하다.
    *
    * 또한 양방향 연결리스트이니, 검색 과정에서 효율적이게 index 값이 tail에 가까운지 (=size값에 가까운지) head에 가까운지(=0에 가까운지)를
    * 구분하여 어디서부터 시작할지 정하여 탐색하도록 하자.
    * */
    //특정 위치의 노드를 반환하느 ㄴ메서드
    public Node<E> search(int index){
        //범위 밖 (잘못된 위치) 일 경우 예외 던지기
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        // 뒤에서부터 검색
        if (index > size /2 ){
            Node<E> x = tail;
            for (int i = size -1; i < index; i--){
                x = x.prev;
            }
            return x;
        }
        //앞에서부터 검색
        else{
            Node x= head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
    }

    /*
    * add 메서드 구현
    * add 메서드에는 SinglyLinkedList와 마찬가지로 크게 3가지로 분류 된다.
    * - 가장 앞부분에 추가 : addFirst(E value)
    * - 가장 마지막 부분에 추가 (기본값) : addLast(E value)
    * - 특정 위치에 추가 : add(int index, E value)
    *
    * 자바에 내장되어있는 LinkedList에서는 add() 역학을 addLast()메서드가 하고,
    * 특정 위치에 추가는 add(int index, E element)메서드, 가장 첫 부분에 추가는 addFirst()가 한다.
    * */

    /*
    * addFirst (E value)
    * 먼저 기본 값인 add() 및 addLast()를 구현하기 전에 먼저 addFirst()를 구현하고자 한다.
    * 이유는 addLast를 구현할 대 알 수 있으니 일단은 가장 앞에 추가하는 것부터 하자.
    *
    * 앞서 언급했듯이 연결리스트는 말 그대로 '링크'로 연결 된 리스트다. 즉, 가장 앞에 추가한다면 다음과 같은 과정을 거친다.
    *
    * Step 1 . make new Node
    * Step 2 . linking
    * Step 3 . update head
    *
    * */

    public void addFirst(E value){
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;

        /*
        * head가 null이 아닐 경우에나 기존 head 노드의 prev 변수가
        * 새 노드를 가리키도록 한다.
        * 이유는 기존 head노드가 없는 경우(null)는 데이타가
        * 아무 것도 없던 상태엿으므로 head.prev를 하면 잘못된 참조가 된다.
        * */
        if(head != null){
            head.prev = newNode;
        }
        head = newNode;
        size ++;

        /*
        *다음에 가리킬 노드가 없는 경우 (=데이터가 새 노드밖에 없는 경우)
        * 데이터가 한 개(새 노드 )밖에 없으므로 새 노드는 처음 시작노드이자
        * 마지막 노드다. 즉 tail = head 다.
        * */
        if(head.next ==null){
            tail = head;
        }
        /*
        * 새 노드(newNode)를 하나 만들어준 다음 '가장 앞에 추거' 해야 하므로 기존에 있던 head가 가리키는 노드 앞에 존재해야 한다.
        *
        * 즉, 새로운 노드의 next가 다음 노드인 head가 되는 것이다.
        * 또한 기존 head노드의 prev변수도 새노드를 가리키도록 해주면 되는데, 예외적으로 아무런 요소가 없던 상태였을 경우 이 때는
        * head도 null인 상태다. 즉 , null 상태인 객체에 prev를 접근할 수 없기 때문에 'NullPointerException'이 발생한다.
        * 이를 방지하기 위해 head가 null 상태인지 체크를 반드시 하고 head의prev 변수를 업데이트해주어야 한다.
        *
        * 그렇게 링크를 연결해주었다면, head가 가리키는 첫 번째 노드를 새로운 노드로 변경해주고 사이즈를 1 증가 시키면 된다.
        *
        * 그리고 마찬가지로 아무런 노드가 없는 상태에서 처음으로 추가하는 노드일 경우 결국 head가 가리키는 노드는 없다.
        * 이는 head  노드 (새로운 노드) 가 처음이자 마지막 노드가 된다는 말이기 때문에 마짐가을 가리키는 변수 tail은 곧 head와 같은 노드를 가리키게 된다.
        * */
    }
    /*
    * 기본 삽입 : add (E value) & addLast(E value) 메소드
    * add()의 기본 값은 addLast()라고 했다.  그리고 LinkedList APi를 보면 add 메서드를 호출하면 add()메서드는 addLast()를 호출한다.
    * 즉 구현 자체는 addLast를 중점적으로 구현하면 되는 것이다.
    *
    * addFirst()가 먼저 구현인 이유 : size가 0일 경우 (= 아무런 노드가 없는 경우) 는 결국처음으로 데이터를 추가한다는 것이다.
    * 때문에 간단하게 addFirst()를 호출하면 된다.
    * */


    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value); // 새 노드 생성

        if (size == 0 ){// 처음 넣는 노드일 경우 addfirst로 추가
            addFirst(value);
            return;
        }
        /*
        * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
        * tail이 가리키는 노드를 새 노드로 바꿔준다.
        * */

        tail.next = newNode;  //Step 2
        newNode.prev = tail; //Step 2
        tail = newNode; //Step 3
        size++;
        // 오히려 데이터가 없었던 경우는 addFirst()에서 처리하기 때문에 addLast()에서는 고려해줄 필요가 없어 수월하다.
    }

  /*
  * add(int index, Object value)
  *  먼저 넣으려는 위치(예시에서는 index=3)의 노드와 이전 노드를 찾아야 한다.
  *  넣으려는 위치의 이전 노드를 prevNode라고하고, 넣으려는 위치의 기존노드를 nextNode라고 할 때,
  *  앞서 우리가 만든 메소드 search()를 사용하여 넣으려는 위치의 -1 위치의 노드(prevNode)를 찾아내고, nextNode는 prevNode.next를 통해 찾는다.
  *
  *  그리고 이전노드 (prevNode)의 next 변수는 새 노드(newNode)를 가리키도록 하고, 새 노드는 prev와 next를 앞 뒤 노드를 가리키도록 한 뒤,
  *  마지막으로 nextNode의 prev변수는 새 노드를 가리키도록 해야한다. 또한 index 변수가 잘못된 위치를 참조할 수 있으니 이에 대한 예외처리로 IndexOutOfBoundsException을 한다.
  * */
    @Override
    public void add(int index, E value) {
        //잘못된 인덱스를 참조할 경우 예외 발생
        if (index > size || index < 0 ){
            throw new IndexOutOfBoundsException();
        }
        //추가하려는 index 가 가장 앞에 추가하려는 경우 addFirst 호출
        if (index == 0 ){
            addFirst(value);
            return;
        }
        //추가하려는 index가 마지막 위치일 경우 addLast 호출
        if (index == size){
            addLast(value);
            return;
        }

        //추가하려는 위치의 이전 노드
        Node<E> prevNode = search(index -1);
        //추가하려는 위치의 노드
        Node<E> nextNode = prevNode.next;
        //추가하려는 노드
        Node<E> newNode = new Node<E>(value);

        //링크 끊기
        prevNode.next = null;
        nextNode.prev = null;

        //링크 연결하기
        prevNode.next = newNode;

        newNode.prev = prevNode;
        newNode.next = nextNode;

        nextNode.prev = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, Object value) {

    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
