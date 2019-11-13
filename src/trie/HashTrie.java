package trie;

//        You are given a dictionary of english words. Create a Trie, for storing these
//        strings. To create the Trie, you can use repeated insertions. Compare two implementations
//        of the Trie one where all the children of a given node are in a linked list vs use of hashing
//        to jump to the correct child.
//
//        In hash-based implementation, you must use the tuple pair
//        (originating node id, next character ) as a hashing key. In hash-table, you have to store a
//        triplet (originating node id, next character, child node). For node-id, you can give every
//        node a pre-order id after running pre-order on the constructed trie. When you compare the
//        performance, you’ll use 1000 word queries.
//
//        Now the Hashing part, speed up FindChild operation by using a HashTable,
//        Use a chaining based global Hashtable. Use the number of lines N in WORD.LST as the size of Hashtable[N].
//
//        HashEntryNode will have following fields:
//        {Node *parent,
//        char c
//        Node *child
//        HashEntryNode *next
//        }
//
//        The Hashtable is an array of HashEntryNode* Hashtable[N] .
//        Hash function will take two values as an input Node *parent and char c and calculate hash value between 0.. N-1.


public class HashTrie {
    public static HashEntryNode[] hashTable;

    private static class HashEntryNode {
        Node parent;
        Node child;
        HashEntryNode next;
        char c;

        public HashEntryNode() {
            // Default Constructor
        }

        public HashEntryNode(char _c) {
            c = _c;
        }

        public HashEntryNode findChild(char c) {
            if (child == null) {
                return null;
            }

            int hashCode = hashCode(c);
            HashEntryNode current = hashTable[hashCode];
            while (current != null) {
                if (current.c == c) {
                    break;
                }
                current = current.next;
            }
            return current;
        }

        public int hashCode(char _c) {
            char parentC = (this != null) ? this.c : _c;
            int hash = _c * 5 + parentC * 7;
            hash = hash % hashTable.length;
            return hash;
        }
    }

    private class Node {
        char lead;
        String label;
        boolean isWord;

        Node rightSibling;
        Node firstChild;

        int nodeId;

        public Node() {
            // default constructor
        }

        public Node(char _lead, String _label, boolean _isWord) {
            lead = _lead;
            label = _label;
            isWord = _isWord;
        }

        public int hashCode(Node parent, char _c) {
            char parentC = (parent != null) ? parent.lead : _c;
            int hash = _c * 5 + parentC * 7;
            hash = hash % hashTable.length;
            return hash;
        }


    }

    private HashEntryNode root;

    public HashTrie(int size) {
        hashTable = new HashEntryNode[size];
    }

    public void insert(String newWord) {
        char letter = newWord.charAt(0);
        HashEntryNode node = root.findChild(letter);
        HashEntryNode newNode = new HashEntryNode(letter);

        if (node == null) {
            hashTable[newNode.hashCode(letter)] = newNode;
        }
        else {
            
        }


    }
}
