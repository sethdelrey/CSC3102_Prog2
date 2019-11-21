package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

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

//class MyComparator implements Comparator<HNode> {
//    public int compare(HNode x, HNode y) {
//        return x.data - y.data;
//    }
//}

public class Huffman {


//    public static void printCode(HashMap<Character, String> codeMap, char[] strArray) throws FileNotFoundException {
//            PrintWriter out = new PrintWriter(new File ("huffmanoutput.txt"));
//            for (int i = 0; i<strArray.length; i++) {
//                out.print(codeMap.get(strArray[i]));
//            }
//
//    }


//    public static void encodeR(Pair root, String s, HashMap<Character, String> codeMap, char[] strArray) throws FileNotFoundException {
//
//        if (root.left == null && root.right == null) {
////            System.out.println(root.c + ":" + s);
//            codeMap.put(root.c, s);
//            return;
//        }
//
//        encodeR(root.left, s+"0", codeMap, strArray);
//        encodeR(root.right, s+"1", codeMap, strArray);
//
//        printCode(codeMap, strArray);
//
//    }

    public static void main(String[] args) {
        String str = "";
        try {
            Scanner s = new Scanner(new File("huffmaninput"));
            while (s.hasNextLine()) {
                str += s.nextLine();
            }


            str = str.replaceAll("[^a-zA-Z0-9_-]", " ");
            str = str.toLowerCase();
            char[] strArray = str.toCharArray();

            CharFrequency fq = new CharFrequency();
            fq.charCount(str);
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
            HuffmanPrint p = new HuffmanPrint(strArray);
            HashMap<Character, String> codeMap = new HashMap<Character, String>();
            p.encode(root, "");
            p.printCode();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

