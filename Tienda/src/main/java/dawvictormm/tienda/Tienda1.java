/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dawvictormm.tienda;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alu16d
 */
public class Tienda1 {
    
    
    public Tienda1() {
        pedidos = new ArrayList<>();
        articulos = new HashMap<>();
        clientes = new HashMap<>();
    }
    
    Scanner sc=new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;
    
     public static void main(String[] args) {
       Tienda1 t=new Tienda1();
       t.cargaDatos();
       t.mostrarMenuPrincipal();
     }
    
    private void mostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. GESTIÓN DE PEDIDOS");
            System.out.println("2. GESTIÓN DE ARTÍCULOS");
            System.out.println("3. GESTIÓN DE CLIENTES");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion=sc.nextInt(); 
            switch (opcion) { 
                case 1:{
                    MenuPedidos();
                    break;
                }
                case 2:{
                    MenuArticulos();
                    break;
                }
                case 3:{
                    MenuClientes();
                    break;
                }
                case 0:{
                    System.out.println("SALIENDO DE LA TIENDA...");
                    break;
                }
            }
        }while(opcion !=0);
    }
    
 //<editor-fold defaultstate="collapsed" desc="CARGA  DATOS">
    
 public void cargaDatos(){
        
       clientes.put("80580845T",new Cliente("80580845T","ANA ","658111111","ana@gmail.com"));
       clientes.put("36347775R",new Cliente("36347775R","LOLA","649222222","lola@gmail.com"));
       clientes.put("63921307Y",new Cliente("63921307Y","JUAN","652333333","juan@gmail.com"));
       clientes.put("02337565Y",new Cliente("02337565Y","EDU","634567890","edu@gmail.com"));
              
       articulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
       articulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
       articulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
       articulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",9,70));
       articulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",0,200));
       articulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
       articulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",5,100));
       articulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
       articulos.put("4-33",new Articulo("4-33","SAMSUNG ODISSEY G5",2,580));
       
       LocalDate hoy = LocalDate.now();
       pedidos.add(new Pedido("80580845T-001/2024",clientes.get("80580845T"),hoy.minusDays(1), new ArrayList<>
        (List.of(new LineaPedido("1-11",3),new LineaPedido("4-22",3)))));                                                                                                                                                               
       pedidos.add(new Pedido("80580845T-002/2024",clientes.get("80580845T"),hoy.minusDays(2), new ArrayList<>
        (List.of(new LineaPedido("4-11",3),new LineaPedido("4-22",2),new LineaPedido("4-33",4)))));
       pedidos.add(new Pedido("36347775R-001/2024",clientes.get("36347775R"),hoy.minusDays(3), new ArrayList<>
        (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3)))));
       pedidos.add(new Pedido("36347775R-002/2024",clientes.get("36347775R"),hoy.minusDays(5), new ArrayList<>
        (List.of(new LineaPedido("4-33",3),new LineaPedido("2-11",3)))));
       pedidos.add(new Pedido("63921307Y-001/2024",clientes.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
        (List.of(new LineaPedido("2-11",5),new LineaPedido("2-33",3),new LineaPedido("4-33",2)))));
    
 
     for (Pedido pedido : pedidos) {
         System.out.println(pedido);
     }
    
        System.out.println("");
        
        System.out.println(clientes);
       
        System.out.println("");
        
        System.out.println(articulos);
        
        System.out.println("");
 }
    
