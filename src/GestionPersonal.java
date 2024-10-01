import java.util.ArrayList;
import java.util.Scanner;

public class GestionPersonal {
    static ArrayList<Empleado> empleados = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    ingresarPersonal();
                    break;
                case 2:
                    listarPersonal();
                    break;
                case 3:
                    ingresarDatosPlanilla();
                    break;
                case 4:
                    listarSueldos();
                    break;

                case 5:
                    buscarEmpleadoPorID();
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida, por favor elige nuevamente.");
            }
        } while (opcion != 6);
    }

    public static void mostrarMenu() {
        System.out.println("\n===== Menú de Gestión de Personal =====");
        System.out.println("1. Ingreso de Personal");
        System.out.println("2. Listado de Personal");
        System.out.println("3. Ingreso de Datos para el Cálculo de Planilla");
        System.out.println("4. Listado de Sueldos");
        System.out.println("5. Buscar Empleado por ID");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    // Opción 1: Ingresar personal
    public static void ingresarPersonal() {
        System.out.println("\n=== Ingreso de Personal ===");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombres y Apellidos: ");
        String nombre = scanner.nextLine();

        System.out.print("Puesto: ");
        String puesto = scanner.nextLine();

        System.out.print("Salario Base: ");
        double salarioBase = scanner.nextDouble();
        scanner.nextLine();

        Empleado empleado = new Empleado(nombre, id, puesto, salarioBase);
        empleados.add(empleado);
        System.out.println("Empleado registrado con éxito.");
    }

    // Opción 2: Listar personal
    public static void listarPersonal() {
        System.out.println("\n=== Listado de Personal ===");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println("ID: " + empleado.id + ", Nombres y Apellidos: " + empleado.nombre + ", Puesto: " + empleado.puesto);
            }
        }
    }

    // Opción 3: Ingresar datos para cálculo de planilla
    public static void ingresarDatosPlanilla() {
        System.out.println("\n=== Ingreso de Datos para Cálculo de Planilla ===");
        System.out.print("Ingresa el ID del empleado: ");
        int id = scanner.nextInt();
        Empleado empleado = buscarEmpleado(id);
        if (empleado != null) {
            System.out.print("Bonificaciones: ");
            empleado.bonificaciones = scanner.nextDouble();
            System.out.print("Descuentos: ");
            empleado.descuentos = scanner.nextDouble();

            int horasExtras;
            do {
                System.out.print("Horas Extras (máximo 10): ");
                horasExtras = scanner.nextInt();
                if (horasExtras > 10) {
                    System.out.println("No esta permitido más de 10 horas extras. Ingresa nuevamente tus horas");
                }
            } while (horasExtras > 10);

            empleado.horasExtras = horasExtras;
            empleado.calcularSalarioFinal();
            System.out.println("Salario final calculado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    // Opción 4: Listar sueldos
    public static void listarSueldos() {
        System.out.println("\n=== Listado de Sueldos ===");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                // System.out.println("ID: " + empleado.id + ", Nombre: " + empleado.nombre + ", Salario Final: " + empleado.salarioFinal);
                System.out.println("ID: " + empleado.id +
                        ", Nombre: " + empleado.nombre +
                        ", Salario Base: " + empleado.salarioBase +
                        ", Horas Extras: " + empleado.horasExtras +
                        ", Descuentos: " + empleado.descuentos +
                        ", Salario Final: " + empleado.salarioFinal);
            }
        }
    }

    // Buscar empleado por ID
    public static Empleado buscarEmpleado(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.id == id) {
                return empleado;
            }
        }
        return null;
    }
    // Opción 5: BuscarEmpleadoPorID

    public static void buscarEmpleadoPorID() {
        System.out.println("\n=== Buscar Empleado por ID ===");
        System.out.print("Ingresa el ID del empleado: ");
        int id = scanner.nextInt();
        Empleado empleado = buscarEmpleado(id);

        if (empleado != null) {
            System.out.println("\nEmpleado encontrado:");
            System.out.println("ID: " + empleado.id);
            System.out.println("Nombre: " + empleado.nombre);
            System.out.println("Puesto: " + empleado.puesto);
            System.out.println("Salario Final: " + empleado.salarioFinal);
        } else {
            System.out.println("Empleado no encontrado.");
        }

        System.out.println("Regresando al menú principal...");
    }



}
