package com.moon.java.collectionFramework.doubleLinkedList;

public class Node<E> {
    E data;
    Node<E> next; // 다음 노드객체를 가리키는 레퍼런스 변수
    Node<E> prev; // 이전 노드객체를 가리키는 레퍼런스 변수

    Node(E data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
/*
* Next는 다음 노드를 가리키는 변수이며, `노드 자체`를 가리키기 때문에 타입 또한 Node<E> 타입으로 지정해주어야 한다.
* 마찬가지로 이전 노드를 가리키는 변수 prev도 똑같이 만들어주면 된다.
*
* 그리고 위 노드를 이용하기 위한 양방향 연결 리스트(Doubly LinkedList)를 구현한다.
*/