//</editor-fold>
    
 //<editor-fold defaultstate="collapsed" desc="GESTIÓN  DE  PEDIDOS">
     private void MenuPedidos() {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Crear Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Listar Pedidos Por Total Del Precio");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion=sc.nextInt(); 
            switch (opcion) { 
                case 1:{
                    nuevoPedido();
                    break;
                }
                case 2:{
                    listarPedidos();
                    break;
                }
                case 3:{
                    listarPedidosPorTotal();
                    break;
                }
                case 0:{
                    System.out.println("Volviendo al menú principal...");
                    break;
                }
            }
        }while(opcion !=0);
    }
    
     public void stock(String id, int unidadesPed) throws StockAgotado, StockInsuficiente{
        int n=articulos.get(id).getExistencias();
        if (n==0) {
            throw new StockAgotado ("Stock AGOTADO para el artículo: " + articulos.get(id).getDescripcion());
        }else if (n< unidadesPed){
            throw new StockInsuficiente ("Stock AGOTADO para el artículo: " + unidadesPed + "de " + articulos.get(id).getDescripcion() + "y sólo se dispone de: " + n);
        }
        
    }
    
     public String generaIdPedido (String idCliente){
         int contador=0;
         String nuevoId;
         for (Pedido p : pedidos) {
             if (p.getClientePedido().getDni().equalsIgnoreCase(idCliente)) {
                 contador++;
             }
         }
         contador++;
         nuevoId= idCliente+"-" + String.format("%03d", contador) + "/" + LocalDate.now().getYear();
         return nuevoId;
     }
    
     public void nuevoPedido() {
        //ARRAYLIST AUXILIAR PARA CREAR EL PEDIDO
        ArrayList<LineaPedido> CestaCompraAux = new ArrayList();
        String dniT, idT, opc, pedidasS;
        int pedidas=0;
        sc.nextLine();
        do{
            System.out.println("CLIENTE PEDIDO (DNI):");
            dniT=sc.nextLine().toUpperCase();
            //EN CUALQUIER MOMENTO PODEMOS SALIR DEL BUCLE TECLEANDO RETORNO
            if (dniT.isBlank()) break;
            if (!MetodosAux.validarDNI(dniT)|| !clientes.containsKey(dniT)) System.out.println("El DNI no es válido O NO ES CLIENTE DE LA TIENDA");;
        }while (!clientes.containsKey(dniT));
        
        if (!dniT.isBlank()){
            System.out.println("\t\tCOMENZAMOS CON EL PEDIDO");
            System.out.println("INTRODUCE CODIGO ARTICULO (RETURN PARA TERMINAR): ");
            idT=sc.nextLine();
                 
            while (!idT.isEmpty()) {
                if (!articulos.containsKey(idT)){
                    System.out.println("El ID articulo tecleado no existe");
                }else{
                    System.out.print("(" + articulos.get(idT).getDescripcion()+ ") - UNIDADES? ");
                    do {
                        pedidasS=sc.nextLine();
                    }while(!MetodosAux.esInt(pedidasS)); 

                    pedidas=Integer.parseInt(pedidasS);

                    try{
                        stock(idT,pedidas); // LLAMO AL METODO STOCK, PUEDEN SALTAR 2 EXCEPCIONES
                        CestaCompraAux.add(new LineaPedido(idT,pedidas));
                        articulos.get(idT).setExistencias(articulos.get(idT).getExistencias()-pedidas);
                    }catch (StockAgotado e){
                        System.out.println(e.getMessage());
                    }catch (StockInsuficiente e){
                        System.out.println(e.getMessage());
                        int disponibles=articulos.get(idT).getExistencias();
                        System.out.print("QUIERES LAS " + disponibles + " UNIDADES DISPONIBLES? (S/N) ");
                        opc=sc.next();
                        if (opc.equalsIgnoreCase("S")){
                            CestaCompraAux.add(new LineaPedido(idT,disponibles));
                            articulos.get(idT).setExistencias(articulos.get(idT).getExistencias()-disponibles);
                        }
                    }
                }
                System.out.println("INTRODUCE CODIGO ARTICULO (RETURN PARA TERMINAR): ");
                idT=sc.nextLine();
            }
         
            //IMPRIMO EL PEDIDO Y SOLICITO ACEPTACION DEFINITIVA DEL MISMO 
            for (LineaPedido l:CestaCompraAux)
            {
                System.out.println(articulos.get(l.getIdArticulo()).getDescripcion() + " - ("+ l.getUnidades() + ")");
            }
            System.out.println("ESTE ES TU PEDIDO. PROCEDEMOS? (S/N)   ");
            opc=sc.next();
            if (opc.equalsIgnoreCase("S")){
            // ESCRIBO EL PEDIDO DEFINITIVAMENTE Y DESCUENTO LAS EXISTENCIAS PEDIDAS DE CADA ARTICULO
                LocalDate hoy=LocalDate.now();
                pedidos.add(new Pedido(generaIdPedido(dniT),clientes.get(dniT),hoy,CestaCompraAux));
            }
            else{    
                for (LineaPedido l:CestaCompraAux)
                {
                    articulos.get(l.getIdArticulo()).setExistencias(articulos.get(l.getIdArticulo()).getExistencias()+l.getUnidades());
                } 
            }
        }
    }
     

    private void listarPedidos() {
         
        Collections.sort(pedidos);
        System.out.println("Pedidos ordenados por fecha de pedidos");
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
        System.out.println("Pedidos ordenados al revés (por fecha de pedidos)");
        Collections.reverse(pedidos);
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

   
    public void listarPedidosPorTotal() {
         
         pedidos.stream().sorted(Comparator.comparing(p -> totalPedido(p)))
                .forEach(p -> System.out.println(p + "\t - IMPORTE TOTAL:" + totalPedido(p)));
              
    }
     
    public double totalPedido(Pedido p)
    {
        double total=0;
        for (LineaPedido l:p.getCestaCompra())
        {
            total+=(articulos.get(l.getIdArticulo()).getPvp())
                    *l.getUnidades();
        }
        return total;
    }
    
    
     
    
//</editor-fold>
    
 //<editor-fold defaultstate="collapsed" desc="GESTIÓN  DE  ARTÍCULOS">
    private void MenuArticulos() {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n--- GESTIÓN DE ARTÍCULOS ---");
            System.out.println("1. Crear Artículo");
            System.out.println("2. Listar Artículos");
            System.out.println("3. Ordenar Artículos con Streams");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion=sc.nextInt(); 
            switch (opcion) { 
                case 1:{
                    nuevoArticulo();
                    break;
                }
                case 2:{
                    listarArticulos();
                    break;
                }
                 case 3:{
                    OrdenarArticulos();
                    break;
                }
                case 0:{
                    System.out.println("Volviendo al menú principal...");
                    break;
                }
            }
        }while(opcion !=0);
    }
    
    private void nuevoArticulo() {
        String id, descripcion;
    int existencias;
    double precio;

    do {
        System.out.print("Ingrese el ID del artículo: ");
        id = sc.nextLine();
        if (articulos.containsKey(id)) {
            System.out.println("El ID ya existe. Intente con otro.");
            return;
        }
    } while (id.isEmpty());

    System.out.print("Ingrese la descripción del artículo: ");
    descripcion = sc.nextLine();

    do {
        System.out.print("Ingrese la cantidad en stock: ");
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, introduzca un número válido.");
            sc.next();
        }
        existencias = sc.nextInt();
    } while (existencias < 0);

    do {
        System.out.print("Ingrese el precio: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Por favor, introduzca un precio válido.");
            sc.next();
        }
        precio = sc.nextDouble();
    } while (precio <= 0);

    Articulo a = new Articulo(id, descripcion, existencias, precio);
    articulos.put(id, a);
    System.out.println("Artículo añadido correctamente.");
    }
     
    private void listarArticulos() {
        
        ArrayList<Articulo> articulosAux = new ArrayList(articulos.values());

        Collections.sort(articulosAux, new ComparaArticulosPorExistencias());
        for (Articulo a : articulosAux) {
            System.out.println(a);
        }
        
        /**
         * Collections.sort(articulos, new ComparaArticulosPorExistencias());
           artículosAux.forEach(System.out::println); 
           Collections.reverse(artículos);
           artículosAux.forEach(System.out::println);
         */
        
        System.out.println("");
        System.out.println("AL REvÉS");
        System.out.println("");
        
        Collections.reverse(articulosAux);
        for (Articulo a : articulosAux) {
            System.out.println(a);
        }
    }

    private void OrdenarArticulos(){
        
        System.out.println("ARTÍCULOS DESORDENADOS");
        articulos.values().stream().sorted().forEach(System.out::println);
        System.out.println("ARTÍCULOS ORDENADOS POR EXISTENCIAS");
        articulos.values().stream().sorted(new ComparaArticulosPorExistencias()).forEach(System.out::println);
        System.out.println("ARTÍCULOS ORDENADOS POR PRECIO");
        articulos.values().stream().sorted(new ComparaArticulosPorPrecio()).forEach(System.out::println);
        
    }

    
