package aed.bancofiel;

import java.util.Comparator;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;


/**
 * Implements the code for the bank application.
 * Implements the client and the "gestor" interfaces.
 */
public class BancoFiel implements ClienteBanco, GestorBanco {

  // NOTAD. No se deberia cambiar esta declaracion.
  public IndexedList<Cuenta> cuentas;

  // NOTAD. No se deberia cambiar esta constructor.
  public BancoFiel() {
    this.cuentas = new ArrayIndexedList<Cuenta>();
  }

  // ----------------------------------------------------------------------
  // Anadir metodos aqui ...

  public String crearCuenta(String dni, int saldoIncial){ 
    Cuenta c = new Cuenta(dni, saldoIncial);
    cuentas.add(cuentas.size(),c);
		return c.getId();
  }

  public void borrarCuenta(String id)
			throws CuentaNoExisteExc, CuentaNoVaciaExc{
    boolean ok = false; int i=0;
    Cuenta[] libreta = cuentas.toArray(new Cuenta[cuentas.size()]);

    while(i<libreta.length&&!ok){
      if(libreta[i].getId().equals(id)){
        if(libreta[i].getSaldo()!=0) throw new CuentaNoVaciaExc();
        cuentas.removeElementAt(i);
        ok=true;
      }
      i++;
    }
    if(ok==false&& i==libreta.length) throw new CuentaNoExisteExc();
      }

  public int ingresarDinero(String id, int cantidad)
			throws CuentaNoExisteExc{
         boolean ok=false;int i=0; 
        Cuenta encontrada = new Cuenta("a",1);
        Cuenta[] libreta = cuentas.toArray(new Cuenta[cuentas.size()]);

        while(i<libreta.length&&!ok){
          if(libreta[i].getId().equals(id)){
            libreta[i].ingresar(cantidad);
            encontrada=libreta[i];
            ok=true;
          }
          i++;
        }
        if(ok==false&& i==libreta.length) throw new CuentaNoExisteExc();
        return encontrada.getSaldo();
      }
  public int retirarDinero(String id, int cantidad)
			throws CuentaNoExisteExc, InsuficienteSaldoExc{
        boolean ok=false; int i=0;
        Cuenta encontrada = new Cuenta("a",1);
        Cuenta[] libreta = cuentas.toArray(new Cuenta[cuentas.size()]);

        while(i<libreta.length&&!ok){
          if(libreta[i].getId().equals(id)){
            ok=true; encontrada=libreta[i];
            if(encontrada.getSaldo()< cantidad) throw new InsuficienteSaldoExc(); 
          }
          i++;
        }
        if(ok==false&& i==libreta.length) throw new CuentaNoExisteExc();
        return encontrada.retirar(cantidad);
      }
      
  
  public int consultarSaldo(String id) //ok
			throws CuentaNoExisteExc
      {
        boolean ok=false; int i=0;
        Cuenta encontrada = new Cuenta("",0);

        while(i<cuentas.size()&&!ok){ 
          if ((cuentas.get(i).getId().equals(id))){
            encontrada = cuentas.get(i);
            ok=true;
          }
          i++;
        }
        if(ok==false&& i==cuentas.size()) throw new CuentaNoExisteExc();
        return encontrada.getSaldo();
        
      }

  public void hacerTransferencia(String idFrom, String idTo, int cantidad)
    throws CuentaNoExisteExc, InsuficienteSaldoExc{
      boolean ok1= false; boolean ok2= false; int i=0;
      Cuenta from = new Cuenta("A",cantidad+1); Cuenta to = new Cuenta("B",1);
      while(i<cuentas.size()&&(!ok1||!ok2)){
        if(cuentas.get(i).getId().equals(idFrom)){
          from=cuentas.get(i); ok1=true;
        } 
        if(cuentas.get(i).getId().equals(idTo)){
          to=cuentas.get(i); ok2=true;
        }
        i++;
      }   
      if(ok1==false || ok2==false&&i==cuentas.size()) throw new CuentaNoExisteExc();
      if(from.getSaldo()< cantidad)
          throw new InsuficienteSaldoExc();
      retirarDinero(from.getId(), cantidad);
      ingresarDinero(to.getId(), cantidad);
  }

  public IndexedList<String> getIdCuentas(String dni){
    IndexedList<String> libreta_user = new ArrayIndexedList<String>();
    for(int i=0; i<cuentas.size(); i++){
      if(cuentas.get(i).getDNI().equals(dni))
      libreta_user.add(libreta_user.size(),cuentas.get(i).getId());
    }
    return libreta_user;
  }

  public int getSaldoCuentas(String dni){
    int result = 0;
    for(int i=0; i<cuentas.size(); i++){
      if(cuentas.get(i).getDNI().equals(dni))
      result = result + cuentas.get(i).getSaldo();
    }
    return result;
  }

  //-----------------------------------------------------------------------
 
 public IndexedList<Cuenta> getCuentasOrdenadas(Comparator<Cuenta> cmp)
	{
    Cuenta [] aux = cuentas.toArray(new Cuenta[cuentas.size()]);
		IndexedList<Cuenta> resultado = new ArrayIndexedList <Cuenta>();
    
    //hacemos un for que recorre el array y lo compara con lo que 
    //hay en el resultado
    resultado.add(0, aux[0]); 
    for(int i=1; i<aux.length;i++){
      int j=0; boolean fin=false;
      //si el numero es el mayor, se mete al final y se pasa al siguiente
      if(cmp.compare(aux[i], resultado.get(resultado.size()-1))>0){
      resultado.add(resultado.size(), aux[i]); fin=true;}
      while(j<resultado.size()&&!fin){ 
        if(cmp.compare(aux[i],resultado.get(j))<=0){
          fin=true; resultado.add(j, aux[i]);
        }
        j++; 
      }
    }
		return resultado;
	}
  // ----------------------------------------------------------------------
  // NOTAD. No se deberia cambiar este metodo.
  public String toString() {
    return "banco";
  } 
}



