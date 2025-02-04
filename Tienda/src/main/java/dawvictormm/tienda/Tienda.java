/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package dawvictormm.tienda;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;


/**
 *
 * @author alu16d
 */
public class Tienda {

    private static Map<String, Clientes> clientes = new HashMap<>();
    private static Map<String, Articulos> articulos = new HashMap<>();
    private static List<Pedidos> pedidos = new ArrayList<>();
    private static int contadorPedidos = 1;

    public static void main(String[] args) {
        cargarDatos();
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE TIENDA ---");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Artículos");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("4. Copia de Seguridad");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEnteroSeguro(scanner);
            switch (opcion) {
                case 1 -> gestionarClientes();
                case 2 -> gestionarArticulos();
                case 3 -> gestionarPedidos();
                case 4 -> guardarDatos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void gestionarClientes() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Añadir Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Modificar Cliente");
            System.out.println("4. Borrar Cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEnteroSeguro(scanner);
            switch (opcion) {
                case 1 -> anadirCliente(scanner);
                case 2 -> listarClientes();
                case 3 -> modificarCliente(scanner);
                case 4 -> borrarCliente(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void anadirCliente(Scanner scanner) {
        System.out.print("Ingrese DNI del cliente: ");
        String dni = leerTextoSeguro(scanner, "^[0-9]{8}[A-Z]$", "Formato de DNI inválido. Ejemplo: 12345678X");

        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese teléfono del cliente: ");
        String telefono = leerTextoSeguro(scanner, "^[6789][0-9]{8}$", "Formato de teléfono inválido. Ejemplo: 612345678");

        System.out.print("Ingrese email del cliente: ");
        String email = leerTextoSeguro(scanner, "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", "Formato de email inválido. Ejemplo: correo@dominio.com");

        Clientes cliente = new Clientes(dni, nombre, telefono, email);
        clientes.put(dni, cliente);
        System.out.println("Cliente añadido correctamente.");
    }

    private static void listarClientes() {
        clientes.values().stream()
                .sorted(Comparator.comparing(Clientes::getNombre))
                .forEach(System.out::println);
    }

    private static void modificarCliente(Scanner scanner) {
        System.out.print("Ingrese DNI del cliente a modificar: ");
        String dni = scanner.nextLine();
        Clientes cliente = clientes.get(dni);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Ingrese nuevo teléfono (deje en blanco para no cambiar): ");
        String nuevoTelefono = scanner.nextLine();
        if (!nuevoTelefono.isBlank()) {
            cliente.setTelefono(nuevoTelefono);
        }

        System.out.print("Ingrese nuevo email (deje en blanco para no cambiar): ");
        String nuevoEmail = scanner.nextLine();
        if (!nuevoEmail.isBlank()) {
            cliente.setEmail(nuevoEmail);
        }

        System.out.println("Cliente modificado correctamente.");
    }

    private static void borrarCliente(Scanner scanner) {
        System.out.print("Ingrese DNI del cliente a borrar: ");
        String dni = scanner.nextLine();
        if (clientes.remove(dni) != null) {
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void gestionarArticulos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ARTÍCULOS ---");
            System.out.println("1. Añadir Artículo");
            System.out.println("2. Listar Artículos");
            System.out.println("3. Borrar Artículo");
            System.out.println("4. Reponer Stock");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEnteroSeguro(scanner);
            switch (opcion) {
                case 1 -> anadirArticulo(scanner);
                case 2 -> listarArticulos();
                case 3 -> borrarArticulo(scanner);
                case 4 -> reponerStock(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void anadirArticulo(Scanner scanner) {
        System.out.print("Ingrese ID del artículo: ");
        String idArticulo = leerTextoSeguro(scanner, "^[1-5]-\\d{2}$", "Formato inválido. Ejemplo: 1-01");

        System.out.print("Ingrese descripción del artículo: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese precio del artículo: ");
        double precio = leerDoubleSeguro(scanner);

        System.out.print("Ingrese stock del artículo: ");
        int stock = leerEnteroSeguro(scanner);

        Articulos articulo = new Articulos(idArticulo, descripcion, precio, stock);
        articulos.put(idArticulo, articulo);
        System.out.println("Artículo añadido correctamente.");
    }

    private static void listarArticulos() {
        articulos.values().stream()
                .sorted(Comparator.comparing(Articulos::getIdArticulo))
                .forEach(System.out::println);
    }

    private static void borrarArticulo(Scanner scanner) {
        System.out.print("Ingrese ID del artículo a borrar: ");
        String idArticulo = scanner.nextLine();
        if (articulos.remove(idArticulo) != null) {
            System.out.println("Artículo eliminado correctamente.");
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }

    private static void reponerStock(Scanner scanner) {
        System.out.println("Artículos con stock 0:");
        articulos.values().stream()
                .filter(a -> a.getStock() == 0)
                .forEach(System.out::println);

        System.out.print("Ingrese ID del artículo a reponer: ");
        String idArticulo = scanner.nextLine();
        Articulos articulo = articulos.get(idArticulo);
        if (articulo == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }

        System.out.print("Ingrese cantidad a añadir al stock: ");
        int cantidad = leerEnteroSeguro(scanner);
        articulo.setStock(articulo.getStock() + cantidad);
        System.out.println("Stock actualizado correctamente.");
    }

    private static void gestionarPedidos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Crear Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Filtrar Pedidos por Importe");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEnteroSeguro(scanner);
            switch (opcion) {
                case 1 -> crearPedido(scanner);
                case 2 -> listarPedidos();
                
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void crearPedido(Scanner scanner) {
        System.out.print("Ingrese DNI del cliente: ");
        String dni = scanner.nextLine();
        Clientes cliente = clientes.get(dni);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        List<LineaPedidos> cestaCompra = new ArrayList<>();
        boolean continuar = false;
        do {
            System.out.print("Ingrese ID del artículo: ");
            String idArticulo = scanner.nextLine();
            Articulos articulo = articulos.get(idArticulo);
            if (articulo == null) {
                System.out.println("Artículo no encontrado.");
                continue;
            }

            System.out.print("Ingrese cantidad: ");
            int cantidad = leerEnteroSeguro(scanner);

            if (cantidad > articulo.getStock()) {
                System.out.println("Stock insuficiente. Solo hay " + articulo.getStock() + " unidades disponibles.");
                continue;
            }

            articulo.setStock(articulo.getStock() - cantidad);
            cestaCompra.add(new LineaPedidos(idArticulo, cantidad));

            System.out.print("¿Agregar otro artículo? (s/n): ");
            scanner.nextLine();
            continuar = scanner.nextLine().equalsIgnoreCase("s");
        } while (continuar);

        String idPedido = articulos +"-" + String.format("%04d", contadorPedidos++) +"-"+ LocalDate.now().getYear();
        Pedidos pedido = new Pedidos(idPedido, dni, cestaCompra);
        pedidos.add(pedido);
        System.out.println("Pedido creado correctamente.");
    }

    private static void listarPedidos() {
        pedidos.forEach(System.out::println);
    }


    private static int leerEnteroSeguro(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Introduzca un número entero: ");
            }
        }
    }

    private static double leerDoubleSeguro(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Introduzca un número decimal: ");
            }
        }
    }

    private static String leerTextoSeguro(Scanner scanner, String regex, String mensajeError) {
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.matches(regex)) {
                return entrada;
            } else {
                System.out.print(mensajeError + " Intente de nuevo: ");
            }
        }
    }

    private static void cargarDatos() {
        // Implementar carga de datos desde archivos .dat y .csv si existen
    }

    private static void guardarDatos() {
        // Implementar guardado de datos en archivos .dat y .csv
    }
}

class Clientes implements Comparable<Clientes> {
    private String dni;
    private String nombre;
    private String telefono;
    private String email;

    public Clientes(String dni, String nombre, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "DNI='" + dni + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Teléfono='" + telefono + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Clientes o) {
        return this.nombre.compareTo(o.nombre);
    }
}

class Articulos {
    private String idArticulo;
    private String descripcion;
    private double precio;
    private int stock;

    public Articulos(String idArticulo, String descripcion, double precio, int stock) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "ID='" + idArticulo + '\'' +
                ", Descripción='" + descripcion + '\'' +
                ", Precio=" + precio +
                ", Stock=" + stock +
                '}';
    }
}

class Pedidos {
    private String idPedido;
    private String idCliente;
    private List<LineaPedidos> cestaCompra;

    public Pedidos(String idPedido, String idCliente, List<LineaPedidos> cestaCompra) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.cestaCompra = cestaCompra;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "ID='" + idPedido + '\'' +
                ", Cliente='" + idCliente + '\'' +
                ", Total=" +
                '}';
    }
}

class LineaPedidos {
    private String idArticulo;
    private int cantidad;

    public LineaPedidos(String idArticulo, int cantidad) {
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    
    
    
    
    
    
    /**
     * public String generaIdPedido(String idCliente){
     * int contador=0;
     * String nuevoId;
     * for(Pedido p:pedidos){
     *      if(p.getClientePedido().getDni().equalsIgnoraCase(idCliente)){
     *              contador++;
     *      }
     * }
     * contador++;
     * nuevoId=idCliente + " " + String.format(" %o3d", contador)+ "/" + LocalDate.now().
     * return nuevoId;
     * }
     */
    
}
