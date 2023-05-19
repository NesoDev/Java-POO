
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luis
 */
public class Cliente {
    private String dni;
    private String nombre;
    private Date nacimiento;
    private Tarjeta[] tarjetas = new Tarjeta[0];
    private Compra[] compras = new Compra[0];
    private int index = 0;
    private int entradasCant = 0;

    public Cliente(String dni, String nombre, Date nacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
    }
    
    public Cliente(String dni, String nombre, int year, int mes, int dia) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacimiento = new Date(year-1900, mes-1, dia);
    }
    
    public void comprar(Tarjeta t, Entrada[] entradas,int zona){
        Compra c = new Compra(t, entradas, zona);
        entradasCant += entradas.length;
        Compra[] aux = new Compra[this.compras.length+1];
        System.arraycopy(this.compras, 0, aux, 0, this.compras.length);
        aux[this.compras.length] = c;
        this.compras = aux;        
    }
    public int getCantEntradas(){
        return this.entradasCant;
    }
    
    public void setEntradasCant(int entradasCant) {
        this.entradasCant = entradasCant;
    }
    
    public Compra[] getCompras(){
        return this.compras;
    }
    
    public Tarjeta buscarTarjeta(String num){
        for (Tarjeta tarjeta : this.tarjetas) {
            if (num.equals(tarjeta.getNum())) {
                return tarjeta;
            }
        }
        return null;
    }
    
    public void agregarTarjeta(Tarjeta t){
        Tarjeta[] aux = new Tarjeta[this.tarjetas.length+1];
        System.arraycopy(this.tarjetas, 0, aux, 0, this.tarjetas.length);
        aux[this.tarjetas.length] = t;
        this.tarjetas = aux;
    }
    
    public boolean eliminarTarjeta(String num){
        int pos = -1;
        for (int i = 0; i < index; i++) {
            if (this.tarjetas[i].getNum().equals(num)) {
                pos = i;
                break;
            }
        }
        if (pos!=-1) {
            for (int i = pos; i < index-1; i++) {
                this.tarjetas[i] = this.tarjetas[i+1];
            }
            this.tarjetas[index-1] = null;
            this.index--;
            return true;
        }
        return false;
    }
    
    public void imprimeTarjetas(){
        for (int i = 0; i < this.tarjetas.length; i++) {
            System.out.println((i+1)+". "+this.tarjetas[i].getNum());
        }
    }
    
    public boolean isEmptyTarjetas(){
        return this.tarjetas.length == 0;
    }
    
    public boolean isEmptyCompras(){
        return this.compras.length == 0;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "dni=" + dni + ", nombre=" + nombre + '}';
    }
    
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Tarjeta[] getTarjetas() {
        return tarjetas;
    }

    public int getIndex() {
        return index;
    }
}
