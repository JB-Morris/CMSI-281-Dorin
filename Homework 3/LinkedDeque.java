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
		throw new UnsupportedOperationException();
	}

	public void insertRight (T o) {
		throw new UnsupportedOperationException();
	}

	public void deleteLeft() {
		throw new UnsupportedOperationException();
	}

	public void deleteRight() {
		throw new UnsupportedOperationException();
	}

	public T left() {
		throw new UnsupportedOperationException();
	}

	public T right() {
		throw new UnsupportedOperationException();
	}

	public int size() {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		throw new UnsupportedOperationException();
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

	}
}