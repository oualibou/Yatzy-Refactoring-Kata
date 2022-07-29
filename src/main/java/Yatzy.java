import java.util.Arrays;

public class Yatzy {

    protected int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int d5)
    {
        dice = new int[]{d1, d2, d3, d4, d5};
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
        return d1 + d2 + d3 + d4 + d5;
    }

    public static int yatzy(int... dice)
    {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die-1]++;
        for (int i = 0; i <counts.length; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return sumParamsValues(d1, d2, d3, d4, d5, 1);
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return sumParamsValues(d1, d2, d3, d4, d5, 2);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return sumParamsValues(d1, d2, d3, d4, d5, 3);
    }

    private static int sumParamsValues(int d1, int d2, int d3, int d4, int d5, int step) {
        int s = 0;
        if (d1 == step) s += step;
        if (d2 == step) s += step;
        if (d3 == step) s += step;
        if (d4 == step) s += step;
        if (d5 == step) s += step;
        return s;
    }

    public int fours()
    {
        return sumArrayValues(4);
    }

    public int fives()
    {
        return sumArrayValues(5);
    }

    public int sixes()
    {
        return sumArrayValues(6);
    }

    private int sumArrayValues(int step) {
        int sum = 0;
        for (int at = 0; at < dice.length; at++) {
            if (dice[at] == step) {
                sum += step;
            }
        }
        return sum;
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] counts = getTable(d1, d2, d3, d4, d5);

        for (int at = 0; at <counts.length; at++) {
            if (counts[6 - at - 1] >= 2) {
                return (6 - at) * 2;
            }
        }
        return 0;
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] counts = getTable(d1, d2, d3, d4, d5);
        int n = 0, score = 0;

        for (int i = 0; i < 6; i += 1)
            if (counts[6-i-1] >= 2) {
                n++;
                score += (6-i);
            }        
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        return kind_of(d1, d2, d3, d4, d5, 4);
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        return kind_of(d1, d2, d3, d4, d5, 3);
    }

    private static int kind_of(int d1, int d2, int d3, int d4, int d5, int step)
    {
        int[] t = getTable(d1, d2, d3, d4, d5);

        for (int i = 0; i < 6; i++)
            if (t[i] >= step)
                return (i+1) * step;
        return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies = getTable(d1, d2, d3, d4, d5);

        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies = getTable(d1, d2, d3, d4, d5);

        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1 &&
            tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        boolean _2 = false, _3 = false;
        int i, _2_at = 0, _3_at = 0;
        int[] tallies = getTable(d1, d2, d3, d4, d5);

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }

    private static int[] getTable(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = new int[6];
        counts[d1-1]++;
        counts[d2-1]++;
        counts[d3-1]++;
        counts[d4-1]++;
        counts[d5-1]++;
        return counts;
    }
}



