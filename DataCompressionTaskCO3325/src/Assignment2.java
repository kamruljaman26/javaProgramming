import java.util.ArrayList;

/**
 * This Assignment 2 class build with 2 static method
 * one for compress and another one is decompressed, both algorithm based on
 * LS77 LZ77 compression algorithm invented by Abraham Lempel and Jacob Ziv in 1977.
 * This is a lossless data compression algorithm.
 */
public class Assignment2 {

    /**
     * Compress/encode a raw string using LZ77 compression algorithm
     *
     * @param word raw data
     * @return Compressed dataset
     */
    public static ArrayList<Tuple> compress(String word) {

        /*
         * encoding algorithm
         * while input is not empty do
         *     prefix := the longest prefix of input that begins in window
         *
         *     if prefix exists then
         *         d := distance to start of prefix
         *         l := length of prefix
         *         c := char following prefix in input
         *     else
         *         d := 0
         *         l := 0
         *         c := first char of input
         *     end if
         *
         *     output (d, l, c)
         *
         *     discard l + 1 chars from front of window
         *     s := pop l + 1 chars from front of input
         *     append s to back of window
         * repeat
         */

        // encoding start
        StringBuilder dictionary = new StringBuilder(), lastMatched = new StringBuilder();
        int length = 0, offset = 0, size = word.length();
        ;
        ArrayList<Tuple> result = new ArrayList<>();

        // encoding process for each char
        for (int i = 0; i < size; i++) {

            // i-th char added as last matched
            lastMatched.append(word.charAt(i));

            /*
             * if matched and found the starting index
             * if it didn't return -1 we can consider it's found
             * a matched, after found a match we will next add a new char and find for match
             * in next iteration.
             */
            if (dictionary.lastIndexOf(lastMatched.toString()) != -1) {

                // update matched length and offset value
                length = lastMatched.length();
                offset = i - dictionary.lastIndexOf(lastMatched.toString()) - lastMatched.length() + 1;

                // if i is last index value add new tuple with null char value
                if (i == size - 1) {
                    result.add(new Tuple(offset, length, null));
                }
            }

            // if no match found
            else {
                // add to last matched to dictionary
                dictionary.append(lastMatched);

                // delete last match
                lastMatched.delete(0, lastMatched.length());

                // add to the result
                result.add(new Tuple(offset, length, word.charAt(i)));

                // start again with init value
                length = 0;
                offset = 0;
            }
        }

        return result;
    }

    /**
     * Decompress a dataset using LZ77 decompression algorithm
     *
     * @param tuples compress dataset
     * @return decompress string
     */
    public static String decompress(ArrayList<Tuple> tuples) {

        /*
         * decoding algorithm
         * for each tuple in tupleList
         *
         *     if tuple.offset == 0 and tuple.length == 0
         *         result.add(tuple.chr)
         *     else
         *         startIndex = result.length - tuple.offset
         *         endIndex = rstartIndex + tuple.length
         *         resultStr = result.substring(startIndex, endIndex)
         *         result.add(resultStr)
         *.             if tuple.chr != null
         *                  result.add(tuple.chr)
         *     end if
         *
         * repeat
         */

        // result string
        StringBuilder result = new StringBuilder();

        // construct the result from data tuple
        for (Tuple tuple : tuples) {

            // if both offset and length is 0 add char to result
            if (tuple.getOffset() == 0 && tuple.getLength() == 0) {
                result.append(tuple.getChr());
            } else {

                // can find matched string start index by minus offset value from
                // current result string and end index build simply by adding length with
                // start index
                int startIdx = result.length() - tuple.getOffset();
                int endIdx = startIdx + tuple.getLength();

                // build substring: fetch matched data
                String temp = result.substring(startIdx, endIdx);
                result.append(temp);

                // if char value null ignore it
                if (tuple.getChr() != null) {
                    result.append(tuple.getChr());
                }
            }
        }

        return result.toString();
    }

/*    // main
    public static void main(String[] args) {
        String data = "ababcbababaa";

        ArrayList<Tuple> compress = compress(data);
        compress.forEach(System.out::println);

        // decompress
        String decompress = decompress(compress);
        System.out.println(decompress);

    }*/
}
