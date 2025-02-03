package il.ac.telhai.ds.heap;




public class MinHeap<T extends Comparable<T>> {

	private T[] data;
	private int size;

	// Ctor for empty array
	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(int length) {
		data = (T[]) new Comparable[length + 1]; // For 1 based index
		size = 0;
	}

	// Ctor from array
	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(T[] arr) {
		size = arr.length;
		data = (T[]) new Comparable[size + 1];
		for (int i = 0; i < size; i++) {
			data[i + 1] = arr[i]; // Start at index 1 - for 1 based index
		}

		// Heapify array (from the end)
		for (int i = size / 2; i > 0; i--) {
			SiftDown(i);
		}
	}

	private void SiftDown(int i) {
		int smallest = i;
		int left = getLeft(i);
		int right = getRight(i);

		// Check left child
		if (left <= size && data[left].compareTo(data[smallest]) < 0) {
			smallest = left;
		}

		// Check right child
		if (right <= size && data[right].compareTo(data[smallest]) < 0) {
			smallest = right;
		}

		// Swap and continue sifting down
		if (smallest != i) {
			swap(i, smallest);
			SiftDown(smallest);
		}
	}

	//Helper for swapping
	private void swap(int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	private int getParent(int i) {
		return i / 2;
	}

	private int getLeft(int i) {
		return i * 2;
	}

	private int getRight(int i) {
		return i * 2 + 1;
	}

	public boolean isFull() {
		return size == data.length - 1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(T element) {
		if (isFull()) {
			throw new IllegalStateException("Heap is full");
		}

		// Insert to end
		data[++size] = element;

		// Sift to keep heap rules
		int current = size;
		while (current > 1 && data[current].compareTo(data[getParent(current)]) < 0) {
			swap(current, getParent(current));
			current = getParent(current);
		}
	}

	public T getMin() {
		if (isEmpty()) throw new IllegalStateException("Heap is empty");
		return data[1];
	}

	public T deleteMin() {
		if (isEmpty()) return null;

		T deleted = data[1];
		data[1] = data[size];
		data[size--] = null;

		if (size > 0) {
			SiftDown(1);
		}

		return deleted;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 1; i <= size; i++) {
			sb.append(data[i]);
			if (i < size) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}

