package com.moon.java.collectionFramework.arrayList;

import java.util.Arrays;

public class ArrayList2<E> implements List<E>{

    private static final int defaultCapacity = 10;  //최소 (기본) 용적크기
    private static final Object[] emptyArray = {}; // 빈 배열

    private int size; // 요소 개수
    Object [] array; // 요소를 담을 배열
    
    // 생성자 1 (초기 공간 할당 X)
    public ArrayList2() {
        this.array = emptyArray;

    }
    // 생성자 2 (초기 공강 할당 O)
    public ArrayList2(int capacity) {
        this.array = new Object[capacity];
        this.size= 0;
    }
/*
* add 메소드는 크게 3가지로 분류 된다 .
*  1. 가장 마지막 부분에 추가 (기본값) - addLast(E value)
*  2. 가장 앞 부분에 추가 - addFirst(E value)
*  3. 특정 위치에 추가  - add(int index , E value)
*  물론 현재 자바에 내장되어 있는 ArrayList에서는 addLast() 역할을 add() 메소드가 하고,
*  특정 위치에 추가는 add(int index, E element)가 하며 , addFirst()는 없다.
*  하지만 편의상 3가지로 나눠서 보고자 한다.
*
* */

    /* 기본 삽입 : add(E value) & addLast(E value) 메소드
    *
    * 먼저 add()의 기본 값은 addLast()라고 했다. 가장 마지막 부분에만 추가하면 되다보니 이 부분 구현 자체는 그리 어렵지 않다.
    * add Last () 메소드를 구현하자면 이렇다.
    * */

    /*
    * 중간 삽입 : add
    *  중간에 추가하는 것은 조금 까다롭다. 우선 index가 범위를 벗어나진 않는지를 확인해야하고, 중간에 삽입할 경우 기존에 있던 index의 요소와
    * 그의 뒤에 있는 데이터들을 모두 한 칸씩 밀어야하기 때문이다.
    * 일단 코드를 보기 전에 데이터가 추가되는 과정을 그린 그림을 먼저 보도록 하자.
    *
    * 즉, add에서 index로 들어오는 파라미터에 대해 만약 값이 3이 들어왔다면 원래 있던 배열에서 3을 포함한 뒤에 있는 모든 요소들을 모두 한칸씩 뒤로 옮긴 뒤에 3의 위치에 새로운 데이터를 삽입해주는 것이다.
    *
    * addLast는 단순히 데이터를 추가하는 과정이였다면 중간 삽입은 데이터를 미루는 코드가 추가된 것이다.
    *
    * 먼저 index로 들어오는 값이 size를 벗어나는지 (빈공간을 허용하지 않으므로 ) 또는 음수가 들어오는지를 확인한다.
    *  만약에 범위를 벗어나거나 인덱스가 음수가 들어오면 예외를 발생시키도록 한다.
    *
    * 그리고 사용자가 넘겨준 index가 size와 같다는 것은 결국 가장 마지막에 추가하는 것과 같은 의미이므로 만들어두었던 addLast()메소드로 가도록 한다.
    *
    * 그 외의 경우가 이제 중간에 삽입되는 경우다.
    * 당연하게도 size가 현재 배열의 용적과 같다는 것은 이미 꽉차서 더 이상 들어올 공간이 없다는 뜻이므로 resize()메소드를 호출해줌으로써 용적량을 늘리도록 한다.
    * 그리고 index와 그 후방에 있는 데이터들을 한칸씩 뒤로 밀어야하므로 반목문을 통해 뒤로 밀어주도록 한 뒤 array[index] 에는 새로운 요소로 교체해주도록 한다.
    *
    * */
    @Override
    public void add(int index, E value) {
        if (index > size || index <0){ //  영역을 벗어날 경우 예외 발생.
            throw new IndexOutOfBoundsException();
        }
        if (index == size ){
            addLast(value); // index가 마지막 위치라면 addLastIndex 메서드로 요소추가 .
        }
        else{
            if (size == array.length){
                resize();
            }

            //index 기준 후자에 있는 모든 요소들 한 칸씩 뒤로 밀기
            for (int i = 0; i < index; i++) {
                array[i] = array[i+1];
            }

            array[index] = value; // index  위치에 요소 반영
            size ++;
        }
    }

