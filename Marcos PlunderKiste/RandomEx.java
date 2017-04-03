import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Collections;

public class RandomEx extends java.util.Random {
    
    final int nextInt(int start, int end, List<Integer> exs) {
        List<Integer> range = new ArrayList<>();
        IntStream.range(start, end + 1)
                 .filter(i -> !exs.contains(i))
                 .forEach(e -> range.add(e));
        Collections.shuffle(range);
        return range.get(0);
    }

    final int nextInt(int start, int end, int... ex) {
        int[] exs = ex;
        List<Integer> range = new ArrayList<>();
        IntStream.range(start, end + 1).forEach(e -> range.add(e));
        for(Integer x : exs) {
            range.remove(x);
        }
        Collections.shuffle(range);
        return range.get(0);
    }

}
