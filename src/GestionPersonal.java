import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionPersonal {
    static ArrayList<Empleado> empleados = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static String rojo = "\033[31m";
    static String verde = "\033[32m";
    static String amarillo = "\033[33m";
    static String azul = "\033[34m";

    // Resetear color
    static String reset = "\033[0m";

    public static void main(String[] args) {

        empleados.add(new Empleado("Ruth Karin, Huamaní Paz", 1, "Contadora General", 4500));
        empleados.add(new Empleado("Michelle Lesly, Isasi Mauricio", 2, "Gerente Software", 3800));
        empleados.add(new Empleado("Anthony Williams, Micha Cortez", 3, "Analista de Sofware", 3000));
        empleados.add(new Empleado("Jhony Frank, Llontop Caballero", 4, "Administrador", 2500));


        int opcion;
        do {
            mostrarMenu();
            opcion = leerEnteroOpciones();
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

    static void mostrarMenu() {
        System.out.println(azul + "\n===== Menú de Gestión de Personal =====" + reset);
        System.out.println(verde + "1. Ingreso de Personal" + reset);
        System.out.println(verde + "2. Listado de Personal"+ reset);
        System.out.println(verde + "3. Ingreso de Datos para el Cálculo de Planilla"+ reset);
        System.out.println(verde + "4. Listado de Sueldos"+ reset);
        System.out.println(verde + "5. Buscar Empleado por ID"+ reset);
        System.out.println(verde + "6. Salir"+ reset);
        System.out.print("Elige una opción: "+ reset);
    }

    // Opción 1: Ingresar personal
    static void ingresarPersonal() {
        System.out.println(azul +"\n=== Ingreso de Personal ==="+reset);

        //int id = leerEntero("ID: ");

        int id;
        boolean idExistente;
        do {
            id = leerEntero("ID: ");
            idExistente = verificarIdExistente(id);
            if (idExistente) {
                System.out.println(rojo+"El ID ingresado ya existe. Por favor, ingresa un ID diferente."+reset);
            }
        } while (idExistente);

        System.out.print("Nombres y Apellidos: ");
        String nombre = scanner.nextLine();

        System.out.print("Puesto: ");
        String puesto = scanner.nextLine();

        double salarioBase = leerDouble("Salario Base: ");

        Empleado empleado = new Empleado(nombre, id, puesto, salarioBase);
        empleados.add(empleado);
        System.out.println("Empleado registrado con éxito.");
    }

    // Opción 2: Listar personal
    static void listarPersonal() {
        System.out.println(azul +"\n=== Listado de Personal ==="+reset);
        if (empleados.isEmpty()) {
            System.out.println(rojo + "No hay empleados registrados."+reset);
        } else {
            for (Empleado empleado : empleados) {
                System.out.println("ID: " + empleado.id + ", Nombres y Apellidos: " + empleado.nombre + ", Puesto: " + empleado.puesto);
            }
        }
    }

    // Opción 3: Ingresar datos para cálculo de planilla
    public static void ingresarDatosPlanilla() {
        System.out.println(azul +"\n=== Ingreso de Datos para Cálculo de Planilla "+ rojo +"(Ingresar nuevos Valores o dejar en blanco para mantener el valor actual) ===" +reset);

        int id = leerEntero("Ingresa el ID del empleado: ");
        Empleado empleado = buscarEmpleado(id);
        if (empleado != null) {

            System.out.println("Salario base actual: " + empleado.salarioBase);
            double salariobase = leerDoubleOpcional("Ingresa el nuevo salario base: ", empleado.salarioBase);

            System.out.println("Bonificación actual: " + empleado.bonificaciones);
            double bonificacion = leerDoubleOpcional("Ingresa la nueva bonificación: ", empleado.bonificaciones);

            System.out.println("Descuentos actuales: " + empleado.descuentos);
            double descuento = leerDoubleOpcional("Ingresa el nuevo descuento: ", empleado.descuentos);

            empleado.salarioBase=salariobase;
            empleado.bonificaciones = bonificacion;
            empleado.descuentos = descuento;

            int horasExtras;
            do {

                horasExtras = leerEntero("Horas Extras (máximo 10): ");

                if (horasExtras > 10) {
                    System.out.println(rojo+"No esta permitido más de 10 horas extras. Ingresa nuevamente tus horas"+reset);
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
    static void listarSueldos() {
        System.out.println(azul +"\n=== Listado de Sueldos ==="+reset);
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println("ID: " + empleado.id +
                        ", Nombre: " + empleado.nombre +
                        ", Salario Base: " + empleado.salarioBase +
                        ", Bonificaciones: " + empleado.bonificaciones +
                        ", Horas Extras: " + empleado.horasExtras +
                        ", Descuentos: " + empleado.descuentos +
                        ", Pensión 10%: " + empleado.pension +
                        amarillo + ", Salario Final: " + empleado.salarioFinal + reset);
            }
        }
    }

    // Opción 5: BuscarEmpleadoPorID
    static void buscarEmpleadoPorID() {
        System.out.println(azul+"\n=== Buscar Empleado por ID ==="+reset);

        int id = leerEntero("Ingresa ID del empleado: ");
        Empleado empleado = buscarEmpleado(id);

        if (empleado != null) {
            System.out.println("\nEmpleado encontrado:");
            System.out.println("ID: " + empleado.id);
            System.out.println("Nombre: " + empleado.nombre);
            System.out.println("Puesto: " + empleado.puesto);
            System.out.println("Salario Final: " + empleado.salarioFinal);
        } else {
            System.out.println(rojo+"Empleado no encontrado."+reset);
        }

        System.out.println("Regresando al menú principal...");
    }

    // Buscar empleado por ID
    static Empleado buscarEmpleado(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.id == id) {
                return empleado;
            }
        }
        return null;
    }

     static boolean verificarIdExistente(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.id == id) {
                return true;  // El ID ya existe
            }
        }
        return false;  // El ID no existe
    }

    // Validación para leer un entero
    static int leerEntero(String mensaje) {
        int numero = 0;
        boolean esValido = false;
        while (!esValido) {
            try {
                System.out.print(mensaje);
                numero = scanner.nextInt();
                esValido = true;
            } catch (InputMismatchException e) {
                System.out.println(rojo+"Por favor, ingresa un número válido."+reset);
                scanner.nextLine();  // Limpiar el buffer
            }
        }
        scanner.nextLine(); // Limpiar el buffer después de leer el número
        return numero;
    }

    // Validación para leer un entero
    static int leerEnteroOpciones() {
        int numero = 0;
        boolean esValido = false;
        while (!esValido) {
            try {
                //System.out.print("Elige una opción: ");
                numero = scanner.nextInt();
                esValido = true;
            } catch (InputMismatchException e) {
                System.out.println(rojo+"Por favor, ingresa una opción válida para el menú."+reset);
                scanner.nextLine();  // Limpiar el buffer
            }
        }
        scanner.nextLine(); // Limpiar el buffer después de leer el número
        return numero;
    }

    // Validación para leer un doble
    static double leerDouble(String mensaje) {
        double numero = 0.0;
        boolean esValido = false;
        while (!esValido) {
            try {
                System.out.print(mensaje);
                numero = scanner.nextDouble();
                esValido = true;
            } catch (InputMismatchException e) {
                System.out.println(rojo+"Por favor, ingresa un importe válido."+reset);
                scanner.nextLine();  // Limpiar el buffer
            }
        }
        scanner.nextLine(); // Limpiar el buffer después de leer el número
        return numero;
    }

    // Validación para leer un doble opcional (dejar en blanco para mantener el valor actual)
    static double leerDoubleOpcional(String mensaje, double valorActual) {
        String entrada;
        double numero = valorActual;
        System.out.print(mensaje);
        entrada = scanner.nextLine();
        if (!entrada.isEmpty()) {
            try {
                numero = Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println(rojo+"Entrada no válida, se mantendrá el valor actual: " + valorActual+reset);
            }
        }
        return numero;
    }
}