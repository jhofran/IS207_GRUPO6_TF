public class Empleado {
    String nombre;
    int id;
    String puesto;
    double salarioBase;
    double bonificaciones;
    double descuentos;
    double salarioFinal;
    int horasExtras;
    final double Costo_Hora_extra = 50;

    public Empleado(String nombre, int id, String puesto, double salarioBase) {
        this.nombre = nombre;
        this.id = id;
        this.puesto = puesto;
        this.salarioBase = salarioBase;
        this.bonificaciones = 0;
        this.descuentos = 0;
        this.salarioFinal = salarioBase;
        this.horasExtras = 0;
    }

    public void calcularSalarioFinal() {
        double pagoHorasExtras = horasExtras * Costo_Hora_extra;
        this.salarioFinal = salarioBase + bonificaciones - descuentos + pagoHorasExtras;

    }
}
