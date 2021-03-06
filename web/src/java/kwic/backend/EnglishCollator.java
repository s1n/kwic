package kwic.backend;

import java.text.RuleBasedCollator;
import java.util.Arrays;

/**
 * Defines the Collator rules for sorting order.
 */
public class EnglishCollator extends RuleBasedCollator {
   public EnglishCollator() throws java.text.ParseException {
      super(_rules);
   }

   public static boolean noise(String n_) {
      return Arrays.asList(_noise).contains(n_.toLowerCase());
   }

   private static final String _rules = ("<' ' < a < A < b < B < c < C < d < D < e < E < f < F " +
                                         "< g < G < h < H < i < I < j < J < k < K < l < L " +
                                         "< m < M < n < N < o < O < p < P < q < Q < r < R " +
                                         "< s < S < t < T < u < U < v < V < w < W < x < X " +
                                         "< y < Y < z < Z");

   //these are noise words we want to completely ignore, in either uppercase or lowercase
   private final static String[] _noise = {"a", "an", "the", "and", "or", "of",
                                           "to", "be", "is", "in", "out", "by",
                                           "as", "at", "off"};
}
