/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luis
 */
public class Zona {
    private String nombre;
    private int capacidad;
    private int precio;
    private Entrada[] entrada;
    public static int codigo = 1000;
    private int index = 0;

    public Zona(String nombre, int capacidad, int precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        entrada = new Entrada[this.capacidad];
        for (int i = 0; i < this.capacidad; i++) {
            entrada[i] = new Entrada(Zona.codigo);
            Zona.codigo++;
        }
    }
    
    public int contarDisponibles(){
        int x = 0;
        for (int i = 0; i < this.capacidad; i++) {
            if (this.entrada[i].isDisponible()) {
                x++;
            }
        }
        return x;
    }
    
    public Entrada[] vender(int num){
        Entrada[] aux = new Entrada[num];
        int x = 0;
        for (int i = this.index; i < num+this.index; i++) {
            this.entrada[i].setDisponible(false);
            aux[x] = this.entrada[i];
            x++;
        }
        this.index += num;
        return aux;
    }

    @Override
    public String toString() {
        return nombre + "\tprecio: " + precio + "\tDisponibles: "+this.contarDisponibles();
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}