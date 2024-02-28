package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Client;

public class ClientRepository extends Database {
    private final  String SQL_SELECT_ALL="select * from Client" ;
    private final  String SQL_INSERT="INSERT INTO Client (nomComplet, telephone, email) VALUES (?,?,?)";
    private final  String SQL_SELECT_BY_TEL="Select * from Client where telephone like ? " ;
        

        public  List<Client> selectAll(){
            List<Client> clients=new ArrayList<>();
            try {
                openConnexion();
                initPreparedStatement(SQL_SELECT_ALL);
                ResultSet rs= executeSelect();
                    while (rs.next()) {
                    //Une ligne du ResultSet ==> Une Agence
                        Client cl=new Client();
                        cl.setId(rs.getInt("id_client"));
                        cl.setNomComplet(rs.getString("nomComplet"));
                        cl.setTelephone(rs.getString("telephone"));
                        cl.setEmail(rs.getString("email"));
                        clients.add(cl);
                    }
                    rs.close();
                closeConnexion();
                }
            catch (SQLException e) {
                System.out.println("Erreur de Connexion a la BD");
            }
            return clients;
            }
            
            public Client selectClientByTel(String tel){
                Client client=null;
                try {
                    openConnexion();
                    initPreparedStatement(SQL_SELECT_BY_TEL);
                    statement.setString(1, tel);
                    ResultSet rs= executeSelect();
                    if (rs.next()) {
                       //Une ligne ==> rs de la requete
                        client=new Client();
                        client.setId(rs.getInt("id_client"));
                        client.setNomComplet(rs.getString("nomComplet"));
                        client.setTelephone(rs.getString("telephone"));
                        client.setEmail(rs.getString("email"));
                    }
                    statement.close();
                    rs.close();
                    conn.close();
               } 
               catch (SQLException e) {
                 System.out.println("Erreur de Connexion a la BD");
               }
                   return client;
              }
              public  void insert(Client client){
                openConnexion();
                try {
                    initPreparedStatement(SQL_INSERT);
                    statement.setString(1, client.getNomComplet());
                    statement.setString(2, client.getTelephone());
                    statement.setString(3, client.getEmail());
                    int nbreLigne=executeUpdate();
                   closeConnexion();
                 } catch (SQLException e) {
                  e.printStackTrace();
                 }
                 }
}


