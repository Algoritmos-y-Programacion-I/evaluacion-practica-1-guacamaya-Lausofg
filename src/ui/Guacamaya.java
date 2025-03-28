package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {
        inicializarGlobales();
        menu();
    }

    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    public static void menu() {
        System.out.println("Bienvenido a Guacamaya!");
        establecerCantidadVendida();

        boolean salir = false;
        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que han superado un limite minimo de ventas");
            System.out.println("6. Bono: Mostrar el producto con más ventas");
            System.out.println("7. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: " + calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el dia, " + consultarReferenciasSobreLimite(limite) + " superaron el limite minimo de ventas de " + limite);
                    break;
                case 6:
                    mostrarProductoMasVendido();
                    break;
                case 7:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);
    }

    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];
    }

    public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.println("\nIngrese el precio de la referencia " + (i + 1) + ": ");
            precios[i] = reader.nextDouble();
            System.out.println("Ingrese la cantidad vendida de la referencia " + (i + 1) + ": ");
            unidades[i] = reader.nextInt();
        }
    }

    public static int calcularTotalUnidadesVendidas() {
        int totalUnidades = 0;
        for (int cantidad : unidades) {
            totalUnidades += cantidad;
        }
        return totalUnidades;
    }
 /**
     * Descripcion: calcula el promedio de precios de las referencias
     * pre: precios debe de tener al menos un dato
     * pos: Muestra el promedio de precios de toads las referenciads
     * entradas: precios[]
     * proceso: Suma todos los precios de las referencias y divide por precios.length
     * salidad: Determina el promedio de todos los productos de las referencias y los plasma en consola
     */
    public static double calcularPrecioPromedio() {
        double sumaPrecios = 0;
        for (double precio : precios) {
            sumaPrecios += precio;
        }
        return sumaPrecios / precios.length;
    }
     /**
     * Descripcion: calcula las ventas totales de todos los productos 
     * pre: El arreglo unidades y precios debe de tener al menos un dato
     * pos: Muestra las ventas totales en la consla
     * entradas: unidades[], precios[]
     * proceso: multiplica el precios[i] * unidades[i] en ciclo hasta que se hayan calculado todos 
     * salidad: Determina la cantidad de ventas totales y demuestra en la consola
     */

    public static double calcularVentasTotales() {
        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

    public static int consultarReferenciasSobreLimite(double limite) {
        int contador = 0;
        for (int cantidad : unidades) {
            if (cantidad > limite) {
              contador++;
            }
        }
        return contador;
    }

    /**
     * Descripcion: Encuentra el producto más vendido y muestra su precio y cantidad vendida
     * pre: El arreglo unidades debe estar inicializado y tener al menos un valor
     * pos: Muestra el producto con más ventas en consola
     * entradas: unidades[]
     * proceso: lee el unidades.length
     * salidad: Determina el producto de unidades.length mas largo
     */
    public static void mostrarProductoMasVendido() {
        if (unidades.length == 0) {
            System.out.println("\nNo hay productos registrados.");
            return;
        }

        int indiceMax = 0;
        for (int i = 1; i < unidades.length; i++) {
            if (unidades[i] > unidades[indiceMax]) {
                indiceMax = i;
            }
        }

        System.out.println("\nEl producto con más ventas fue:");
        System.out.println("Precio: $" + precios[indiceMax]);
        System.out.println("Cantidad vendida: " + unidades[indiceMax]);
    }

}