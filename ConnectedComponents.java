/*
 * Justin Woo 250860368
 * jwoo58@uwo.ca
 * Assignment 2, CS3340
 */

/**
 * Connects components in a binary image
 * 
 * @author Justin Woo
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ConnectedComponents {
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Incorrect number of arguments");
			return;
		}
		File file = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(file));
		int next, height = 0, width = 0;
		boolean widthFound = false;
		uandf connectedComponents = new uandf(5041);

		// Read next character until end of file
		while ((next = br.read()) != -1) {
			if (next != '\n' && next != '\r' && !widthFound) {
				width += 1;
			} else if (next == '\n') {
				widthFound = true;
				height += 1;
			}
		}
		// Reset the reader
		br = new BufferedReader(new FileReader(file));

		String str;
		char[][] img = new char[height][width];
		int row = 0, col = 0;
		while ((str = br.readLine()) != null) {
			img[row] = str.toCharArray();
			row += 1;
		}

		// Create sets for each + in the image
		for (row = 1; row <= height; row++) {
			for (col = 1; col <= width; col++) {
				if (img[row - 1][col - 1] == '+') {
					connectedComponents.make_set((row - 1) * width + col);
				}
			}
		}

		// Exclude the first row and connect the components
		for (row = 1; row <= height; row++) {
			for (col = 1; col <= width; col++) {
				// Check above
				// img[row-1][col-1] to get the index values
				// row - 1 * width + col = set
				if (row > 1 && img[row - 1][col - 1] == '+' && img[row - 2][col - 1] == '+')
					connectedComponents.union_sets(((row - 1) * width) + col, ((row - 2) * width) + col);
				// Check left
				if (col > 1 && img[row - 1][col - 1] == '+' && img[row - 1][col - 2] == '+') {
					connectedComponents.union_sets(((row - 1) * width) + col, ((row - 1) * width) + col - 1);
				}
			}
		}

		// Print the original image
		for (int i = 0; i < height; i++) {
			String string = new String(img[i]);
			System.out.println(string);
		}

		// Finalize the components and label each component
		char label = 'a';
		int[] compCounts = new int[connectedComponents.final_sets()];
		for (row = 1; row <= height; row++) {
			for (col = 1; col <= width; col++) {
				// Find representative value for the current value
				int rep = connectedComponents.find_set(((row - 1) * width) + col);

				// If the value is part of a component
				if (rep != -1) {
					img[row - 1][col - 1] = (char) (label + (rep - 1));
					// Count component size
					compCounts[rep - 1] += 1;
				}
			}
		}

		// Print the labeled image
		for (int i = 0; i < height; i++) {
			String string = new String(img[i]);
			System.out.println(string);
		}

		// Print the list
		for (int i = 0; i < compCounts.length; i++) {
			System.out.println("Label: " + (char) (label + i) + " Count: " + compCounts[i]);
		}

		for (row = 1; row <= height; row++) {
			for (col = 1; col <= width; col++) {
				// Find representative value for the current value
				int rep = connectedComponents.find_set(((row - 1) * width) + col);

				// If the value is part of a component that has size < 4
				if (rep != -1 && compCounts[rep - 1] < 4) {
					img[row - 1][col - 1] = ' ';
				}
			}
		}
		// Print the updated image
		for (int i = 0; i < height; i++) {
			String string = new String(img[i]);
			System.out.println(string);
		}

	}

}
