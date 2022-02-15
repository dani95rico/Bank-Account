// EXAMPLE OF A BANK ACCOUNT IN JAVA LENGUAGE BY DANI95RICO

public class Aplicacion 
{
	public static void main(String[] args) {
		
		//INICIALIZACIÓN 
		double cantidad = 500;
		Banco banco=new Banco(10,"BANCO123");

		Cliente cliente1= new Cliente("LARA","LARA123");
		Cliente cliente2= new Cliente("DANI","DANI123");
		
		banco.abrirCuenta(cliente1);
		banco.abrirCuenta(cliente2);
		
		CuentaBancaria[] aux=banco.getCuentas();
		
		aux[0].ingresar(cantidad);
		aux[1].ingresar(cantidad);

		System.out.println("Se ha ingresado "+cantidad+" euros en la cuenta 1");
		System.out.println("Se ha ingresado "+cantidad+" euros en la cuenta 2");
	
		//MOSTRAR INFORMACIÓN
		System.out.println("**********");
		System.out.println("INFORMACIÓN\nCódigo del banco: "+banco.getCodigoBanco());
		System.out.println("Número máximo de cuentas: "+banco.getMaxCuentas());
		System.out.println("Número de cuentas abiertas actualmente: "+banco.getNumCuentas());
		aux=banco.getCuentas();
		for(int i=0;i<aux.length;i++)
		{
				System.out.println("\nCuenta número: "+(i+1));
				System.out.println("Código: " + aux[i].getCodigo());
				System.out.println("Saldo: " + aux[i].getSaldo());
				Cliente auxCliente=aux[i].getCliente();
				System.out.println("Nombre del cliente: "+auxCliente.getNombre());
				System.out.println("NIF del cliente: "+auxCliente.getNIF());
		}
		System.out.println("**********");
		
		// TRANSFERENCIA
		System.out.println("Se va a hacer una transferencia de "+cantidad+" euros de la cuenta 1 a la cuenta 2" );
		System.out.print("Se ha transferido ");
		if (cantidad< 0 )
		{
			System.out.println("0.0");
		}
		else
		{
			System.out.println(aux[0].transferir(cantidad,aux[1])+" euros de la cuenta 1 a la cuenta 2.");
		}
		
		System.out.println("\nSaldo en la cuenta origen: "+aux[0].getSaldo());
		System.out.println("Saldo en la cuenta destino: "+aux[1].getSaldo());
		
		banco.cerrarCuenta(aux[0].getCodigo());
		System.out.println("\nSe ha cerrado la cuenta de origen.");
		
		//MOSTRAR INFORMACIÓN
		System.out.println("**********");
		System.out.println("INFORMACIÓN\nCódigo del banco: "+banco.getCodigoBanco());
		System.out.println("Número máximo de cuentas: "+banco.getMaxCuentas());
		System.out.println("Número de cuentas abiertas actualmente: "+banco.getNumCuentas());
		aux=banco.getCuentas();
		for(int i=0;i<aux.length;i++)
		{
				if(aux[i]!=null)
				{
				System.out.println("\nCuenta número: "+(i+1));
				System.out.println("Código: " + aux[i].getCodigo());
				System.out.println("Saldo: " + aux[i].getSaldo());
				Cliente auxCliente=aux[i].getCliente();
				System.out.println("Nombre del cliente: "+auxCliente.getNombre());
				System.out.println("NIF del cliente: "+auxCliente.getNIF());
				}
		}
		System.out.println("**********");
	}
}
