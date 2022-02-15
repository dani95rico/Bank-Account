
public class CuentaBancaria extends java.lang.Object
 {
	private java.lang.String codigoCuenta;
	private java.util.Date fecha;
	private Cliente cliente;
	private double saldo;
	public CuentaBancaria(java.lang.String codigoCuenta, Cliente cliente)
	{
		this.codigoCuenta=codigoCuenta;
		this.cliente=cliente;
		this.saldo=0;
	}
	public java.lang.String getCodigo()
	{
		return codigoCuenta;
	}
	public Cliente getCliente()
	{
		return cliente;
	}
	public java.util.Date getFecha()
	{
		return fecha;
	}
	public double getSaldo()
	{
		if(saldo>0)
		{
			return saldo;
		}else
		{
			return 0.0;
		}
	}
	public double sacar(double cantidad)
	{
		double salir=0;
		if(getSaldo()>=cantidad && cantidad>0)
		{
			saldo=saldo-cantidad;
			salir=cantidad;
		}
		return salir;
	}
	public double ingresar(double cantidad)
	{
		double salir=0;
		if(cantidad>0)
		{
			saldo=saldo+cantidad;
			salir=cantidad;
		}
		return salir;
	}
	public double transferir(double cantidad,CuentaBancaria destino)
	{
		double salir=0;
		if(getSaldo()>=cantidad && cantidad>0)
		{
			sacar(cantidad);
			destino.ingresar(cantidad);
			salir=cantidad;
		}
		return salir;
	}
}
