/*
 * Justin Woo 250860368
 * jwoo58@uwo.ca
 * Assignment 2, CS3340
 */

/**
 * ADT of union find
 * 
 * @author Justin Woo
 */
public interface UnionFindADT {

	// constructs an union-find data type with n elements, 1,2, . . . , n.
//	public uandf(int n);

	// creates a new set whose only member (and thus representative) is i
	public void make_set(int i);

	// unites the dynamic sets that contains i and j, respectively, into a new set
	// that is the union of these two sets.
	public void union_sets(int i, int j);

	// returns the representative of the set containing i
	public int find_set(int i);

	// returns the total number of current sets and finalizes the current sets:
	// (i) make_set() and union_sets() will have no effect after this operation and
	// (ii) resets the representatives of the sets so that integers from 1 to
	// final_sets() will be used as representatives.
	public int final_sets();
}
