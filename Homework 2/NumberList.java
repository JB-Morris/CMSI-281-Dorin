/**    

    <b>Note: Corrections have been made to the return types for both toArray() methods. (2015-10-13).</b>

    An object of this class represents a number list, i.e., an ordered collection
    of integers, each of Java class <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html">Long</a>, 
    with duplicates permitted. Be sure to read the Java documentation on
    <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html">interface java.util.Collection</a>.

*/
import java.util.ArrayList;

public class NumberList implements java.util.Collection {

    private Long[] list;
    // private long[] nonDuplicated;
    private int currentIndex;
    private int currentCapacity;
    // private int nonDuplicatedIndex;

    /** Constructs an empty number list. */
    public NumberList(){
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        this.list = new Long[10];
        // this.nonDuplicated = new long[10];
        this.currentIndex = 0;
        // this.nonDuplicatedIndex = 0
        this.currentCapacity = 10;
        // throw new UnsupportedOperationException();
    }


    /** Constructs a number list from an array of Longs. */
    public NumberList( Long[] l ){
        this.list = new Long[l.length];
        int count = 0;
        for(int i = 0; i < l.length; i++){
            if (l[i] != null){
                this.list[count] = l[i];
                count++;
            }
        }
        this.currentIndex = count;
        this.currentCapacity = l.length;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
    
    /** Increases by one the number of instances of the given element in this collection. */
    public boolean add ( Object obj ) {
        if(this.currentIndex >= this.currentCapacity - 1){
            this.expand();
        }
        if(!(obj instanceof Long)){
            return false;
        }
        this.list[currentIndex] = (Long)obj;
        this.currentIndex++;
        return true;

        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
    

    /** Adds all of the elements of the given number list to this one. */
    public boolean addAll ( java.util.Collection c  ) {
        int inputLength = ((NumberList)c).list.length;
        if(this.currentIndex  + inputLength <= this.currentCapacity){
            this.expand(inputLength);
        }
        if(!(c instanceof NumberList)){
            return false;
        }
        System.arraycopy(((NumberList)c).list, 0, this.list, currentIndex, inputLength);
        currentIndex += inputLength;
        return true;
        // System.arraycopy
        // ((NumberList)c).list
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
 

    /** Removes all of the elements from this collection. */
    public void clear () {
        this.currentIndex = 0;
        list = new Long[10];
        this.currentCapacity = 10;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
 

    /** Returns true iff this number list contains at least one instance of the specified element. */
    public boolean contains ( Object obj ) {
        for(Long x : list){
            if(x == (Long)obj){
                return true;
            } 
        }
        return false;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
 


    /** Returns true iff this number list contains at least one instance of each element 
        in the specified list. Multiple copies of some element in the argument do not
        require multiple copies in this number list. */
    public boolean containsAll ( java.util.Collection c ) {
        // java.util.Iterator it = c.iterator();

        for(Object o : c){
            if(!(contains(o))){
                return false;
            }
        }

        // if(!(((java.util.Collection)c).toArray() instanceof Long[])){
        //     return false;
        // }
        // Long[] inputList = ((java.util.Collection)c).toArray();
        // for(Long l : inputList){
        //     if(!(this.contains(l))){
        //         return false;
        //     }
        // }

        // Iterator it = c.iterator();
        // for(it.hasNext()){
        //     if(!(contains(it.next))){
        //         return false;
        //     }
        // }
        return true;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
 
 


    /** Compares the specified object with this collection for equality. */
    public boolean equals ( Object obj ) {
        if(!(obj instanceof NumberList)){
            return false;
        }
        if(!(((NumberList)obj).currentIndex == this.currentIndex)){
            return false;
        }
        for(int i = 0; i < currentIndex; i++){
            if(!(((NumberList)obj).list[i] == this.list[i])){
                return false;
            }
        }
        return true;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
 



    /** Returns the hashcode value for this collection. */
    public int hashCode () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // return Object.hashCode();
        throw new UnsupportedOperationException();
    }



    /** Returns true if this collection contains no elements. */
    public boolean isEmpty () {
        if(this.currentIndex == 0){
            return true;
        }else return false;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }



    /** Returns an iterator over the elements in this collection. Replicated elements should
        be "iterated over" just once. */
    public java.util.Iterator iterator () {
        // int currentIndex = 0;

        // boolean hasNext(){
        //     if(currentIndex < this.currentIndex){
        //         return true;
        //     }
        //     return false;
        // }

        // Object next(){
        //     if(!(hasNext())){
        //         throw new NoSuchElelementException();
        //     }
        //     currentIndex++;
        //     return this.list[currentIndex - 1]
        // }

        // remove(){
        //     this.list[currentIndex] = null;
        // } 


        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // for each statements?
        throw new UnsupportedOperationException();
    }



    /** Removes a single instance of the specified element from 
        this collection, if it is present. */
    public boolean remove ( Object obj ) {
        if(!(obj instanceof Long)){
            return false;
        }
        for(int i = 0; i < currentIndex; i++){
            if(this.list[i] == (Long)obj){
                for(int j = i; j < currentIndex - 1; j++){
                    // if(j < this.currentIndex){
                        this.list[j] = this.list[j + 1];    
                    // }
                }
                this.currentIndex--;
                return true;
            }
        }
        return false;

        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }



    /** Removes all of this collection's elements that are also contained 
        in the specified collection. */
    public boolean removeAll ( java.util.Collection c ) {
        boolean returnValue = false;
        // java.util.Iterator it = c.iterator();
        for(Object o : c){
            // System.out.println(it.next());
            for(int i = 0; i <= this.currentIndex; i++){
                if(o == this.list[i]){
                    for(int j = i; j < currentIndex; j++){
                        this.list[j] = this.list[j + 1];
                    }
                    returnValue = true;
                    this.currentIndex--;
                }
            }
        }
        return returnValue;


        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }




	/** Retains only the elements in this collection that are contained in the specified collection. 
		 In other words, removes from this collection all of its elements that are not contained in the 
		 specified collection. */
	public boolean retainAll ( java.util.Collection c ) {
        boolean returnValue = false;
        for(int i = 0; i < this.currentIndex; i++){
            if(!(c.contains(this.list[i]))){
                this.remove(this.list[i]);
                i--;
            }else returnValue = true;
        }
        return returnValue;
        


		// throw new UnsupportedOperationException();
	}


    /** Returns the number of elements in this number list, including duplicates. */
    public int sizeIncludingDuplicates () {
        return this.currentIndex;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
    
    

    /** Returns a Long[] containing all of the elements in this collection, not including duplicates. */
    public Long[] toArray () {
        Long[] arr = new Long[this.currentIndex];
        boolean copyElement;
        int i = 0;
        int j = 0;
        for(Long x : this.list){
            copyElement = true;
            for(Long y : arr){
                if(x == y){
                    copyElement = false;
                    j++;
                }
            }
            if(copyElement){
                arr[i++] = this.list[j++];
            }
        }

        if(i < arr.length){
            Long [] shortenedArr = new Long[i];
            for(int k = 0; k < i; k++){
                shortenedArr[k] = arr[k];
            }

            arr = shortenedArr;
        }
        return arr;
        // return this.list;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }



    /** Not supported for this class. */
    public Object[] toArray ( Object[] obj ) {
        throw new UnsupportedOperationException();
    }




    /** Returns the number of elements in this number list, not including duplicates. */
    public int size () {
        // Long[] noDuplicates = this.toArray();
        // int i = 0;
        // while(noDuplicates[i + 1] != null){
        //     i++;
        // }

        // return i + 1;

        return this.toArray().length;

        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }




    /** Returns the number of instances of the given element in this number list. */
    public int count ( Object obj ) {
        if(!(obj instanceof Long)){
            return 0;
        }
        int counter = 0;
        for(int i = 0; i <= this.currentIndex; i++){
            if(obj == this.list[i]){
                counter++;
            }
        }
        return counter;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
    

    
    /** This returns a stringy version of this number list. */
    public String toString () { // overrides Object.toString()
        Long[] stringArr = new Long[currentIndex];
        for(int i = 0; i < this.currentIndex; i++){
            stringArr[i] = this.list[i];
        }
        return java.util.Arrays.toString(stringArr);
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }


    
    /** This so-called "static factory" returns a new number list comprised of the numbers in the specified array.
        Note that the given array is long[], not Long[]. */
    public static NumberList fromArray ( long[] l ) {
        int i = 0;
        Long[] converted = new Long[l.length];
        for(long x : l){
            converted[i++] = x;
        }

        NumberList n = new NumberList(converted);
        return n;
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }

    private void expand(){
        Long[] newArr = new Long[this.currentCapacity * 2];
        System.arraycopy(this.list, 0, newArr, 0, this.currentIndex);
        this.currentCapacity *= 2;
        this.list = newArr;
    }

     private void expand(int inputSize){
        int s;
        if(inputSize > this.currentCapacity){
            s = inputSize;
        }else s = this.currentCapacity;
        Long[] newArr = new Long[s * 2];
        System.arraycopy(this.list, 0, newArr, 0, this.currentIndex);
        this.list = newArr;
    }

    // private void removeNull(Long[] l){
    //     for(int i = 0; i < currentIndex; i++){
    //         if(l[i] == null){
    //             this.list[i] = this.list[i + 1];
    //             this.list[i + 1] = null;
    //         }
    //     }
    // }

    
    /** This main method is just a comprehensive test program for the class. */

    private static int attempts = 0;
    private static int successes = 0;

    public static void main ( String[] args ) {




        System.out.println("Testing NumberList()");

        NumberList testList = new NumberList();

        if(("[]" == testList.toString())){
            System.out.println("success");
        }else System.out.println("failure");


        System.out.println("Testing NumberList([])");



        Long[] testArr = new Long[5];
        testArr[0] = 6L;
        testArr[1] = 7L;
        testArr[2] = 8L;
        testArr[3] = 9L;
        testArr[4] = 10L;
        NumberList testList2 = new NumberList(testArr);

        if(testList2.toString().equals("[6, 7, 8, 9, 10]")){
            System.out.println("success");
        }else System.out.println("failure");

        System.out.println("");

        

        System.out.println("Testing add()");

        testList.add(5L);
        if(testList.toString().equals("[5]")){
            System.out.println("success");
        }else System.out.println("failure");


        testList.add(3L);

        if(testList.toString().equals("[5, 3]")){
            System.out.println("success");
        }else System.out.println("failure");

        System.out.println("");
      

        System.out.println("Testing addAll()");

        testList.addAll(testList2);

        if(testList.toString().equals("[5, 3, 6, 7, 8, 9, 10]")){
            System.out.println("success");
        }else System.out.println("failure");
        // System.out.println(testList.toString());

        System.out.println("");
        System.out.println("Testing clear()");

        testList.clear();

        if(testList.toString().equals("[]")){
            System.out.println("success");
        }else System.out.println("failure");
        // System.out.println(testList.toString());

        System.out.println("");
        System.out.println("Testing contains()");

        if(testList2.contains(10L)){
            System.out.println("success");
        }else System.out.println("failure");

        if(!testList2.contains(50L)){
            System.out.println("success");
        }else System.out.println("failure");        

        System.out.println("");
        System.out.println("Testing containsAll()");

        java.util.ArrayList<Long> testCollection = new ArrayList<Long>();
        testCollection.add(1L);
        testCollection.add(6L);
        
        if(!testList2.containsAll(testCollection)){
            System.out.println("success");
        }else System.out.println("failure");

        testList2.add(1L);

        if(testList2.containsAll(testCollection)){
            System.out.println("success");
        }else System.out.println("failure");



        System.out.println("");
        System.out.println("Testing count()");

        if(testList2.count(1L) == 1){
            System.out.println("success");
        }else System.out.println("failure");

        if(testList2.count(2L) == 0){
            System.out.println("success");
        }else System.out.println("failure");


        System.out.println("");
        System.out.println("Testing equals()");

        Long[] testArr3 = new Long[4];
        testArr3[0] = 1L;
        testArr3[1] = 2L;
        testArr3[2] = 3L;
        testArr3[3] = 4L;

        NumberList testList4 = new NumberList(testArr3);

        NumberList testList5 = new NumberList(testArr3);

        NumberList testList6 = new NumberList();

        if(testList4.equals(testList5)){
            System.out.println("success");
        }else System.out.println("failure");

        if(!testList4.equals(testList6)){
            System.out.println("success");
        }else System.out.println("failure");        

        testList6.add(1L);
        testList6.add(2L);
        testList6.add(3L);
        testList6.add(4L);

        if(testList4.equals(testList6)){
            System.out.println("success");
        }else System.out.println("failure");

        System.out.println("");
        System.out.println("Testing fromArray()");

        NumberList testList3 = new NumberList();
        long[] testArr2 = new long[6];
        testArr2[0] = 1L;
        testArr2[1] = 2L;
        testArr2[2] = 3L;
        testArr2[3] = 4L;
        testArr2[4] = 5L;
        testArr2[5] = 6L;
        testList3 = testList3.fromArray(testArr2);
        
        if(testList3.toString().equals(java.util.Arrays.toString(testArr2))){
            System.out.println("success");
        }else System.out.println("failure");

        System.out.println("");
        System.out.println("Testing hashCode()");

        System.out.println("");
        System.out.println("Testing isEmpty()");

        if(!testList6.isEmpty()){
            System.out.println("success");
        }else System.out.println("failure");

        if(testList.isEmpty()){
            System.out.println("success");
        }else System.out.println("failure");

        System.out.println("");
        System.out.println("Testing iterator()");

        System.out.println("");
        System.out.println("Testing remove()");


        testList3.remove(5L);

        long[] testArr4 = new long[5];
        testArr4[0] = 1L;
        testArr4[1] = 2L;
        testArr4[2] = 3L;
        testArr4[3] = 4L;
        testArr4[4] = 6L;

        if(testList3.toString().equals(java.util.Arrays.toString(testArr4))){
            System.out.println("success");
        }else System.out.println("failure");

        System.out.println("");
        System.out.println("Testing removeAll()");

        Long[] testArr5 = new Long[1];
        testArr5[0] = 2L;
        java.util.ArrayList<Long> testCollection2 = new ArrayList<Long>();
        testCollection2.add(2L);
        testCollection2.add(20L);

        testList3.add(2L);
        testList3.removeAll(testCollection2);

        Long[] testArr6 = new Long[4];
        testArr6[0] = 1L;
        testArr6[1] = 3L;
        testArr6[2] = 4L;
        testArr6[3] = 6L;



        if(testList3.toString().equals(java.util.Arrays.toString(testArr6))){
            System.out.println("success");
        }else System.out.println("failure"); 

        NumberList testList8 = new NumberList();
        testList8.add(1L);
        testList8.add(2L);
        testList8.add(2L);
        testList8.add(1L);
        testList8.add(1L);
        testList8.add(1L);

        

        java.util.ArrayList<Long> testCollection4 = new ArrayList<Long>();
        
        testCollection4.add(1L);
        testCollection4.add(1L);
        testCollection4.add(1L);
        testCollection4.add(1L);

        testList8.removeAll(testCollection4);


        Long[] testArr7 = new Long[2];
        testArr7[0] = 2L;
        testArr7[1] = 2L;


        if(testList8.toString().equals(java.util.Arrays.toString(testArr7))){
            System.out.println("success");
        }else System.out.println("failure"); 



        System.out.println("");
        System.out.println("Testing retainAll()");

        NumberList testList7 = new NumberList();
        testList7.add(1L);
        testList7.add(2L);
        testList7.add(2L);
        testList7.add(1L);
        testList7.add(1L);
        testList7.add(1L);

        

        java.util.ArrayList<Long> testCollection3 = new ArrayList<Long>();
        
        testCollection3.add(1L);
        testCollection3.add(1L);
        testCollection3.add(1L);
        testCollection3.add(1L);

        testList7.retainAll(testCollection3);


        if(testList7.toString().equals(testCollection3.toString())){
            System.out.println("success");
        }else System.out.println("failure");   
        // System.out.println(testList7.toString());
        // System.out.println(testCollection3.toString());     


        System.out.println("");
        System.out.println("Testing size()");


        NumberList testList9 = new NumberList();
        testList9.add(1L);
        testList9.add(1L);
        testList9.add(2L);
        testList9.add(3L);
        testList9.add(4L);
        testList9.add(5L);

        if(testList9.size() == 5){
            System.out.println("success");
        }else System.out.println("failure");   
        // System.out.println(testList9.size());

        System.out.println("");
        System.out.println("Testing sizeIncludingDuplicates()");

        if(testList9.sizeIncludingDuplicates() == 6){
            System.out.println("success");
        }else System.out.println("failure");

        System.out.println("");
        System.out.println("Testing toArray()");

        System.out.println(java.util.Arrays.toString(testList9.toArray()));        

        System.out.println("");
        System.out.println("Testing toString()");



         /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        // throw new UnsupportedOperationException();
    }
}