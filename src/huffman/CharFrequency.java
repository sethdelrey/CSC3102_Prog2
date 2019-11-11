package huffman;
import java.util.HashMap;
import java.util.Map;

/**
 * to do:
 * make pairs for each frequency and character in MinHeap so MinHeap is by frequency w/ characters connected
 * figure out messy main method here vvv
 * finish huffman algorithm in HuffmanEncoding class: from Shah ppt 13 last 2 slides
 */

public class CharFrequency {

    HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();


    public void charCount(String inputString) {

        inputString = inputString.replaceAll("[^a-zA-Z0-9_-]", "").toLowerCase();
        char[] strArray = inputString.toCharArray();

        for(char c: strArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }

        for (Map.Entry entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public int getFrequency(char entry) {
        for (Map.Entry<Character, Integer> c : charCountMap.entrySet()) {
            if (entry == c.getKey())
                return c.getValue();
        }
        return 0;
    }


    public static void main(String[] args) {
        String str = "Ajit hey. tAAheare";
        charCount(str);
    }
}
