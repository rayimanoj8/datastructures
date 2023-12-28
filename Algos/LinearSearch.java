package Algos;

public class LinearSearch<T extends Comparable<T>>{
    T arr[];
    int length;

    public LinearSearch(T arr[]) {
        this.arr = arr;
        length = arr.length;
    }

    public int search(T key){
        for(int i=0;i<length;i++){
            if(arr[i].compareTo(key) == 0)
                return i;
        }
        return -1;
    }
}
