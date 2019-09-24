
public class MaximumAbsoluteValueExpr {


    // | arr1[i] - arr1[j] | + | arr2[i] - arr2[j] | + | i + j |

    // The goal is to find the greatest distance between the values in arr1 and arr2

    // Iterate through each array to find the min and max of both

    public int maxAbsValExpr(int[] arr1, int[] arr2) {

        int answer = 0;

        for (int i = 0; i < arr1.length; i++) {
            for (int j = i+1; j < arr2.length; j++) {
                 answer = Math.max(answer, absValExpr(i, j, arr1, arr2));
            }
        }

        return answer;

    }

    private int absValExpr(int i, int j, int[] arr1, int[] arr2) {
        return Math.abs(arr1[i] - arr1[j]) + Math.abs(arr2[i] - arr2[j]) + Math.abs(i - j);
    }

    public static void main(String[] args) {
        Integer answer = new MaximumAbsoluteValueExpr().maxAbsValExpr(new int[]{1,-2,-5,0,10}, new int[]{0,-2,-1,-7,-4});
        System.out.println(answer);
        answer = new MaximumAbsoluteValueExpr().maxAbsValExpr(new int[]{1,2,3,4}, new int[]{-1,4,5,6});
        System.out.println(answer);
    }

}
