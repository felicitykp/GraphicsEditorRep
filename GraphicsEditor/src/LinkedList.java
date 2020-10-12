import java.util.AbstractList;

public class LinkedList<T> extends AbstractList<T> {
	
	//variables
	private Node head;
	
	private class Node {
		
		private Node next;
		private T info;
		
		
		public Node(T info) {
			this.info = info;
		}
		
		public Node (T info, Node next) {
			this.next = next;
			this.info = info;
		}
	}

	//adding a node
	public boolean add(T info) {
		
		if (head == null) {
			head = new Node (info);
			return true;
		}
		
		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		
		curr.next = new Node(info);
		
		return true;
	}
	
	public boolean addTo(T info, int index) {
		
		if (head == null) {
			head = new Node (info);
			return true;
		}
		
		Node curr = head;
		
		
		for(int i = 0; i < index-1; i++) {
			curr = curr.next;
		}
		
		//save next link
		Node temp = curr.next;
		
		//add in node
		curr.next = new Node(info);
		
		//connect it to old link
		curr.next.next = temp;
		
		return true;
		
	}

	//removing a node
	public T remove (int index) { //index must be within our list
		
		if (index == 0) {
			Node temp = head;		
			head = temp.next;
			return temp.info;
		}
		
		Node curr = head;
		
		//get to one before the one we are removing
		for(int i = 0; i < index-1; i++) {
			curr = curr.next;
		}
		
		//save value you are removing
		Node temp = curr.next;
		
		//remove link
		if(curr.next.next != null) {
			curr.next = curr.next.next;
		} else {
			curr.next = null;
		}
		
		
		//return removed variable
		return temp.info;
	}

	//get size of Linked List
	public int size() {

		Node temp = head;
		int size = 0;
		
		while(temp != null) {
			size++;
			temp = temp.next;
		}
		
		return size;
	}
	
	public T get(int index) {
		
		Node temp = head;
		
		int current = 0;
		
		while(current != index) {
			temp = temp.next;
			current++;
		}
		
		return temp.info;
	}
	
	//Mr. David's toString -> don't edit
	public String toString() {
		if (size() == 0) return "[ ]";
		String output = "[";
		Node curr = head;
		while (curr != null) {
			output += curr.info.toString() + ", ";
			curr = curr.next;
		}
		return output.substring(0,output.length()-2) +"]";
	}
	
}
