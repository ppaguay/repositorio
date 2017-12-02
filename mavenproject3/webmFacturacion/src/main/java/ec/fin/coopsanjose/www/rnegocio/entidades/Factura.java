/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.entidades;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int numero;
    private LocalDate fecha;
    private double total;
    private Cliente cliente;

    public Factura() {
    }

    public Factura(int numero, LocalDate fecha, double total, Cliente cliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
    }

   

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
            
}
