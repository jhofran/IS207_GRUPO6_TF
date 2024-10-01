public class Empleado {
    String nombre;
    int id;
    String puesto;
    double salarioBase;
    double bonificaciones;
    double descuentos;
    double salarioFinal;
    int horasExtras;
    double pension;
    final double Costo_Hora_extra = 50;
    final double porcentajePension = 0.10;

    public Empleado(String nombre, int id, String puesto, double salarioBase) {
        this.nombre = nombre;
        this.id = id;
        this.puesto = puesto;
        this.salarioBase = salarioBase;
        this.bonificaciones = 0;
        this.descuentos = 0;
        this.pension = salarioBase*porcentajePension;
        this.salarioFinal = (salarioBase-pension);
        this.horasExtras = 0;

    }

    public void calcularSalarioFinal() {
        double pagoHorasExtras = horasExtras * Costo_Hora_extra;
        this.pension = salarioBase*porcentajePension;
        this.salarioFinal = salarioBase + bonificaciones - descuentos-pension + pagoHorasExtras;

    }
}