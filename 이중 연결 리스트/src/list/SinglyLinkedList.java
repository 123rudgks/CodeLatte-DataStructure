package list;

public class SinglyLinkedList<T> {

    Node<T> head = null;
    Node<T> tail = null;
    int size = 0;


    // 특정 인덱스에 있는 Node를 찾아서 그 Node를 리턴
    // list가 비었는지 아닌지는 이후 add, remove에서 체크를 해주기 때문에 여기서는 list가 비어있지 않다고 가정함
    private Node<T> findNode(int searchIndex) {
        // 찾으려는 인덱스가 0보다 작거나, 현재 리스트의 사이즈보다 클 경우
        if (searchIndex < 0 || searchIndex >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> pointer;
        int nodeIndex;
        // searchIndex가 head와 더 가까울 때
        if (size / 2 > searchIndex) {
            // pointer는 head에서 시작, 인덱스는 0
            pointer = head;
            nodeIndex = 0;
            while (searchIndex != nodeIndex) {
                ++nodeIndex;
                pointer = pointer.nextNode;
            }
        } else { // searchIndex가 tail과 더 가까울 때
            // pointer는 tail에서 시작, 인덱스는 size-1
            pointer = tail;
            nodeIndex = size - 1;
            while (searchIndex != nodeIndex) {
                --nodeIndex;
                pointer = pointer.prevNode;
            }
        }

        return pointer;
    }

    public T getData(int searchIndex) {
        Node<T> searchNode = findNode(searchIndex);
        return searchNode.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // 리스트에 원소 추가하는 메소드
    // 리스트 범위밖의 인덱스를 추가하는지 체크는 findNode메소드를 이용해서 함으로 
    // 이 메소드에서는 올바른 인덱스를 받는다고 가정
    public void add(int index, T newData) {
        Node<T> newNode = new Node<>();
        newNode.data = newData;

        // 리스트에 아직 아무것도 없을 때
        if (this.head == null && this.tail == null) {
            if (index == 0) {
                this.head = newNode;
                this.tail = newNode;
                size++;
            } else {
                System.out.println("현재 리스트에 원소가 없습니다. 0번부터 채워주세요");
            }
            return;
        }

        // 리스트 맨 앞에 추가할 때
        if (index == 0) {
            newNode.nextNode = this.head;
            this.head.prevNode = newNode;
            this.head = newNode;
        } else if (index == size) { // 리스트 맨 뒤에 추가할 때
            newNode.prevNode = this.tail;
            this.tail.nextNode = newNode;
            this.tail = newNode;
        } else { // 리스트 중간에 삽입할 때
            Node rightNode = findNode(index); // 삽입하는 노드의 뒤로 갈 노드
            Node leftNode = rightNode.prevNode; // 삽입하는 노드의 앞에 갈 노드

            newNode.nextNode = rightNode;
            rightNode.prevNode = newNode;
            newNode.prevNode = leftNode;
            leftNode.nextNode = newNode;
        }
        ++size;
    }

    public void addFirst(T newData) {
        add(0, newData);
    }

    public void addLast(T newData) {
        add(size, newData);
    }

    public void remove(int index) {

        // 리스트에 아무것도 없을 때
        if (this.head == null && this.tail == null) {
            System.out.println("리스트가 비어있어 삭제할 원소가 없습니다.");
            return;
        }

        Node<T> willDeletedNode = findNode(index);
        // 삭제할 노드의 으론쪽, 왼쪽 노드
        Node<T> rightNode = willDeletedNode.nextNode;
        Node<T> leftNode = willDeletedNode.prevNode;

        // 삭제할 노드의 왼쪽 노드가 존재할 시
        if(leftNode != null){
            leftNode.nextNode = rightNode;
            willDeletedNode.prevNode = null;
        }
        // 삭제할 노드의 오른쪽 노드가 존재할 시
        if(rightNode != null){
            rightNode.prevNode = leftNode;
            willDeletedNode.nextNode = null;
        }
        // 맨 앞의 원소 삭제
        if (index == 0) {
            // 이미 위에서 삭제할 노드의 오른쪽 노드를 삭제한 뒤이기 때문에
            // head만 연결을 rightNode로 변경해준다.
            this.head = rightNode;
        }
        // 맨 뒤의 원소 삭제
        if(index == size -1){
            // 이미 위에서 삭제할 노드의 왼쪽 노드를 삭제한 뒤이기 때문에
            // tail만 연결을 leftNode로 변경해준다.
            this.tail = leftNode;
        }

        --size;
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> pointer = this.head;
        stringBuilder.append("head").append(" -> ");
        while (null != pointer) {
            stringBuilder.append(pointer.data).append(" -> ");
            pointer = pointer.nextNode;
        }
        stringBuilder.append("null ");
        if (null != this.tail) {
            stringBuilder.append(", tail(").append(this.tail.data).append("), ");
        }
        pointer = this.tail;
        stringBuilder.append("tail").append(" -> ");
        while (null != pointer) {
            stringBuilder.append(pointer.data).append(" -> ");
            pointer = pointer.prevNode;
        }
        stringBuilder.append("null");
        if (null != this.head) {
            stringBuilder.append(", head(").append(this.head.data).append(")");
        }
        return stringBuilder.toString();
    }


}
