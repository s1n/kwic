package kwic.index;

/**
 * Shifter interface to classes that can reorganize input data for indexing.
 */
public interface Shifter {
   void generatePermutations();
   void generatePermutations(ShiftedInput input_);
}