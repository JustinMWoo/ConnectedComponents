/*
 * Justin Woo 250860368
 * jwoo58@uwo.ca
 * Assignment 2, CS3340
 */

/**
 * Implements union find
 * 
 * @author Justin Woo
 */

import java.util.ArrayList;
import java.util.List;

public class uandf implements UnionFindADT {
	private Node[] nodeList;
	private boolean final_sets = false;
	private int n;

	/*
	 * Constructor for union find data type
	 * 
	 * @param n the number of elements in the data type
	 */
	public uandf(int n) {
		nodeList = new Node[n];
		// Set up an array of n nodes
		for (int i = 0; i < n; i++) {
			nodeList[i] = new Node(i + 1);
		}
		// Store the value of n for use in final_sets
		this.n = n;
	}

	/*
	 * Makes a singleton set
	 * 
	 * @param i element to be made into a set
	 */
	public void make_set(int i) {
		if (final_sets == true) {
			return;
		}
		nodeList[i - 1].set_parent(nodeList[i - 1]);
		nodeList[i - 1].set_rank(0);
	}

	/*
	 * Unions two sets together
	 * 
	 * @param i, j the sets to be unioned together
	 */
	public void union_sets(int i, int j) {
		if (final_sets == true) {
			return;
		}
		Node i_root = nodeList[i - 1];
		// Get the root of each set
		while (!i_root.get_parent().equals(i_root)) {
			i_root = i_root.get_parent();
		}
		Node j_root = nodeList[j - 1];
		while (!j_root.get_parent().equals(j_root)) {
			j_root = j_root.get_parent();
		}
		// Make the root with the smaller rank point to the root with the larger rank
		if (i_root.get_rank() > j_root.get_rank()) {
			j_root.set_parent(i_root);
		} else if (i_root.get_rank() < j_root.get_rank()) {
			i_root.set_parent(j_root);
		} else { // If the roots have the same rank
			// Arbitrarily choose one of the roots (i_root) and raise its rank by 1, then
			// set j_root's parent to i
			i_root.set_rank(i_root.get_rank() + 1);
			j_root.set_parent(i_root);
		}

	}

	/*
	 * Finds the representative of the set
	 * 
	 * @param i the set to find the representative of
	 * 
	 * @return the representative of the set
	 */
	public int find_set(int i) {
		if (nodeList[i - 1].get_parent() == null) {
			return -1;
		}
		Node root = nodeList[i - 1];
		Node currNode = nodeList[i - 1];
		Node parent;
		// Get the root of node
		while (!root.get_parent().equals(root)) {
			root = root.get_parent();
		}
		// Collapse tree
		while (!currNode.equals(root)) {
			parent = currNode.get_parent();
			currNode.set_parent(root);
			currNode = parent;
		}

		return root.get_name();
	}

	/*
	 * Finalizes the sets to no longer be editable
	 * 
	 * @return the number of sets
	 */
	public int final_sets() {
		final_sets = true;
		// currRep stores the name of the node (not the index so 1 must be subtracted)
		// sets stores the number of sets (can be used for the index of the next
		// representative)
		int sets = 0, currRep;
		int newRep = 1;
		List<Node> representatives = new ArrayList<>();

		// Get the number of sets and representatives of the sets
		for (int i = 1; i <= n; i++) {
			currRep = find_set(i);
			if (currRep == -1)
				continue;

			if (!representatives.contains(nodeList[currRep - 1])) {
				sets += 1;
				representatives.add(nodeList[currRep - 1]);
			}

		}
		for (Node rep : representatives) {
			rep.set_name(newRep);
			newRep += 1;
		}
		return sets;
	}

}