    /*
    * addFirst(E value)
    *
    * 이 부분은 별다를것 없다 . 생각해보면 기존 데이터가 있따면 어차피 모든 데이터들을 뒤로 밀어야하는것이 아닌가?
    * 즉 앞서 만들었던 중간 삽입에서 index를 0으로 보내면 되는 것이다.
    *
    * */
    public void addFirst (E value){
        add(0, value);
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    /*
    * addLast  메소드를 보면 된다 .
    * add를 호출하면 자동으로 파라미터로 넘어온 value는 addLast로 보내진다. 그리고 데이터 넣기에 앞서 현재 용적이 꽉 차있는지를 검사한다.
    *
    * 만약 꽉 차 있다면 우리가 만들었던  resize() 메소드를 호출해서 array가 더 큰 용적을 갖도록 만들어 준다.
    * 그리고 마지막 위치(size)에 value를 추가해주고 size를 1증가시켜준다. 그림으로 보자면 다음과 같다.
    *
    * size가 요소의 개수이고, index 는 0부터 시작하다보니 array[size] = value; 를 해주면 된다 . 이부분을 헷갈려서 잘못된 인덱스에 참조하는 경우가 많으니 주의할 필요가 있다.
    *
    * */
    public void addLast(E value){
        // 꽉 차 있는 상태라면 용적 재할당
        if(size == array.length){
            resize();
        }
        array[size] = value; // 마지막 위치에 요소 추가
        size ++; // 사이즈 1 증가

    }


    /*
    * Remove 메소드 구현
    *
    * remove 메소드의 경우 크게 2가지로 구분할 수 있다.
    * 1.  특정 index의 요소를 삭제 - remove(int index);
    * 2.  특정 요소를 삭제 = remove (Object value);
    *
    * 물론 자바에 내장되어있는 ArrayList도 remove()메소드는 없다. remove(int index)와 remove(Object value)메소드만 존재한다.
    * 하지만 이후에 다룰 Stack이나 LinkedList ,Queue등 다양한 자료 구조들은 remove()메소드가 존재한다.
    *
    * 만약 remove() 기능을 넣어 가장 앞 또는 가장 뒤 요소를 제거하는 기능을 넣고 싶다면 밑에서 다룰 remove(int index)를 호출하여 파라미터로 0또는 size-1을 넘겨주면 된다.
    *
    * */

    /*
    * remove(int index) 메소드
    *
    * remove(int index)는 '특정 위치에 있는 요소를 제거' 하는 것이다.
    * 앞서 add(int index, E value)를 했던 방식을 거꾸로 하면 된다.
    * 쉽게 생각해서 index에 위치한 데이터를 삭제하고, 해당 위치 이후의 데이터들을 한칸씩 땡겨오는 것이다.
    *
    * index의 요소를 임시 변수에 담고 배열에서는 지운다. 그 다음 이후의 데이터들을 한칸씩 당겨온다. 그렇게 데이터 사이의 빈 공간을 매꿨으면
    * size값을 줄여주면 된다. 물록 삭제가 되면서 데이터가 일정 이상 비워진 경우 용적을 줄이기 위해 resize() 메서드를 가장 마지막에 추가하면 된다.
    * 마찬가지로 삭제된 원소를 반환해야하는지라 Object타입을 E 타입으로 캐스팅을 해주면서 경고창이 뜬다.
    * 하지만 get()메소드에서 설명했듯이 삭제되는 원소 또한 E type 외에들어오는 것이 없기 때문에 형 안정성이 확보되므로 경고표시를 무시하기 위해
    * @SuppressWarnings("unchecked")를 붙인다.
    *
    * 그리고 잘 생각해야 할 것이 항상 마지막 원소의 인덱스는 size 보다 -1 작다.
    * 그렇기 때문에 범위 체크와, 이후의 배열 요소들을 한칸씩 당겨올 때 시작점과 끝 점을 잘 생각하면서 참조해야한다.
    * 또한 명시적으로 요소를 null로 처리해주어야 가비지컬렉터에 의해 더 이상 쓰지 않는 데이터의 메모리를 수거(반환)해주기 때문에
    * 최대한 Null 처리를 하는 것이 좋다.
    * (물론 명시적으로 안해도 크게 문제는 없지만 그럴 경우 가비지 컬렉터가 쓰지 않는 데이터더라도 나중에 참조될 가능성이 있는 데이터로 볼 가능성이 높아진다.
    * 이는 결국 메모리를 많이 잡아먹을 수 있는 가능성이 있다는 것이고 결과적으로 프로그램의 성능에 영향을 끼친다.)
    * 만약 index가 정상적인 참조가 가능한 값일 경우 해당 인덱스의 요소를 반환해준다.
    * 이 때 원본 데이터 타입으로 반환하기 위해 E 타입으로 캐스팅을 해준다.
    *
    * */
    @SuppressWarnings("unchecked")
    @Override
    public E remove (int index){
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        E element = (E) array[index];
        array[index] = null;

        //삭제한 요소의 뒤에 있는 모든 요소들을 한칸씩 당겨옴
        for(int i = index; i< size; i++){
            array[i] = array[i+1];
            array[i+1] = null;
        }
        size--;
        resize();
        return element;
    }
    /*
    * remove (Object value) 메소드
    *
    *
    * remove(Object value) 메소드는 사용자가 원하는 특정 요소 (value)를 리스트에서 찾아서 삭제하는 것이다.
    * IndexOf 메소드와 마찬가지로 리스틍나에 특정 요소와 매칭되는 요소가 여러개 있을 경우 가장 먼저 마주하는 (가장 앞부분에 있는) 요소만 삭제한다.
    *
    * 결과적으로 이 메소드에서 필요한 동작은 value와 같은 요소가 존재하는지, 만약에 존재한다면 몇 번째 위치에 존재하는지를 알아야 하는것 하나,
    * 그리고 그 index의 데이터를 지우고 나머지 뒤의 요소들을 하나씩 당기는 작업 하나, 이렇게 총 두가지의 동작이 필요하다.
    *
    * 그러면 우리는 그동안 만들었던 메소드들을 이용하여 매우 간단하게 만들 수 있다.
    * 리스트에 특정 요소와 같은 요소의 index를 찾는 작업은 우리가 만들어놓았던 indexOf()메소드가 있다.
    * 그리고 index를 통해 삭제하는 작업은 앞선 remove (int index) 메소드가 있다. 이 두가지를 조합해야한다.
    * 보면 indexOf 메소드를 통해 해당 value와 일치하는 요소를 찾아온다. 만약 -1 이라면 일치하는 요소가 없다는 의미이므로 false를 반환,
    * 아닐 경우에 index에 해당하는 요소를 제거해주고 true를 반환하면 끝이 난다.
    *
    * */
    @Override
    public boolean remove(Object value){
        //삭제하고자 하는 요소의 인덱스 찾기
        int index = indexOf(value);
        // -1이라면 array에 요소가 없다는 의미이므로 false를 반환
        if (index == -1){
            return false;
        }
        // index 위치에 있는 요소를 삭제
        remove(index);
        return true;

    }


    /*
    * get()은 리스트를 써본 사람이라면 누구나 쉽게 알 것이다. index로 들어오는 값을 인덱스 삼아 해당 위치에 있는 요소를 반환하는 메소드다.
    * 배열의 위치를 찾아가는 것이기 때문에 반드시 잘못된 위치 참조에 대한 예외처리를 해주어야 한다.
    * */
    @SuppressWarnings("unchecked")
    /*
    * @SuppressWarnings("unchecked")를 붙이지 않으면, type safe(타입안정성) 에 대해 경고를 보낸다.
    *  반환되는 것을 보면 E타입으로 캐스팅을 하고 있고 그 대상이 되는 것은 Object[] 배열의 Object 데이터이다.
    * 즉, Object -> E타입으로 변환을 하는 것인데 이 과정에서 변환할 수 없는 타입을 가능성이 있따는경고로 메소드 옆에 경고표시가 뜨는데,
    * 우리가 add하여 받아들이는 데이터 타입은 유일하게 E타입만 존재한다.
    * 그렇기 때문에 형 안정성이 보장된다. 한마디로 ClassCastException이 뜨지 않으니 이 경고들을 무시하겠다는 것이
    * @SuppressWarning("unchecked") 이다. 물론 절대 남발하면 안되고, 형 변환시 예외 가능성이 없을 확실한 경우에 최소한의 범위에서 사용하는 것이 좋다.
    * 그렇지 않으면 중요한 경고 메세지를 놓칠 수도 있기 때문이다.
    *
    * get 메소드에서도 마찬가지로 index가 음수이거나, size와 같거나 큰 수가 들어올 경우 잘못된 참조를 하고 있기 때문에
    * IndexOutOfBoundsException() 예외를 발생 시킨다.
    *
    * 만약 Index가 정상적인 참조가 가능한 값일 경우 해당 인덱스의 요소를 반환해준다. 이 때 원본 데이터 타입으로 반환하기 위해 E 타입으로 캐스팅을 해준다.
    *
    * */
    @Override
    public E get(int index) {
        if(index >= size || index < 0){//범위를 벗어나면 예외 발생
            throw new IndexOutOfBoundsException();
        }
        //Object 타입에서 E타입으로 캐스팅 후 반환
        return (E) array[index];
    }
    /*
    * set(int index, E value) 메소드
    *
    * set 메소드는 기존에 index에 위치한 데이터를 새로운 데이터(value)로 '교체'하는 것이다. add 메서드는 데이터 '추가'인 반면에
    * set은 '교체'라는 점을 기억해두록 하자.
    * 결과적으로 index에 위치한 데이터를 교체하는 것이기 때문에 get이랑 메소드가 유사하다.
    * 다만 get은 해당 인덱스의 값을 반환하는 것이였다면 set은 데이터만 교체해주면 된다.
    *
    * 마찬가지로 잘못된 인덱스를 참조하고 있진 않은지 반드시 검사가 필요하다. 그냥 index로 참조하는 모든 메소드들을 반드시 검사해야한다고 하면 편하다.
    * */
    @Override
    public void set(int index, Object value) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        else {// 해당 위치의 요소를 교체
            array[index] = value;
        }
    }


