package Algos;

import Heaps.Heap;
import Heaps.MinHeap;

public class Sorts {
    public void BubbleSort(int arr[]){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[i]){
                    swap(arr,i,j);
                }
            }
        }
    }
    public void SelectionSort(int arr[]){
        for(int i=0;i<arr.length;i++){
            int min=i;
            for(int j=i;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            swap(arr,min,i);
        }
    }
    public void InsertionSort(int arr[]){
        for(int i=0;i<arr.length;i++){
            int key=arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }
    public void MergeSort(int arr[]){
        if(arr.length>1){
            int mid=arr.length/2;
            int left[]=new int[mid];
            int right[]=new int[arr.length-mid];
            for(int i=0;i<mid;i++){
                left[i]=arr[i];
            }
            for(int i=mid;i<arr.length;i++){
                right[i-mid]=arr[i];
            }
            MergeSort(left);
            MergeSort(right);
            Merge(arr,left,right);
        }
    }
    private void Merge(int[] arr, int[] left, int[] right) {
        int i,j,k;
        i=j=k=0;
        while(i<left.length && j<right.length){
            if(left[i]<right[j]){
                arr[k]=left[i];
                i++;
            }
            else{
                arr[k]=right[j];
                j++;
            }
            k++;
        }
        while(i<left.length){
            arr[k]=left[i];
            i++;k++;
        }
        while(j<right.length){
            arr[k]=right[j];
            j++;k++;
        }
    }
    private static int Partition(int[] arr, int low, int high) {
        int pivot=arr[high];
        int i=low-1;
        for(int j=low;j<high;j++){
            if(arr[j]<pivot){
                swap(arr,++i,j);
            }
        }
        swap(arr,i+1,high);
        return i+1;
    }
    public void QuickSort(int []arr){
        QuickSort(arr, 0, arr.length-1);
    }
    public void QuickSort(int arr[],int low,int high){
        if(low<high){
            int p=Partition(arr,low,high);
            QuickSort(arr,low,p-1);
            QuickSort(arr,p+1, high);
        }
    }
    public void HeapSort(int arr[]){
        Heap<Integer> heap = new MinHeap<>(arr.length);
        for(int i:arr){
            heap.enque(i);
        }
        int i=0;
        while(!heap.isEmpty()){
            arr[i++] = heap.deque();
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void print(int[] arr) {
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
