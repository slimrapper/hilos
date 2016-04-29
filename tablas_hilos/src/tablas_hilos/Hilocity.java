/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas_hilos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author slim
 */

public class Hilocity extends Thread{
    int cont=0;
PreparedStatement pst=null;
ResultSet rs=null;
DefaultTableModel dtm;
Connection con ;
Conexion conc;
String sql ;
int tiempo=0;

public Hilocity (JTable tcity,int tiempo){
        dtm = (DefaultTableModel) tcity.getModel();
        conc =new Conexion();
   con = conc.conexion();
           sql ="select * from city";
           this.tiempo = tiempo;
}
    @Override
    public void run() {
        try {
                pst=con.prepareStatement(sql);

                rs=pst.executeQuery();
               while(rs.next() ){
                   dtm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
               cont++;
               if(cont==10){
           break;
           }
                   this.sleep(tiempo);
           }
           this.stop();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
}
