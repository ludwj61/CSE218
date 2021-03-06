package p1;

import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

// For Project:
/*
    Check instructions
    
    Do Increments BEFORE if statements for comparisons.

    SEPARATE "and" conditions (others ?) so that the number of accesses is 
        accurate (short circuit evaluation will make it wrong sometimes)
        can take out old output because we want a lot of values (10k+)

 */
public class HeapSortTemplate {

    private static int numComparisons = 0;
    private static int numSwaps = 0;

    public static void main(String[] args) {
        //  int[] data = {7, 36, 19, 17, 3, 25, 1, 2, 4};

        int size = Integer.parseInt(JOptionPane.showInputDialog("How Many Items to"
                + " sort," + " n = ?"));
        int[] data = new int[size];
        randomValues(data);
//        System.out.println("Unsorted");
//        System.out.println(Arrays.toString(data));
//        System.out.println();

        heapSort(data);
        // reHeapDown(data.length, data, 0);
        
        double predictedSortEffort = 3 * data.length * (Math.log10(data.length) / Math.log10(2));
        
        JOptionPane.showMessageDialog(null, "Actual swaps = " + numSwaps + "; Predicted swaps = "
                + String.format("%.2f", ((2.0 / 3) * predictedSortEffort)) + "\nActual Sort effort = " 
                + numComparisons + "; Predicted Sort effort = "
                + String.format("%.2f", predictedSortEffort) + "; Min sort effort = " 
                + String.format("%.2f", data.length * (Math.log10(data.length) / Math.log10(2))));
        
        System.out.println("Sorted");
        // System.out.println(Arrays.toString(data));
    }

    public static void heapSort(int[] data) {
        // Step 1: 

        // do nothing; remember rules
        // Step 2:
        // build initial heap using rhd
        for (int ip = data.length / 2 - 1; ip >= 0; ip--) { // ip = index of parent
            reHeapDown(data.length, data, ip); // doesnt use root as zero so it can traverse tree backwards
        }

        // Step 3: 
        for (int i = 1; i <= data.length; i++) {
            swap(data, 0, data.length - i);
            reHeapDown(data.length - i, data, 0);
        }
    }

    public static void reHeapDown(int size, int[] data, int rootIndex) { // Gets root to be highest value in "tree". Recursive.
        if (size <= 0) { // no nodes
            return;
        }

        if (rootIndex * 2 + 1 >= size) { // root has no children
            return;
        }

        if (rootIndex * 2 + 1 == size - 1) { // one child
            numComparisons++;
            if (data[rootIndex] > data[rootIndex * 2 + 1]) { // root larger than its 1 child
                return;
            } else {
                swap(data, rootIndex, rootIndex * 2 + 1);
                return;
            }
        }

        // root has two children
        numComparisons++;
        if (data[rootIndex] > data[rootIndex * 2 + 1]) { // root is bigger than both
            numComparisons++;
            if (data[rootIndex] > data[rootIndex * 2 + 2]) {
                return;
            }
        } else { // root isn't bigger than both
            numComparisons++;
            if (data[rootIndex * 2 + 1] > data[rootIndex * 2 + 2]) { // left child is larger than right
                swap(data, rootIndex, rootIndex * 2 + 1);
                reHeapDown(size, data, rootIndex * 2 + 1);
            } else { // right child is larger than left
                swap(data, rootIndex, rootIndex * 2 + 2);
                reHeapDown(size, data, rootIndex * 2 + 2);
            }
        }

    }

    public static void randomValues(int[] data) // random numbers from 0 to 999, no duplicates
    {
        Random rn = new Random();
        int r = -1;
        boolean duplicate;
        data[0] = rn.nextInt(data.length);

        for (int index = 1; index < data.length; index++) {
            duplicate = true;
            while (duplicate == true) // r is a duplicate value
            {
                r = rn.nextInt(data.length);
                duplicate = false;
                for (int j = 0; j < index; j++) // check all the set elements for a duplicate
                {
                    if (data[j] == r) // a duplicate found
                    {
                        duplicate = true;
                        break;
                    }// end if	
                }// end for 
                if (duplicate == false) {
                    data[index] = r;
                }
            }
        }
    }

    public static void swap(int[] data, int i1, int i2) {
        int temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
        numSwaps++;
    }
}
