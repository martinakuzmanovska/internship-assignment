public class PathCollector {
    static void Solution(String[][] matrix) {
        StringBuilder path = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        int i = 0, j = 0;
        String placement = matrix[i][j];
        boolean[][] stepped_on = new boolean[matrix.length][matrix[0].length];

         while(!placement.equals("s")) {
             stepped_on[i][j] = true;
         if (placement.equals(">")) {
             path.append("@");
             j++;
         }
         else if(!placement.isEmpty()){
             path.append(placement);
             if(placement.equals("-") || placement.equals("@")){
                 j = moveHorizontal(i,j,matrix,stepped_on);
             }
             else if(placement.equals("|")){
                 i = moveVertical(i,j,matrix,stepped_on);
             }
             else if(Character.isLetter(placement.charAt(0)) && Character.isUpperCase(placement.charAt(0))){
                 letters.append(placement);
                 if ((i+1< matrix.length && matrix[i + 1][j].equals("|")) || (i-1>= 0 && matrix[i - 1][j].equals("|"))) {
                     i = moveVertical(i,j,matrix,stepped_on);
                 }
                 else if ((j+1 < matrix[0].length && matrix[i][j+1].equals("-")) || (j-1>= 0 && matrix[i][j-1].equals("-") )) {
                     j = moveHorizontal(i,j,matrix,stepped_on);
                 }
             }
             else if (placement.equals("+") && path.charAt(path.length()-2) == '-') {
                 i = moveVertical(i,j,matrix,stepped_on);
                 }

             else if (placement.equals("+") && path.charAt(path.length()-2) == '|') {
                 j = moveHorizontal(i,j,matrix,stepped_on);
                 }
             }
             placement = matrix[i][j];
         }

         path.append("s");
         System.out.println("Path " + path.toString());
         System.out.println("Letters " + letters.toString());
    }

    static int moveVertical(int i, int j, String [][]matrix, boolean[][] stepped_on){
        if(i+1< matrix.length && !stepped_on[i+1][j] && !matrix[i+1][j].isEmpty())
            i = i + 1;
        else if(i-1>= 0 && !stepped_on[i-1][j] && !matrix[i-1][j].isEmpty())
            i = i - 1;
        return i;
    }

    static int moveHorizontal(int i, int j, String [][]matrix, boolean[][] stepped_on){
        if(j+1 < matrix[0].length && !stepped_on[i][j+1] && !matrix[i][j+1].isEmpty())
            j = j + 1;
        else if(j-1>= 0 && !stepped_on[i][j-1]  && !matrix[i][j-1].isEmpty())
            j = j - 1;
        return j;
    }

    public static void main(String[] args){
        String [][]matrix_example =
                {{">","-","-","-","A","-","-","-","+"},
                 {"" ,"", "", "", "", "", "", "", "|"},
                 {"s","-","B","-","+","", "", "", "C"},
                 {"", "", "", "", "|","", "", "", "|"},
                 {"", "", "", "", "+","-","-","-","+"}};

        String [][]matrix_test =
                {{">","-","-","-","A","-","@","-","+"},
                 {"" ,"", "", "", "", "", "", "", "|"},
                 {"+","-","U","-","+","", "", "", "C"},
                 {"|", "", "", "", "|","", "", "", "|"},
                 {"s", "", "", "", "C","-","-","-","+"}};
        System.out.println("Example matrix solution: ");
        Solution(matrix_example);
        System.out.println("Test matrix solution: ");
        Solution(matrix_test);
    }

}