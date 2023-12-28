package Algos;

public class BinarySearch<T extends Comparable<T>> {
      T arr[];
      public BinarySearch(T arr[]){
        this.arr= arr;
      }
      public int search(T key){
        return search(key,0,arr.length);
      }
      int search(T key,int l,int r){
        if (r >= l) {
            int mid = l + (r - l) / 2;
            // If the element is present at the middle itself
            if (arr[mid] == key)
            return mid;
            // If element is smaller than mid, then it can only be present in left subarray
            if (arr[mid].compareTo(key)<0)
            return search(key, l, mid - 1);
            // Else the element can only be present in right subarray
            return search(key,mid + 1, r);
            }
            // We reach here when element is not present in array
            return -1;
      }  
}
