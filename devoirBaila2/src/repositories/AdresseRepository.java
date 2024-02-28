package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Adresse;
import entities.Client;

public class AdresseRepository extends Database {
    private final  String SQL_SELECT_ALL="select * from Adresse a, Client c where a.id_client=c.id_client" ;
    private final  String SQL_INSERT="INSERT INTO Adresse (ville, quartier, numVilla, id_client) VALUES (?,?,?,?)";
    //select
    public  List<Adresse> selectAll(){
         List<Adresse> adresses=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL);
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
               Client client=new Client();
               client.setId(rs.getInt("id_client"));
               client.setNomComplet(rs.getString("nomComplet"));
               client.setTelephone(rs.getString("telephone"));
               client.setEmail(rs.getString("email"));

                 Adresse ad=new Adresse();
                 ad.setId(rs.getInt("id_ad"));
                 ad.setVille(rs.getString("ville"));
                 ad.setQuartier(rs.getString("quartier"));
                 ad.setNumVilla(rs.getString("numVilla"));
                 ad.setClient(client);
                 adresses.add(ad);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  adresses;
    }
    public  void insert(Adresse adresse){
        openConnexion();
        try {
            initPreparedStatement(SQL_INSERT);
            statement.setString(1, adresse.getVille());
            statement.setString(2, adresse.getQuartier());
            statement.setString(3, adresse.getNumVilla());
            statement.setInt(4, adresse.getClient().getId());
            int nbreLigne=executeUpdate();
           closeConnexion();
         } catch (SQLException e) {
          e.printStackTrace();
         }
         }
    
}
