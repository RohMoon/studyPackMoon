package com.moon.java.collectionFramework.doubleLinkedList;

import com.moon.java.collectionFramework.List;

import java.util.NoSuchElementException;

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


    /*
    * 가장 앞의 요소(head)를 삭제 - remove()
    * 특정 index의 요소를 삭제 -remove(int index)
    * 특정 요소를 삭제 -remove(Object value)
    *
    * 기본적으로 삭제 연산의 가장 기초는 remove() 메소드로 head가 가리키는 요소, 즉 첫번째 요소를 삭제하는 것이다.
    * 인덱스로 생각한다면 0 위치에 있는 요소를 말한다.
    * 그리고 다른 remove() 메소드들을 구현할 때 자칫 null을 참조하거나 잘못된 참조를 하는 경우도 있으니 주의해야한다.
    * remove() 는 '가장 앞에 있는 요소' 를 제거하는 것이다. 즉 head 가 가리키는 요소만 없애주면 된다.
    *
    * step1. search Node.
    * step2. unlinking.
    * step3. update head.
    *
    * 쉽게 생각해서 head가 가리키는 노드의 링크와 데이터를null로 지워준 뒤 head를 다음 노드로 업데이트 해주는 것이다.
    * 그리고 삭하려는 노드가 리스트에서의 유일한 노드였을 경우 해당 노드를 삭제하면 tail이 가리키는 노드 또한 없어지게 된다.
    * (요소가 한 개일 경우 head와 tail이 가리키는 노드가 같기 때문) 이에 대해서도 정확하게 처리를 해주어야 한다.
    *
    * */
    public E remove(){
        Node<E> headNode = head;

        if(headNode == null){
            throw new NoSuchElementException();
        }

        //삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;;

        //head의 다음 노드
        Node<E> nextNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        /*
        * head의 다음 노드 (=nextNode)가 null이 아닐 경우에만
        * prev 변수를 null로 업데이트 해주어야 한다.
        * 이유는 nextNode 가 없는 경우 (null)는 데이터가
        * 아무것도 없던 상태였으며로 nextNode.prev 하면 잘못된 참조가 된다.
        * */
        if (nextNode != null){
            nextNode.prev = null;
        }

        head = nextNode;
        size--;

        /*
        * 삭제된 요소가 리스트의 유일한 요소였을 경우
        * 그 요소는 head 이자 tail이었으므로
        * 삭제 되면서 tail도 가리킬 요소가 없기 때문에
        * size가 0일 경우 tail도 null로 변함.
        *
        * addFirst()와 마찬가지로 다음 노드 (nextNode)가 null인지 아닌지 체크 해주어야 한다.
        * 만약 null인 경우는 prev 변수 자체에 접근할 수가 없어 NullPointerException이 발생한다.
        *
        * 참고로 리스트에 아무런 요소가 없는데 삭제를 시도하려는 경우 요소를 찾을 수 없기 때문에
        * NoSuchElementException() 예외를 던져주도록 하는 것이다.
        * */

        if(size ==0){
            tail = null;
        }
        return element;

    }

    @Override
    public E remove(int index) {
        /*
        * remove(int index) 메서드는 사용자가 원하는 특정 위치(index)를 리스트에서 찾아서 삭제하는 것이다.
        * add(int index, E value)와 정바낻로 구현해주면 된다.
        *
        * 쉽게 생각해보자면 '삭제하려는 노드의 이전 노드' 의 next변수를 '삭제하려는 노드의 다음 노드'를 가리키도록 해주면 된다.
        * Step 1. searchNode
        * Step 2. unlinking
        * Step 3. linking
        *
        * 결과적으로 우리가 알아야 할 노드는 총 3개다.
        * 1. 삭제대상노드
        * 2. 삭제대상의 이전노드
        * 3. 삭제대상의 이후노드
        *
        * 첫번째 요소가 삭제되는 경우와 마지막 요소가 삭제되는 경우, 요소가 한개 남았을 때 삭제하려고 하는 경우를 고려해야한다.
        * */

        /*
        * 첫 번째 요소가 삭제되는 경우이거나 요소가 한개 남았을 경우는 이전에 만들었던 remove()를 호출한다.
        * 참고로 요소가 한 개 남았을때는 size = 1 이다. 그러면 결국 사용자는 index 파라미터로 0밖에 입력할 수 없다.
        * 그 외의 숫자를 입력하면 범위 체크에 걸려 IndexOutOfBoundsException 예외를 던지기 때문이다.
        *
        * 결과적으로 index ==0 을 검사하는 것은 자연스레 요소가 한 개 남았을 때와 같은 경우가 된다.
        * 이 말은 거꾸로 말하면 위조건에 걸리지 않았을 경우 반드시 삭제하려는 노드의 앞 노드인 'prevNode'는 존재한다는 것이다.
        * 그러면 이제 고려해야 할 것은 삭제되는 노드 (removedNode)가 마지막 노드일 경우다.
        *
        * 이는 nextNode가 null인지 아닌지를 확인하며 된다.
        *
        *
        * */
        // 삭제 하려는 노드가 첫번째 노드일 경우
        if (index==0){
            E element = head.data;
            remove();
            return element;
        }
        Node<E> prevNode = search(index -1 );// 삭제할 노드의 이전 노드 
        Node<E> removedNode = prevNode.next; // 삭제할 노드
        Node<E> nextNode = removedNode.next; // 삭제할 노드의 다음 노드

        E element = removedNode.data; // 삭제되는 노드의 데이터를 반환하기 위한 임시변수

        /*
        * index == 0 일 때의 조건에서 이미 head 노드의 삭제에 대한 분기가 있기 때문에
        * prevNode 는 항상 존재한다.
        *
        * 그러나 nextNode의 경우는 null일 수 있기 때문에 (= 마지막 노드를 삭제하려는 경우)
        * 이전처럼 반드시 검사를 해준 뒤, nextNode.prev에 접근해야 한다.
        * */

        prevNode.next = null;
        removedNode.next = null;
        removedNode.prev = null;
        removedNode.data = null;

        if (nextNode != null){
            nextNode.prev = null;

            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }
        /*
        * nextNode가 null이라는 것은 마지막 노드를 삭제했다는 의미이므로
        * prevNode가 tail이 된다. (연결해줄 것이 없음.)
        * */
        else{
            tail = prevNode;
        }
        size --;
        return element;
    }

    /*
    * 사용자가 원하는 특정 요소(value)를 리스트에서 찾아서 삭제하는 것이다.
    *
    * remove(int index)  메서드하고 동일한 메커니즘으로 작동한다. 다만 고려해야 할 점은 ' 삭제하려는 요소가 존재하는지를 ' 염두해야 한다.
    * 예로 들어 삭제하려는 요소를 찾지 못 했을 경우 false를 반환하고, 만약 찾았을 경우 remove(int index)와 동일한 삭제 방식으로 사용하면 된다.
    *
    * */
    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = head;
        Node<E> x = head;  // removedNode

        //value 와 일치하는 노드를 찾는다.
        for (; x != null ; x= x.next) {
            if (value.equals(x.data)){
                break;
            }
            prevNode = x;
        }

        //일치하는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }
        
        //삭제하려는 노드가 head일 경우 remove()로 삭제
        if(x.equals(head)){
            remove();
            return true;
        }

        //remove (int index) 와 같은 메커니즘으로 삭제
        else{
            Node<E> nextNode = x.next;

            prevNode.next = null;
            x.data = null;
            x.next = null;

            if (nextNode != null){
                nextNode.prev = null;

                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            }
            else{
                tail = prevNode;
            }

            size--;
            return true;
        }
    }

    @Override
    public E get(int index) {
        /*
        *
        * list와 같이 index로 들어오는 값을 인덱스삼아 해당 위치에 있는 요소를 반환하는 메소드다.
        * 하지만 이미 search()메서드가 구현이 되어있기 때문에 이를 이용한다.
        * 다만 차이점이 있다면 search()메서드는 '노드'를 반환하고, get()메서드는 '노드의 데이터'를 반환하는 것이다.
        *
        * */
        return search(index).data;
    }

    /*
     *
     * set 메서드는 기존에 index에 위치한 데이터를 새로운 데이터(value)로 "교체" 하는 것이다.
     * add메서드는 데이터 ' 추가' 인 반면에 set은 '교체' 라는 점을 기억해두도록 하자.
     * 결과적으로 index에 위치한 데이터를 교체하는 것이기 때문에
     * 마찬가지로 search() 메서드를 사용하여 노드를 찾아내고
     * 해당 노드의 데이터만 새로운 데이터로 변경해주면 된다.
     *
     * */
    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
        //잘못된 인덱스를 참조하는 체크는 search()안에서 .
    }
    /*
    * indexOf 메서드는 사용자가 찾고자 하는 요소(value)의 위치(indeX)를 반환하는 메소드였다면
    * contains는 사용자가 찾고자하는 요소(value)가 존재 하는지 안하는지를 반환하는 메서드다.
    * 찾고자 하는 요소 존재한다면 true를, 존재하지 않는다면 false를 반환한다.
    *
    * 기능 자체가 indexOf 와 기능이 비슷하니깐 이를 쓸 수 있을것 같다.
    * 어차피 해당 요소가 존재하는지를 "검사" 하는 것이니까 indexOf메서드를 이용하여 음수가 아닌 수가 반환되었다면
    * 요소가 존재한다는 뜻이고 , 음수(-1)이 나온다면 요소가 존재하지 않는 다는 뜻이다.
    *
    * */
    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    /*
    *
    * indexOf 메서드는 사용자가 찾고자 하는 요소 (value)의 '위치(index)'를 반환하는 메서드다.
    *
    * Singly LinkedList는 단방향이라 불가피하게 head부터 탐색했어야 했으나,
    * 이번에는 DoublyLinkedList는 tail부터도 접근 할 수 있기 때문에 indexOf 메서드를 두개 만들어본다.
    *
    * 하나는 head부터 검색해나가는 indexOf(), 그리고 tail부터 역방향으로 검색해나가는 LastIndexOf 메서드를 구현할 것이다.
    *
    * 만약 찾고잔 하는 요소가 중복된다면 가장 먼저 마주치는 요소의 인덱스를 반환한다.
    * (자바에서 제공하는 indexOf 또한 동일하게 구현되어있다.)
    * 위와 같은 메커니즘 때문에 동일한 요소들이 여러개 있을 경우 SinglyLinkedList의 경우 항상 먼저 마주하는 요소의 인덱스를 반환하게 되었는데,
    * 만약 동일한 요소들 중 가장 뒤에 있는 요소부터 찾고 싶다면 뒤에서부터 탐색해야 한다.
    * DoublyLinkedList 는 양방향이기 때문에 tail부터 거꾸로 탐색할 수 있다는 장점이 있으므로 이를 유용하게 사용하기 위해 LastIndexOf를 만들고 사용하면 되는 것이다.
    * 찾고자하는 요소가 없다면 -1 을 반환한다.
    * 객체끼리 비교할 때는 동등연산자(==)가 아니라 반드시 .equals()로 비교해야 한다.
    * 객체기리 비교할 때 동등연사자를 쓰면 값을 비교하는 것이 아닌 주소를 비교하는 것이기 때문이다.
    *
    * */
    @Override
    public int indexOf(Object value) { // 정방향 탐색
        int index =0;

        for (Node<E> x = head; x != null ; x= x.next) {
            if (value.equals(x.data)){
                return index;
            }
            index ++;
        }
        return -1;
    }

    public int lastIndexOf(Object value){//역방향 탐색
        int index = size;

        for (Node<E> x = tail; x !=null; x = x.prev) {
            index--;
            if (value.equals(x.data)){
                return index;
            }
        }
        return -1;
    }
    /*
    *
    * 모든 리스트 자료 구조는 기본적으로 동적 할당을 전제로 한다.
    * 즉 그만큼 요소들을 삽입, 삭제가 많아지면 사용자가 리스트에 담긴 요소의 개수가 몇 개인지 기억하기 힘들다.
    * 더군다나 리스트의 변수들을 기본적으로 private 접근제한자로 size 또한 마찬가지다.
    * (왜냐하면 size를 접근할 수 잇게 될 경우 size에 사용자가 고의적으로 데이터를 조작 할 수 있기 때문이다.)
    * 그렇기에 size변수의 값을 반환 해주는 메서드인 size()를 만들어줄 필요가 있다.
    * size만 반환해주면 되기 때문에 매우 간단하다.
    *
    * */
    @Override
    public int size() {
        return size;
    }
    /*
    *
    * isEmpty() 메서드는 현재 ArrayList에 요소가 단 하나도 존재하지 않고 비어있는지를 알려준다.
    * 리스트가 비어있을 경우 true를, 비어있지 않고 단 한개라도 요소가 존재 할 경우 false를 반환해주면 된다.
    * 이 말은 size가 요소의 개수였으므로 size == 0 이면 true , 0이 아니면 false라는 것이다.
    * 굳이 배열을 모두 순회하여 데이터가 존재하는지 검사해줄 필요가 없다.
    *
    * */
    @Override
    public boolean isEmpty() {
        return size == 0 ;
    }
    /*
    *
    * clear()는 단어에서 짐작 할 수 있는 모든 요소들을 비워버리는 작업이다.
    * 예로 리스트에 요소를 담아두었다가 초기화가 필요할 때 쓸 수 있는 유용한 존재이다.
    * 또한 모든 요소를 비워버린다는 것은 요소가 0개라는 말로 size 또한 0으로 초기화 해준다.
    *
    * 이 때 그냥 객체 자체를 null 해주기 보다는 모든 노드를 하나한 null 해주느 ㄴ것이 자바의 가비지 컬렉터가 명시적으로
    * 해당 메모리를 안쓴다고 인지하기 때문에 메모리 관리 효율측면에서 조금이나마 좋다.
    *
    * */
    @Override
    public void clear() {
        for (Node<E> x = head; x != null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;

    }
}
