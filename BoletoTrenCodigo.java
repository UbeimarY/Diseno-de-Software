import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoletoTrenCodigo {
    private static Map<String, Double> destinos = new HashMap<>();

    static {
        destinos.put("Ciudad X", 40.0);
        destinos.put("Ciudad Y", 60.0);
        destinos.put("Ciudad Z", 55.0);
        destinos.put("Mexico", 150.00);
        destinos.put("Colombia", 200.00);
        destinos.put("Ecuador", 160.00);
        destinos.put("Peru", 300.00);
        destinos.put("Argentina", 500.00);
    }

    public static String elegirDestino() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Destinos disponibles:");
        destinos.forEach((key, value) -> System.out.println(key + ": $" + value));

        System.out.print("Escoge el destino a viajar: ");
        String destino = scanner.nextLine();

        if (destinos.containsKey(destino)) {
            return destino;
        } else {
            System.out.println("Destino inválido, intenta nuevamente.");
            return elegirDestino();
        }
    }

    public static boolean verificarPago() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Porfavor, ingresa tu número de tarjeta: ");
        String tarjeta = scanner.nextLine();
        
        System.out.print("Ingresa tu PIN de 4 dígitos: ");
        String pin = scanner.nextLine();

        if (pin.length() == 4) {
            System.out.println("Tarjeta y PIN validados correctamente, Procesando.");
            return true;
        } else {
            System.out.println("PIN incorrecto, intenta nuevamente.");
            return false;
        }
    }

    public static boolean realizarPago(double monto) {
        System.out.println("Procesando el pago de $" + monto + "...");
        return true;
    }

    public static void emitirBoleto(String destino) {
        System.out.println("Se genero un boleto para " + destino + ". ¡Ten un Buen viaje, Disfruta tu Destino!");
    }

    public static void iniciarExpedicion() {
        String destino = elegirDestino();
        double precio = destinos.get(destino);

        if (verificarPago()) {
            if (realizarPago(precio)) {
                emitirBoleto(destino);
            } else {
                System.out.println("Error en el procesamiento del pago.");
            }
        } else {
            System.out.println("Validación de tarjeta fallida.");
        }
    }

    public static void main(String[] args) {
        iniciarExpedicion();
    }
}

    

