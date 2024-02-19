package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Zone;

public class ZoneRepository {
    public void insert(Zone zone){
        try {
    
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir" 
                    , "root", "");
            Statement statement = conn.createStatement();
            /*
             * String.format , on remplace les variables de la requete par des code format
             * %d => variable de Type int 
             * %s => variable de Type string
             * %f => variable de Type float
             */
             String sql=String.format("INSERT INTO `zone` (`id`, `nomZone`) "
                      + " VALUES ('%d', '%s')",
                      zone.getId(),zone.getNomZone());
             int nbreLigne=statement.executeUpdate(sql);
             statement.close();
             conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        }
       catch (SQLException e) {
          System.out.println("Erreur de Connexion a la BD");
      }
      }

      public List<Zone> selectAll(){
         List<Zone> zones=new ArrayList<>();
        try {
    
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir" 
                   , "root", "");
           Statement statement = conn.createStatement();
           String sql="Select * from Zone";
           ResultSet rs=statement.executeQuery(sql);
            while (rs.next()) {
               //Une ligne ==> rs de la requete
                Zone Zone=new Zone();
                Zone.setId(rs.getInt("id"));
                Zone.setNomZone(rs.getString("nomZone"));
                zones.add(Zone);
            }
            statement.close();
            rs.close();
            conn.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Erreur de chargement de Driver");
       }
       catch (SQLException e) {
         System.out.println("Erreur de Connexion a la BD");
       }
       return zones;
      }
      public Zone selectZoneById(int id){
        Zone zone=null;
        try {
    
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir" 
                   , "root", "");
           Statement statement = conn.createStatement();
           String sql=String.format("Select * from Zone where id like '%d' ",id);
           ResultSet rs=statement.executeQuery(sql);
            if (rs.next()) {
               //Une ligne ==> rs de la requete
                zone=new Zone();
                zone.setId(rs.getInt("id"));
                zone.setNomZone(rs.getString("nomZone"));
            }
            statement.close();
            rs.close();
            conn.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Erreur de chargement de Driver");
       }
       catch (SQLException e) {
         System.out.println("Erreur de Connexion a la BD");
       }
           return zone;
      }
}
