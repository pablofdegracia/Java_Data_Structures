package aed.bancofiel;

import java.util.Comparator;


public class BancoFielTester {
    private static Comparator<Cuenta> cmp;
    private static BancoFiel b;

    private static void cargar(){
        b = new BancoFiel();

        //Ok
        b.crearCuenta("5248", 200);
        b.crearCuenta("3489",20);
        b.crearCuenta ("5248",300);
        b.crearCuenta("111",100);
        b.crearCuenta("222",500);

        System.out.println(b.cuentas.toString());
    }

    private static void returns(){
        try {
            System.out.println(b.consultarSaldo("111/3"));
        } catch (CuentaNoExisteExc e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {
            System.out.println(b.consultarSaldo("222/4"));
        } catch (CuentaNoExisteExc e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            b.hacerTransferencia("111/3", "222/4", 50);
        } catch (CuentaNoExisteExc e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InsuficienteSaldoExc e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            System.out.println(b.consultarSaldo("111/3"));
        } catch (CuentaNoExisteExc e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        try {
            System.out.println(b.consultarSaldo("222/4"));

        } catch (CuentaNoExisteExc e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


    public static void organize(){
        cmp = new  ComparadorSaldo();
        System.out.println(b.cuentas.toString()+ "  ORIGINAL");
        System.out.println(b.getCuentasOrdenadas(cmp));
       
    }
 public static void main(String[] args) {
    cargar();
    returns();
    organize();
    //3489/1,111/3,222/4,5248/0,5248/2
    }
}
