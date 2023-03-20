package assignment.birds;

public class OrderedDictionary implements OrderedDictionaryADT {

    Node root;

    OrderedDictionary() {
        root = new Node();
    }

    /**
     * Returns the Record object with key k, or it returns null if such a record
     * is not in the dictionary.
     *
     * @param k
     * @return
     * @throws assignment/birds/DictionaryException.java
     */
    @Override
    public BirdRecord find(DataKey k) throws DictionaryException {
        Node current = root;
        int comparison;
        if (root.isEmpty()) {         
            throw new DictionaryException("There is no record matches the given key");
        }

        while (true) {
            comparison = current.getData().getDataKey().compareTo(k);
            if (comparison == 0) { // key found
                return current.getData();
            }
            if (comparison == 1) {
                if (current.getLeftChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getLeftChild();
            } else if (comparison == -1) {
                if (current.getRightChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getRightChild();
            }
        }

    }

    /**
     * Inserts r into the ordered dictionary. It throws a DictionaryException if
     * a record with the same key as r is already in the dictionary.
     *
     * @param r
     * @throws birds.DictionaryException
     */
    @Override
    public void insert(BirdRecord r) throws DictionaryException {
        // Write this method

        Node current = root;
        int comparison;
        Node record = new Node(r);
        if (current.isEmpty()){
            current.setData(r);
            return;
        }

        while (!current.isEmpty()){
            comparison = current.getData().getDataKey().compareTo(r.getDataKey());
            if (comparison == 0){
                throw new DictionaryException("Key already existed.");
            }
            else if (comparison == 1){
                if (current.hasLeftChild()){
                    current = current.getLeftChild();
                }
                else {
                    current.setLeftChild(record);
                    break;
                }
            }
            else {
                if (current.hasRightChild()){
                    current = current.getRightChild();
                }
                else {
                    current.setRightChild(record);
                    break;
                }
            }
        }

    }

    /**
     * Removes the record with Key k from the dictionary. It throws a
     * DictionaryException if the record is not in the dictionary.
     *
     * @param k
     * @throws birds.DictionaryException
     */
    @Override
    public void remove(DataKey k) throws DictionaryException {
//        int comparison;
//        Node current = root;
//        if (isEmpty()) throw new DictionaryException("No such record key exists");
//
//        comparison = current.getData().getDataKey().compareTo(k);
//
//        if (comparison < 0) //the Datakey needed to be removed is less than the root
//        {
//            current = current.getLeftChild();
//            remove(current.getData().getDataKey());
//        }
//        else if (comparison > 0) //the Datakey to be rmoved is to the right subtree
//        {
//            current = current.getRightChild();
//            remove(current.getData().getDataKey());
//        }
//        else {
//            //the node to removed is a leaf
//            if (!current.hasRightChild() && !current.hasLeftChild()) {
//                current = null;
//            }
//            //the node is not a leaf and has a right child
//            else if (current.getRightChild() != null) {
//                current.setData(successor(k));
//                current = current.getRightChild();
//                remove(current.getData().getDataKey());
//            }
//            else {
//                current.setData(predecessor(k));
//                current = current.getLeftChild();
//                remove(current.getData().getDataKey());
//            }
//        }

    }


    /**
     * Returns the successor of k (the record from the ordered dictionary with
     * smallest key larger than k); it returns null if the given key has no
     * successor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws birds.DictionaryException
     */
    @Override
    public BirdRecord successor(DataKey k) throws DictionaryException{
        Node current = root;
        BirdRecord successorBird = null;
        while (current != null){
            int comparison = current.getData().getDataKey().compareTo(k);
            if (comparison == 1){
                successorBird = current.getData();
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }
        if (successorBird == null){
            throw new DictionaryException("There is no successor for the given record key");
        }
        return successorBird;
    }

   
    /**
     * Returns the predecessor of k (the record from the ordered dictionary with
     * largest key smaller than k; it returns null if the given key has no
     * predecessor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws birds.DictionaryException
     */
    @Override
    public BirdRecord predecessor(DataKey k) throws DictionaryException{
        Node current = root;
        BirdRecord preBird = null;
        while (current != null){
            int comparison = current.getData().getDataKey().compareTo(k);
            if (comparison == -1){
                preBird = current.getData();
                current = current.getRightChild();
            }
            else {
                current = current.getLeftChild();
            }
        }
        if (preBird == null){
            throw new DictionaryException("There is no predecessor for the given record key");
        }
        return preBird;

    }

    /**
     * Returns the record with smallest key in the ordered dictionary. Returns
     * null if the dictionary is empty.
     *
     * @return
     */
    @Override
    public BirdRecord smallest() throws DictionaryException{
        if (isEmpty()) {
            throw new DictionaryException("The dictionary is empty.");
        }
        Node current = root;
        BirdRecord smallestBird = new BirdRecord();

        while (current.hasLeftChild()) {
            current = current.getLeftChild();
        }
        smallestBird = current.getData();
        return smallestBird;
    }

    /*
	 * Returns the record with largest key in the ordered dictionary. Returns
	 * null if the dictionary is empty.
     */
    @Override
    public BirdRecord largest() throws DictionaryException{
        if (isEmpty()) throw new DictionaryException("The dictionary is empty.");

        Node current = root;
        BirdRecord largestBird = new BirdRecord();

        while (current.hasRightChild()) {
            current = current.getRightChild();
        }
        largestBird = current.getData();
        return largestBird;
    }
      
    /* Returns true if the dictionary is empty, and true otherwise. */
    @Override
    public boolean isEmpty (){
        return root.isEmpty();
    }
}
