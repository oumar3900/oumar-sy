import java.util.List;
import java.util.Scanner;

import entities.Adresse;
import entities.Client;
import services.AdresseService;
import services.ClientService;

public class App {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        //Dependances
        AdresseService adresseService=new AdresseService();
        ClientService clientService=new ClientService();
       
        do {
            System.out.println("1-Creer un client");
            System.out.println("2-Lister les clients"); 
            System.out.println("3-Ajouter les adresses et lui associer un client"); 
            System.out.println("4-Lister les adresses en affichants le nom du client");
            System.out.println("5-Quitter"); 
             choix=sc.nextInt();
             sc.nextLine();
            switch (choix) {
                case 1:
                     System.out.println("Entrer le nom du client");
                     String nom=sc.nextLine(); 
                     System.out.println("Entrer un Telephone");
                     String tel=sc.nextLine();  
                     System.out.println("Entrer l'email");
                     String email=sc.nextLine();   
                     Client client=new Client();
                      client.setNomComplet(nom);
                      client.setTelephone(tel);
                      client.setEmail(email);
                    clientService.ajouterClient(client);
                    break;
                
                case 2:
                    List<Client> clients=  clientService.listerClient();
                     for (Client cl: clients) {
                          System.out.println("Numero "+ cl.getNomComplet());
                          System.out.println("Telephone "+ cl.getTelephone());
                          System.out.println("Email "+ cl.getEmail());
                          System.out.println("=================================");
                     }
                    break;

                    case 3:
                    System.out.println("Entrer le nom de la ville");
                    String ville=sc.nextLine(); 
                     System.out.println("Entrer le nom d'un quartier");
                     String quartier=sc.nextLine();
                     System.out.println("Entrer le numero de villa");
                     String numVilla=sc.nextLine();
                      System.out.println("Entrer le Telephone du client");
                      tel=sc.nextLine(); 
                      //Rechercher un client a travers son tel(Use Case)
                        client = clientService.rechercherClientParTel(tel);
                         if (client==null) {
                            System.out.println("Ce client n'existe pas");
                              System.out.println("Entrer un Nom");
                               nom=sc.nextLine(); 
                              System.out.println("Entrer un numero");
                              tel=sc.nextLine();   
                              System.out.println("Entrer un email");
                              email=sc.nextLine();   
                              client=new Client();
                              client.setNomComplet(nom);
                              client.setTelephone(tel);
                              client.setEmail(email);
                              clientService.ajouterClient(client);
                         }
                         Adresse adresse=new Adresse();
                         adresse.setVille(ville);
                         adresse.setQuartier(quartier);
                         adresse.setNumVilla(numVilla);
                         adresse.setClient(client);
                         adresseService.ajouterAdresse(adresse);
                         
                   break;
                   case 4:
                   List<Adresse>  adresses= adresseService.listerAdresses();
                   for (Adresse ad: adresses) {
                     System.out.println("Ville "+ ad.getVille());
                     System.out.println("Quartier "+ ad.getQuartier());
                     System.out.println("Telephone "+ad.getNumVilla() );
                     System.out.println("Client "+ad.getClient().getNomComplet() );
                     System.out.println("=================================");
               }
                    break;
                    
                default:
                    break;
            }

        } while (choix!=5);
    }
}
