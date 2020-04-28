public class LinkedListQueue{
	private Node first;
	private Node last;

	private class Node{
		private String item;
		private Node next;
	}

	public void enqueue(String i){
		Node n = new Node();
		n.item = i;

		if(is_empty()){
			this.last = n;
			this.first = n;
		}
		else{
			Node old_last = this.last;
			this.last = n;
			old_last.next = this.last;
		}
	}

	public String dequeue(){
		Node old_first = this.first;
		this.first = old_first.next;
		if(is_empty()) last = null;
		return old_first.item;
	}

	
	public boolean is_empty(){ return this.first == null; }

	public void display_queue(){
		System.out.println(this.first.item);
		System.out.println(this.last.item);

	}


	public static void main(String[] args){


		LinkedListQueue l = new LinkedListQueue();

		l.enqueue("1");
		// l.display_queue();
		l.enqueue("2");
		// l.display_queue();
		l.enqueue("3");
		// l.display_queue();
		l.dequeue();
		l.dequeue();
		// l.display_queue();
		l.dequeue();
		System.out.println(l.is_empty());

		l.enqueue("7");
		// l.display_queue();


		
	}


}