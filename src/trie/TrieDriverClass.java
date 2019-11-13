package trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrieDriverClass {
    public static void main(String args[]) {
        LinkedListTrie linkedListTrie = new LinkedListTrie();
        try (Scanner fin = new Scanner(new File("input.txt"))) {
            while (fin.hasNext()) {
                linkedListTrie.insert(fin.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        linkedListTrie.lookup("a");

    }
}
