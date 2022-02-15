
public class Banco extends java.lang.Object
{
	private int maxCuentas;
	private int numero;
	private java.lang.String codigoBanco;
	CuentaBancaria cuentas[];
	private  int indice;
	public Banco(int maxCuentas,java.lang.String codigoBanco)
	{
		this.codigoBanco=codigoBanco;
		this.maxCuentas=maxCuentas;
		this.numero=0;
		cuentas=new CuentaBancaria[maxCuentas];
		this.indice=0;
	}
	public CuentaBancaria getCuenta(java.lang.String codigo)
	{
		boolean salir=false;
		CuentaBancaria cuenta=null;
		for(int i=0;i<maxCuentas;i++)
		{	
			if(cuentas[i]!=null)
			{
				if(cuentas[i].getCodigo().equals(codigo))
				{
					cuenta=cuentas[i];
					salir=true;
				}
			}
		}
		if(!salir)
		{
			System.out.println("Error: no existe una cuenta con ese código.");
		}
		return cuenta;
	}
	public CuentaBancaria abrirCuenta(Cliente cliente)
	{
		CuentaBancaria cuentaNueva=null;
		if(getNumCuentas()<maxCuentas)
		{
			java.lang.String codigo="ES803333333333"+numero;
			cuentaNueva=new CuentaBancaria(codigo,cliente);
			cuentas[indice]=cuentaNueva;
			indice++;
			numero++;
		}
		return cuentaNueva;
	}
	public int getNumCuentas()
	{
		return indice;
	}
	public java.lang.String getCodigoBanco()
	{
		return codigoBanco;
	}
	public int getMaxCuentas()
	{
		return maxCuentas;
	}
	public CuentaBancaria cerrarCuenta(java.lang.String codigo)
	{
		CuentaBancaria aux=null;
		int j=0;
		for(int i=0;i<maxCuentas;i++)
		{	
			
			if(cuentas[i]!=null)
			{
				if(cuentas[i].getCodigo().equals(codigo))
				{
					aux=cuentas[i];
					cuentas[i]=null;
					indice--;
				}
			}
		}
		CuentaBancaria[] aux2=new CuentaBancaria[maxCuentas];
		for(int i=0;i<maxCuentas;i++)
		{	
			if(cuentas[i]!=null)
			{
				aux2[j]=cuentas[i];
				j++;
			}
		}
		cuentas=aux2;
		return aux;
	}
	public CuentaBancaria[] getCuentas()
	{
		CuentaBancaria aux[]=new CuentaBancaria[getNumCuentas()];
		for(int i=0;i<maxCuentas;i++)
		{	
			if(cuentas[i]!=null)
			{
				aux[i]=cuentas[i];
			}
		}
		return aux;
	}
}
