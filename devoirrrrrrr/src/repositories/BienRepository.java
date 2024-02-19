package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Bien;
import entities.Zone;


public class BienRepository {
    public void insert(Bien bien){
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
             String sql=String.format("INSERT INTO `Bien` (`idBien`, `reference`,`description`,`prix`,`id`) "
                      + " VALUES ('%d', '%s','%s',%f,'%d') ", bien.getId(), bien.getReference(), bien.getDescription(), bien.getPrix(), bien.getZone().getId());
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

      public List<Bien> selectAll(){
         List<Bien> biens=new ArrayList<>();
        try {
    
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir" 
                   , "root", "");
           Statement statement = conn.createStatement();
           String sql="SELECT * FROM `Bien` b, Zone z WHERE b.idBien=z.id;";
           ResultSet rs=statement.executeQuery(sql);
            while (rs.next()) {
               //Une ligne ==> rs de la requete
                Bien bien=new Bien();
                bien.setId(rs.getInt("id"));
                Zone zn=new Zone();
                zn.setNomZone(rs.getString("nomZone"));
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
       return biens;
      }
      
}
