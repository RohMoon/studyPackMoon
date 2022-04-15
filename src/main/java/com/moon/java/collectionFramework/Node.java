package com.moon.java.collectionFramework;

public class Node<E> {

    E data;
    Node<E> next;// 다음 노드 객체를 가리키는 레퍼런스 변수

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}
/*
* next가 앞서 그림에서 보여주었던 Reference 변수.
* 다음 노드를 가리키는 변수이며, '노드 자체'를 가리키기 때문에 타입 또한 Node<E> 타입으로 지정해주어야 한다.
*
* 그리고 위 노드를 이용하기 위한 단일 연결리스트(Singly LinkedList)를 구현하면 된다.
* */

