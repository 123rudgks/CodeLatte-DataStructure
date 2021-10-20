import list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.add(0, "B");
        System.out.println(list);

        list.add(1, "A");
        System.out.println(list);

        list.add(0, "E");
        System.out.println(list);

        list.addLast( "D");
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        list.remove(2);
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        System.out.printf("노드의 개수:%d\n", list.size());

    }
}
