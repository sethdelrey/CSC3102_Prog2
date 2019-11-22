package trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TrieDriverClass {
    public static void main(String[] args) {
        // LINKEDLIST TRIE MAIN METHOD
        long startTime = System.nanoTime();
        LinkedListTrie linkedListTrie = new LinkedListTrie();
        try (Scanner fin = new Scanner(new File("WORD.LST"))) {
            while (fin.hasNext()) {
                linkedListTrie.insert(fin.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner cin = new Scanner(new File("lookup.txt")); PrintWriter writer = new PrintWriter("outputLinkedListTrie.txt")) {
            while (cin.hasNext()) {
                linkedListTrie.lookup(cin.next(),writer);
            }
        }
        catch (Exception e) {
            System.out.print(e.getStackTrace());
        }

        long endTime = System.nanoTime();
        long timeLinkedList = endTime - startTime;
        timeLinkedList = timeLinkedList / 1000;
        System.out.println("The time for the linked list trie was " + timeLinkedList + " ms");


        // HASH TRIE MAIN METHOD
        int size = 0;
        try (Scanner fin = new Scanner(new File("WORD.LST"))) {
            while (fin.hasNext()) {
                size++;
                fin.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        startTime = System.nanoTime();
        HashTrie trie = new HashTrie(size);

        try (Scanner fin = new Scanner(new File("WORD.LST"))) {
            while (fin.hasNext()) {
                trie.insert(fin.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner cin = new Scanner(new File("lookup.txt")); PrintWriter writer = new PrintWriter("outputHashTrie.txt")) {
            while (cin.hasNext()) {
                trie.lookup(cin.next(),writer);
            }
        }
        catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
        endTime = System.nanoTime();
        long timeHashTrie = endTime - startTime;
        timeHashTrie = timeHashTrie / 1000;
        System.out.println("The time for the hash trie was " + timeHashTrie + " ms");
    }
}
