package trie;

public class Trie {
    TrieNode root;

    private class TrieNode {
        CharLinkedList children;
        char letter;

        TrieNode(char _letter) {
            letter = _letter;
            children = new CharLinkedList();
        }
    }

    public Trie() {
        root = new TrieNode(' ');
    }

    public void insert(String word) {
        word = word.toLowerCase();
        int len = word.length();
        TrieNode current = root;

        for (int index = 0; index < len; index++) {
            char letter = word.charAt(index);
            if (current.children.first == null) {
                current.children.first = new CharLinkedList.Node(new TrieNode(letter));
                current = (TrieNode) current.children.first.getData();
                continue;
            }
            CharLinkedList.Node currentNode = current.children.first;
            while (currentNode.hasNext() && ((TrieNode) currentNode.getData()).letter < letter) {
                currentNode = currentNode.getNext();
            }
            if (((TrieNode) currentNode.getData()).letter < letter) {
                currentNode.add(new TrieNode(letter));
                current = ((TrieNode) currentNode.getNext().getData());
            } else if (((TrieNode) currentNode.getData()).letter == letter) {
                current = (TrieNode) currentNode.getData();
            } else {
                CharLinkedList.Node temp = current.children.first;
                while (temp.hasNext() && ((TrieNode) temp.getNext().getData()).letter < ((TrieNode) currentNode.getData()).letter) {
                    temp = temp.getNext();
                }
                // CurrentNode is first node
                if (currentNode == current.children.first) {
                    current.children.addFirst(new TrieNode(letter));
                    current = (TrieNode) current.children.first.getData();
                }
                else {
                    temp.add(new TrieNode(letter));
                    current = (TrieNode) temp.getNext().getData();
                }
            }
        }
    }
}
