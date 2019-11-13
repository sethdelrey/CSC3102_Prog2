package trie;

//You are given a dictionary of english words. Create a Trie, for storing these
//        strings. To create the Trie, you can use repeated insertions. Compare two implementations
//        of the Trie one where all the children of a given node are in a linked list vs use of hashing
//        to jump to the correct child. In hash-based implementation, you must use the tuple pair
//        (originating node id, next character ) as a hashing key. In hash-table, you have to store a
//        triplet (originating node id, next character, child node). For node-id, you can give every
//        node a pre-order id after running pre-order on the constructed trie. When you compare the
//        performance, you’ll use 1000 word queries.


public class TrieHash {
    public static HashEntryNode[] hashTable;

    private static class HashEntryNode {
        HashEntryNode parent;
        HashEntryNode child;
        char c;
        HashEntryNode next;

        public HashEntryNode findChild(char c) {
            return null;
        }
    }

    private HashEntryNode root;

    public TrieHash(int size) {
        hashTable = new HashEntryNode[size];
    }

    public void insert(String newWord) {
        char letter = newWord.charAt(0);
        HashEntryNode node = root.findChild(letter);


    }
}
