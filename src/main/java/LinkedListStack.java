public class LinkedListStack {
    private Node first = null;

    private class Node{
        String item;
        Node next;
    }

    public void push(String i){
        Node old_first = this.first;
        first = new Node();
        first.item = i;
        first.next = old_first;

    }

    public String pop(){
        String i = this.first.item;
        this.first = this.first.next;
        return i;
    }

    public boolean is_empty(){
        return first == null;
    }


    public void display_current_node(){
        System.out.println("Current: " + this.first.item);
        if(this.first.next!=null){
            System.out.println("Next: " + this.first.next.item);
        }
    }



    public static void main(String[] args){
        LinkedListStack s = new LinkedListStack();
        s.push("1");
        s.display_current_node();

        s.push("2");
        s.pop();
        s.display_current_node();

        s.push("3");
        s.display_current_node();

        s.push("4");
        s.pop();
        s.display_current_node();
        


    }



}
