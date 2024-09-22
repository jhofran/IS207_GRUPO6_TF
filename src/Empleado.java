public class Empleado {
    String nombre;
    int id;
    String puesto;
    double salarioBase;
    double bonificaciones;
    double descuentos;
    double salarioFinal;

    public Empleado(String nombre, int id, String puesto, double salarioBase) {
        this.nombre = nombre;
        this.id = id;
        this.puesto = puesto;
        this.salarioBase = salarioBase;
        this.bonificaciones = 0;
        this.descuentos = 0;
        this.salarioFinal = salarioBase;
    }

    public void calcularSalarioFinal() {
        this.salarioFinal = salarioBase + bonificaciones - descuentos;
    }
}
