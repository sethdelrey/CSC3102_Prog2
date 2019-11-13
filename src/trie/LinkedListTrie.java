package trie;

public class LinkedListTrie {
    private class TrieNode {
        char lead;
        String label;
        boolean isWord;

        TrieNode rightSibling;
        TrieNode firstChild;

        public TrieNode(char lead, String label, boolean isWord) {
            this.lead = lead;
            this.label = label;
            this.isWord = isWord;
        }

        public TrieNode findChild(char c) {
            if (firstChild == null) {
                return null;
            }
            else {
                TrieNode iterator = firstChild;
                while (iterator.rightSibling != null && iterator.rightSibling.lead <= c) {
                    iterator = iterator.rightSibling;
                }

                return iterator;
            }
        }

        public void insert(TrieNode newNode) {
            TrieNode temp = findChild(newNode.lead);

            if (temp == null) {
                firstChild = newNode;
            }
            else if (temp.lead < newNode.lead) {
                newNode.rightSibling = temp.rightSibling;
                temp.rightSibling = newNode;
            }
            else if (temp.lead == newNode.lead) {
                char[] wordAsArray = newNode.label.toCharArray();
                char[] labelAsArray = temp.label.toCharArray();
                int i = 0;
                if (wordAsArray.length >= labelAsArray.length) {
                    for (i = 0; i < labelAsArray.length; i++) {
                        if (wordAsArray[i] != labelAsArray[i]) {
                            break;
                        }
                    }
                }
                else {
                    for (i = 0; i < wordAsArray.length; i++) {
                        if (wordAsArray[i] != labelAsArray[i]) {
                            break;
                        }
                    }
                }

                // Split label at i and form two nodes.
                if (i == labelAsArray.length && labelAsArray.length == wordAsArray.length) {
                }
                else {
                    String newOriginalLabel = temp.label.substring(0,i);
                    String newLabel1 = temp.label.substring(i);
                    String newLabel2 = newNode.label.substring(i);
                    temp.label = newOriginalLabel;
                    if (newLabel1.length() != 0 && newLabel2.length() != 0) {
                        char newLead1 = newLabel1.charAt(0);
                        char newLead2 = newLabel2.charAt(0);
                        newLabel1 = (newLabel1.length() == 1) ? "" : newLabel1.substring(1);
                        newLabel2 = (newLabel2.length() == 1) ? "" : newLabel2.substring(1);
                        TrieNode nodeForOriginalNode = new TrieNode(newLead1, newLabel1, temp.isWord);
                        newNode.lead = newLead2;
                        newNode.label = newLabel2;
                        temp.isWord = false;
                        if (nodeForOriginalNode.lead < newNode.lead) {
                            nodeForOriginalNode.firstChild = temp.firstChild;
                            temp.firstChild = nodeForOriginalNode;
                            nodeForOriginalNode.rightSibling = newNode;
                        }
                        else {
                            newNode.firstChild = temp.firstChild;
                            temp.firstChild = newNode;
                            newNode.rightSibling = nodeForOriginalNode;
                        }
                    }
                    else if (newLabel1.length() != 0) {
                        TrieNode nodeForOriginalNode = new TrieNode(newLabel1.charAt(0), newLabel1, true);
                        temp.firstChild = nodeForOriginalNode;
                    }
                    else {
                        newNode.lead = newLabel2.charAt(0);
                        newNode.label = (newLabel2.length() == 1) ? "" : newLabel2.substring(1);
                        temp.insert(newNode);
                    }
                }
            }
            else if (temp == firstChild) {
                newNode.rightSibling = firstChild;
                firstChild = newNode;
            }
        }

        public void display(String str) {
            str += lead + label;
            if (isWord) {
                System.out.println(str);
            }
            if (firstChild != null) {
                firstChild.display(str);
            }
            if (rightSibling != null) {
                str = str.substring(0,str.length()-(1+label.length()));
                rightSibling.display(str);
            }
        }
    }

    private TrieNode root;
    private int size;

    public LinkedListTrie() {
        root = new TrieNode(' ', null, false);
    }

    public void insert(String newWord) {
        char lead  = newWord.charAt(0);
        newWord = (newWord.length() == 1) ? "" : newWord.substring(1);
        root.insert(new TrieNode(lead, newWord, true));
        size++;
    }

    public void lookup(String prefix) {
        if (prefix != null && !prefix.isEmpty()) {
            char[] prefixAsArray = prefix.toCharArray();
            TrieNode current = root.findChild(prefixAsArray[0]);
            TrieNode parent = root;
            int i = 0;
            while (i < prefix.length() && current != null) {
                if (current.lead == prefixAsArray[i]) {
                    parent = current;
                    current = current.firstChild;
                    i++;
                } else {
                    current = current.rightSibling;
                }
            }

            current.display(prefix);
        }
    }
}
