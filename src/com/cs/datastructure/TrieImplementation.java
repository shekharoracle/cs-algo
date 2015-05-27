package com.cs.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class TrieImplementation {

	public static void main(String[] args){

		TrieImpl impl = new TrieImpl();
		impl.loadPhrase("abc");
		impl.loadPhrase("abcd");
		impl.loadPhrase("abcde");
		impl.loadPhrase("abcdef");
		
		impl.findPhrase("abcd");
		
	}
}

/**
 * 
 * @author shekharagrawal
 *
 */
class TrieImpl{
	
	private TrieNode rootNode;
/**
 * 	
 */
	public TrieImpl(){
		rootNode = new TrieNode(' ');
	}
/**
 * 	
 * @return
 */
	public TrieNode getTrieNode(){
		return this.rootNode;
	}
/**
 * 	
 * @param phrase
 */
	public void loadPhrase(String phrase){
		loadPhraseRecur(rootNode, phrase+"$");
		System.out.println("Added!");
	}
/**
 * 
 * @param string
 */
	private void loadPhraseRecur(TrieNode node, String phrase) {
		if(StringUtils.isBlank(phrase))return;
		
		char firstChar = phrase.charAt(0);
		System.out.print(" "+firstChar);
		node.add(firstChar);
		TrieNode childNode = node.getChildNode(firstChar);
		if(childNode != null){
			loadPhraseRecur(childNode, phrase.substring(1));
		}
	}
	
/**
 * Print All node of Trie	
 */
	public void printTrieNode(){
		printRecursively(this.rootNode);
	}
	
/**
 * 	
 * @param rootNode2
 */
	private void printRecursively(TrieNode node) {
		if(node == null)return;
		if(node.c == '$'){
			return;
		}
		for(Character ch : node.getChilderen()){
			TrieNode childNode = node.getChildNode(ch);
			printRecursively(childNode);
		}
	}
/**
 * 	
 * @param phrase
 */
	public void findPhrase(String phrase){
		TrieNode node = findMatchingNode(rootNode, phrase);
		if(node != null){
			System.out.println(""+node.c);
			List<String> strList = new ArrayList();
			
			loadAllMatchingWords(node, strList, phrase);
			System.out.println(""+strList);
			
		}else{
			System.out.println("No Matching String! with phrase : "+phrase);
		}
		
	}
/**
 * 	
 * @param node
 * @param strList
 * @param phrase 
 */
	private void loadAllMatchingWords(TrieNode node, List<String> strList, String phrase) {
		if(node == null)return;
		if(node.c == '$') strList.add(phrase.substring(0, phrase.length()-1));
		
		for(Character ch : node.getChilderen()){
			TrieNode childNode = node.getChildNode(ch);
			loadAllMatchingWords(childNode, strList, phrase+ch);
		}
	}
/**
 * 	
 * @param phrase
 * @return
 */
	private TrieNode findMatchingNode(TrieNode node, String phrase) {
		if(StringUtils.isBlank(phrase))return node;
		char firstChar = phrase.charAt(0);
		TrieNode childNode = node.getChildNode(firstChar);
		if(childNode != null){
			return findMatchingNode(childNode, phrase.substring(1));
		}
		return null;
	}
}


class TrieNode{
	char c;
	Map<Character, TrieNode> childeren;
	
	public TrieNode(char c){
		this.c = c;
		this.childeren = new HashMap<Character, TrieNode>();
	}
/**
 * 	
 * @param firstChar
 * @return
 */
	public TrieNode getChildNode(char c) {
		return childeren.get(new Character(c));
	}

	public void add(char c){
		if(!childeren.containsKey(new Character(c))){
			childeren.put(new Character(c), new TrieNode(c));
		}
	}
	
	public Set<Character> getChilderen(){
		return childeren.keySet();
	}
}
