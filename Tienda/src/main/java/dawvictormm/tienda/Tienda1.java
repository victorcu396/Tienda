/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dawvictormm.tienda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author alu16d
 */
public class Tienda1 {

    Scanner sc=new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;
    
    
    
    private static void MenuPedidos() {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Crear Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion=sc.nextInt(); 
            switch (opcion) { 
                case 1:{
                    crearPedido();
                    break;
                }
                case 2:{
                    listarPedidos();
                    
                    break;
                }
                case 0:{
                    System.out.println("Volviendo al menú principal...");
                    break;
                }
            }
        }while(opcion !=0);
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="GESTIÓN DE PEDIDOS">
     public void stock(int unidadesPed, String id) throws StockAgotado, StockInsuficiente{
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
    
     public void nuevoPedido(){
         //ARRAYLIST AUXILIAR PARA CREAR EL PEDIDO
         ArrayList<LineaPedido> cestaCompraAux = new ArrayList();
         String dniT, idT, opc, perdidoasS;
         int pedidas=0;
         
         sc.nextLine();
         
         /**
         do {
             System.out.println("CCLIENTE PEDIDO (DNI): ");
             dniT=sc.nextLine().toUpperCase();
             if (dniT.isBlank()) {
                 break;
             }
             if (!MetodosAux.validar) {
                 
             }
             
         } while (true);
         */
         
         
         
         /**
         if (!dniT.isBlank()) {
             
         }
         
         */
         
     }
     
     
     
     
     
    
//</editor-fold>
 
   
    private static void listarPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void crearPedido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   
    
    
    
    
    
}
