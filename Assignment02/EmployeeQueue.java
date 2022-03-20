
class EmployeeQueue {
	Node[] employeeNodes;
	int head,tail,capacity,size;

	public EmployeeQueue () {
		this.employeeNodes = new Node[2];
		head = -1;
		tail = 0;
		capacity = 2;
		size = 0;
	}

	public Node top() {
		return employeeNodes[head];
	}


	// pop top element
	// O(1)
	public Node pop() {
		Node temp = employeeNodes[head];
		if (size == 1) {
			head = -1;
			tail = 0;
		} else {
			head++;
		}
		size--;
		return temp;
	}

	// adding element to queue
	// O(1)
	public void enqueue(Node node) {
		if (head == -1)  {
			head++;
			tail++;
			employeeNodes[head] = node;
			
		} else if (tail == capacity) {
			Node[] newQueue = new Node[2*capacity];
			int j = head;
			for (int i=0; i<tail-head; i++) {
				newQueue[i] = employeeNodes[j];
				j++;
			}
			
			capacity = 2*capacity;
			employeeNodes = newQueue;
			employeeNodes[tail-head] = node;
			tail = (size+1);
			head = 0;
		} else {
			employeeNodes[tail] = node;
			tail = tail+1;
		}
		size++;
	}


	// Quicksort the elements of the queue
	// O(nlog(n))
	public void quickSort() {
		quickSort(head, tail-1);
	}
	private void quickSort(int startIndex, int endIndex) {
		if (endIndex<=startIndex) {
			return;
		}
		int leftIndex = startIndex;
		int rightIndex = endIndex;
		int pivot = employeeNodes[rightIndex].getId();
		
		while (leftIndex < rightIndex) {
			while (employeeNodes[leftIndex].getId() <= pivot && leftIndex < rightIndex) {
				leftIndex++;
			}
			while (employeeNodes[rightIndex].getId() >= pivot && leftIndex < rightIndex) {
				rightIndex--;
			}
			swap(leftIndex, rightIndex);
		}
		swap(leftIndex, endIndex);

		quickSort(startIndex, leftIndex-1);
		quickSort(leftIndex+1, endIndex);
	}

	private void swap (int firstIndex, int secondIndex) {
		Node temp = employeeNodes[firstIndex];
		employeeNodes[firstIndex] = employeeNodes[secondIndex];
		employeeNodes[secondIndex] = temp;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int size() {
		return this.size;
	}

}
