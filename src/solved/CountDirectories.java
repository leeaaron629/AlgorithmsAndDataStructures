package solved;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CountDirectories {

    public int countDirectories(String[] input) {

        Set<String> uniqueDirectories = new HashSet<>();

        for (int i = 0; i < input.length; i++) {

            int position;
            String dir = input[i];
            do {
                position = dir.lastIndexOf('/');
                dir =  input[i].substring(0, position);

                uniqueDirectories.add(dir);
            } while (position > 0);

        }

        return uniqueDirectories.size();
    }

    @Test
    public void tests() {
        String[] input1 = new String[] {"/"};
        System.out.println(countDirectories(input1));

        String[] input2 = new String[] {"/a"};
        System.out.println(countDirectories(input2));

        String[] input3 = new String[] {"/a/b/", "/x/y"};
        System.out.println(countDirectories(input3));

        String[] input4 = new String[] {"/a/b/", "/x/a"};
        System.out.println(countDirectories(input4));

//        System.out.println("/a/b/".lastIndexOf('/'));
//        int position = "/a/b/".lastIndexOf('/');
//        System.out.println("/a/b/".substring(0, position));

    }
}
