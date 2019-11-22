/*
Raquel Hodgeson (rhodg14@lsu.edu, 89-399-5177)
Seth Richard (sric111@lsu.edu, 89-053-2395)
Programming Project 2
CSC 3102 - Dr. Shah
11/23/19
 */
package huffman;

import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Scanner;

//pair class used in place of nodes to hold characters and their frequencies
class Pair implements Comparable<Pair> {

    int freq;
    char c;
    Pair right;
    Pair left;

    public int getFreq(){
        return this.freq;
    }

    public char getChar(){
        return this.c;
    }

    public int compareTo(Pair other) {
        //returns negative if object is LESS than other
        int retVal = 0;
        if (freq < other.freq)
            retVal = -1;
        else if (freq > other.freq)
            retVal = 1;
        return retVal;
    }
}

public class Huffman {

    //reads file, finds frequency of each character, prints huffman code and decodes
    public static void main(String[] args) {
        String str = "";
        try {
            CharFrequency fq = new CharFrequency();
//            fq.charCount(str);
            File f = new File("huffmaninput.txt");
            fq.charCount(f);

            char[] charArray = fq.getCharArray();
            int[] charFreq = fq.getFreqArray();
            int n = charArray.length;

            System.out.println("Freq Done");

            MinHeap q = new MinHeap(n, 2);

            // creates Pairs and adds them to MinHeap
            for (int i = 0; i < n; i++) {
                Pair hn = new Pair();
                hn.c = charArray[i];
                hn.freq = charFreq[i];

                hn.left = null;
                hn.right = null;

                q.insert(hn);
            }

            System.out.println("Create Pairs Done");

            Pair root = null;

            //extract 2 min nodes from the heap and make a parent node that
            // stores their added frequencies and points to the 2 char nodes
            while (q.getSize() > 1) {
                Pair x = q.extractMin();

                Pair y = q.extractMin();

                Pair t = new Pair();

                t.freq = x.freq + y.freq;
                t.c = '-';

                t.left = x;
                t.right = y;
                root = t;
                q.insert(t);
            }

            System.out.println("Heap Done");

            HuffmanPrint p = new HuffmanPrint(f);
            HashMap<Character, String> codeMap = new HashMap<Character, String>();

            //encodes file and prints it to file "huffmanoutput.txt"
            p.encode(root, "");


            p.printCode();
            System.out.println("Encoded output done");

            //prints decoded file to file "decodedhuffman.txt"
            decode(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //decodes huffman code and prints it to new file
    public static void decode(Pair root) {
        try {
            FileWriter out = new FileWriter("decodedhuffman.txt");
            Pair curr = root;
            char x;
            BufferedReader br = new BufferedReader(new FileReader(new File("huffmanoutput.txt")));
            String line;
            Scanner fin;
            while ((line = br.readLine()) != null) {
                fin = new Scanner(line);
                fin.useDelimiter("");
                while (fin.hasNext()) {
                    x = fin.next().charAt(0);
                    if (x == '0')
                        curr = curr.left;
                    else
                        curr = curr.right;

                    //reached leaf
                    if (curr.left == null && curr.right == null) {
                        out.write(curr.c);
                        curr = root;
                    }
                }
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

