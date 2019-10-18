package unsolved;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RangeSize {

    public int rangeSize(String input1) {

        String[] inputs = input1.split(":");
        // inputs[0] = from | inputs[1] = to

        // Extract first input
        String fromRowHeader = getRowHeader(inputs[0]);
        int fromColHeader = getColHeader(inputs[0]);

        // Extract second input
        String toRowHeader = getRowHeader(inputs[1]);
        int toColHeader = getColHeader(inputs[1]);

        int X = getDistance(fromRowHeader, toRowHeader);
        int Y = toColHeader - fromColHeader + 1;

        return X*Y;
    }

    private int getDistance(String fromRowHeader, String toRowHeader) {
        return 0;
    }

    private static final Pattern regex = Pattern.compile("(.*)([A-Z]+)");

    private String getRowHeader(String input) {
        return "";
    }

    private int getColHeader(String input) {
        return 0;
    }

    @Test
    public void tests() {
        Matcher m = regex.matcher("AA53");
        if (m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }


    }
}
