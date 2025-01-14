/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package dawvictormm.biblioteca2025;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author alu16d
 */
public class Biblioteca2025 {

    private ArrayList <Libro> libros;
    private ArrayList <Usuario> usuarios;
    private ArrayList <Prestamo> prestamos;
    private ArrayList <Prestamo> prestamosHist;
    
    public Biblioteca2025() {
        this.libros = new ArrayList();
        this.usuarios = new ArrayList();
        this.prestamos = new ArrayList();
        this.prestamosHist = new ArrayList();
    }
    
    public static void main(String[] args) {
        Biblioteca2025 b= new Biblioteca2025();
        b.cargaDatos();
        
        b.menu();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Auxiliares">
     private void cargaDatos() {
        
        libros.add(new Libro("1-11","El Hobbit","JRR Tolkien","Aventuras",3)); 
        libros.add(new Libro("1-22","El Silmarillon","JRR Tolkien","Aventuras",3)); 
        libros.add(new Libro("1-33","El Médico","N. Gordon","Aventuras",4)); 
        libros.add(new Libro("1-44","Chamán","N. Gordon","Aventuras",3)); 
        libros.add(new Libro("1-55","Momo","M. Ende","Aventuras",2)); 
        libros.add(new Libro("1-66","Paraíso inhabitado","A.M.Matute","Aventuras",2)); 
        libros.add(new Libro("1-77","Olvidado Rey Gudú","A.M.Matute","Aventuras",2)); 
        libros.add(new Libro("1-88","El último barco","D.Villar","Novela Negra",3)); 
        libros.add(new Libro("1-99","Ojos de agua","D.Villar","Novela Negra",0)); 
        
        
        usuarios.add(new Usuario("11","Ana","ana@email.com","621111111")); 
        usuarios.add(new Usuario("22","David","david@email.com","622222222")); 
        usuarios.add(new Usuario("33","Bea","bea@email.com","623333333")); 
        usuarios.add(new Usuario("44","Lucas","lucas@email.com","624444444")); 
        usuarios.add(new Usuario("55","Carlota","carlota@email.com","625555555")); 
        usuarios.add(new Usuario("66","Juan","juan@email.com","626666666"));
        
        LocalDate hoy= LocalDate.now();
        prestamos.add(new Prestamo(libros.get(2),usuarios.get(0), hoy.minusDays(12), hoy.plusDays(3)));
        prestamos.add(new Prestamo(libros.get(8),usuarios.get(2), hoy.minusDays(17), hoy.plusDays(-1)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(4), hoy.minusDays(20), hoy.plusDays(-4)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6),usuarios.get(2), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(2),usuarios.get(1), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy, hoy.plusDays(15)));
        
        for (Libro l : libros) {
            System.out.println(l);
        }
        System.out.println("");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
        System.out.println("");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
        System.out.println("");
        System.out.println("Prestamos fuera de plazo: ");
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().isBefore(LocalDate.now())) {
                     System.out.println(p);
                 }
            
        }
        System.out.println("");
        
    }
     /**
      * Método para buscar un dni en la coleccion de usuarios
      * @param dni (String) del usuario a buscar en la coleccion
      * @return pos (int) del usuario del Arraylist, valor-1 si no se encuentra
      */
     public int buscaDni (String dni){
         int pos=-1;
         for (int i = 0; i < usuarios.size(); i++) {
             if(usuarios.get(i).getDni().equals(dni)){
                 pos=i;
                 break;
             }
             
         }return pos;
     }
     
     /**
      * Método para solicitar por teclado dni de un usuario
      * @param 
      * @return dni (string)
      */
     public String solicitaDni (){
         Scanner sc=new Scanner(System.in);
         System.out.println("Teclea el dni del usuario: ");
         String dni=sc.nextLine();
         return dni;
     }
     
     /**
      * Método para solicitar por teclado un isbn de un libro
      * @return isbn (string)
      */
     public String solicitaIsbn (){
         Scanner sc=new Scanner(System.in);
         System.out.println("Teclea el isbn del libro: ");
         String isbn=sc.nextLine();
         return isbn;
     }
     
     /**
      * Método para buscar un isbn en la coleccion de libros
      * @param isbn (String) del libro a buscar en la coleccion
      * @return pos (int) del usuario del Arraylist, valor-1 si no se encuentra
      */
     public int buscaIsbn(String isbn){
        int pos=-1; 

        for (int i = 0; i < libros.size(); i++) {  
            if (libros.get(i).getIsbn().equals(isbn)) {  

                pos=i; 

                break; 

            } 

        }return pos; 
    }
        /**
         * Método para buscar un préstamo en la colección préstamos
         * @param dni (string) del usuario que realizó el préstamo
         * @param  isbn (string) del libro prestado
         * @return posicion (int) del préstamo del Arraylist,
         *         valor -1 si no se ha encontró el préstamos con esos datos datos
         */ 
     public int buscaPrestamo (String dni, String isbn){
        
        int pos=-1;
         for (int i = 0; i < prestamos.size(); i++) {
             if(prestamos.get(i).getUsuarioPrest().getDni().equals(dni) && prestamos.get(i).getLibroPrest().getIsbn().equals(isbn)){
                 pos=i;
                 break;
             }
         }return pos;
     }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MENU GENERAL">
    private void menu() {
            Scanner sc=new Scanner (System.in); 

        int opcion=0; 

        do { 
            System.out.println("Menú de opciones"); 
            System.out.println("\t\t\t\t1 - GESTION USUARIOS/AS"); 
            System.out.println("\t\t\t\t2 - GESTION LIBROS"); 
            System.out.println("\t\t\t\t3 - GESTION PRÉSTAMO/DEVOLUCIÓNES");
            System.out.println("\t\t\t\t4 - SALIR"); 

            opcion=sc.nextInt(); 
            
            switch (opcion) { 
                case 1:{
                    menuUsuario();
                    break;
                }
                case 2:{
                    menuLibro();
                    break;
                }
                case 3:{
                    menuPrestamo();
                    break;
                }
            }
        }while(opcion !=4);
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MENU LIBROS">
    private void menuLibro() {
         Scanner sc=new Scanner (System.in); 

        int opcion=0; 

        do { 
            System.out.println("\n\n\n\n\n\t\t\t\t - GESTION DE LIBROS\n"); 
            System.out.println("\t\t\t\t1 - ALTA NUEVO LIBRO"); 
            System.out.println("\t\t\t\t2 - BAJA LIBRO"); 
            System.out.println("\t\t\t\t3 - MODIFICACION DATOS LIBRO");
            System.out.println("\t\t\t\t4 - LISTADO DE LIBROS DISPONIBLES");
            System.out.println("\t\t\t\t4 - ORDEN POR GENERO");
            System.out.println("\t\t\t\t9 - VOLVER"); 

            opcion=sc.nextInt(); 
            
            switch (opcion) { 
                case 1:{
                    nuevoLibro();
                    break;
                }
                case 2:{
                    eliminarLibro();
                    break;
                }
                case 3:{
                    modificarLibro();
                    break;
                }
                case 4:{
                    listarLibro();
                    break;
                }
                case 5:{
                    ordenado();
                    break;
                }
            }
        }while(opcion !=9);
    }
    private void nuevoLibro() {
        String isbn, titulo, autor, genero;
        int ejemplares;
        Scanner sc=new Scanner (System.in); 

        System.out.println("Nuevo Contacto"); 

        System.out.println("isbn"); 
        isbn = sc.nextLine(); 

        System.out.println("titulo"); 
        titulo = sc.nextLine(); 
        
        System.out.println("autor"); 
        autor = sc.nextLine(); 
        
        System.out.println("genero"); 
        genero = sc.nextLine(); 
        
        System.out.println("ejemplares"); 
        ejemplares = sc.nextInt(); 
        
        Libro l=new Libro(isbn,titulo, autor, genero, ejemplares); 
        libros.add(l); 
        
        
    }
    private void eliminarLibro() {
        Scanner sc=new Scanner (System.in); 
        
        ArrayList <String> nombres=new ArrayList();
        
        System.out.println("Introduce el isbn de los libros a borrar, uno a uno 'FIN'");
        System.out.println("ISBN: ");
        String nombre= sc.nextLine();
        while(!nombre.equalsIgnoreCase("FIN")) {
             nombres.add(nombre);
             System.out.println("Libro puesto de baja");
             System.out.println("ISBN: ");
             nombre=sc.next();
        }
        for (String n : nombres) {
            int pos=buscaIsbn(n);
            if (pos!=-1) {
                libros.remove(pos);
            }
        }
    }
    private void modificarLibro() {
        System.out.println("Indique el isbn del libro a modificar"); 
        Scanner sc=new Scanner (System.in); 
        String nombre = sc.nextLine(); 
        int posicion = buscaIsbn(nombre); 

        if (posicion==-1) { 

            System.out.println("El isbn del libro que buscas no está en esta biblioteca :("); 

        } else { 

            System.out.println("Quieres añadir ejemplar(+) o restar ejemplar(-): "); 
            int agrego=sc.nextInt();
            if (agrego>=1) {
                libros.get(posicion).setEjemplares(libros.get(posicion).getEjemplares()+agrego);
            }
            else{
                libros.get(posicion).setEjemplares(libros.get(posicion).getEjemplares()+agrego);
            }
            
            System.out.println("Libro modificado :D"); 

        } 
    }
    private void listarLibro() {
        for (Libro l : libros) {
            System.out.println(l);
        }
    }
    
    private void ordenado(){
        System.out.println("Dime el genero de libros que quieres ver; ");
        
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MENU USUARIOS">
    private void menuUsuario() {
         Scanner sc=new Scanner (System.in); 

        int opcion=0; 

        do { 
            System.out.println("\n\n\n\n\n\t\t\t\t - GESTION DE USUARIOS/AS\n"); 
            System.out.println("\t\t\t\t1 - ALTA NUEVO USUARIO"); 
            System.out.println("\t\t\t\t2 - BAJA USUARIO/A"); 
            System.out.println("\t\t\t\t3 - LISTADO USUARIOS/AS");
            System.out.println("\t\t\t\t9 - VOLVER"); 

            opcion=sc.nextInt(); 
            
            switch (opcion) { 
                case 1:{
                    nuevoUsuario();
                    break;
                }
                case 2:{
                    eliminarUsuario();
                    
                    break;
                }
                case 3:{
                    listarUsuario();
                    
                    break;
                }
                
            }
        }while(opcion !=9);
    }
    private void nuevoUsuario() {
        String dni, nombre, email, telefono;
        Scanner sc=new Scanner (System.in); 

        System.out.println("Nuevo Usuario"); 

        System.out.println("dni"); 
        dni = sc.nextLine(); 

        System.out.println("nombre"); 
        nombre = sc.nextLine(); 
        
        System.out.println("email"); 
        email = sc.nextLine(); 
        
        System.out.println("telefono"); 
        telefono = sc.nextLine(); 
        
        Usuario u=new Usuario(dni, nombre, email, telefono); 
        usuarios.add(u); 
    }
    private void eliminarUsuario() {
        Scanner sc=new Scanner (System.in); 
        
        ArrayList <String> nombres=new ArrayList();
        
        System.out.println("Introduce el dni de los usuarios a borrar, uno a uno 'FIN'");
        System.out.println("DNI: ");
        String nombre= sc.nextLine();
        while(!nombre.equalsIgnoreCase("FIN")) {
             nombres.add(nombre);
             System.out.println("DNI: ");
             nombre=sc.next();
        }
        for (String n : nombres) {
            int pos=buscaDni(n);
            if (pos!=-1) {
                usuarios.remove(pos);
            }
        }
    }
    private void listarUsuario() {
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    } 
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MENU PRESTAMOS">
    private void menuPrestamo() {
         Scanner sc=new Scanner (System.in); 

        int opcion=0; 

        do { 
            System.out.println("\n\n\n\n\n\t\t\t\t - GESTION DE PRÉSTAMOS\n"); 
            System.out.println("\t\t\t\t1 - PRÉSTAMOS"); 
            System.out.println("\t\t\t\t2 - DEVOLUCIÓNES"); 
            System.out.println("\t\t\t\t3 - PRÓRROGAS");
            System.out.println("\t\t\t\t4 - LISTADO PRÉSTAMOS (TODOS)");
            System.out.println("\t\t\t\t5 - PRÉSTAMOS USUARIO");
            System.out.println("\t\t\t\t6 - PRÉSTAMOS LIBRO");
            System.out.println("\t\t\t\t7 - LIBRO MAS PRESTADO");
            System.out.println("\t\t\t\t8 - USUARIO MAS LECTOR");
            System.out.println("\t\t\t\t9 - VOLVER"); 

            opcion=sc.nextInt(); 
            
            switch (opcion) { 
                case 1:{
                    nuevoPrestamo();
                    break;
                }
                case 2:{
                    devolucion();
                    
                    break;
                }
                case 3:{
                    prorroga();
                    
                    break;
                }
                case 4:{
                    listarPrestamo();
                    
                    break;
                }
                case 5:{
                    listaPrestamoUsu();
                    
                    break;
                }
                case 6:{
                    listaPrestamoLibro();
                    
                    break;
                }
                case 7:{
                    libroMaxPrest();
                    
                    break;
                }
                case 8:{
                    usuarioMaxLector();
                    
                    break;
                }
            }
        }while(opcion !=9);
    }
    private void nuevoPrestamo() {
        System.out.println("Identificación del usuario: ");
        
        String dni=solicitaDni();
        int posUsuario = buscaDni(dni);
        if (posUsuario==-1){
            System.out.println("No es aún usuario de la biblioteca: ");
        }else{
            System.out.println("Identificación del Libro: ");
            String isbn=solicitaIsbn();
            int posLibro=buscaIsbn(isbn);
            if (posLibro==-1) {
                System.out.println("El isbn pertenece a un libro que no tenemos: ");
            }
            
            else if (libros.get(posLibro).getEjemplares()>0 ){
                if ((buscaPrestamo(dni,isbn))==-1){
                    LocalDate hoy= LocalDate.now();
                    prestamos.add(new Prestamo(libros.get(posLibro),usuarios.get(posUsuario),hoy,hoy.plusDays(15)));
                    libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares()-1);
                    System.out.println("Prestamo añadido");
                }else {
                    System.out.println("Este usuario ya tiene este préstamo");
                }
            }else{
                System.out.println("No quedan unidades disponibles de ese libro: ");
            }
        }
        
        
    }
    private void devolucion() {
        System.out.println("Datos para la prórroga del préstamo: ");
        String dni=solicitaDni();
        String isbn=solicitaIsbn();
            int pos= buscaPrestamo(dni,isbn);
            if (pos==-1) {
                System.out.println("No hay ningún préstamo con esos datos :(");
            }else{
                prestamos.get(pos).setFechaDev(LocalDate.now());
                int posLibro=buscaIsbn(isbn);
                libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares()+1);
                prestamosHist.add(prestamos.get(pos));
                prestamos.remove(pos);
            }
            System.out.println("Devolución hecha :D");
        
        
        
/**        no hace falta eliminar préstamos, pk nos dna una info extra.
*       System.out.println("Identificación del usuario: ");
*        int posUsuario=buscaDni(solicitaDni());
*        if (posUsuario==-1){
*            System.out.println("No es aún usuario de la biblioteca: ");
*        }else{
*            System.out.println("Identificación del Libro: ");
*            int posLibro=buscaIsbn(solicitaIsbn());
*         if (posLibro==-1) {
*             System.out.println("El isbn pertenece a un libro que no tenemos: ");
*          }else if (libros.get(posLibro).getEjemplares()>0)
*                   {
*              libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares()+1);
*                   }else{
*                       System.out.println("Ese prestamo ya esta hecho: ");
*                      }
*            }
*/
    }
    private void prorroga() {
            System.out.println("Datos para la prórroga del préstamo: ");
            int pos= buscaPrestamo(solicitaDni(),solicitaIsbn());
            if (pos==-1) {
                System.out.println("No hay ningú préstamo con esos datos :(");
            }else{
                prestamos.get(pos).setFechaDev(prestamos.get(pos).getFechaDev().plusDays(15));
                prestamos.get(pos).setFechaPrest(LocalDate.now());
            }
            System.out.println("Préstamo modificado :D"); 
    }
    private void listarPrestamo() {
        System.out.println("Listado de Préstamos activos: ");
            for (Prestamo p : prestamos) {
                if (p.getFechaDev().isBefore(LocalDate.now())) {
                     System.out.print("Libro fuera de plazo: ");
                 }
                System.out.println(p);
            }   
        System.out.println("\nListado de Préstamos históricos: ");
            for (Prestamo p : prestamosHist) {
                if (p.getFechaDev().isBefore(LocalDate.now())) {
                     System.out.print("Libro fuera de plazo: ");
                 }
                System.out.println(p);
            } 
    }
    
    private void listaPrestamoUsu() {
       System.out.println("Dni del usuario: ");
       String dni=solicitaDni();
       int pos = buscaDni(dni);
       
        if (pos==-1) {
            System.out.println("No tengo a nadie con ese Dni");
        }else   {
       
        System.out.println("Listado de Préstamos activos de: "+ usuarios.get(pos).getNombre());
         for (Prestamo p : prestamos) {
             if (p.getUsuarioPrest().getDni().equals(dni)) {
                 if (p.getFechaDev().isBefore(LocalDate.now())) {
                     System.out.print("Libro fuera de plazo: ");
                 }
                 System.out.println(p);
             }
         }
         System.out.println("\nListado de Préstamos históricos: "+ usuarios.get(pos).getNombre());
         for (Prestamo p : prestamosHist) {
             if (p.getUsuarioPrest().getDni().equals(dni)) {
                 System.out.println(p);
             }
         }
        }
    }
    private void listaPrestamoLibro() {
       System.out.println("Isbn del libro: ");
       String isbn=solicitaIsbn();
       int pos = buscaIsbn(isbn);
       
        if (pos==-1) {
            System.out.println("No tengo ningun libro con ese Isbn");
        }else   {
       
        System.out.println("Listado de Préstamos activos de: "+ libros.get(pos).getTitulo());
         for (Prestamo p : prestamos) {
             if (p.getLibroPrest().getIsbn().equals(isbn)) {
                 if (p.getFechaDev().isBefore(LocalDate.now())) {
                     System.out.print("Libro fuera de plazo: ");
                 }
                 System.out.println(p);
             }
         }
         System.out.println("\nListado de Préstamos históricos: "+ libros.get(pos).getTitulo());
         for (Prestamo p : prestamosHist) {
             if (p.getLibroPrest().getIsbn().equals(isbn)) {
                 System.out.println(p);
             }
         }
        }
    }
    private void libroMaxPrest() {
        
/**        int maxPrestamos = 0;
        Libro libroMasPrestado = null;

        for (Libro libro : libros) {
            int contador = 0;
            // Contar en préstamos activos
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(libro.getIsbn())) {
                    contador++;
                }
            }
            // Contar en préstamos históricos
            for (Prestamo p : prestamosHist) {
                if (p.getLibroPrest().getIsbn().equals(libro.getIsbn())) {
                    contador++;
                }
            }
            // Actualizar si es el libro más prestado
            if (contador > maxPrestamos) {
                maxPrestamos = contador;
                libroMasPrestado = libro;
            }
        }
        if (libroMasPrestado != null) {
            System.out.println("El libro más prestado es: " + libroMasPrestado.getTitulo() + " con " + maxPrestamos + " préstamos");
        } else {
            System.out.println("No hay préstamos registrados");
        }
*/
    }

    private void usuarioMaxLector() {
 /**       int maxLector = 0;
       Usuario usuarioMasLector = null;

       for (Usuario usuario : usuarios) {
            int contador = 0;
            // Contar en préstamos activos
            for (Prestamo p : prestamos) {
                if (p.getUsuarioPrest().getDni().equals(usuario.getDni())) {
                    contador++;
                }
            }
            // Contar en préstamos históricos
            for (Prestamo p : prestamosHist) {
                if (p.getUsuarioPrest().getDni().equals(usuario.getDni())) {
                    contador++;
                }
            }
            // Actualizar si es el libro más prestado
            if (contador > maxLector) {
                maxLector = contador;
                usuarioMasLector = usuario;
            }
        }
        if (usuarioMasLector != null) {
            System.out.println("El usuario mas lector es: " + usuarioMasLector.getNombre() + " con " + maxLector + " préstamos");
        } else {
            System.out.println("No hay lectores registrados");
        }
 */      
    }
//</editor-fold>
    
}

   

    