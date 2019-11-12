package huffman;

public class Pair implements Comparable<Pair> {

    private int freq;
    private char c;

    public Pair(int f, char x){
        freq = f;
        c = x;
    }

    public int getFreq(Pair obj){
        return obj.freq;
    }

    public char getChar(Pair obj){
        return obj.c;
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
