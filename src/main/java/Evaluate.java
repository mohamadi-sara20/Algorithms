// This is a very very simple form of Dijkstra's algorithm assuming a very simple input: 
// containing only two operators and with the priorities are marked in the input through
// parantheses.   
  
public class Evaluate{
	private GenericResizingStack<Integer> val_stack;
	private GenericResizingStack<Character> op_stack;

	public Evaluate(){
		val_stack = new GenericResizingStack<Integer>();
		op_stack = new GenericResizingStack<Character>();
	}
	
	public int evaluate(String expression){

		for(int i = 0; i < expression.length(); i++){
			if(expression.charAt(i) == '(') continue; 
			
			else if(Character.isDigit(expression.charAt(i))) val_stack.push(Character.getNumericValue(expression.charAt(i)));
				
			else if(expression.charAt(i) == ')') {
				Character operator = op_stack.pop();

				if (operator == '+') {
					System.out.println("Addition");
					int res = val_stack.pop() + val_stack.pop();
					val_stack.push(res);
				}
				else if (operator == '*'){
					int res = val_stack.pop() * val_stack.pop();
					val_stack.push(res);
				}
			}

			else op_stack.push(expression.charAt(i));
		}

		return val_stack.pop();


	}
	

	public static void main(String[] args){
			Evaluate e = new Evaluate();
			String expression = "((2+5)*(1+2))";
			System.out.print(e.evaluate(expression));
	}
}