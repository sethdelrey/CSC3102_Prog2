package trie;

public class CharLinkedList {
    public static class Node {
        private Node next;

        private Object data;

        public Node(Object _data) {
            data = _data;
        }

        public void add(Object _obj) {
            Node temp = next;
            next = new Node(_obj);
            next.next = temp;
        }

        public Object getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
    
    public Node first;

    public CharLinkedList() {
        first = null;
    }

    public void addFirst(Object _obj) {
        Node temp = first;
        first = new Node(_obj);
        first.next = temp;
    }
}
