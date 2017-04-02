import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class RandomEx extends java.util.Random {
    
    final int nextInt(int range, int... ex) {
        return nextInt(0, range, ex);
    }

    final int nextInt(int start, int end, int... ex) {
        int[] exs = ex;
        List<Integer> range = new ArrayList<>();
        IntStream.range(start, end + 1).forEach(e -> range.add(e));
        for(Integer x : exs) {
            range.remove(x);
        }
        java.util.Collections.shuffle(range);
        return range.get(0);
    }

}
