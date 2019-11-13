package trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrieDriverClass {
    // LINKEDLIST TRIE MAIN METHOD
//    public static void main(String args[]) {
//        LinkedListTrie linkedListTrie = new LinkedListTrie();
//        try (Scanner fin = new Scanner(new File("input.txt"))) {
//            while (fin.hasNext()) {
//                linkedListTrie.insert(fin.next());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        linkedListTrie.lookup("a");
//    }
    // HASH TRIE MAIN METHOD
    public static void main(String[] args) {
        int size = 0;
        try (Scanner fin = new Scanner(new File("input.txt"))) {
            while (fin.hasNext()) {
                size++;
                fin.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashTrie trie = new HashTrie(size);

        try (Scanner fin = new Scanner(new File("input.txt"))) {
            while (fin.hasNext()) {
                trie.insert(fin.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
