import java.util.ArrayDeque;

/* Time complexity : 
 * adding elements -  worst case O(n)
 *  case: 6 -> 3 -> 2 -> 1 -> 4 
 *  then every other element would have to be removed before 6 (max) is added
 *  getting max - worst case O(1)
 *  the max will always be on top
 *  
 *  Space complexity :
 *  worst case O(n) - descending elements 
 *  case: main :  2 -> 3 -> 4 -> 5 -> 6
 *  	  max :  2 -> 3 -> 4 -> 5 -> 6								
*/
public class ContantTimeMax {
	public static void main(String[] args) {
		// Main deque which will just act as a queue
		ArrayDeque<Integer> main = new ArrayDeque<Integer>();
		
		// Max deque because removing from front and back is important
		ArrayDeque<Integer> max = new ArrayDeque<Integer>();
		
		// main : 3 -> 2 -> 1 -> 4 -> 6
		// max :  3 -> 4 -> 6
		// max element : 6
		add(6,main,max);
		add(4,main,max);
		add(1,main,max);
		add(2,main,max);
		add(3,main,max);
		
		System.out.println(getMax(max));
		
		// main : 3 -> 2 -> 1 -> 4 
		// max : 3 -> 4 
		// max element : 4
		poll(main,max);
		
		System.out.println(getMax(max));
		
		
	}
	
	// add method
	public static void add(int element, ArrayDeque<Integer> main, ArrayDeque<Integer> max) {
		// always add to the main
		main.add(element);
		
		// if the max stack is empty then the first element will be the max
		if(max.isEmpty()) {
			max.add(element);
		}
		// if the last element added is the max then every other element doesnt matter
		// so we remove those and add the new max
		else if(max.peek() < element) {
			while(!max.isEmpty()) {
			max.remove();
			}
			
			max.add(element);
		}
		// if the last element is greater than the element we want to add 
		// then we still have to add it because if the element is polled the one after it
		// can become the max
		else if(max.peekLast() >= element) {
			max.offerLast(element);
		}
		// in the case of 2 -> 1 -> 4 
		// 1 cant be the max so it has to be removed and replaced by 2 in the queue
		else if(max.peekLast() < element) {
			max.removeLast();
			max.offerLast(element);
		}
	}
	
	// poll method
	// because the add method also accounts for dups this method needs only to check if the values match
	public static void poll(ArrayDeque<Integer> main, ArrayDeque<Integer> max) {
		if(max.peek().equals(main.poll())) {
			max.poll();
		}
		
	}
	
	// get max from max deque method
	// should be the top of the deque
	public static int getMax(ArrayDeque<Integer> max) {
		return max.peek();
	}
	
	// print method used to troubleshoot
	public static void printDeque(ArrayDeque<Integer> deque) {
		for(Object obj : deque.toArray()) {
			System.out.print(obj + " ");
		}
		System.out.println();
	}
	
	

}
