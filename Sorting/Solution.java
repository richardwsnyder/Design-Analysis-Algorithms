public class Solution {

    int comparisonCount;
    int[] sortingArray;

    Solution(int[] input)
    {
        sortingArray = input;
        comparisonCount = 0;
    }


    public static void main(String[] args) {
        // TODO code application logic here
    }

    public void merge(int[] a, int p, int q, int r) {
    	int n1 = q - p + 1;
    	int n2 = r - q;
    	int[] left = new int[n1];
    	int[] right = new int[n2];
    	int i, j, k;

    	for(i = 0; i < n1; i++)
    		left[i] = a[p + i];
    	for(j = 0; j < n2; j++)
    		right[j] = a[q + j + 1];

    	i = 0; j = 0;

        for(k = p; k <= r; k++)
        {
            if(i < n1)
            {
                if(j < n2)
                {
                    if(left[i] < right[j])
                        a[k] = left[i++];
                    else
                        a[k] = right[j++];
                }
                else
                    a[k] = left[i++];
            }
            else
                a[k] = right[j++];
            comparisonCount++;
        }
    }

    public void mergeSort(int p, int r) {
        // TODO
        if(p < r)
        {
        	int q = (p + r) / 2;
        	mergeSort(p, q);
        	mergeSort(q+1, r);
        	merge(sortingArray, p, q, r); 
        }
    }

    public void insertionSort() {
        // TODO
        int i, j, length = sortingArray.length, key;
        for(j = 1; j < length; j++) 
        {
        	key = sortingArray[j];
        	i = j - 1;
        	while(i >= 0 && sortingArray[i] > key)
        	{
        		comparisonCount++;
        		sortingArray[i+1] = sortingArray[i];
        		i = i-1;
        	}
            // if i == -1, then you reached the 
            // end of the array and there is no additional
            // comparison. Else, you failed on the comparison
            // and must add one to the final count
        	if(i != -1)
                comparisonCount++;
        	sortingArray[i+1] = key;
        }
    }

    public static int Left(int i) {
    	return (2 * i) + 1;
    }

    public static int Right(int i) {
    	return (2 * i) + 2;
    }

    public static int Parent(int i) {
    	return (i-1) / 2;
    }

    public void swap(int i, int j) {
    	int temp = sortingArray[i];
    	sortingArray[i] = sortingArray[j];
    	sortingArray[j] = temp;
    }

    public void maxHeapify(int i, int heapSize) {
    	int l = Left(i);
    	int r = Right(i);
    	int largest = i;

        if(l < heapSize)
        {
            if(sortingArray[l] > sortingArray[i])
                largest = l;
            comparisonCount++;
        }

        if(r < heapSize)
        {
            if(sortingArray[r] > sortingArray[largest])
                largest = r;
            comparisonCount++;
        }

    	if(largest != i)
    	{
    		swap(i, largest);
    		maxHeapify(largest, heapSize);
    	}
    }

    public void buildMaxHeap() {
    	int heapSize = sortingArray.length;
    	int i, length = sortingArray.length;

    	for(i = length / 2; i >= 0; i--)
    		maxHeapify(i, heapSize);
    }

    public void printHeap() {
        int i, length = sortingArray.length;
        for(i = 0; i < length; i++)
            System.out.print(sortingArray[i] + " ");
        System.out.println();
    }

    public void heapSort()  {
        // TODO
        buildMaxHeap();
        int i, length = sortingArray.length;
        int heapSize = sortingArray.length;

        for(i = length - 1; i > 0; i--)
        {
        	swap(0, i);
        	heapSize--;
        	maxHeapify(0, heapSize);
        }
    }

}
