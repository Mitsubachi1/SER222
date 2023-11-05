public class test {


    public static void main(String[] c) {
        int n;

        for (int i = 0; i < n; i++) {
            for (int j = i + 10; j < n; j++) {
                System.out.print("");
            }
        }

        for (int i = 1; i < n; i *= 3) {
            for (int j = 0; j < n; j++) {
                System.out.print("");
            }
        }

        int address = 0, step = 100;
        while (address < n) {
            while (step * 2 < n) {
                System.out.print("");
                step *= 2;
            }
            step = 100;
            address += 10;
        }




        for(int i =0;i<n; i++){
            for(int j = i+10; j<n;j++){
                System.out.print("");
            }
        }
    }

}
