import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaquinaDispensadora  {
    private static Map<String, Double> productos = new HashMap<>();
    private static Map<String, Integer> stock = new HashMap<>();

    static {
        productos.put("Agua", 10.0);
        productos.put("Refresco", 15.0);
        productos.put("Galletas", 20.0);
        productos.put("Chocolate", 25.0);
        productos.put("Papas", 18.0);

        stock.put("Agua", 10);
        stock.put("Refresco", 8);
        stock.put("Galletas", 5);
        stock.put("Chocolate", 3);
        stock.put("Papas", 7);
    }

    public static String elegirProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Hola! Estos son los productos que tenemos para ti:");
        productos.forEach((key, value) -> System.out.println(key + ": $" + value + " (Disponibles: " + stock.get(key) + ")"));

        System.out.print("Por favor, elige tu producto: ");
        String producto = scanner.nextLine();

        if (productos.containsKey(producto) && stock.get(producto) > 0) {
            return producto;
        } else {
            System.out.println("Lo siento, ese producto no está disponible o está agotado. Intenta con otro, por favor.");
            return elegirProducto();
        }
    }

    public static String seleccionarMetodoPago() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cómo te gustaría pagar?");
        System.out.println("1. Tarjeta");
        System.out.println("2. Efectivo");
        System.out.print("Por favor, selecciona 1 o 2: ");
        String opcion = scanner.nextLine();

        if (opcion.equals("1")) {
            return "Tarjeta";
        } else if (opcion.equals("2")) {
            return "Efectivo";
        } else {
            System.out.println("Opción no válida. Intentemos de nuevo.");
            return seleccionarMetodoPago();
        }
    }

    public static boolean procesarPago(String metodo, double monto) {
        Scanner scanner = new Scanner(System.in);
        if (metodo.equals("Tarjeta")) {
            System.out.print("Por favor, ingresa tu número de tarjeta: ");
            String tarjeta = scanner.nextLine();

            System.out.print("Ahora, ingresa tu PIN de 4 dígitos: ");
            String pin = scanner.nextLine();

            if (pin.length() == 4) {
                System.out.println("¡Tarjeta y PIN validados con éxito!");
                System.out.println("Procesando el pago de $" + monto + "...");
                return true;
            } else {
                System.out.println("El PIN ingresado es incorrecto. Por favor, intentemos de nuevo.");
                return false;
            }
        } else if (metodo.equals("Efectivo")) {
            System.out.print("Por favor, ingresa la cantidad de dinero: ");
            double efectivo = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea

            if (efectivo >= monto) {
                double cambio = efectivo - monto;
                if (cambio > 0) {
                    System.out.println("Pago recibido. Tu cambio es de $" + cambio + ".");
                } else {
                    System.out.println("Pago exacto recibido. ¡Gracias!");
                }
                return true;
            } else {
                System.out.println("El dinero ingresado no es suficiente. Necesitas $" + (monto - efectivo) + " más. Intentemos de nuevo.");
                return false;
            }
        } else {
            System.out.println("Método de pago no reconocido. Por favor, selecciona entre Tarjeta o Efectivo.");
            return false;
        }
    }

    public static void dispensarProducto(String producto) {
        System.out.println("Preparando tu " + producto + "... ¡Listo! ¡Disfrútalo!");
        stock.put(producto, stock.get(producto) - 1);
    }

    public static void iniciarCompra() {
        String producto = elegirProducto();
        double precio = productos.get(producto);
        String metodoPago = seleccionarMetodoPago();

        if (procesarPago(metodoPago, precio)) {
            dispensarProducto(producto);
        } else {
            System.out.println("Hubo un problema con el pago. Por favor, intentemos de nuevo.");
            iniciarCompra();
        }
    }

    public static void main(String[] args) {
        iniciarCompra();
    }
}