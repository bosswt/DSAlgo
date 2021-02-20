
public class IntegerList extends CDLinkedList {

	public IntegerList() {
		super();

	}

	public IntegerList(int[] a) throws Exception {
		super(a);

	}

	/////////////////////////////////////////////////////////
	public void removeOddValue() throws Exception {// remove all odd numbers
		if (isEmpty())
			return;
		DListIterator itr = new DListIterator(header);
		while (itr.hasNext() && itr.currentNode.nextNode != header) {
			Object data = itr.next();
			Integer num = (Integer) data;
			if (num % 2 == 1)
				removeAt(itr);
		}

	}

	public void removeRange(DListIterator itr1, DListIterator itr2, int rangeSize) throws Exception {
		while (itr1.hasNext() && itr1.currentNode != itr2.currentNode) {
			removeAt(itr1);
			itr1.next();
		}
		removeAt(itr1);

	}

	public void evenToFront() throws Exception { // move even data to the front (must preserve order)
		DListIterator itr = new DListIterator(header);
		DListIterator insertItr = new DListIterator(header);
		while (itr.hasNext() && itr.currentNode.nextNode != header) {
			Object data = itr.next();
			Integer num = (Integer) data;
			if (num % 2 == 0) {
				insert(itr.currentNode.data, insertItr);
				insertItr.next();
				removeAt(itr);
			}
		}

	}

	public int sum() throws Exception {// add the value of all data
		if (isEmpty())
			return 0;
		DListIterator itr = new DListIterator(header);
		int sum = 0;
		while (itr.hasNext() && itr.currentNode.nextNode != header) {
			Object data = itr.next();
			Integer num = (Integer) data;
			sum += num;
		}
		return sum;
	}

	public void appendToBack(IntegerList l) throws Exception { // append list l to the back of this list
		// must be done in constant time. l will not be used again in the future.
		if (l.isEmpty())
			return;
		DListIterator lItr = new DListIterator(l.header);
		lItr.next();
		DListIterator itr = new DListIterator(header);
		while (itr.hasNext() && itr.currentNode.nextNode != header)
			itr.next();
		itr.currentNode.nextNode = lItr.currentNode;
		lItr.next();
		lItr.currentNode.previousNode = itr.currentNode;
		while (lItr.hasNext() && lItr.currentNode.nextNode != l.header)
			lItr.next();
		itr.currentNode.previousNode = lItr.currentNode;
		lItr.currentNode.nextNode = header;
		size += l.size;
	}

	public void insertList(DListIterator itr, IntegerList l) throws Exception {
		if (l.isEmpty())
			return;
		DListIterator lItr = new DListIterator(l.header);
		lItr.next();
		lItr.currentNode.previousNode = itr.currentNode;
		DListNode lastNodeAfterInsertion = itr.currentNode.nextNode;
		itr.currentNode.nextNode = lItr.currentNode;
		while (lItr.hasNext() && lItr.currentNode.nextNode != l.header)
			lItr.next();
		lItr.currentNode.nextNode = lastNodeAfterInsertion;
		size += l.size;

	}

}
