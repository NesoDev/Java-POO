
import java.util.Scanner;
import java.io.IOException;
public class App {
    
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        int opc, iterador = 1;
        System.out.print("\033[H\033[2J"); // Borrar la pantalla
        // Creación de los objetos de la aplicación
        Evento evento = new Evento("CONCIERTO POR ANIVERSARIO DE LA UNMSM");
        Cliente cliente;
        System.out.println("-- BIENVENIDO --\n");
        do {
            System.out.println("¿ Qué desea hacer hoy ?\n");
            mainMenu();
            System.out.println("");
            System.out.print("Inserta opcion: ");
            opc = input.nextInt();
            System.out.print("\033[H\033[2J"); // Borrar la pantalla
            switch(opc){
                case 1 -> { // registrarse
                    System.out.println("\n-- USTED ESTÁ REGISTRANDOSE --  \n");
                    cliente = inputCliente();
                    System.out.print("\033[H\033[2J"); // Borrar la pantalla
                    if (evento.buscarCliente(cliente.getDni()) == null) {
                        evento.addCliente(cliente);
                        System.out.println("\nNota: Enhorabuena "+cliente.getNombre()+", se ha registrado con éxito\n");
                    } else {
                        System.out.println("\nERROR: El usuario ya existe\n");
                        System.out.println("Nota: Intenta de nuevo\n");
                    }
                    System.out.print("Presione 'c' para volver al menú...");
                    input.next().charAt(0);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
                case 2 -> { // log in
                    String dni;
                    System.out.println("\n-- USTED ESTÁ INICIANDO SESION --  \n");
                    System.out.print("Inserta DNI: ");
                    dni = input.next();
                    cliente = evento.buscarCliente(dni);
                    System.out.print("\033[H\033[2J"); // Borrar la pantalla
                    if (cliente==null) {
                        System.out.println("ERROR : Usuario no encontrado\n");
                        System.out.print("Presione 'c' para volver al menú...");
                        input.next().charAt(0);
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } else {
                        int opc2;
                        int numZona;
                        do {
                            System.out.print("\033[H\033[2J"); // Borrar la pantalla
                            System.out.println("Bienvenido "+cliente.getNombre()+". ¿ Qué haremos hoy ?\n");
                            menu();
                            System.out.print("\nInserta opcion: ");
                            opc2 = input.nextInt();
                            System.out.print("\033[H\033[2J"); // Borrar la pantalla
                            switch(opc2){
                                case 1 -> { // comprar
                                    if (cliente.isEmptyTarjetas()) {
                                        System.out.println("ERROR: No tienes tarjetas asociadas");
                                        System.out.println("Nota: Para realizar compras, debe afiliar al menos una tarjeta a su cuenta");
                                        System.out.print("Presione 'c' para volver al menú anterior...");
                                        input.next().charAt(0);
                                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                    } else {
                                        String numTarjeta;
                                        System.out.println("\n-- USTED ESTÁ REALIZANDO UNA COMPRA --  \n");
                                        System.out.print("Inserta numero de tarjeta: ");
                                        numTarjeta = input.next();
                                        Tarjeta t = cliente.buscarTarjeta(numTarjeta);
                                        if (t == null) {
                                            System.out.println("\nERROR: Esta tarjeta no está afiliada a su cuenta");
                                        } else {
                                            int cant;
                                            do {
                                                System.out.print("\033[H\033[2J"); // Borrar la pantalla
                                                System.out.println("\n-- USTED ESTÁ REALIZANDO UNA COMPRA --  \n");
                                                evento.mostrarZonas();
                                                System.out.print("\nElije una zona: ");
                                                numZona = input.nextInt();
                                            } while (numZona<1 || numZona>4);
                                            System.out.print("Inserte cantidad de entradas: ");
                                            cant = input.nextInt();
                                            Zona z = evento.getZona(numZona-1);
                                            System.out.println("");
                                            if (z.contarDisponibles()<cant) {
                                                System.out.println("ERROR: No hay entradas suficientes");
                                            } 
                                            /*else if(cant + cliente.getCantEntradas() > 4){
                                                System.out.println("ERROR: Solo puedes comprar 4 entradas");
                                            } */
                                            else {
                                                cliente.comprar(t, z.vender(cant), numZona);
                                                System.out.println("Nota: Enhorabuena "+cliente.getNombre()+", la compra fue exitosa");
                                                /*for (Compra compra : cliente.getCompras()){
                                                    iterador = compra.asignarCodigo(iterador);
                                                }*/
                                                evento.setZona(z, numZona-1);
                                            }
                                        }
                                        System.out.print("\nPresione 'c' para volver al menú anterior...");
                                        input.next().charAt(0);
                                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                    }
                                }
                                case 2 -> { // mostrar compras
                                    System.out.print("COMPRAS: ");
                                    if (cliente.isEmptyCompras()) {
                                        System.out.print("Aún no ha realizado ninguna compra");
                                    } else {
                                        for (Compra compra : cliente.getCompras()){
                                            System.out.println("\n");
                                            System.out.println(compra);
                                            compra.imprimirEntradas();
                                        }
                                    }
                                    System.out.print("\nPresione 'c' paras volver al menú anterior...");
                                    input.next().charAt(0);
                                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                }
                                case 3 -> { // afiliar tarjeta
                                    System.out.println("\n-- USTED ESTÁ AFILIANDO UNA TARJETA A SU CUENTA --  \n");
                                    Tarjeta t = inputTarjeta(cliente.getNombre());
                                    System.out.println("");
                                    if (cliente.buscarTarjeta(t.getNum()) == null) {
                                        cliente.agregarTarjeta(t);
                                        System.out.println("Nota: Tarjeta afiliada con exito");
                                    } else {
                                        System.out.println("Error: Esta tarjeta ya se encuentra afiliada a su cuenta");
                                    }
                                    System.out.print("\nPresione 'c' para volver al menú anterior...");
                                    input.next().charAt(0);
                                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                }
                            }
                            cliente.setEntradasCant(0);
                        } while (opc2!=0);
                    }
                }
                case 3 -> {
                    evento.mostrarZonas();
                }
            }
        } while (opc!=0);
        System.out.print("\033[H\033[2J"); // Borrar la pantalla
        input.close();
    }
    
