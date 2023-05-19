
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luis
 */
public class Compra {
    private Date fecha;
    private int codigo;
    private boolean estado;
    private Tarjeta tarjeta;
    private Entrada[] entradas;
    private int zona;
    public static int cod = 1;

    public Compra(Tarjeta t, Entrada[] e, int zona) {
        fecha = new Date();
        this.codigo = Compra.cod;
        Compra.cod++;
        this.tarjeta = t;
        this.entradas = e;
        this.zona = zona;
        this.estado = true;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fechaFormateada = formatoFecha.format(fecha);
        return "Fecha de compra: " + fechaFormateada + "\nCodigo de compra: " + codigo + "\nNumero de tarjeta: " + tarjeta.getNum();
    }
    
    public int asignarCodigo(int iterador){
        for (Entrada e : this.entradas){
            e.setNumero(iterador);
            iterador++;
        }
        return iterador;
    }
    
    public void imprimirEntradas(){
        System.out.println("Entradas compradas: ");
        int i = 1;
        for (Entrada e : this.entradas){
            System.out.println("\tCodigo entrada "+i+": "+e.getNumero()+"\tZona: "+zona);
            i++;
        }
    }
    
    public int getCod() {
        return cod;
    }
    
    public int getZona() {
        return zona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
