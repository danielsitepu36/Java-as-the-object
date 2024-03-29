import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Sort{
    public static class HeapSort { 
        public void sort(int arr[]){ 
            int n = arr.length; 
        
            // Build heap (rearrange array) 
            for (int i = n / 2 - 1; i >= 0; i--) 
                heapify(arr, n, i); 
        
            // One by one extract an element from heap 
            for (int i=n-1; i>=0; i--) { 
                // Move current root to end 
                int temp = arr[0]; 
                arr[0] = arr[i]; 
                arr[i] = temp; 
            
                // call max heapify on the reduced heap 
                heapify(arr, i, 0); 
            } 
        } 
    
        // To heapify a subtree rooted with node i which is 
        // an index in arr[]. n is size of heap 
        void heapify(int arr[], int n, int i) { 
            int largest = i; // Initialize largest as root 
            int l = 2*i + 1; // left = 2*i + 1 
            int r = 2*i + 2; // right = 2*i + 2 
        
            // If left child is larger than root 
            if (l < n && arr[l] > arr[largest]) 
                largest = l; 
        
            // If right child is larger than largest so far 
            if (r < n && arr[r] > arr[largest]) 
                largest = r; 
        
            // If largest is not root 
            if (largest != i) { 
                int swap = arr[i]; 
                arr[i] = arr[largest]; 
                arr[largest] = swap; 
            
                // Recursively heapify the affected sub-tree 
                heapify(arr, n, largest); 
            } 
        }
    }

    public static class MergeSort { 
        // Merges two subarrays of arr[]. 
        // First subarray is arr[l..m] 
        // Second subarray is arr[m+1..r] 
        void merge(int arr[], int l, int m, int r) { 
            // Find sizes of two subarrays to be merged 
            int n1 = m - l + 1; 
            int n2 = r - m; 
        
            /* Create temp arrays */
            int L[] = new int [n1]; 
            int R[] = new int [n2]; 
        
            /*Copy data to temp arrays*/
            for (int i=0; i<n1; ++i) 
                L[i] = arr[l + i]; 
            for (int j=0; j<n2; ++j) 
                R[j] = arr[m + 1+ j]; 
        
        
            /* Merge the temp arrays */
        
            // Initial indexes of first and second subarrays 
            int i = 0, j = 0; 
        
            // Initial index of merged subarry array 
            int k = l; 
            while (i < n1 && j < n2) { 
                if (L[i] <= R[j]) { 
                    arr[k] = L[i]; 
                    i++; 
                } else { 
                    arr[k] = R[j]; 
                    j++; 
                } 
                k++; 
            } 
        
            /* Copy remaining elements of L[] if any */
            while (i < n1) { 
                arr[k] = L[i]; 
                i++; 
                k++; 
            } 
        
            /* Copy remaining elements of R[] if any */
            while (j < n2) { 
                arr[k] = R[j]; 
                j++; 
                k++; 
            } 
        } 
    
        // Main function that sorts arr[l..r] using 
        // merge() 
        void sort(int arr[], int l, int r) { 
            if (l < r) { 
                // Find the middle point 
                int m = (l+r)/2; 
            
                // Sort first and second halves 
                sort(arr, l, m); 
                sort(arr , m+1, r); 
            
                // Merge the sorted halves 
                merge(arr, l, m, r); 
            } 
        } 
    }

    public static class QuickSort { 
        /* This function takes last element as pivot, 
           places the pivot element at its correct 
           position in sorted array, and places all 
           smaller (smaller than pivot) to left of 
           pivot and all greater elements to right 
           of pivot */
        int partition(int arr[], int low, int high) { 
            int pivot = arr[high];  
            int i = (low-1); // index of smaller element 
            for (int j=low; j<high; j++) { 
                // If current element is smaller than or 
                // equal to pivot 
                if (arr[j] <= pivot) { 
                    i++; 
                
                    // swap arr[i] and arr[j] 
                    int temp = arr[i]; 
                    arr[i] = arr[j]; 
                    arr[j] = temp; 
                } 
            } 
        
            // swap arr[i+1] and arr[high] (or pivot) 
            int temp = arr[i+1]; 
            arr[i+1] = arr[high]; 
            arr[high] = temp; 
        
            return i+1; 
        } 
  
        /* The main function that implements QuickSort() 
          arr[] --> Array to be sorted, 
          low  --> Starting index, 
          high  --> Ending index */
        void sort(int arr[], int low, int high) { 
            if (low < high) { 
                /* pi is partitioning index, arr[pi] is  
                  now at right place */
                int pi = partition(arr, low, high); 
            
                // Recursively sort elements before 
                // partition and after partition 
                sort(arr, low, pi-1); 
                sort(arr, pi+1, high); 
            } 
        }
    }

    public static void printTime(long starttime,long endtime){
        long time = endtime-starttime;
        //System.out.println("Sorting time : " + time + " ns");
        long s = time/1000000000;
        time%=1000000000;
        long ms = time/1000000;
        time%=1000000;
        long ns = time;
        // System.out.println("Sorting time (in nanoseconds) : " + time + " ns");
        // System.out.println("Sorting time (in miliseconds) : " + TimeUnit.NANOSECONDS.toMillis(time) + " ms");
        // System.out.println("Sorting time (in seconds) : " + TimeUnit.NANOSECONDS.toSeconds(time) + " s");
        System.out.println("Sorting time : " + s + "s " + ms + "ms " + ns + "ns");
    }

    public static void main(String[] args) throws NoSuchElementException{
        Scanner input = new Scanner(System.in);
        int i = 0;

        System.out.print("\nInput file name (number) : ");
        String name=input.next();

        File f = new File("randomNumber" + name + ".txt");
        try(Scanner inFile = new Scanner(f)){
            System.out.println("Data found !");
            System.out.print("Input data size : ");
            int size=input.nextInt();
    
            int[] Arr=new int[size];
            int[] Arr2=new int[size];

            System.out.println("\nTransfering number... (may take seconds for big data)");
            while(inFile.hasNextInt()){
                Arr[i] = inFile.nextInt();
                Arr2[i] = Arr[i];
                i++;
            }
            inFile.close();
            System.out.println("Transfer success !");

            String menu;
            boolean end=false;
            long starttime;
            long endtime;
            do{
            for(int j=0;j<size;j++){
                Arr[j]=Arr2[j];
            }
            System.out.println("\nSorting method :");
                System.out.println("A) Heap Sort");
                System.out.println("B) Merge Sort");
                System.out.println("C) Quick Sort");
                System.out.println("D) Exit");
                System.out.print("Your input (A/B/C/D) : ");
                menu = input.next();
                switch (menu) {
                    case "A":
                        System.out.println("\nHEAP SORT STARTED");
                        starttime = System.nanoTime();
                        HeapSort HS = new HeapSort(); 
                        HS.sort(Arr); 
                        endtime = System.nanoTime();
                        System.out.println("SORTED !");
                        printTime(starttime, endtime);
                        break;
                    case "B":
                        System.out.println("\nMERGE SORT STARTED");
                        starttime = System.nanoTime();
                        MergeSort MS = new MergeSort(); 
                        MS.sort(Arr,0,size-1); 
                        endtime = System.nanoTime();
                        System.out.println("SORTED !");
                        printTime(starttime, endtime);
                        break;
                    case "C":
                        System.out.println("\nQUICK SORT STARTED");
                        starttime = System.nanoTime();
                        QuickSort QS = new QuickSort(); 
                        QS.sort(Arr,0,size-1); 
                        endtime = System.nanoTime();
                        System.out.println("SORTED !");
                        printTime(starttime, endtime);
                        break;
                    case "D":
                        end=true;
                        break;
                    default :
                        System.out.println("Wrong input ! Please input A/B/C/D !");
                }
            } while(!end);
        } catch (FileNotFoundException e) {
            System.out.println("Data not found ! Please put your file inside this folder !");
        }
        System.out.println("Thank you !");
        input.close();
    }
}