    public static void mainMenu(){
        System.out.println("1. Registrarse");
        System.out.println("2. Ingresar");
        System.out.println("3. Lista de precios");
        System.out.println("0. Salir");
    }
    
    public static void menu(){
        System.out.println("1. Comprar entradas");
        System.out.println("2. Ver compras");
        System.out.println("3. Afiliar tarjeta");
        System.out.println("0. Salir");
    }
    
    public static Cliente inputCliente() {
        String dni;
        String nombre;
        int year, mes, dia;
        System.out.print("Inserte su nombre: ");
        nombre = input.next();
        input.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Inserte DNI: ");
        dni = input.next();
        input.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Año de nacimiento: ");
        year = input.nextInt();
        System.out.print("Mes de nacimiento: ");
        mes = input.nextInt();
        System.out.print("Dia de nacimiento: ");
        dia = input.nextInt();
        return new Cliente(dni, nombre, year, mes, dia);
}
    
    public static Tarjeta inputTarjeta(String nombre){
        String num;
        int year, mes;
        String cvv;
        System.out.print("Inserte numero de tarjeta: ");
        num = input.next();
        System.out.print("Inserte año de vencimiento: ");
        year = input.nextInt();
        System.out.print("Inserte mes de vencimiento: ");
        mes = input.nextInt();
        System.out.print("Inserte CVV: ");
        cvv = input.next();
        return new Tarjeta(nombre, num, year, mes, cvv);
    }
}
