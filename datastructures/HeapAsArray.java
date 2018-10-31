import java.util.Arrays;

public class HeapAsArray {

	int size;
	int capacity;
	int[] heap;

	public HeapAsArray(int capacity) {
		heap = new int[capacity];
		this.capacity = capacity;
		size = 0;
	}

	public void add(int val) {

		if (size < capacity) {
			heap[size] = val;
			size++;
		} else {
			// Check if valid for max heap
			if (compare(val, heap[size-1])) {
				// It's valid
				heap[size-1] = val;
			}
		}

		// Re-Heap with New Value
		int i = size-1;

		while (compare(heap[i], heap[(i-1)/2])) {
			// Bubble Up or Swap Value
			int temp = heap[(i-1)/2];
			heap[(i-1)/2] = heap[i];
			heap[i] = temp;
			i = (i-1)/2;
		}
	}

	public int peek() {
		return heap[0];
	}

	public int pop() {

		if (size == 0) return -1;

		int retVal = heap[0];
		// Remove top
		int i = 0;

		// Bubble up values
		while (i < (capacity/2) ) {
			// Compare right to left
			if (compare(heap[(2*i)+1], heap[(2*i)+2])) {
				// Right is larger than left
				heap[i] = heap[(2*i)+1];
				i = (2*i)+1;
			} else {
				// Left is equal or less than right
				heap[i] = heap[(2*i)+2];
				i = (2*i) + 2;
			}
		}

		// Decrease size by 1
		size--;

		return retVal;
	}

	public boolean compare(int a, int b) {
		if (a > b)
			return true;
		else
			return false;
	}

	public void printHeap() {
		System.out.println(Arrays.toString(heap));
	}

	public static void main(String[] args) {
		HeapAsArray heap = new HeapAsArray(12);

		int N = 20;

		for (int i = 0; i < N; i++) {
//			int randomNum = (int) (Math.random() * (i/5));
			if (i % 5 == 0) {
				System.out.println(heap.pop());
			} else {
				heap.add(i);
			}
			heap.printHeap();
		}

	}
}
