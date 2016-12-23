/*
 * Prueba.java
 *
 * Created on 3 de marzo de 2005, 08:04 PM
 */

package Utils;

/**
 *
 * @author Proyecto Tariy
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.util.HashMap;

public class BaseDatos {
    private Connection db;
    private Statement  stm;	
    private String nombre;
    private int indice = 0;

    public BaseDatos(){
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("1. " + e);
            indice = 1;
        }
    }

    public void iniciarBD(String BD, String usuario, String password){
        try{
            nombre = BD;
            db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + BD + "?user=" + usuario + "&password=" + password);
            stm = db.createStatement();
            indice = 0;
        }catch(SQLException e1){
            System.out.println("2. " + e1);
            indice = 2;
        }
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Vector getTablas(){
        ResultSet rs;
        Vector nombres = new Vector();
        try{
            DatabaseMetaData dbmd = db.getMetaData();

            rs = dbmd.getTables("%", "%", "%", null);

            while(rs.next()){
                //System.out.println(rs.getString(3));
                nombres.addElement(rs.getString(3));
            }
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            return (Vector)nombres.clone();
        }
    }
    public void insertarBD(String tabla, int tid, int item){
        try{
            stm.executeUpdate("insert into " + tabla + " values(" + tid + "," + item + ")");
        }catch(SQLException e1){
            System.out.println("2. " + e1);
        }
    }
    
    public int getIndice(){
        return indice;
    }
    
    public void cerrarBD(){
        try{
            if(db != null){
                db.close();
                stm.close();
            }
        }catch(SQLException e){
            System.out.print(e.getSQLState());
        }
    }
    
    public Vector getDatos(String tabla){
        Vector v = new Vector(2);
        Vector v1; 
        Vector v2 = new Vector(100); 
        try{
            ResultSet rs = stm.executeQuery("SELECT * FROM " + tabla);
            ResultSetMetaData rsm = rs.getMetaData();
            v1 = new Vector(rsm.getColumnCount());
            while(rs.next()){
                for(int i = 1; i <= rsm.getColumnCount(); i++){
                    v1.addElement(rs.getObject(i));
                }
                v2.addElement(v1.clone());
                v1.clear();
            }
            for(int i = 1; i <= rsm.getColumnCount(); i++){
                v1.addElement(rsm.getColumnName(i));
            }
            v.addElement(v1);
            v.addElement(v2);
            rs.close();
            //System.out.println(v2.toString());
        }catch(SQLException e){
            System.out.print(e.getErrorCode());
        }
         
        return (Vector)v.clone();
    } 
    
}