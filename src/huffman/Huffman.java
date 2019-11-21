package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

//make whitespace its own character; can do "if not LetterOrDigit, add 1 to whitespace"

class Pair implements Comparable<Pair> {

    int freq;
    char c;
    Pair right; /*figure out left and right children!!*/
    Pair left;

//    public Pair(int f, char x){
//        freq = f;
//        c = x;
//    }

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

//class MyComparator implements Comparator<HNode> {
//    public int compare(HNode x, HNode y) {
//        return x.data - y.data;
//    }
//}

public class Huffman {

    public static void printCode(Pair root, String s) {

        if (root.left == null && root.right == null) {
            System.out.println(root.c + ":" + s);
            return;
        }

        printCode(root.left, s+"0");
        printCode(root.right, s+"1");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = "";
        try {
            str = new String (Files.readAllBytes(Paths.get("huffmaninput.txt")));
            CharFrequency fq = new CharFrequency();
            fq.charCount(str);

            str = str.replaceAll("[^a-zA-Z0-9_-]", " ");
            char[] strArray = str.toCharArray();

            char[] charArray = fq.getCharArray();
            int[] charFreq = fq.getFreqArray();
            int n = charArray.length;

            MinHeap q = new MinHeap(n, 2);

            for (int i = 0; i < n; i++) {
                Pair hn = new Pair();
                hn.c = charArray[i];
                hn.freq = charFreq[i];

                hn.left = null;
                hn.right = null;

                q.insert(hn);
            }

            Pair root = null;

            //extract 2 min nodes from the heap and make a parent node that
            // stores their added frequencies and points to the 2 char nodes
            while (q.getSize() > 1) {
                Pair x = q.extractMin();

                Pair y = q.extractMin();

                Pair f = new Pair();

                f.freq = x.freq + y.freq;
                f.c = '-';

                f.left = x;
                f.right = y;
                root = f;
                q.insert(f);
            }
            printCode(strArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

