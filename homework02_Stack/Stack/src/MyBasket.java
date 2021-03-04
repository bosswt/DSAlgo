import java.util.Iterator;

import org.hamcrest.core.IsInstanceOf;

public class MyBasket {
	MyStack s;

	public MyBasket(MyStack s) {
		super();
		this.s = s;
	}

	/////////////////////////////////////////////////////
	/////////////////////////////////////////////////////

	public void removeKthFromTop(int k) throws Exception {
		if (k >= s.size() || k < 0)
			return;
		MyStack tempStack = new StackLinkedList();
		for (int i = 0; i < k; i++) {
			int tempValue = s.top();
			s.pop();
			tempStack.push(tempValue);
		}
		s.pop();
		while (!tempStack.isEmpty()) {
			int tempValue = tempStack.top();
			tempStack.pop();
			s.push(tempValue);
		}
	}

	public void insertKthFromTop(int data, int k) throws Exception {
		if (k > s.size() || k < 0)
			return;
		MyStack tempStack = new StackLinkedList();
		for (int i = 0; i < k; i++) {
			int tempValue = s.top();
			s.pop();
			tempStack.push(tempValue);
		}
		s.push(data);
		while (!tempStack.isEmpty()) {
			int tempValue = tempStack.top();
			tempStack.pop();
			s.push(tempValue);
		}
	}

}