//</editor-fold>
 
 //<editor-fold defaultstate="collapsed" desc="GESTIÓN  DE  CLIENTES">
    private void MenuClientes() {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion=sc.nextInt(); 
            switch (opcion) { 
                case 1:{
                    nuevoCliente();
                    break;
                }
                case 2:{
                    listarClientes();
                    break;
                }
                case 0:{
                    System.out.println("Volviendo al menú principal...");
                    break;
                }
            }
        }while(opcion !=0);
    }
    
     private void nuevoCliente() {
         String dni, nombre, telefono, email;

    do {
        System.out.print("Deme su DNI: ");
        dni = sc.nextLine().toUpperCase();
        if (!MetodosAux.validarDNI(dni)) {
            System.out.println("DNI no válido. Inténtelo de nuevo.");
        } else if (clientes.containsKey(dni)) {
            System.out.println("Ese cliente ya existe. Intente con otro DNI.");
            return;
        }
    } while (!MetodosAux.validarDNI(dni));

    System.out.print("Deme su NOMBRE: ");
    nombre = sc.nextLine();
    
    do {
        System.out.print("Deme su TELÉFONO: ");
        telefono = sc.nextLine();
        if (!telefono.matches("\\d{9}")) {
            System.out.println("Número de teléfono no válido. Debe tener 9 dígitos.");
        }
    } while (!telefono.matches("\\d{9}"));
    
    do {
        System.out.print("Deme su EMAIL: ");
        email = sc.nextLine();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Correo electrónico no válido. Inténtelo de nuevo.");
        }
    } while (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"));

    Cliente c = new Cliente(dni, nombre, telefono, email);
    clientes.put(dni, c);
    System.out.println("Cliente añadido correctamente.");
    }
     
    private void listarClientes() {
         
        ArrayList<Cliente> clientesAux = new ArrayList(clientes.values());

        Collections.sort(clientesAux);
        for (Cliente c : clientesAux) {
            System.out.println(c);
        }
        
        System.out.println("");
        System.out.println("AL REvÉS");
        System.out.println("");
        
        Collections.reverse(clientesAux);
        for (Cliente c : clientesAux) {
            System.out.println(c);
        }
    }

    
//</editor-fold>
    
    
    
    
    
    
}
