package com.moon.java.collectionFramework.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> implements StackInerface<E> {

    private static final int defaultCapacity = 10; //최소 (기본) 용적 크기
    private static final Object[] emptyArray = {}; // 빈 비열

    private Object[] array; // 요소를 담을 배열
    private int size; // 요소 갯수


    /*
     * 생성자 2개를 둔 이유
     * 생성자 1의 경우 사용자가 공간할당을 미리 안하고 객체만 생성하고 싶을때 ,
     * Stack<Integer> stack = new Stack<>();
     * 위와 같은 상태일 때다.
     * 이 때는 사용자가 공간 할당을 명시하지 않았기 때문에 array 변수를 emptyArray로 초기화 시켜 놓는다.
     * 반면에 사용자가 데이터의 개수를 예측할 수 있어서 미리 공간 할당을 해놓고 싶을 경우, 즉 다음과 같이 생성할 경우
     * Stack<Integer> stack = new Stack<>(100);
     * array의 공간 할당을 입력 된 수 만큼 (예제에서는 array =new Object[100]) 배열을 만든다.
     * 그리고 마찬가지로 size를 0으로 초기화 시켜놓는다.
     * (size는 요소(원소)의 개수를 의미하는 것이다. 공간을 할당해놓는 것하고는 다른 개념이다.)
     *
     * */
    //생성자1( 초기 공간 할당x)
    public Stack() {
        this.array = emptyArray;
        this.size = 0;
    }

    // 생성자2(초기 공간 할당 O)
    public Stack(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;

    }

    /*
     * 동적 할당을 위한 resize 메서드 구현
     * 들어오는 데이터에 개수에 따라 "최적화"된 용적을 갖을 필요가 있다.
     * 만약 데이터는 적은데 용적이 크면 메모리 낭비가 되고,
     * 반대로 용적은 적은데 데이터가 많으면 넘치는 데이트들은 보관할 수가 없게 되는 상황을 마주칠 수 있다.
     *
     * 그렇기 때문에 size(요소의개수)가 용적(capacity)에 얼마만큼 차있는지를 확인하고,
     * 적절한 크기에 맞게 배열의 용적을 변경해야 한다.
     * 또한 용적은 외부에서 마음대로 접근하면 데이터의 손상을 야기할 수 있기 때문에 private로 접근을 제한 해준도록한다.
     *
     * 현재 array 의 용적 (=array 길이)과 데이터의 개수 (size)를 비교한다.
     *
     * */
    private void resize() {
        /*
         * 조건문1 : if (Arrays.equals(array,emptyArray))
         * 앞서 생성자에서 사용자가 용적을 별도로 설정하지 않은 경우 emptyArray로 초기화 되어있던 용적이 0인 상태다.
         * 이경우를 고려하여 이제 새로 arrays의 용적을 할당하기 위해 최소 용적으로 설정해두었던 defaultCapacity의 크기만큼 배열을
         * 성성해주고 메서드를 종료한다.
         * 또한 주소가 아닌 값을 비교해야 하기 때문에 Arrays.equals() 메서드를 사용하도록 하자.*/
        //빈 배열일 경우 (capacity is 0)
        if (Arrays.equals(array, emptyArray)) {
            array = new Object[defaultCapacity];
            return;
        }
        int arrayCapacity = array.length; // 현재 용적 크기
        /*
         * 조건2 if(size == arrayCapacity)
         * 데이터가 꽉 찰 경우에는 데이터(요소)를 더 받아오기 위해서 용적을 늘려야 한다.
         * 즉 데이터의 개수가 용적과 같을 경우는 꽉 차 있는 경우를 말한다.
         * 이 때는 새롭게 용적을 늘릴 필요가 있으므로 새로운 용적을 현재 용적의 2배로 설정하도록 한다.
         *
         * 또한 기존에 담겨있던 요소들을 새롭게 복사해와야한다. 이 때 편리하게 쓸 수 있는 것이 Arrays.copyOf() 메서드이다.
         * Arrays.copyOf() 는 첫번째 파라미터로 "복사할 배열'을 넣어주고, 두 번째 파라미터는 용적의 크기를 넣어주면 된다.
         * 만약 복사할 배열보다 용적의 크기가 클 경우 먼저 배열을 복사한 뒤, 나머지 빈 공간은 null로 채워지기 때문에 편리하다.
         *
         * */

        //용적이 가득 찰 경우
        if (size == arrayCapacity) {
            int newSize = arrayCapacity * 2;

            // 배열 복사
            array = Arrays.copyOf(array, newSize);
            return;
        }
        /*
         * 조건문 3: if(size <(arrayCapacity/2))
         * 데이터가 용적에 절만 미만으로 차지않을 경우다.
         * 이 경우는 데이터는 적은데 빈 공간이 메모리르 차지하고 있어 불필요한 공간을 낭비할 뿐이다.
         * 이럴 때에는 사이즈를 적적하게 줄여주는 것이 좋지 않겠는가?
         * 데이터의 개수가 용적의 절반 미만이라면 용적도 절반으로 줄여주도록 하기 위해 새로운 용적(newCapacity)을 현재 용적의 절반으로 둔 뒤
         * Arrays.copyOf()메서드를 통해 새로운 용적의 배열을 생성해주도록 하자.
         *
         * 다만, 우리가 설정한 최서 용적(defaultCapacity)보다 아래로 떨어지지 않도록 하기 위해 새로운 용적과 최소 용적 중 큰 것을 새로운 용적으로 설정하도록 한다.
         *
         * */
        // 용적의 절반 미만으로 요소가 차지하고 있는경우
        if (size < (arrayCapacity / 2)) {
            int newCapacity = (arrayCapacity / 2);

            //배열 복사
            array = Arrays.copyOf(array, Math.max(defaultCapacity, newCapacity));
            return;
        }
    }

    /*
     * push 메서드 구현
     *
     * Stack의 push는 항상 최상단( 맨위) 에 데이터를 추가해야하므로 한 종류 밖에 없다. 리스트로 치면 add(E value)와 같은 역할이다.
     *
     * - 가장 마지막 부분(최상단)에 추가 - push (E item)
     * 물론 현재 자바에 내장되어있는 Stack에서는 Vector를 상속받다보니 중간 삽입 같은 특정 위치의 삽입도 가능하다.
     * push의 경우 아까 위의 그림에서 보았듯 가장 마지막 위치에 데이터를 추가하는 것이다.
     *
     * */
    /*
     * 기본 삽입 : push (E item)
     * 자바 내부에서 push 메서드는 push된 데이터를 리턴하기 때문에 E 타입을 반환하는 메서드로 구현한다.
     * */
    @Override
    public E push(E item) {
        //용적이 꽉 차있다면 용적을 재할당 해준다.
        if (size == array.length) {
            resize();
        }
        array[size] = item;// 마지막 위치에 요소추가.
        size++; //사이즈 1 증가
        return item;
    }

    /*
     * pop메서드
     * push 메서드 메커니즘을 반대로 구현하면 된다.
     * "삭제할 요소가 없는 경우" 즉, 스택이 비어있는 경우다. 이에 대한 예외로 EmptyStackException을 던지도록 한다.
     * @SuppressWarnings("unchecked")를 붙이지 않으면 type safe(타입 안정성)에 대해 경고를 보낸다.
     * 반환되는 것을 보면 E타입으로 캐스팅하고 있고 그 대상이 되는 것은 Obejct[] 배열의 Object 데이터이다.
     * 즉, Object ->E 타입으로 변환을 하는 것인데 이 과정에서 변환할 수 없는 타입을 가능성이 있다는 경고로 메서드 옆에
     * 경고표시가 뜨는데, 우리가 push하여 받아들이는 데이터 타입은 유일하게 E타입만 존재한다.
     * 그렇기 때문에 형 안정성이 보장된다. 한마디로 ClassCastException 이 뜨지 않으니 이 경고들을 무시하겠다는 것이
     * @SuppressWarnings("uncheckd")이다. 그렇다고 남발하면 안되고, 형변환시 예외 가능성이 없을 확실한 경우에
     * 최소한의 범위에서 사용하는 것이 좋다. 그렇지 않으면 중요 경고 메세지를 놓칠 수 있다.
     * */
    @Override
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        E object = (E) array[size - 1];// 삭제될 요소를 반환하기 위한 임시 변수

        array[size - 1] = null; // 요소 삭제
        size--;// 사이즈 1 감소
        resize();// 용적 재할당

        return object;

    }

    /*
     *  peek() 메서드 구현
     * 가장 상단에 있는 데이터를 삭제 하지 않고 확인만 하고 싶을 때가 있다. 그럴 때 쓰는 것이 peek()메서드이다.
     * 한마디로 pop()메서드에서 삭제 과정만 없는 것이 peek() 이다.
     *
     * 또한 마찬가지로 스택에 데이터가 없는 경우를 생각하여 예외를 던진다.
     *
     * 마지막 원소를 반환해야하는지라 Object 타입을 E 타입으로 캐스팅을 해주면서 경고창이 뜬다.
     * 하지만 pop() 메서드에서 설명했듯이 마지막 원소 또한 E type 외에 들어오는 것이 없기 때문에
     * 형 안정성이 확보되므로 경고 표시를 무시하기 위해 @SuppressWarnings("unchecked")를 붙인다.
     *
     * 그리고 마지막 원소의 인덱스는 size 보다 1작다는 점을 주의한다.
     * */
    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        return (E) array[size - 1];
    }

    /*
     * '찾으려는 데이터가 상단의 데이터로부터 얼마만큼 떨어져 있는지'에 대한 상대적 위치 값이다.
     * Top으로부터 떨어져있는 거리를 의미한다. (단, 1부터시작)
     * 수식으로 표현하자면 size - index 값이 되겠다.
     *
     * 다만 일치하는 데이터를 찾이 못했을 경우는 -1을 반환한다.
     * */
    @Override
    public int search(Object value) {
        for (int index = size - 1; index >= 0; index--) {
            //같은 객체를 찾았을 경우 size - index 값을 반환
            if (array[index].equals(value)) {
                return size - index;
            }
        }
        return -1;
    }

    /*
     * size() 메서드
     * size()메서드는 현재 stack에 있는 요소의 개수를 알려준다.
     * */
    @Override
    public int size() {
        return size;
    }

    /*
     * clear()메서드
     *
     * clear()는 단어에서 짐작 할 수 있듯 모든 요소들을 비워버리는 작업이다.
     * 예로 들어 리스트에 요소를 담아두었다가 초기화가 필요할 때 쓸 수 있는 유용한 존재다.
     * 또한 모든 요소를 비워버린다는 것은 요소가 0 개라는 말로 size또한 0으로 초기화해주고,
     * 배열의 용적 또한 현재 용적의 절반으로 줄일 수 있도록 해준다.
     *
     * 왜 초기 값이아니라 절반인가하면,
     * 초기값으로 초기화 해주어도 되나 생각해보면 현재 배열의 용적은 결국 데이터를 해당 용적에 만족하는 조건만큼 썼다는 의미가 된다.
     *
     * 예로 들어 데이터가 1500개 였다고 가정해보자,
     * 그럼 용적량은 10부터 2씩 곱해지므로 2560이었을 것이다.
     * 요소들을 모두 초기화 했더라도 앞으로 들어올 데이터들 또한 데이터가 1500개일 가능성이 높다.
     * 즉, 현재 용적량의 기대치에 근접할 가능성이 높기 때문에 일단은 용적량을 절반으로만 줄이는 것이다.
     * (또한 그만큼 데이터를 쓰지 않더라도 삭제 과정에서 용적량을 줄이기 때문에 크게 문제되진 않는다.)
     *
     * */
    @Override
    public void clear() {
        // 저장되어 있던 모든 요소를 null처리한다.
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();

    }

    /*
     * empty() 메서드
     *
     * empty()메서드는 단어 그대로 Stack이 비어있는지, 즉 요소가 한 개도 남아 있지 않은지를 true 또는 false로 반환해주는 메서드이다.
     * 만약 요소가 하나도 없다면 true, 하나라도 존재한다면 true가 된다.
     * 그렇다고 모든 요소들을 하나씩 검사해줄 필요는 없다. size 변수가 0이면 데이터가 없다는 뜻이므로 size 값에 따라 반화만 다르게 해주면된다.
     * */
    @Override
    public boolean empty() {
            return size ==0;
    }
}
