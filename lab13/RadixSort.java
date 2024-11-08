/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxLen = Integer.MIN_VALUE;
        for (String str : asciis) {
            maxLen = Math.max(maxLen, str.length());
        }
        String[] sorted = asciis.clone();
        for (int i = maxLen - 1; i >= 0; i--) {
            sorted = sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] count = new int[256];
        String[] sorted = new String[asciis.length];
        for (String str : asciis) {
            int charAtIndex = (index < str.length()) ? str.charAt(index) : 0;
            count[charAtIndex]++;
        }
        int pos = 0;
        int[] starts = new int[256];
        for (int i = 0; i < 256; i++) {
            starts[i] = pos;
            pos += count[i];
        }

        for (String str : asciis) {
            int charAtIndex = (index < str.length()) ? str.charAt(index) : 0;
            sorted[starts[charAtIndex]] = str;
            starts[charAtIndex]++;
        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] args) {
        String[] test2 = {"", "apple", "banana", "grape", "kiwi"};
        String[] sorted2 = sort(test2);
        for (String str : sorted2) {
            System.out.println(str);
        }

    }
}
