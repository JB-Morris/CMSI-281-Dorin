import java.lang.IllegalArgumentException;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.lang.StringBuilder;

public class LinkedDeque<T> {

	private int size;
	private Node<T> head;
	private Node<T> current;
	
	public LinkedDeque () {
		this.size = 0;
		this.head = null;
		this.current = this.head;
	}	

	public void insertLeft (T o) {
		Node<T> newNode = new Node<T>(o);

		if(this.size == 0){
			this.head = newNode;
			this.current = this.head;
			this.size++;
		}else{
			this.head.setPrevious(newNode);
			this.head.getPrevious().setNext(this.head);
			this.head = this.head.getPrevious();
			this.size++;
		}
		
		// throw new UnsupportedOperationException();
	}

	public void insertRight (T o) {
		Node<T> newNode = new Node<T>(o);

		if(this.size == 0){
			this.head = newNode;
			this.current = this.head;
			this.size++;
		}else{
			newNode.setPrevious(this.current);
			this.current.setNext(newNode);
			this.current = this.current.getNext();
			this.size++;

		}
		// throw new UnsupportedOperationException();
	}

	public void deleteLeft() {
		if(this.head == null){
			throw new IllegalStateException("Cannot delete from an empty deque.");
		}
		if(this.head.hasNext()){
			this.head = this.head.getNext();
			this.head.setPrevious(null);
			this.size--;
		}else{
			this.head = null;
			this.current = null;
			this.size = 0;
		}
		
		// throw new UnsupportedOperationException();
	}

	public void deleteRight() {
		if(this.head == null){
			throw new IllegalStateException("Cannot delete from an empty deque.");
		}
		if(this.size == 1){
			this.head = null;
			this.current = null;
			this.size--;
		}else{
			this.current = this.current.getPrevious();
			this.current.setNext(null);
			this.size--;
		}

		// throw new UnsupportedOperationException();
	}

	public T left() {
		if(this.size == 0){
			throw new IllegalStateException("Cannot return element from empty deque.");
		}
		return this.head.getValue();
		// throw new UnsupportedOperationException();
	}

	public T right() {
		if(this.size == 0){
			throw new IllegalStateException("Cannot return element from empty deque.");
		}
		return this.current.getValue();
		// throw new UnsupportedOperationException();
	}

	public int size() {
		return this.size;
		// throw new UnsupportedOperationException();
	}

	public String toString() {

		StringBuilder output = new StringBuilder("[");
		Node<T> stringNode = this.head;
		for(int i = 0; i < this.size; i++){
			output.append(stringNode.getValue().toString());
			if(i < this.size - 1){
				output.append(", ");
			}
			stringNode = stringNode.getNext();
		}
		output.append("]");
		return output.toString();

		// throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {

		LinkedDeque<Integer> lDeque = new LinkedDeque<Integer>();
		lDeque.insertLeft(5);
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());
		lDeque.insertLeft(6);
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());
		lDeque.insertRight(4);
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());
		lDeque.deleteRight();
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());
		lDeque.deleteLeft();
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());
		System.out.println(lDeque.left());
		System.out.println(lDeque.right());
		lDeque.insertLeft(4);
		lDeque.insertRight(6);
		System.out.println(lDeque.left());
		System.out.println(lDeque.right());
		lDeque.insertLeft(3);
		lDeque.insertRight(7);
		lDeque.insertLeft(2);
		lDeque.insertRight(8);
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());
		lDeque.deleteRight();
		lDeque.deleteLeft();
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());
		lDeque.deleteRight();
		lDeque.deleteRight();
		lDeque.deleteRight();
		lDeque.deleteRight();
		lDeque.deleteRight();
		System.out.println(lDeque.toString());
		System.out.println("Size: " + lDeque.size());

		
		// System.out.println(lDeque.left());
		// System.out.println(lDeque.right());
		// lDeque.deleteRight();
		// throw new UnsupportedOperationException();
	}

	private class Node<T> {

		private T component;
		private Node<T> next = null;
		private Node<T> previous = null;

		public Node(T component) {
			this.component = component;
		}

		public T getValue() {
			return this.component;
		}

		public void setValue(T value) {
			this.component = value;
		}

		public Node<T> getNext() {
			return this.next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node<T> getPrevious() {
			return this.previous;
		}

		public void setPrevious(Node<T> previous) {
			this.previous = previous;
		}

		public boolean hasNext(){
			if(this.next == null){
				return false;
			}
			return true;
		}

	}
}