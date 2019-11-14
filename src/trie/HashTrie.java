package trie;

//        You are given a dictionary of english words. Create a Trie, for storing these
//        strings. To create the Trie, you can use repeated insertions. Compare two implementations
//        of the Trie one where all the children of a given node are in a linked list vs use of hashing
//        to jump to the correct child.
//
//        In hash-based implementation, you must use the tuple pair
//        (originating node id, next character ) as a hashing key. In hash-table, you have to store a
//        triplet (originating node id, next character, child node). For node-id, you can give every
//        node a pre-order id after running pre-order on the constructed trie. When you compare the
//        performance, you’ll use 1000 word queries.
//
//        Now the Hashing part, speed up FindChild operation by using a HashTable,
//        Use a chaining based global Hashtable. Use the number of lines N in WORD.LST as the size of Hashtable[N].
//
//        HashEntryNode will have following fields:
//        {
//              Node *parent,
//              char c
//              Node *child
//              HashEntryNode *next
//        }

//        The Hashtable is an array of HashEntryNode* Hashtable[N] .
//        Hash function will take two values as an input Node *parent and char c and calculate hash value between 0.. N-1.

public class HashTrie {
    public HashEntryNode[] hashTable;
    public static int idCounter;

    private static class HashEntryNode {
        Node parent;
        Node child;
        HashEntryNode next;
        char c;

        public HashEntryNode() {
            // Default Constructor
        }

        public HashEntryNode(char _c) {
            c = _c;
        }

//        public HashEntryNode findChild(char c) {
//            if (child == null) {
//                return null;
//            }
//
//            int hashCode = hashCode(c);
//            HashEntryNode current = hashTable[hashCode];
//            while (current != null) {
//                if (current.c == c) {
//                    break;
//                }
//                current = current.next;
//            }
//            return current;
//        }

//        public int hashCode(char _c) {
//            char parentC = (this != null) ? this.c : _c;
//            int hash = _c * 5 + parentC * 7;
//            hash = hash % hashTable.length;
//            return hash;
//        }
    }

    private class Node {
        char lead;
        String label;
        boolean isWord;

        Node rightSibling;
        Node firstChild;

        int nodeId;

        public Node(char lead, String label, boolean isWord) {
            this.lead = lead;
            this.label = label;
            this.isWord = isWord;
        }

        public Node findChild(char c) {
            if (firstChild == null) {
                return null;
            }

            int hashCode = hashCode(this, c);
            HashEntryNode current = hashTable[hashCode];
            while (current != null) {
                if (current.c == c) {
                    break;
                }
                current = current.next;
            }
            return (current != null) ? current.child : null;
        }

        public int hashCode(Node parent, char _c) {
            int parentC = (parent != null) ? parent.nodeId : 0;
            int hash = _c * 5 + parentC * 7;
            hash = hash % hashTable.length;
            return hash;
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

        public void insert(Node newNode) {
            Node temp = findChild(newNode.lead);

            if (temp == null) {
                if (firstChild != null) {
                    Node currentIterator = firstChild;
                    while (currentIterator != null) {
                        if (currentIterator.rightSibling != null && currentIterator.rightSibling.lead < newNode.lead) {
                            currentIterator = currentIterator.rightSibling;
                        }
                        else {
                            break;
                        }

                    }

                    if (currentIterator.lead < newNode.lead) {
                        newNode.rightSibling = currentIterator.rightSibling;
                        currentIterator.rightSibling = newNode;
                    }
                    else {
                        newNode.rightSibling = currentIterator.rightSibling;
                        currentIterator = newNode;
                    }
                }
                else {
                    firstChild = newNode;
                }

                insertIntoHashTable(this,newNode,newNode.lead);
            }
            else if (temp.lead < newNode.lead) {
                newNode.rightSibling = temp.rightSibling;
                temp.rightSibling = newNode;
                insertIntoHashTable(this,newNode,newNode.lead);
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
                        Node nodeForOriginalNode = new Node(newLead1, newLabel1, temp.isWord);
//                        nodeForOriginalNode.nodeId = idCounter;
//                        idCounter++;
                        newNode.lead = newLead2;
                        newNode.label = newLabel2;
                        temp.isWord = false;
                        nodeForOriginalNode.firstChild = temp.firstChild;
                        Node current = nodeForOriginalNode.firstChild;
                        while (current != null) {
                            HashEntryNode deleteNode = hashTable[current.hashCode(temp, current.lead)];
                            HashEntryNode prev = null;
                            if (deleteNode != null && deleteNode.c == current.lead) {
                                hashTable[current.hashCode(temp, current.lead)] = deleteNode.next;
                                insertIntoHashTable(nodeForOriginalNode, current, current.lead);
                                current = current.rightSibling;
                                continue;
                            }

                            while (deleteNode != null && deleteNode.c != current.lead) {
                               prev = deleteNode;
                               deleteNode = deleteNode.next;
                            }

                            if (deleteNode != null) {
                                prev.next = deleteNode.next;
                            }

                            insertIntoHashTable(nodeForOriginalNode, current, current.lead);
                            current = current.rightSibling;
                        }

                        if (nodeForOriginalNode.lead < newNode.lead) {
                            temp.firstChild = nodeForOriginalNode;
                            nodeForOriginalNode.rightSibling = newNode;
                            insertIntoHashTable(temp, newNode, newNode.lead);
                        }
                        else {
                            temp.firstChild = newNode;
                            newNode.rightSibling = nodeForOriginalNode;
                            insertIntoHashTable(temp, newNode, newNode.lead);
                        }



                    }
                    else if (newLabel1.length() != 0) {
                        Node nodeForOriginalNode = new Node(newLabel1.charAt(0), newLabel1, true);
//                        nodeForOriginalNode.nodeId = idCounter;
//                        idCounter++;
                        temp.firstChild = nodeForOriginalNode;
                    }
                    else {
                        // Shrinks newWord and searches where it should go.
                        newNode.lead = newLabel2.charAt(0);
                        newNode.label = (newLabel2.length() == 1) ? "" : newLabel2.substring(1);
                        temp.insert(newNode);
                    }
                }
            }
            else if (temp == firstChild) {
                newNode.rightSibling = firstChild;
                firstChild = newNode;
                insertIntoHashTable(this,newNode,newNode.lead);
            }
        }
    }


    private Node root;

    public HashTrie(int size) {
        hashTable = new HashEntryNode[size];
        root = new Node(' ', null, false);
        root.nodeId = idCounter;
        idCounter++;
    }

    public void insertIntoHashTable(Node _parent, Node _child, char _c) {
        int hashCode = _child.hashCode(_parent, _c);
        HashEntryNode eNode = hashTable[hashCode];

        HashEntryNode newNode = new HashEntryNode() {
            {
                parent = _parent;
                child = _child;
                c = _c;
                next = (eNode != null) ? eNode.next : null;
            }
        };

        if (eNode != null) {
            eNode.next = newNode;
        }
        else {
            hashTable[hashCode] = newNode;
        }
    }

    public void insert(String newWord) {
        if (newWord.equals("arborize")) {
            int i = 0;
        }
        char letter = newWord.charAt(0);
        newWord = (newWord.length() == 1) ? "" : newWord.substring(1);
        Node newNode = new Node(letter, newWord, true);
        newNode.nodeId = idCounter;
        root.insert(newNode);

        idCounter++;
    }

    public void lookup(String prefix) {
        if (prefix != null && !prefix.isEmpty()) {
            char[] prefixAsArray = prefix.toCharArray();
            Node current = root.findChild(prefixAsArray[0]);
            Node parent = root;
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