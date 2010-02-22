package kwic.index;

import java.text.RuleBasedCollator;

/**
 * Defines the Collator rules for sorting order.
 */
public class EnglishCollator extends RuleBasedCollator {
   public EnglishCollator() throws java.text.ParseException {
      super(_rules);
   }

   private static final String _rules = ("<' ' < a < A < b < B < c < C < d < D < e < E < f < F " +
                                         "< g < G < h < H < i < I < j < J < k < K < l < L " +
                                         "< m < M < n < N < o < O < p < P < q < Q < r < R " +
                                         "< s < S < t < T < u < U < v < V < w < W < x < X " +
                                         "< y < Y < z < Z");
}