    /*
    * indexOf(Object value) 메소드
    * indexOf 메소드는 사용자가 찾고자 하는 요소(value)의 위치(index)를 반환하는 메소드이다.
    * 그러면 이러한 의문이 들 수 있다. "찾고자 하는 요소가 중복된다면 어떻게 반환해야 하나?" 이에 대한 답은 가장 먼저 마주치는 요소의 인덱스를 반환한다.
    * "그럼 찾고자 하는 요소가 없다면?" -1을 반환한다.
    *
    * 그리고 중요한 점은 객체끼리 비교할 때는 동등연산자가(==)가 아니라 반드시 .equals()로 비교해야 한다.
    * 객체끼리 비교할 때 동등연산자를 쓰면 값을 비교하는게 아니라 주소를 비교하는 것이기 때문에 잘못된 결과를 초래한다.
    *
    * */
    @Override
    public int indexOf(Object value) {
        int i = 0;

        //value와 같은 객체(요소 값)일 경우 i(위치)반환
        for ( i = 0; i < size; i++) {
            if (array[i].equals(value)){
                return i;
            }
        }
        //일치하는 것이 없다면 -1을 반환
        return -1;
    }

    /*
    * LastIndexOf(Object Value)메소드
    * indexOf 메소드는 indedx가 0부터 시작했다면 반대로 거꾸로 탐색하는 과정도 있는 것이 좀 더 좋다.
    * 예를 들어 사용자가 찾고자 하는 인덱스가 뒤 쪽이라고 예상 가능할 때 굳이 앞에서부터 찾아 줄 필요가 없기 때문이다.
    * 또한 이후에 구현할 Stack 에서도 이용 가능하므로 만들어 두는 것이 좋다.
    * */
    public int lastIndexOf(Object value){
        for (int i = size-1; i >=0 ; i--) {
            if(array[i].equals(value)){
                return i;
            }
        }
        return -1;
    }
    /*
    * contains(Object value) 메소드
    *
    * indexOf 메소드는 사용자가 찾고자 하는 요소(value)의 '위치(index)'를 반환하는 메소드였다면, contains 는 사용자가 찾고자 하는 요소(value)가 존재하는지 안하는지를 반환하는 메소드다.
    *
    * 어차피 해당 요소가 존재하는를 검사 한다는 기능은 같기 때문에 indexOf 메소드를 이용하여 만약 음수가 아닌 수가 반환되었다면 요소가 존재한다는 뜻이고
    * 음수가 나왔다면 요소가 존재하지 않는다는 뜻이다.
    *
    * */
    @Override
    public boolean contains(Object value){
        if (indexOf(value)>= 0){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    * ArrayList가 동적으로 할당되면서 요소들을 삽입, 삭제가 많아지면 사용자가 리스트에 담긴 요소의 개수가 몇 개인지 기억하기 힘들다.
    * 더군다나 ArrayList에서 size변수는 private 접근제한자를 갖고 있기 때문에 직접 참조를 할 수 없다.
    * (왜냐하면 size를 접근할 수 있게 될 경우 size에 사용자가 고의적으로 데이터를 조작할 수 있기 때문이다.)
    * 그렇기에 size 변수의 값을 반환해주는 메소드인 size()를 만들어줄 필요가 있다. size만 반환해주면 되기 때문에 매우 간단한다.
    * */
    @Override
    public int size() {
            return size;
    }
    /*
    * isEmpty() 메소드는 현재 ArrayList에 요소가 단 하나도 존재하지 않고 비어있는지를 알려준다.
    * 리스트가 비어있을 경우 true를, 비어있지 않고 단 한개라도 요소가 존재 할 경우 false를 반환해주면 된다.
    * 즉 이말은 size가 요소의 개수였으므로 size ==0 이면 true, 0 이 아니면 false라는 것이다.
    * 굳이 배열을 모두 순회하여 데이터가 존재하는지 검사해줄 필요가 없다.
    * */
    @Override
    public boolean isEmpty() {
        return size == 0; // 요소가 0개일 경우 비어있다는 의미이므로 true를 반환.
    }
    /*
    * clear 메서드
    * clear()는 단어에서 짐작 할 수 있듯 모든 요소들을 비워버리는 작업이다.
    * 예로 들어 리스트에 요소를 담아두었다가 초기화가 필요할 때 쓸 수 있는 유용한 존재이다.
    * 또한 모든 요소를 비워버린다는 것은 요소가 0개라는 말로 size또한 0으로 초기화 해주고, 배열의 용적 또한 현재 용적의 절반으로 줄일 수 있도록 해준다.
    *
    * 왜 초기 값이 아니라 절반인 이유는 물론 초기값으로 초기화해주어도 되나 생각해보면 현재 배열의 용적은 결국 데이터를 해당 용적에 만족하는 조건만큼 썼다는 의마가 된다.
    *
    * 예로 들어서 데이터가 1500개였다고 가정해보자, 그럼 용적량은 10부터 2씩 곱해지므로 2560이었을 것이다.
    * 요소들을 모두 초기화 했더라도 앞으로 들어올 데이터들 또한 데이터가 1500개일 가능성이 높다. 즉 현재 용적량의 기대치에 근접할 가능성이 높기 때문에 일단은 용적량을 일단 절반으로만 줄이는 것이다.
    * (또한 그만큼 데이터를 쓰지 않더라도 삭제 과정에서 욕적량을 줄이기 때문에 크게 문제가 되진 않는다.)
    * 모든 배열을 명시적으로 null로 처리해주는 것이 좋다.
    *
    * */
    @Override
    public void clear() {

    }

    private void resize(){
        int arrayCapacity = array.length;
        // 배열의 용적량이 0 이면
        if(Arrays.equals(array, emptyArray)){
            array = new Object[defaultCapacity];
            return;
        }
        /*
         앞서 생성자에서 사용자가 용적을 별도로 설정하지 않은 경우 emptyArray로 초기화 되어 있어 용적이 0인 상태다.
         이 경우 고려하여 이제 새로 array의 용적을 할당하기 위해 최소 용적으로 설정해두었던 defaultCapacity의 크기만큼 배열을 생성해주고 메소드를 종료한다.
         또한 주소가 아닌 값을 비교해야 하기 때문에 Arrays.equals() 메소드를 사요하도록 하자.
        */


        //용량이 꽉 찰 경우
        if (size == arrayCapacity){
            int newCapacity = arrayCapacity * 2;

            //copy
            array = Arrays.copyOf(array,newCapacity);
            return;
        }
        /*
        * 데이터가 꽉 찰 경우에는 데이터(요소)를 더 받아오기 위해서 용적을 늘려야 한다.
        * 즉 데이터의 개수가 용적과 같을 경우는 꽉 차 있는 경우를 말한다.
        * 이 때는 새롭게 용적을 늘릴 필요가 있으므로 새로운 용적을 현재 용적의 2배로 설정하도록 한다.
        *
        * 또한 기존에 담겨있던 요소들을 새롭개 복사해와야 한다. 이 때 편리하게 쓸 수 있는 것이 Array.copyOf()메소드다.
        * Arrays.copyOf()는 첫 번째 파라미터로 '복사할 배열'을 넣어주고, 두 번째 파라미터는 용적의 크기를 넣어주면 된다.
        * 만약 복사할 배열보다 용적의 크기가 클 경우 먼저 배열을 복사한 뒤, 나머지 빈 공간은 null로 채워지기 때문에 편리하다.
        * */

        //용적의 절반 미만으로 요소가 차지하고 있을 경우
        if (size < (arrayCapacity/2)){
            int newCapacity = arrayCapacity/2;

            //copy
            array = Arrays.copyOf(array,Math.max(newCapacity, defaultCapacity));

            return;
        }
        /*
        *
        * 데이터가 용적에 절반 미만으로 차지 않을 경우다. 이 경우 데이터는 적은데 빈 공간이 메모리를 차지하고 있어, 불필요한 공간을 낭비할 뿐이다.
        * 이럴 때에는 사이즈를 적당하게 줄여주는 것이 좋다.
        * 데이터의 개수가 용적의 절만 미만이라면 용적도 절반으로 줄여주도록 하기 위해 새로운 용적 (newCapacity)을 현재 용적의 절반으로 둔 뒤,
        * Arrays.copyOf() 메서드를 통해서 새로운 용적의 배열을 생성해주도록 하자.
        *
        * 만약 복사할 배열보다 용적의 크기가 작을 경우 새로운 용적 까지만 복사하고 반환하기 때문에 예외 발생에 대해 안전하다.
        *
        * */
    }
}
