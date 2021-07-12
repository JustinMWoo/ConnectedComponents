/*
 * Justin Woo 250860368
 * jwoo58@uwo.ca
 * Assignment 2, CS3340
 */

/**
 * One node for union find
 * 
 * @author Justin Woo
 */
public class Node {
	private Node parent;
	private int rank, name;

	/*
	 * Constructor for a node
	 * 
	 * @param i name of the node
	 */
	public Node(int i) {
		name = i;
		parent = null;
	}

	/*
	 * Setter function for the rank of the node
	 * 
	 * @param rank the new rank of the node
	 */
	public void set_rank(int rank) {
		this.rank = rank;
	}

	/*
	 * Setter function for the parent of the node
	 * 
	 * @param parent the new parent of the node
	 */
	public void set_parent(Node parent) {
		this.parent = parent;
	}

	/*
	 * Setter function for the name of the node
	 * 
	 * @param name the new name of the node
	 */
	public void set_name(int name) {
		this.name = name;
	}

	/*
	 * Getter function for the rank of the node
	 * 
	 * @return the rank of the node
	 */
	public int get_rank() {
		return rank;
	}

	/*
	 * Getter function for the parent of the node
	 * 
	 * @return the parent of the node
	 */
	public Node get_parent() {
		return parent;
	}

	/*
	 * Fetter function for the name of the node
	 * 
	 * @return then name of the node
	 */
	public int get_name() {
		return name;
	}
}
