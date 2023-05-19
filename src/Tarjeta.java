
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luis
 */
public class Tarjeta {
    private String nombre;
    private String num;
    private Date vencimiento;
    private String cvv;

    public Tarjeta(String nombre, String num, Date vencimiento, String cvv) {
        this.nombre = nombre;
        this.num = num;
        this.vencimiento = vencimiento;
        this.cvv = cvv;
    }
    
    public Tarjeta(String nombre, String num, int year, int mes, String cvv) {
        this.nombre = nombre;
        this.num = num;
        this.vencimiento = new Date(year-1900, mes-1, 1);
        this.cvv = cvv;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
}
