/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luis
 */
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Evento {
    private String nombre;
    private String fecha;
    private Zona[] zona ;
    private Cliente[] clientes;
    private int indexCliente = 0;

    public Evento(String nombre) {
        this.nombre = nombre;
        this.fecha = Evento.formatoFecha(new Date(123, 4, 9));
        this.clientes = new Cliente[0];
        this.zona = new Zona[4];
        for (int i = 0; i < 4; i++) {
            zona[i] = new Zona("Zona "+(i+1), 100, 120+(i*20));
        } 
    }
    
    public static String formatoFecha(Date fecha){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }
    
    public void mostrarZonas(){
        for (int i = 0; i < this.zona.length; i++) {
            System.out.println((i+1)+". "+this.zona[i]);
        }
    }
    
    public Cliente buscarCliente(String dni){
        for (Cliente cliente : this.clientes) {
            if (dni.equals(cliente.getDni())) {
                return cliente;
            }
        }
        return null;
    }
    
    public void addCliente(Cliente c){
        Cliente[] aux = new Cliente[this.clientes.length+1];
        System.arraycopy(this.clientes, 0, aux, 0, this.clientes.length);
        aux[this.clientes.length] = c;
        this.clientes = aux;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Zona getZona(int index){
        return this.zona[index];
    }
    public void setZona(Zona z, int index){
        this.zona[index] = z;
    }
}