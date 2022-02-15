
public class TestBanco {

	/**
	 * Pograma principal de prueba la aplicación
	 * @param args
	 */
	public static void main(String[] args){
		final int maxCuentas=3;
		final String codigoBanco="ES80";
		final Cliente cliente1=new Cliente("Cliente1","2515418096Z");
		final Cliente cliente2=new Cliente("Cliente2","1520041745A");
		double resultado=0;

		Banco banco=new Banco(maxCuentas,codigoBanco);
		assert banco.getNumCuentas()==0:"Error en Banco: el número de cuentas inicial debería ser 0.";

		///////////////// Pruebas de creación de las cuentas 
		CuentaBancaria cuenta1=banco.abrirCuenta(cliente1);
		CuentaBancaria cuenta2=banco.abrirCuenta(cliente2);
		CuentaBancaria cuenta3=banco.abrirCuenta(cliente2);	
		assert banco.abrirCuenta(cliente2)==null : "Error en Banco: el número de cuentas máximo se debería haber superado.";
		assert banco.abrirCuenta(null)==null : "Error en Banco: no se deben abrir cuentas sin cliente.";
		assert banco.getNumCuentas()==3:"Error en Banco: el número de cuentas  debería ser 3.";
		assert cuenta1.getCodigo().equals(codigoBanco + String.format("%08d",0)): "Error en Banco: el código de la primera cuenta debería ser ES8000000000.";
		assert cuenta2.getCodigo().equals(codigoBanco + String.format("%08d",1)): "Error en Banco: el código de la primera cuenta debería ser ES8000000001.";
		assert cuenta3.getCodigo().equals(codigoBanco + String.format("%08d",2)): "Error en Banco: el código de la primera cuenta debería ser ES8000000002.";

		/////////////////  Pruebas de las cuentas 
		resultado=cuenta1.ingresar(500);
		assert resultado==500: "Error en CuentaBancaria: resultado  debería ser 500.";
		assert cuenta1.getSaldo()==500: "Error en CuentaBancaria: el saldo  debería ser 500.";

		resultado=cuenta2.ingresar(500);
		assert resultado==500: "Error en CuentaBancaria: resultado  debería ser 500.";
		assert cuenta2.getSaldo()==500: "Error en CuentaBancaria: el saldo  debería ser 500.";

		resultado=cuenta3.ingresar(-500);
		assert resultado==0: "Error en CuentaBancaria: resultado  debería ser 0 al ingresar con números negativos.";
		assert cuenta3.getSaldo()==0: "Error en CuentaBancaria: el saldo  debería ser 0 al ingresar con números negativos.";

		resultado=cuenta1.transferir(500,cuenta2);
		assert resultado==500: "Error en CuentaBancaria: resultado  debería ser 500 al transferir.";
		assert cuenta1.getSaldo()==0: "Error en CuentaBancaria: el saldo  debería ser 0 al transferir en la cuenta origen.";
		assert cuenta2.getSaldo()==1000: "Error en CuentaBancaria: el saldo  debería ser 1000 al transferir en la cuenta destino.";

		resultado=cuenta3.transferir(500,cuenta2);
		assert resultado==0: "Error en CuentaBancaria: resultado  debería ser 0 al transferir sin saldo.";
		assert cuenta3.getSaldo()==0: "Error en CuentaBancaria: el saldo  debería ser 0 al transferir en la cuenta origen.";
		assert cuenta2.getSaldo()==1000: "Error en CuentaBancaria: el saldo  debería ser 1000 al transferir en la cuenta destino.";

		resultado=cuenta3.transferir(-500,cuenta2);
		assert resultado==0: "Error en CuentaBancaria: resultado  debería ser 0 al transferir con números negativos.";
		assert cuenta3.getSaldo()==0: "Error en CuentaBancaria: el saldo  debería ser 0 al transferir en la cuenta origen.";
		assert cuenta2.getSaldo()==1000: "Error en CuentaBancaria: el saldo  debería ser 1000 al transferir en la cuenta destino.";

		resultado = cuenta2.sacar(-1000);
		assert resultado==0: "Error en CuentaBancaria: resultado  debería ser 0 al sacar con números negativos.";
		assert cuenta2.getSaldo()==1000: "Error en CuentaBancaria: el saldo  debería ser 1000 al sacar con números negativos.";

		resultado = cuenta2.sacar(1001);
		assert resultado==0: "Error en CuentaBancaria: resultado  debería ser 0 al sacar un saldo inexistente.";
		assert cuenta2.getSaldo()==1000: "Error en CuentaBancaria: el saldo  debería ser 1000 al transferir en la cuenta destino.";

		resultado = cuenta2.transferir(1001,cuenta1);
		assert resultado==0: "Error en CuentaBancaria: resultado  debería ser 0 al transferir un saldo inexistente.";
		assert cuenta2.getSaldo()==1000: "Error en CuentaBancaria: el saldo  debería ser 1000 al transferir un saldo inexistente."; 

		resultado = cuenta2.sacar(500);
		assert resultado==500: "Error en CuentaBancaria: resultado  debería ser 500 al sacar un saldo inexistente.";
		assert cuenta2.getSaldo()==500: "Error en CuentaBancaria: el saldo  debería ser 500 tras sacar 500.";

		/////////////////  Pruebas de borrado de las cuentas
		CuentaBancaria b = banco.cerrarCuenta(cuenta3.getCodigo());
		assert (b!=null) : "Error en Banco: la cuenta a borrar debería haberse encontrado.";
		assert (b.getCodigo().equals(cuenta3.getCodigo())) : "Error en Banco: el código de la cuenta borrada debería ser ES8000000002.";
		assert banco.getNumCuentas()==2:"Error en Banco: el número de cuentas resultante debería ser 2.";
		assert banco.getCuenta(cuenta1.getCodigo())==cuenta1 : "Error en Banco: la cuenta ES800000000 debería existir.";
		assert banco.getCuenta(cuenta2.getCodigo())==cuenta2 : "Error en Banco: la cuenta ES800000001 debería existir.";
		assert banco.getCuenta(cuenta3.getCodigo())==null : "Error en Banco: la cuenta ES800000002 no debería existir.";

		b = banco.cerrarCuenta(cuenta1.getCodigo());
		assert (b!=null) : "Error en Banco: la cuenta a borrar debería haberse encontrado.";
		assert (b.getCodigo().equals(cuenta1.getCodigo())) : "Error en Banco: el código de la cuenta borrada debería ser ES8000000000.";
		assert banco.getNumCuentas()==1:"Error en Banco: el número de cuentas resultante debería ser 1.";
		assert banco.getCuenta(cuenta1.getCodigo())==null : "Error en Banco: la cuenta ES800000000 no debería existir.";
		assert banco.getCuenta(cuenta2.getCodigo())==cuenta2 : "Error en Banco: la cuenta ES800000001 debería existir.";
		assert banco.getCuenta(cuenta3.getCodigo())==null : "Error en Banco: la cuenta ES800000002 no debería existir.";

		/////////////////  Pruebas de recorridos de las cuentas
		CuentaBancaria[] cuentas = banco.getCuentas();
		assert cuentas.length==1:"Error en Banco: el número de cuentas en el array debería ser 1.";
		assert (cuentas[0].getCodigo().equals(cuenta2.getCodigo())) : "Error en Banco: el código de la cuenta existente debería ser ES8000000001.";

		///////////////// Pruebas de codigos de las cuentas 
		CuentaBancaria cuenta4 = banco.abrirCuenta(cliente2);
		assert cuenta4!=null : "Error en Banco: el número de cuentas máximo no se debería haber superado.";
		assert banco.getNumCuentas()==2:"Error en Banco: el número de cuentas  debería ser 2.";
		assert cuenta4.getCodigo().equals( codigoBanco + String.format("%08d",3)): "Error en Banco: el código de la cuenta debería ser ES8000000003.";

		System.out.println ("Enhorabuena, la práctica ha pasado todas las comprobaciones correctamente.");

	}

}
