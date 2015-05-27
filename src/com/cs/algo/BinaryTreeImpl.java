package com.cs.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * 
 * @author shekharagrawal
 *
 */
public class BinaryTreeImpl implements Iterable<BinaryTree>{
	
	private BinaryTree rootNode;
	
	private void createTree(int value) {
		if(rootNode == null)rootNode = new BinaryTree(value);
	}
	
	private void addNodeToTree(int value) {
		BinaryTree node = rootNode;
		node = addNode(node, value);
	}

/**
 * 	
 * @param node
 * @param value
 */
	private BinaryTree addNode(BinaryTree node, int value) {
		if(node == null){
			node = new BinaryTree(value);
			return node;
		}
		if(value >= node.value){
			node.right = addNode(node.right, value);
		}else{
			node.left = addNode(node.left, value);
		}
		return node;
	}
	
	
	public boolean isBST(){
		BinaryTree node = rootNode;
		return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(BinaryTree node, int minValue, int maxValue) {
		if(node == null)return true;
		if(node.value < minValue || node.value > maxValue)return false;
		return isBST(node.left, minValue, node.value-1) && isBST(node.right, node.value, maxValue);
	}
	
	
	public void flattenToLinkedList(){
		BinaryTree node = rootNode;
		flattenToLinkedList(node);
	}
/**
 * 
 * @param node
 */
	private void flattenToLinkedList(BinaryTree node) {
		BinaryTree treeNode = node;
		while(treeNode != null){
			BinaryTree rightNode = treeNode.right;
			BinaryTree leftNode = treeNode.left;
			if(leftNode != null){
				treeNode.right = leftNode;
				treeNode.left = null;
				while(leftNode.right != null){
					leftNode = leftNode.right;
				}
				leftNode.right = rightNode;
			}
			treeNode = treeNode.right;
		}
	}
	
	
/**
 * 	Using Depth First Search
 */
	public boolean sumOfPath(int sum, boolean recursion){
		BinaryTree node = rootNode;
		return hasSumPath(node, sum, 0);
		
	}
/**
 * 	
 * @param node
 * @param sum
 */
	private boolean hasSumPath(BinaryTree node, int sum, int sumValue) {
		if(node == null)return false;
		int value = node.value + sumValue;
		if(value == sum && node.left == null && node.right == null)return true;
		return hasSumPath(node.left, sum, value) || hasSumPath(node.right, sum, value);
	}
	
/**
 * 	Using Depth First Search
 */
	private void pathToLeaf(){
		BinaryTree node = rootNode;
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		stack.add(node);
		pathToLeaf(node, stack);
	}
/**
 * 
 * @param node
 * @param stack
 */
	private void pathToLeaf(BinaryTree node, Stack<BinaryTree> stack) {
		if(node.left == null && node.right == null){
			for(BinaryTree item : stack){
				System.out.print(" "+item.value);
			}
			System.out.println();
			return;
		}
		
		if(node.left != null){
			stack.add(node.left);
			pathToLeaf(node.left, stack);
			stack.pop();
		}
		if(node.right != null){
			stack.add(node.right);
			pathToLeaf(node.right, stack);
			stack.pop();
		}
	}

/**
 * Breadth First Search	
 */
	public void SumOfPath(int sum){
		BinaryTree node = rootNode;
		java.util.Queue<BinaryTree> queueList = new LinkedList<BinaryTree>();
		java.util.Queue<ArrayList<Integer>> sumList = new LinkedList<ArrayList<Integer>>();
		
		queueList.add(node);
		ArrayList newList = new ArrayList();
		newList.add(node.value);
		sumList.add(newList);
		
		while(!queueList.isEmpty()){
			BinaryTree treeNode = queueList.poll();
			if(treeNode.right == null && treeNode.left == null){
			}else{
				newList = sumList.poll();
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.addAll(newList);
				if(treeNode.left != null){
					queueList.offer(treeNode.left);
					list.add(treeNode.left.value);
					sumList.offer(list);
				}
				list = new ArrayList();
				list.addAll(newList);
				if(treeNode.right != null){
					queueList.add(treeNode.right);
					list.add(treeNode.right.value);
					sumList.offer(list);
				}
			
			}
		}
		
		System.out.println(""+sumList);
		
	}

	public static void main(String[] args){
		
		BinaryTreeImpl impl = new BinaryTreeImpl();
		//int[] inOrder = {20, 23, 45, 100, 112, 135, 150};
		int[] inOrder = {1, 2, 3, 5, 7, 9, 10};
		int[] postOrder = {20, 23, 100, 45, 135, 150, 112};
		
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		for(int i=0; i< inOrder.length; i++){
			sortedList.add(inOrder[i]);
		}
		//BinaryTree node = impl.sortedBST(sortedList);
		//BinaryTree node = impl.sortedArrayToBST(inOrder);
		//impl.levelOrder(node);
		//printLine();
		//impl.getNumber(node);
		//impl.levelOrder(impl.buildTree(inOrder, postOrder));
		
		impl.createTree(112);
		impl.addNodeToTree(45);
		impl.addNodeToTree(23);
		impl.addNodeToTree(150);
		impl.addNodeToTree(135);
		impl.addNodeToTree(100);
		impl.addNodeToTree(50);
		impl.addNodeToTree(110);
		impl.addNodeToTree(65);
		impl.addNodeToTree(20);
		impl.inOrder(true);
		impl.levelOrder();
		printLine();
		impl.removeNode(45);
		printLine();
		impl.inOrder(true);
		
		/*
		for(BinaryTree tree : impl){
			System.out.println("From Iterator "+tree.value);
		}*/
		
	//	System.out.println(impl.sumOfPath(257, true));
		//impl.SumOfPath(257);
		//impl.pathToLeaf();
		/*
		impl.inOrder(true);
		impl.inOrder(false);
		printLine();
		impl.preOrder(true);
		impl.preOrder(false);
		printLine();
		impl.postOrder(true);
		printLine();
		System.out.println("INTO Flatten List : ");
		impl.flattenToLinkedList();
		impl.inOrder(true);
		printLine();
		impl.levelOrder();
		printLine();
		*/
		//System.out.println(" Boolean : "+impl.isBST());
	}
	
/**
 * 	
 * @param value
 */
	public void removeNode(int value){
		BinaryTree node = rootNode;
		BinaryTree parentNode = getParent(value, node);
		if(parentNode != null){
			System.out.println("Parent Node Value : "+parentNode.value);
		}
		
		if(parentNode.value > value){
			BinaryTree currentNode = parentNode.left;
			removeNode(currentNode, parentNode, 0);
		}else{
			BinaryTree currentNode = parentNode.right;
			removeNode(currentNode, parentNode, 1);
		}
	}
	
/**
 * 	
 * @param currentNode
 * @param parentNode
 */
private void removeNode(BinaryTree currentNode, BinaryTree parentNode, int child) {
	if(currentNode.left != null && currentNode.right != null){ // If Node has both the childs
		
		BinaryTree nextElement = getNextLargestElement(currentNode);
		nextElement.left = currentNode.left;
		nextElement.right = currentNode.right;
		if(child == 0){
			parentNode.left = nextElement;
		}else if(child == 1){
			parentNode.right = nextElement;
		}
	}else if(currentNode.right != null || currentNode.left != null){ //If Node has only child.
		if(currentNode.right != null){
			if(child == 0){
				parentNode.left = currentNode.right;
			}else if(child == 1){
				parentNode.right = currentNode.right;
			}
		}else if(currentNode.left != null){
			if(child == 0){
				parentNode.left = currentNode.left;
			}else if(child == 1){
				parentNode.right = currentNode.left;
			}
		}
	}else{ //If Node has no child at all.
		if(child == 0){
			parentNode.left = null;
		}else{
			parentNode.right = null;
		}
	}
	
}
/**
 * This method find the Next Largest Element in the Tree and Remove it from the Tree
 * @param currentNode
 */
private BinaryTree getNextLargestElement(BinaryTree currentNode) {
	BinaryTree node = currentNode;
	BinaryTree rightNode = node.right;
	
	if(rightNode.left != null){
		while(rightNode.left != null){
			rightNode = rightNode.left;
		}
		removeNode(rightNode.value);
		return rightNode;
	}else{
		System.out.println("Removing the Element : "+rightNode.value);
		removeNode(rightNode.value);
		return rightNode;
	}
}

/**
 * 	
 * @param value
 * @return
 */
	private BinaryTree getParent(int value, BinaryTree node) {
		if(node == null) return null;
		if(node.value == value) return null;
		if(node.value > value){
			if(node.left != null && node.left.value == value)return node;
			return getParent(value, node.left);
		}else{
			if(node.right != null && node.right.value == value)return node;
			return getParent(value, node.right);
		}
	}

/**
 * 	
 * @param node
 */
	private void getNumber(BinaryTree node) {
		Integer sum = new Integer(0);
		getNumber(node, node.value);
	}
/**
 * 
 * @param node
 * @param value
 */
private void getNumber(BinaryTree node, int value) {
	if(node.left == null && node.right == null){
		System.out.println("Value :  "+value);
	}
	if(node.left != null){
		getNumber(node.left, value*10+node.left.value);
	}
	
	if(node.right != null){
		getNumber(node.right, value*10+node.right.value);
	}
}

/**
 * 	
 */
	private BinaryTree sortedBST(ArrayList sortedList) {
		return sortedBST(sortedList, 0, sortedList.size()-1);
	}
	
	private static int listIndex = 0;
/**
 * 
 * @param sortedList
 * @param i
 * @param j
 * @param k
 */
	private BinaryTree sortedBST(ArrayList sortedList, int start, int end) {
		if(start > end)return null;
		
		int middle = (start+end)/2;
		//System.out.println(" Start : "+start+" Middle : "+middle+" End : "+end);
		BinaryTree left = sortedBST(sortedList, start, middle-1);
		//System.out.println("Creating : "+listIndex);
		BinaryTree root = new BinaryTree((Integer)sortedList.get(listIndex));
		listIndex++;
		BinaryTree right = sortedBST(sortedList, middle+1, end);
		root.left = left;
		root.right = right;
		
		return root;
	}

/**
 * 	
 * @param inOrder
 */
	private BinaryTree sortedArrayToBST(int[] inOrder) {
		if(inOrder.length <= 0)return null;
		return sortedArrayToBST(inOrder, 0, inOrder.length-1);
	}
/**
 * 
 * @param inOrder
 * @param i
 * @param j
 */
	private BinaryTree sortedArrayToBST(int[] inOrder, int start, int end) {
		if(start > end)return null;
		int middle = (start + end)/2;
		BinaryTree root = new BinaryTree(inOrder[middle]);
		root.left = sortedArrayToBST(inOrder, start, middle-1);
		root.right = sortedArrayToBST(inOrder, middle+1, end);
		return root;
	}

	public BinaryTree buildTree(int[] inOrder, int[] postOrder){
		
		int inStart = 0;
		int inEnd = inOrder.length-1;
		int postStart = 0;
		int postEnd = postOrder.length-1;
		
		return buildTree(inOrder, inStart, inEnd, postOrder, postStart, postEnd);
		
	}
/**
 * 	
 * @param inOrder
 * @param inStart
 * @param inEnd
 * @param postOrder
 * @param postStart
 * @param postEnd
 * @return
 */
	private BinaryTree buildTree(int[] inOrder, int inStart, int inEnd,
			int[] postOrder, int postStart, int postEnd) {
		
		if(inStart > inEnd || postStart > postEnd)return null;
		
		int rootValue = postOrder[postEnd];
		//System.out.println(""+rootValue);
		BinaryTree root = new BinaryTree(rootValue);
		int k=0;
		for(int i=0; i< inOrder.length;i++){
			if(inOrder[i] == rootValue){
				k = i;
				break;
			}
		}
		root.left = buildTree(inOrder, inStart, k-1, postOrder, postStart, postStart + k-(1 + inStart));
		//System.out.println("Going into Right Side: ");
		root.right = buildTree(inOrder, k+1, inEnd, postOrder, postStart + k - inStart, postEnd-1);
		return root;
	}

/**
 * 	
 */
	private static void printLine() {
		System.out.println("=====================================");
	}

/**
 * 
 */
	private void printTree() {
		BinaryTree node = rootNode;
		printTree(node);
	}
/**
 * 
 * @param node
 */
	private void printTree(BinaryTree node) {
		System.out.println("Node : "+node.value);
		if(node.left != null)printTree(node.left);
		if(node.right != null)printTree(node.right);
	}
	
/**
 * LEVEL ORDER	
 */
	public void levelOrder(){
		BinaryTree node = rootNode;
		System.out.println("LEVEL ORDER : ");
		levelOrder(node);
	}
	


/**
 * In Order : left - root - right	
 */
	public void inOrder(boolean recursive){
		BinaryTree node = rootNode;
		if(recursive){
			System.out.print("IN ORDER(R) : ");
			inOrder(node);
		}else{
			System.out.print("IN ORDER :    ");
			inOrderIterative(node);
		}
		System.out.println();
	}
	


/**
 * Post Order : left right root	
 */
	public void postOrder(boolean recursive){
		BinaryTree node = rootNode;
		
		if(recursive){
			System.out.print("POST ORDER(R) : ");
			postOrder(node);
		}else{
			System.out.print("POST ORDER    : ");
			postOrderIterative(node);
		}
		System.out.println();
	}
	
/**
 * Pre Order : root - left - right;
 * 	
 */
	public void preOrder(boolean recursive){
		BinaryTree node = rootNode;
		
		if(recursive){
			System.out.print("PRE ORDER (R): ");
			preOrder(node);
		}else{
			System.out.print("PRE ORDER:     ");
			preOrderIteative(node);
		}
		System.out.println();
	}
	


/**
 * In Order : left - root - right
 * @param node
 */
	private void inOrder(BinaryTree node) {
		if(node == null)return;
		inOrder(node.left);
		System.out.print(" "+node.value);
		inOrder(node.right);
	}
	

/**
 * 
 * @param node
 */
	private void preOrder(BinaryTree node) {
		if(node == null)return;
		System.out.print(" "+node.value);
		preOrder(node.left);
		preOrder(node.right);
	}

/**
 * 
 * @param node
 */
	private void postOrder(BinaryTree node) {
		if(node == null)return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(" "+node.value);
	}
/**
 * 	
 * @param node
 */
	private void inOrderIterative(BinaryTree node) {
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		while(node != null){
			stack.add(node);
			node = node.left;
		}
		
		while(!stack.isEmpty()){
			BinaryTree leftNode = stack.pop();
			System.out.print(" "+leftNode.value);
			if(leftNode.right != null){
				BinaryTree rightNode = leftNode.right;
				while(rightNode != null){
					stack.push(rightNode);
					rightNode = rightNode.left;
				}
			}
			
		}
		
	}
/**
 * 	
 * @param node
 */
	private void preOrderIteative(BinaryTree node) {
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		while(node != null){
			System.out.print(" "+node.value);
			stack.push(node);
			node = node.left;
		}
		
		while(!stack.isEmpty()){
			BinaryTree treeNode = stack.pop();
			if(treeNode.right != null){
				BinaryTree rightNode = treeNode.right;
				System.out.print(" "+rightNode.value);
				stack.push(rightNode);
				while(rightNode.left != null){
					rightNode = rightNode.left;
					stack.push(rightNode);
					System.out.println(" "+rightNode.value);	
				}
			}
		}
		
	}
/**
 * 	
 * @param node
 */
	private void postOrderIterative(BinaryTree node) {
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		while(node != null){
			stack.push(node);
			node = node.left;
		}
		
		while(!stack.isEmpty()){
			BinaryTree treeNode = stack.peek();
			if(treeNode.right != null){
				while(treeNode.right != null){
					treeNode = treeNode.right;
					stack.push(treeNode);
					if(treeNode.left != null){
						while(treeNode.left != null){
							treeNode = treeNode.left;
							stack.push(treeNode);
						}
					}
				}
				System.out.print(" "+treeNode.value);
			}else{
				treeNode = stack.pop();
				System.out.print(" "+treeNode.value);
			}
		}
		
	}
/**
 * 	
 * @param node
 */
	private void levelOrder(BinaryTree node) {
		java.util.Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
		java.util.Queue<ArrayList<BinaryTree>> levelList = new LinkedList<ArrayList<BinaryTree>>();
		queue.add(node);
		ArrayList<BinaryTree> nodeList = new ArrayList<BinaryTree>();
		nodeList.add(node);
		levelList.add(nodeList);
		
		nodeList = new ArrayList<BinaryTree>();
		
		while(!queue.isEmpty()){
			BinaryTree treeNode = queue.poll();
			if(treeNode.left != null)
				nodeList.add(treeNode.left);
			if(treeNode.right != null)
				nodeList.add(treeNode.right);
			
			if(queue.isEmpty() && nodeList.size() > 0){
				queue.addAll(nodeList);
				levelList.add(nodeList);
				nodeList = new ArrayList<BinaryTree>();
			}
		}
		
		System.out.println("Printing Bottom UP");
		while(!levelList.isEmpty()){
			ArrayList<BinaryTree> treeList = levelList.poll();
			for(BinaryTree tree : treeList){
				System.out.print(" "+tree.value);
			}
			System.out.println();
		}
	}

	public BinaryTreeIterator iterator() {
		return new BinaryTreeIterator(rootNode);
	}
	
}

class BinaryTreeIterator implements Iterator<BinaryTree>{
	
	private BinaryTree currentNode;
	private Stack<BinaryTree> nodeList = new Stack<BinaryTree>();
	
	public BinaryTreeIterator(BinaryTree node) {
		if(node == null) System.out.println("No value exist in the Tree");
		while(node.left != null){
			nodeList.add(node);
			node = node.left;
		}
		nodeList.add(node);
		currentNode = node;
	}

	public boolean hasNext() {
		if(nodeList.size() > 0) return true;
		return false;
	}

	public BinaryTree next() {
		if(hasNext()){
			currentNode = nodeList.pop();
			if(currentNode.right != null){
				BinaryTree rightNode = currentNode.right;
				nodeList.push(rightNode);
				while(rightNode.left != null){
					rightNode = rightNode.left;
					nodeList.push(rightNode);
				}
			}
			return currentNode;
		}else{
			return null;
		}
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}




