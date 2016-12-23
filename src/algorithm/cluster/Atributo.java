/*
 * Atributo.java
 *
 * Created on 14 de mayo de 2009, 18:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster;

import java.io.Serializable;

/**
 *
 * @author vismine
 */
public class Atributo implements Serializable{
    private static final long serialVersionUID = -2517667587989843021L;
  
//    private static final long serialVersionUID = 1L; 
    
    private Object atributo;
    private boolean desabilitar;
    private String tipo;
    private boolean comBinario;
    private boolean estandarizar;
    private int posicion;
    private int col;
    private String clase;
    /** Creates a new instance of Atributo */
    public Atributo() {
        desabilitar=false;
        comBinario=false;
        estandarizar=false;
    }
    public void setAtributo(Object atributo){
        this.atributo=atributo;
    }
    public void setDesabilitar(boolean desabilitar){
        this.desabilitar=desabilitar;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setcomBinario(boolean combinario){
        this.comBinario = combinario;
    }
    public void setPosicion(int posicion){
        this.posicion = posicion;
    }
    public void setEstandarizar(boolean estandarizar){
        this.estandarizar = estandarizar;
    }
    public void setClase(String clase){
        this.clase = clase;
    }
    public void setCol(int col){
        this.col = col;
    }
    public boolean getDesabilitar(){
        return this.desabilitar;
    }
    public String  getTipo(){
        return this.tipo;                
    }
    
    public boolean getcomBinario(){
        return this.comBinario;
    }
    public int getPosicion() {
        return this.posicion;
        
    }
    public boolean getEstandarizar(){
        return this.estandarizar;
    }
    public Object getAtributo(){
        return this.atributo;
    }
    public String getClase(){
        return this.clase;
    }
    public int getCol(){
        return this.col;
    }
}
