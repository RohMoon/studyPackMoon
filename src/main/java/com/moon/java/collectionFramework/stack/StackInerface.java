package com.moon.java.collectionFramework.stack;

public interface StackInerface<E> {
    /*
    * 스택의 맥 위에 요소를 추가한다.
    *
    * @Param item 스택에 추가할 요소
    * @return 스택에 추가된 요소
    *
    * */
    E push(E item);

    /*
    *
    * 스택의 맨 위에 있는 요소를 제거하고 제거 된 요소를 반환한다.
    *
    * @return 제거 된 요소
    *
    * */
    E pop();

    /*
    * 스택의 맨 위에 있는 요소를 제거하지 않고 반환한다.
    * */
    E peek();
    /*
    * 스택의 상반 부터 특정 요소가 몇 번째 위치에 있는지를 반환한다.
    * 중복되는 원소가 있을 경우 가장 위에 있는 요소의 위치가 반환된다.
    *
    * @param value 스택에서 위치를 찾을 요소
    * @return 스택의 상단부터 처음으로 요소와 일치하는 위치를 반환.
    * 만약 일치하는 요소가 없을 경우 -1 반환.
    * */

    /*
    *               _______
    *               |a    |
    *    idx 3      |_____|   search("w")
    *               |e    |   --> 상단(idx 3)으로 부터 3번 째에 위치
    *    idx 2      |_____|       == return 되는 값 : 3
    *               |w    |
    *    idx 1      |_____|
    *               |k    |
    *    idx 0      |_____|
    *
    *
    * */
    int search(Object value);
    /*
    * 스택의 요소 개수를 반환한다.
    * 
    * @return 스택에 있는 요소 개수를 반환
    * */
    int size();

    /*
    * 스택에 있는 모든 요소를 삭제한다.
    * */
    void clear();

    /*
    * 스택에 요소가 비어있는지를 반환한다.
    * 
    * @return 스택에 요소가 없을 경우 {@code true}, 그 외의 경우 {@code false}를 반환
    * */
    boolean empty();
}
