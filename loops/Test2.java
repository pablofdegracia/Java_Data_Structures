package aed.loops;

public class Test2 {
    private static Integer a[] ={2};
    private static Integer b[] ={2,3};
    private static Integer c[] ={1,3,2};
    private static Integer d[] ={3,1,2};

    private static boolean prueba(){
        return Utils.maxNumRepeated(a, 2)==1&&
               Utils.maxNumRepeated(b, 3)==1&&
               Utils.maxNumRepeated(c, 2)==1&&
               Utils.maxNumRepeated(d, 2)==1;
    }
    public static void main(String[] args) {
        System.out.println(prueba());
    }
    
}
