package com.cs.datastructure;

public class CircularQueueImpl {

	public static void main(String[] args) {
		CircularQueue queue = new CircularQueue(3);
		queue.addElement(3);
		queue.addElement(4);
		queue.addElement(5);
		queue.addElement(10);
		
		System.out.println(queue.popElement());
		queue.addElement(10);
		System.out.println(queue.popElement());
		System.out.println(queue.popElement());
		System.out.println(queue.popElement());
	}

}

class CircularQueue{
	
	int size;
	int head;
	int tail;
	int counter;
	int[] queueArray;

	/**
	 * 
	 */
	public CircularQueue(int size){
		queueArray = new int[size];
		this.head = 0;
		this.tail = 0;
		counter = 0;
		this.size = size;
	}
	
/**
 * 	
 */
	public void addElement(int item){
		if(counter == size){
			System.out.println("Queue is not empty, please remove items in order to insert more items!");
		}else{
			queueArray[tail] = item;
			tail = (tail + 1)%size;
			counter++;
		}
	}
/**
 * 	
 * @return
 */
	public int popElement(){
		if(counter == 0){
			System.out.println("No item to remove from the Circular array!");
			return -1;
		}else{
			int pop = queueArray[head];
			counter--;
			head = (head+1)%size;
			return pop;
		}
	}
	
}





