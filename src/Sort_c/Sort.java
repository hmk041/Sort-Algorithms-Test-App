/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort_c;

import interfaces.ICompare;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sort<T> implements interfaces.ISort {

    public ArrayList<T> array;
    public Object Iarray[];
    private String type;
    public JTable table;

    //nano saniye verilerin tutulduğu stringlerdir
    public String totalTimeB, totalTimeQ, totalTimeM, totalTimeH, totalTimeI, totalTimeT, totalTimeS, totalTimeR,
            totalTimeG, totalTimeBR, totalTimeP, totalTimeSH, totalTimeSHI, totalTimeBIN;

    //Finish-Time verilerin tutulduğu stringlerdir
    public String FNTimeB, FNTimeQ, FNTimeM, FNTimeH, FNTimeI, FNTimeT, FNTimeS, FNTimeR, FNTimeG, FNTimeBR, FNTimeP, FNTimeSH, FNTimeSHI, FNTimeBIN;

    public Sort() {
        
    }

    public Sort(ArrayList<T> array, String type, JTable table) {
        this.array = array;
        this.type = type;
        this.table = table;
    }

    @Override
    public void setData(Object[] os) {
        Iarray = new Object[os.length];
        for (int i = 0; i < os.length; i++) {
            Iarray[i] = os[i];
        }
        ArrayList arr = new ArrayList(Arrays.asList(Iarray));
        this.array = arr;
    }

    @Override
    public void setCompareFunction(ICompare ıc) {

    }

    @Override
    public Object[] getData() {
        
        Iarray = array.toArray();
        return Iarray;
    }

//****************************************QUICK SORT START ****************************************    
    @Override
    public void quickSort() {
        if (this.array.get(0) instanceof Integer) {
            quickSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            quickSortOther();
        } else {
            quickSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    quickSortString();
                    break;
                case "integer":
                    quickSortInteger();
                    break;
                default:
                    quickSortOther();
                    break;
            }
        }

    }

    private ArrayList<Double> quickSortOther() {
        //Geçen süre sorting den önce
        long startTime = System.nanoTime();

        //Sorting
        Qsort(this.array, 0, this.array.size() - 1);

        //Geçen süre sorting den sonra
        long endTime = System.nanoTime();
        this.totalTimeQ = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeQ = sdf.format(instant);
        return null;
    }

    private ArrayList<String> quickSortString() {
        //Geçen süre sorting den önce
        long startTime = System.nanoTime();

        //Sorting
        Qsort(this.array, 0, this.array.size() - 1);

        //Geçen süre sorting den sonra
        long endTime = System.nanoTime();
        this.totalTimeQ = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeQ = sdf.format(instant);
        return null;
    }

    private ArrayList<Integer> quickSortInteger() {
        //Geçen süre sorting den önce
        long startTime = System.nanoTime();

        //Sorting
        Qsort(this.array, 0, this.array.size() - 1);

        //Geçen süre sorting den sonra
        long endTime = System.nanoTime();
        this.totalTimeQ = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeQ = sdf.format(instant);
        return null;
    }

    int partition(ArrayList<T> arr, int low, int high) {
        T pivot = arr.get(high);
        int i = (low - 1); //indeksin en küçük elemanı
        for (int j = low; j < high; j++) {

            // pivotla karşılaştırma
            //Cheking if input is integer,double or string
            if (type == "integer" || this.array.get(0) instanceof Integer) {

                if ((Integer) arr.get(j) <= (Integer) pivot) {
                    i++;

                    // swap işlemi 
                    T temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
                printTable(table);
            } else if (type == "double" || this.array.get(0) instanceof Double) {//For Doubles
                if ((Double) arr.get(j) <= (Double) pivot) {
                    i++;

                    // swap işlemi 
                    T temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
                printTable(table);
            } else { //For Strings

                ArrayList<String> str = (ArrayList<String>) this.array;
                String temp;
                for (int k = 0; k < str.size(); k++) {
                    for (int s = k + 1; s < str.size(); s++) {
                        // comparing strings
                        if (str.get(k).compareTo(str.get(s)) < 0) {
                            temp = str.get(k);
                            str.set(k, str.get(s));
                            str.set(s, temp);
                        }
                    }
                    this.array = (ArrayList<T>) str;
                    printTable(table);
                }
            }
        }

        // swap arr[i+1] ve arr[high]  
        T temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }

    //Quick Sort Function
    void Qsort(ArrayList<T> arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            //Qsort recursive çağırma
            Qsort(arr, low, pi - 1);
            Qsort(arr, pi + 1, high);
        }
    }

//****************************************MERGE SORT START ****************************************    
    @Override
    public void mergeSort() {
        if (this.array.get(0) instanceof Integer) {
            mergeSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            mergeSortOther();
        } else {
            mergeSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    mergeSortString();
                    break;
                case "integer":
                    mergeSortInteger();
                    break;
                default:
                    mergeSortOther();
                    break;
            }
        }
    }

    public class MergeSort<T extends Comparable<T>> {

        public void sort(ArrayList<T> values) {
            mergesort(0, values.size() - 1, values, new ArrayList<>(values));

        }

        private void mergesort(int low, int high, ArrayList<T> values, ArrayList<T> aux) {

            if (low < high) {
                int mid = low + (high - low) / 2;
                mergesort(low, mid, values, aux);
                mergesort(mid + 1, high, values, aux);
                merge(low, mid, high, values, aux);
            }
        }

        private void merge(int low, int mid, int high, ArrayList<T> values, ArrayList<T> aux) {
            int left = low;
            int right = mid + 1;

            for (int i = low; i <= high; i++) {
                aux.set(i, values.get(i));
            }

            while (left <= mid && right <= high) {
                values.set(low++, aux.get(left).compareTo(aux.get(right)) < 0 ? aux.get(left++) : aux.get(right++));
            }

            while (left <= mid) {
                values.set(low++, aux.get(left++));
            }
            printTable(table);
        }
    }

    private ArrayList<T> mergeSortInteger() {
        long startTime = System.nanoTime();
        MergeSort mrg = new MergeSort();
        mrg.sort(this.array);
        long endTime = System.nanoTime();
        this.totalTimeM = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeM = sdf.format(instant);
        return null;
    }

    public ArrayList<String> mergeSortAlgh(ArrayList<String> whole) {
        ArrayList<String> left = new ArrayList<String>();
        ArrayList<String> right = new ArrayList<String>();
        int center;

        if (whole.size() == 1) {
            return whole;
        } else {
            center = whole.size() / 2;
            // copy the left half of whole into the left.
            for (int i = 0; i < center; i++) {
                left.add(whole.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i = center; i < whole.size(); i++) {
                right.add(whole.get(i));
            }

            // Sort the left and right halves of the arraylist.
            left = mergeSortAlgh(left);
            right = mergeSortAlgh(left);

            // Merge the results back together.
            Merge(left, right, whole);
        }
        return whole;
    }

    private void Merge(ArrayList<String> left, ArrayList<String> right, ArrayList<String> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<String> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        for (int i = restIndex; i < rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
        this.array = (ArrayList<T>) whole;
    }

    private ArrayList<T> mergeSortOther() {
        long startTime = System.nanoTime();
        MergeSort mrg = new MergeSort();
        mrg.sort(array);
        long endTime = System.nanoTime();
        this.totalTimeM = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeM = sdf.format(instant);
        return this.array;
    }

    private ArrayList<String> mergeSortString() {

        long startTime = System.nanoTime();
        mergeSortAlgh((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeM = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeM = sdf.format(instant);
        return null;
    }

//****************************************HEAP SORT START ****************************************
    @Override
    public void heapSort() {
        if (this.array.get(0) instanceof Integer) {
            heapSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            heapSortOther();
        } else {
            heapSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    heapSortString();
                    break;
                case "integer":
                    heapSortInteger();
                    break;
                default:
                    heapSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> heapSortInteger() {
        long startTime = System.nanoTime();
        Heapsort(this.array);
        long endTime = System.nanoTime();
        this.totalTimeH = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeH = sdf.format(instant);
        return this.array;
    }

    private ArrayList<T> heapSortOther() {
        long startTime = System.nanoTime();
        Heapsort(this.array);
        long endTime = System.nanoTime();
        this.totalTimeH = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeH = sdf.format(instant);
        return this.array;

    }

    private ArrayList<String> heapSortString() {
        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeH = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeH = sdf.format(instant);
        return null;
    }

    public void Heapsort(ArrayList<T> array) {
        int n = array.size();

        // Build heap (rearrange array) 
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // One by one extract an element from heap 
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end 
            T temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            // call max heapify on the reduced heap 
            heapify(array, i, 0);

            this.array = array;

            printTable(table);
        }
    }

    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(ArrayList<T> arr, int n, int i) {
        int largest = i; // Initialize largest as root 
        int l = 2 * i + 1; // left = 2*i + 1 
        int r = 2 * i + 2; // right = 2*i + 2 
        if (type == "integer" || this.array.get(0) instanceof Integer) {

            if (l < n && (Integer) arr.get(l) > (Integer) arr.get(largest)) {
                largest = l;
            }

            // If right child is larger than largest so far 
            if (r < n && (Integer) arr.get(r) > (Integer) arr.get(largest)) {
                largest = r;
            }
        } else if (type == "double" || this.array.get(0) instanceof Double) {

            if (l < n && (Double) arr.get(l) > (Double) arr.get(largest)) {
                largest = l;
            }

            // If right child is larger than largest so far 
            if (r < n && (Double) arr.get(r) > (Double) arr.get(largest)) {
                largest = r;
            }
        } else {// for strings

            ArrayList<String> str = (ArrayList<String>) this.array;
            String temp;
            for (int j = 0; j < str.size(); j++) {
                for (int s = j + 1; s < str.size(); s++) {
                    // comparing strings
                    if (str.get(s).compareTo(str.get(j)) < 0) {
                        temp = str.get(j);
                        str.set(j, str.get(i));
                        str.set(s, temp);
                    }
                }
                this.array = (ArrayList<T>) str;
            }
        }

        // If largest is not root 
        if (largest != i) {
            T tmp = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, tmp);

            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest);
        }
    }

    //****************************************BUBBLE SORT START ****************************************
    @Override
    public void bobbleSort() {
        if (this.array.get(0) instanceof Integer) {
            bobbleSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            bobbleSortOther();
        } else {
            bobbleSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    bobbleSortString();
                    break;
                case "integer":
                    bobbleSortInteger();
                    break;
                default:
                    bobbleSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> bobbleSortInteger() {
        long startTime = System.nanoTime();
        T tut;
        int n = this.array.size();
        int j;
        int gec;
        for (gec = 0; gec < n - 1; gec++) {
            for (j = 0; j < n - gec - 1; j++) {
                if ((Integer) this.array.get(j) > (Integer) this.array.get(j + 1)) {
                    tut = this.array.get(j);
                    this.array.set(j, this.array.get(j + 1));
                    this.array.set(j + 1, tut);
                }
                printTable(table);
            }
        }

        long endTime = System.nanoTime();
        this.totalTimeB = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeB = sdf.format(instant);
        return this.array;
    }

    private ArrayList<T> bobbleSortOther() {
        long startTime = System.nanoTime();
        T tut;
        int n = this.array.size();
        int j;
        int gec;
        for (gec = 0; gec < n - 1; gec++) {
            for (j = 0; j < n - gec - 1; j++) {
                if ((Double) this.array.get(j) > (Double) this.array.get(j + 1)) {
                    tut = this.array.get(j);
                    this.array.set(j, this.array.get(j + 1));
                    this.array.set(j + 1, tut);
                }
                printTable(table);
            }
        }
        long endTime = System.nanoTime();
        this.totalTimeB = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeB = sdf.format(instant);
        return this.array;
    }

    private ArrayList<String> bobbleSortString() {

        long startTime = System.nanoTime();
        ArrayList<String> str = (ArrayList<String>) this.array;
        String temp;
        for (int j = 0; j < str.size(); j++) {
            for (int i = j + 1; i < str.size(); i++) {
                // comparing strings
                if (str.get(i).compareTo(str.get(j)) < 0) {
                    temp = str.get(j);
                    str.set(j, str.get(i));
                    str.set(i, temp);
                }
            }
            this.array = (ArrayList<T>) str;
            printTable(table);
        }
        long endTime = System.nanoTime();
        this.totalTimeB = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeB = sdf.format(instant);
        return null;
    }

    //****************************************INSERTION SORT START ****************************************
    @Override
    public void insertionSort() {

        if (this.array.get(0) instanceof Integer) {
            insertionSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            insertionSortOther();
        } else {
            insertionSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    insertionSortString();
                    break;
                case "integer":
                    insertionSortInteger();
                    break;
                default:
                    insertionSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> insertionSortInteger() {
        long startTime = System.nanoTime();

        int n = this.array.size();
        for (int i = 1; i < n; i++) {
            T key = (T) this.array.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are 
             greater than key, to one position ahead 
             of their current position */
            while (j >= 0 && (Integer) this.array.get(j) > (Integer) key) {
                this.array.set(j + 1, this.array.get(j));
                j = j - 1;
            }
            this.array.set(j + 1, key);
            printTable(table);

        }
        long endTime = System.nanoTime();
        this.totalTimeI = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeI = sdf.format(instant);
        return this.array;
    }

    private ArrayList<T> insertionSortOther() {

        long startTime = System.nanoTime();
        int n = this.array.size();
        for (int i = 1; i < n; i++) {
            T key = (T) this.array.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are 
             greater than key, to one position ahead 
             of their current position */
            while (j >= 0 && (Double) this.array.get(j) > (Double) key) {
                this.array.set(j + 1, this.array.get(j));
                j = j - 1;
            }
            this.array.set(j + 1, key);
            printTable(table);

        }

        long endTime = System.nanoTime();
        this.totalTimeI = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeI = sdf.format(instant);
        return this.array;

    }

    private ArrayList<String> insertionSortString() {

        long startTime = System.nanoTime();
        ArrayList<String> str = (ArrayList<String>) this.array;
        int i, j;
        String key;
        for (j = 1; j < str.size(); j++) {
            key = str.get(j);
            i = j - 1;
            while (i >= 0) {
                if (key.compareTo(str.get(i)) > 0) {
                    break;
                }
                str.set(i + 1, str.get(i));
                i--;
            }
            str.set(i + 1, key);
            this.array = (ArrayList<T>) str;
            printTable(table);

        }

        long endTime = System.nanoTime();
        this.totalTimeI = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeI = sdf.format(instant);
        return null;
    }

//****************************************SELECTION SORT START ****************************************
    @Override
    public void selectionSort() {

        if (this.array.get(0) instanceof Integer) {
            selectionSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            selectionSortOther();
        } else {
            selectionSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    selectionSortString();
                    break;
                case "integer":
                    selectionSortInteger();
                    break;
                default:
                    selectionSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> selectionSortOther() {

        long startTime = System.nanoTime();
        T tmp;
        int min, n = this.array.size();
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i; j < n; j++) {
                if ((Double) this.array.get(j) < (Double) this.array.get(min)) {
                    min = j;
                }
            }
            tmp = this.array.get(i);
            this.array.set(i, this.array.get(min));
            this.array.set(min, tmp);
            printTable(table);

        }
        long endTime = System.nanoTime();
        this.totalTimeS = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeS = sdf.format(instant);
        return this.array;
    }

    private ArrayList<T> selectionSortInteger() {
        long startTime = System.nanoTime();
        T tmp;
        int min, n = this.array.size();
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i; j < n; j++) {
                if ((Integer) this.array.get(j) < (Integer) this.array.get(min)) {
                    min = j;
                }
            }
            tmp = this.array.get(i);
            this.array.set(i, this.array.get(min));
            this.array.set(min, tmp);
            printTable(table);

        }
        long endTime = System.nanoTime();
        this.totalTimeS = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeS = sdf.format(instant);
        return this.array;

    }

    private ArrayList<String> selectionSortString() {

        long startTime = System.nanoTime();
        ArrayList<String> str = (ArrayList<String>) this.array;
        int min, n = this.array.size();
        for (int j = 0; j < n; j++) {

            min = j;
            for (int k = j + 1; k < n; k++) {
                if (str.get(k).compareTo(str.get(min)) < 0) {
                    min = k;
                }
            }
            // Swap the reference at j with the reference at min 
            String temp = str.get(j);
            str.set(j, str.get(min));
            str.set(min, temp);
            this.array = (ArrayList<T>) str;
            printTable(table);

        }

        long endTime = System.nanoTime();
        this.totalTimeS = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeS = sdf.format(instant);
        return null;

    }

//****************************************TREE SORT START ****************************************
    class TreeSInteger {

        ArrayList<Integer> ary = new ArrayList<>();

        class Node {

            int key;
            Node left, right;

            public Node(int item) {
                key = item;
                left = right = null;
            }
        }

        Node root;

        // Constructor 
        TreeSInteger() {

            root = null;
        }

        // calls insertRec() 
        void insert(int key) {
            root = insertRec(root, key);
        }

        Node insertRec(Node root, int key) {

            if (root == null) {
                root = new Node(key);
                return root;
            }

            if (key < root.key) {
                root.left = insertRec(root.left, key);
            } else if (key > root.key) {
                root.right = insertRec(root.right, key);

            }

            return root;
        }

        void inorderRec(Node root) {

            if (root != null) {
                inorderRec(root.left);
                //System.out.print(root.key + ",");
                ary.add(root.key);
                array = (ArrayList<T>) ary;
                inorderRec(root.right);
                printTable(table);
            }

            //System.out.println("this.array : " + this.array);
        }

        void treeins(ArrayList<T> arr) {
            for (int i = 0; i < arr.size(); i++) {
                insert((Integer) arr.get(i));
            }
        }
    }

    class TreeSDouble {

        ArrayList<Double> ary = new ArrayList<>();

        class Node {

            double key;
            Node left, right;

            public Node(double item) {
                key = item;
                left = right = null;
            }
        }

        Node root;

        // Constructor 
        TreeSDouble() {
            root = null;
        }

        // calls insertRec() 
        void insert(double key) {
            root = insertRec(root, key);
        }

        Node insertRec(Node root, double key) {

            if (root == null) {
                root = new Node(key);
                return root;
            }

            if (key < root.key) {
                root.left = insertRec(root.left, key);
            } else if (key > root.key) {
                root.right = insertRec(root.right, key);
            }

            return root;
        }

        void inorderRec(Node root) {
            // ArrayList<Double> dtr = new ArrayList<>();
            int i = -1;
            if (root != null) {
                inorderRec(root.left);
                //System.out.print(root.key + ",");
                ary.add(root.key);
                array = (ArrayList<T>) ary;
                inorderRec(root.right);
                printTable(table);
            }

        }

        void treeins(ArrayList array) {
            for (int i = 0; i < array.size(); i++) {
                insert((Double) array.get(i));
                //printTable(table);
            }
        }
    }

    @Override
    public void treeSort() {
        if (this.array.get(0) instanceof Integer) {
            treeSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            treeSortOther();
        } else {
            treeSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    treeSortString();
                    break;
                case "integer":
                    treeSortInteger();
                    break;
                default:
                    treeSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> treeSortOther() {
        long startTime = System.nanoTime();

        TreeSDouble tree = new TreeSDouble();
        tree.treeins(this.array);
        tree.inorderRec(tree.root);
        long endTime = System.nanoTime();
        this.totalTimeT = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeT = sdf.format(instant);
        return this.array;
    }

    private ArrayList<String> treeSortString() {

        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeT = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeT = sdf.format(instant);
        return null;
    }

    private ArrayList<T> treeSortInteger() {

        long startTime = System.nanoTime();
        TreeSInteger tree = new TreeSInteger();
        tree.treeins(this.array);
        tree.inorderRec(tree.root);
        long endTime = System.nanoTime();
        this.totalTimeT = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeT = sdf.format(instant);
        return this.array;
    }

//****************************************RADIX SORT STRART ****************************************
    public void radixsort(ArrayList<T> input) {
        final int RADIX = 10;
        // declare and initialize bucket[]
        List<T>[] bucket = new ArrayList[RADIX];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<T>();
        }

        // sort
        boolean maxLength = false;
        int tmp = -1, placement = 1;
        while (!maxLength) {
            maxLength = true;
            // split input between lists

            if (type == "integer" || this.array.get(0) instanceof Integer) {
                for (T i : input) {
                    tmp = (Integer) i / placement;
                    bucket[tmp % RADIX].add(i);
                    if (maxLength && tmp > 0) {
                        maxLength = false;
                    }
                }
            } else {
                double tp = -1.0;
                for (T i : input) {
                    tp = (Double) i / placement;
                    bucket[((int) tp) % RADIX].add(i);
                    if (maxLength && (int) tp > 0) {
                        maxLength = false;
                    }
                }
            }

            // empty lists into input array
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                for (T i : bucket[b]) {
                    input.set(a++, i);
                    printTable(table);
                }
                bucket[b].clear();
            }
            // move to next digit
            placement *= RADIX;
        }
    }

    @Override
    public void radixSort() {

        if (this.array.get(0) instanceof Integer) {
            radixSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            radixSortOther();
        } else {
            radixSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    radixSortString();
                    break;
                case "integer":
                    radixSortInteger();
                    break;
                default:
                    radixSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> radixSortOther() {

        long startTime = System.nanoTime();
        T tmp;
        int min, n = this.array.size();
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i; j < n; j++) {
                if ((Double) this.array.get(j) < (Double) this.array.get(min)) {
                    min = j;
                }
            }
            tmp = this.array.get(i);
            this.array.set(i, this.array.get(min));
            this.array.set(min, tmp);
            printTable(table);

        }
        long endTime = System.nanoTime();
        this.totalTimeR = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeR = sdf.format(instant);
        return this.array;
    }

    private ArrayList<String> radixSortString() {

        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeR = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeR = sdf.format(instant);
        return null;
    }

    private ArrayList<T> radixSortInteger() {

        long startTime = System.nanoTime();
        radixsort(this.array);
        long endTime = System.nanoTime();
        this.totalTimeR = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeR = sdf.format(instant);
        return this.array;
    }

//****************************************GNOME SORT START ****************************************
    @Override
    public void gnomeSort() {

        if (this.array.get(0) instanceof Integer) {
            gnomeSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            gnomeSortOther();
        } else {
            gnomeSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    gnomeSortString();
                    break;
                case "integer":
                    gnomeSortInteger();
                    break;
                default:
                    gnomeSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> gnomeSortOther() {
        long startTime = System.nanoTime();
        gnome();
        long endTime = System.nanoTime();
        this.totalTimeG = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeG = sdf.format(instant);
        return null;
    }

    private ArrayList<String> gnomeSortString() {

        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeG = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeG = sdf.format(instant);
        return null;
    }

    private ArrayList<String> gnomeSortInteger() {

        long startTime = System.nanoTime();
        gnome();
        long endTime = System.nanoTime();
        this.totalTimeG = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeG = sdf.format(instant);
        return null;
    }

    void gnome() {
        int i = 1;
        int j = 2;

        while (i < this.array.size()) {

            if (type == "integer" || this.array.get(0) instanceof Integer) {

                if ((Integer) this.array.get(i - 1) <= (Integer) this.array.get(i)) {
                    i = j;
                    j++;
                } else {
                    T tmp = this.array.get(i - 1);
                    this.array.set(i - 1, this.array.get(i));
                    this.array.set(i--, tmp);
                    i = (i == 0) ? j++ : i;
                }
            } else if (type == "double" || this.array.get(0) instanceof Double) {

                if ((Double) this.array.get(i - 1) <= (Double) this.array.get(i)) {
                    i = j;
                    j++;
                } else {
                    T tmp = this.array.get(i - 1);
                    this.array.set(i - 1, this.array.get(i));
                    this.array.set(i--, tmp);
                    i = (i == 0) ? j++ : i;
                }
            } else { //For strings

            }
            printTable(table);

        }

    }

//****************************************BRICK SORT START ****************************************
    void oddEvenSort(ArrayList<T> array, int n) {
        boolean isSorted = false; // Initially array is unsorted 

        while (!isSorted) {
            isSorted = true;
            T temp;
            if (type == "integer" || this.array.get(0) instanceof Integer) {

                for (int i = 1; i <= n - 2; i = i + 2) {
                    if ((Integer) array.get(i) > (Integer) this.array.get(i + 1)) {
                        temp = array.get(i);
                        array.set(i, array.get(i + 1));
                        array.set(i + 1, temp);
                        isSorted = false;
                        printTable(table);
                    }

                }

                // Perform Bubble sort on even indexed element 
                for (int i = 0; i <= n - 2; i = i + 2) {

                    if ((Integer) array.get(i) > (Integer) array.get(i + 1)) {
                        temp = array.get(i);
                        array.set(i, array.get(i + 1));
                        array.set(i + 1, temp);
                        isSorted = false;
                        printTable(table);
                    }

                }
            } else if (type == "double" || this.array.get(0) instanceof Double) {

                for (int i = 1; i <= n - 2; i = i + 2) {
                    if ((Double) this.array.get(i) > (Double) this.array.get(i + 1)) {
                        temp = this.array.get(i);
                        this.array.set(i, this.array.get(i + 1));
                        this.array.set(i + 1, temp);
                        isSorted = false;
                    }
                    printTable(table);
                }

                // Perform Bubble sort on even indexed element 
                for (int i = 0; i <= n - 2; i = i + 2) {

                    if ((Double) this.array.get(i) > (Double) this.array.get(i + 1)) {
                        temp = this.array.get(i);
                        this.array.set(i, this.array.get(i + 1));
                        this.array.set(i + 1, temp);
                        isSorted = false;
                    }
                    printTable(table);
                }
            } else {
                // Strings
                ArrayList<String> str = (ArrayList<String>) this.array;
                String tmp;
                for (int j = 0; j < str.size(); j++) {
                    for (int i = j + 1; i < str.size(); i++) {
                        // comparing strings
                        if (str.get(i).compareTo(str.get(j)) < 0) {
                            tmp = str.get(j);
                            str.set(j, str.get(i));
                            str.set(i, tmp);
                            isSorted = false;
                        }
                    }
                    printTable(table);
                }
                this.array = (ArrayList<T>) str;
            }

        }
    }

    @Override
    public void brickSort() {

        if (this.array.get(0) instanceof Integer) {
            brickSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            brickSortOther();
        } else {
            brickSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    brickSortString();
                    break;
                case "integer":
                    brickSortInteger();
                    break;
                default:
                    brickSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> brickSortOther() {

        long startTime = System.nanoTime();
        oddEvenSort(this.array, this.array.size());
        long endTime = System.nanoTime();
        this.totalTimeBR = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeBR = sdf.format(instant);
        return this.array;
    }

    private ArrayList<T> brickSortString() {

        long startTime = System.nanoTime();
        oddEvenSort(this.array, this.array.size());
        long endTime = System.nanoTime();
        this.totalTimeBR = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeBR = sdf.format(instant);
        return this.array;
    }

    private ArrayList<T> brickSortInteger() {

        long startTime = System.nanoTime();
        oddEvenSort(this.array, this.array.size());
        long endTime = System.nanoTime();
        this.totalTimeBR = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeBR = sdf.format(instant);
        return this.array;
    }

//****************************************PIGEON HOLE SORT START **************************************** 
    void pigeonhole_sortInt(ArrayList<Integer> arr, int n) {
        int min = (Integer) arr.get(0);
        int max = (Integer) arr.get(0);
        int range, i, j, index;

        for (int a = 0; a < n; a++) {
            if ((Integer) arr.get(a) > max) {
                max = (Integer) arr.get(a);
            }
            if ((Integer) arr.get(a) < min) {
                min = (Integer) arr.get(a);
            }
        }

        range = max - min + 1;
        int[] phole = new int[range];
        Arrays.fill(phole, 0);

        for (i = 0; i < n; i++) {
            phole[(Integer) arr.get(i) - min]++;
        }

        index = 0;

        for (j = 0; j < range; j++) {
            while (phole[j]-- > 0) {
                arr.set(index++, j + min);
                printTable(table);
            }
        }
    }

    void pigeonhole_sortDbl(ArrayList<Double> arr, int n) {
        double min = arr.get(0);
        double max = arr.get(0);
        int range;
        int i, j, index;

        for (int a = 0; a < n; a++) {
            if (arr.get(a) > max) {
                max = arr.get(a);
            }
            if (arr.get(a) < min) {
                min = arr.get(a);
            }
        }

        range = (int) max - (int) min + 1;
        double[] phole = new double[range];
        Arrays.fill(phole, 0);

        for (i = 0; i < n; i++) {
            phole[(int) (arr.get(i) - min)]++;
        }

        index = 0;

        for (j = 0; j < range; j++) {
            while (phole[j]-- > 0) {
                arr.set(index++, j + min);
            }
        }
    }

    @Override
    public void pigeonHoleSort() {

        if (this.array.get(0) instanceof Integer) {
            pigeonHoleSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            pigeonHoleSortOther();
        } else {
            pigeonHoleSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    pigeonHoleSortString();
                    break;
                case "integer":
                    pigeonHoleSortInteger();
                    break;
                default:
                    pigeonHoleSortOther();
                    break;
            }
        }
    }

    private ArrayList<T> pigeonHoleSortOther() {

        long startTime = System.nanoTime();
        pigeonhole_sortDbl((ArrayList<Double>) this.array, this.array.size());
        long endTime = System.nanoTime();
        this.totalTimeP = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeP = sdf.format(instant);
        return null;
    }

    private ArrayList<String> pigeonHoleSortString() {

        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeP = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeP = sdf.format(instant);
        return null;
    }

    private ArrayList<String> pigeonHoleSortInteger() {

        long startTime = System.nanoTime();
        pigeonhole_sortInt((ArrayList<Integer>) this.array, this.array.size());
        long endTime = System.nanoTime();
        this.totalTimeP = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeP = sdf.format(instant);
        return null;
    }

//****************************************SHELL SORT START ****************************************
    @Override
    public void shellSort() {

        if (this.array.get(0) instanceof Integer) {
            shellSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            shellSortDouble();
        } else {
            shellSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    shellSortString();
                    break;
                case "integer":
                    shellSortInteger();
                    break;
                default:
                    shellSortDouble();
                    break;
            }
        }
    }

    private ArrayList<T> shellSortDouble() {

        long startTime = System.nanoTime();
        int n = this.array.size();
        int h = 1;
        while ((h * 3 + 1 < n)) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (int i = h - 1; i < n; i++) {
                T b = this.array.get(i);
                int j = i;
                for (j = i; (j >= h) && ((Double) this.array.get(j - h) > (Double) b); j -= h) {
                    this.array.set(j, this.array.get(j - h));
                }
                this.array.set(j, b);
                printTable(table);
            }
            h /= 3;
        }
        long endTime = System.nanoTime();
        this.totalTimeSH = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeSH = sdf.format(instant);
        return null;
    }

    private ArrayList<String> shellSortString() {

        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeSH = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeSH = sdf.format(instant);
        return null;
    }

    private ArrayList<String> shellSortInteger() {
        long startTime = System.nanoTime();
        int n = this.array.size();
        int h = 1;
        while ((h * 3 + 1 < n)) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (int i = h - 1; i < n; i++) {
                T b = this.array.get(i);
                int j = i;
                for (j = i; (j >= h) && ((Integer) this.array.get(j - h) > (Integer) b); j -= h) {
                    this.array.set(j, this.array.get(j - h));
                }
                this.array.set(j, b);
                printTable(table);
            }
            h /= 3;
        }
        long endTime = System.nanoTime();
        this.totalTimeSH = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeSH = sdf.format(instant);
        return null;
    }

//****************************************COUNTING SORT START ****************************************
    @Override
    public void shellInsortSort() {

        if (this.array.get(0) instanceof Integer) {
            countingSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            countingSortOther();
        } else {
            countingSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    countingSortString();
                    break;
                case "integer":
                    countingSortInteger();
                    break;
                default:
                    countingSortOther();
                    break;
            }
        }
    }

    private ArrayList<String> countingSortString() {

        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeSHI = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeSHI = sdf.format(instant);
        return null;
    }

    private ArrayList<Double> countingSortOther() {

        long startTime = System.nanoTime();
        int n = this.array.size();
        for (int i = 1; i < n; i++) {
            T key = (T) this.array.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are 
             greater than key, to one position ahead 
             of their current position */
            while (j >= 0 && (Double) this.array.get(j) > (Double) key) {
                this.array.set(j + 1, this.array.get(j));
                j = j - 1;
            }
            this.array.set(j + 1, key);
            printTable(table);

        }
        long endTime = System.nanoTime();
        this.totalTimeSHI = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeSHI = sdf.format(instant);
        return (ArrayList<Double>) this.array;
    }

    private ArrayList<Integer> countingSortInteger() {

        long startTime = System.nanoTime();

        int n = this.array.size();
        for (int i = 1; i < n; i++) {
            T key = (T) this.array.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are 
             greater than key, to one position ahead 
             of their current position */
            while (j >= 0 && (Integer) this.array.get(j) > (Integer) key) {
                this.array.set(j + 1, this.array.get(j));
                j = j - 1;
            }
            this.array.set(j + 1, key);
            printTable(table);

        }
        long endTime = System.nanoTime();
        this.totalTimeSHI = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeSHI = sdf.format(instant);
        return null;
    }

//****************************************BINARY INSERTION SORT START ****************************************
    @Override
    public void binaryInsertionSort() {

        if (this.array.get(0) instanceof Integer) {
            binaryInsertionSortInteger();
        } else if (this.array.get(0) instanceof Double) {
            binaryInsertionSortOther();
        } else {
            binaryInsertionSortString();
        }

        if (null != type) {
            switch (type) {
                case "string":
                    binaryInsertionSortString();
                    break;
                case "integer":
                    binaryInsertionSortInteger();
                    break;
                default:
                    binaryInsertionSortOther();
                    break;
            }
        }
    }

    void Binarysort(int n) {
        for (int i = 0; i < n; ++i) {
            T temp = this.array.get(i);
            int left = 0;
            int right = i;
            while (left < right) {
                int middle = (left + right) / 2;
                if (type == "integer" || this.array.get(0) instanceof Integer) {
                    if ((Integer) temp >= (Integer) this.array.get(middle)) {
                        left = middle + 1;
                    } else {
                        right = middle;
                    }
                } else if (type == "double" || this.array.get(0) instanceof Double) {
                    if ((Double) temp >= (Double) this.array.get(middle)) {
                        left = middle + 1;
                    } else {
                        right = middle;
                    }
                }

            }
            for (int j = i; j > left; --j) {
                Swap(j - 1, j);
            }
        }

    }

    void Swap(int i, int j) {
        T k = this.array.get(i);
        this.array.set(i, this.array.get(j));
        this.array.set(j, k);
        printTable(table);
    }

    private ArrayList<T> binaryInsertionSortOther() {

        long startTime = System.nanoTime();
        Binarysort(this.array.size());
        long endTime = System.nanoTime();
        this.totalTimeBIN = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeBIN = sdf.format(instant);
        return this.array;
    }

    private ArrayList<String> binaryInsertionSortString() {

        long startTime = System.nanoTime();
        toStringSort((ArrayList<String>) this.array);
        long endTime = System.nanoTime();
        this.totalTimeBIN = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeBIN = sdf.format(instant);
        return null;
    }

    private ArrayList<T> binaryInsertionSortInteger() {

        long startTime = System.nanoTime();
        Binarysort(this.array.size());
        long endTime = System.nanoTime();
        this.totalTimeBIN = Long.toString(endTime - startTime) + " ns";
        Date instant = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        FNTimeBIN = sdf.format(instant);
        return this.array;
    }

    //*********************************************** END *****************************************************
    public void printTable(JTable table) {

        if (table != null) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String out = "";

            for (int i = 0; i < this.array.size(); i++) {
                out += this.array.get(i) + "  ";
            }

            Object[] object = {out};

            model.addRow(object);
        }

    }

    public ArrayList<String> toStringSort(ArrayList<String> str) {

        str = (ArrayList<String>) this.array;
        String temp;
        for (int j = 0; j < str.size(); j++) {
            for (int i = j + 1; i < str.size(); i++) {
                // comparing strings
                if (str.get(i).compareTo(str.get(j)) < 0) {
                    temp = str.get(j);
                    str.set(j, str.get(i));
                    str.set(i, temp);
                }
            }
            this.array = (ArrayList<T>) str;
            printTable(table);
        }
        return null;
    }
}
