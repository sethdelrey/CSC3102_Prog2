package trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrieDriverClass {
    public static void main(String args[]) {
        Trie trie = new Trie();
        try (Scanner fin = new Scanner(new File("input.txt"))) {
            while (fin.hasNext()) {
                trie.insert(fin.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        trie.display();

    }
}
