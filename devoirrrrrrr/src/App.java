import java.util.List;
import java.util.Scanner;


import entities.Bien;
import entities.Zone;
import services.BienService;
import services.ZoneService;

public class App {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        //Dependances
        ZoneService zoneService=new ZoneService();
        BienService bienService=new BienService();
       
        do {
            System.out.println("1-Créer une Zone");
            System.out.println("2-Lister Toutes les Zones"); 
            System.out.println("3-Creer un Bien en lui aaffectant une Zone"); 
            System.out.println("4-Lister les Biens");
            System.out.println("5-Quitter"); 
             choix=sc.nextInt();
             sc.nextLine();
            switch (choix) {
                case 1:
                     System.out.println("Entrer le nom de la Zone");
                     String nomZone=sc.nextLine(); 
                     Zone zn=new Zone();
                      zn.setNomZone(nomZone);
                    zoneService.ajouterZone(zn);
                    break;
                
                case 2:
                    List<Zone> zones=  zoneService.listerZone();
                     for (Zone zone: zones) {
                          System.out.println("id "+ zone.getId());
                          System.out.println("Nom "+ zone.getNomZone());
                          System.out.println("=================================");
                     }
                    break;

                case 3:
                System.out.println("Entrer la reference du Bien");
                String reference=sc.nextLine();
                System.out.println("Description du Bien");
                String description=sc.nextLine(); 
                System.out.println("Entrez le prix du Bien");
                int prix=sc.nextInt();
                Bien bien= new Bien();
                bien.setReference(reference);
                bien.setDescription(description);
                bien.setPrix(prix);
                 System.out.println("Entrer l'id de la Zone");
                 int id=sc.nextInt(); 
                 //Rechercher un client a travers son tel(Use Case)
                zn = zoneService.rechercherZoneParId(id);
                if (zn==null) {
                         System.out.println("Saisir le nom de la nouvelle Zone");
                        nomZone=sc.nextLine();    
                         zn=new Zone();
                         sc.nextLine();
                         zn.setNomZone(nomZone);
                         zoneService.ajouterZone(zn);
                    }
                    bien.setZone(zn);
                    bienService.ajouterBien(bien);

               break;

                    case 4:
                    List<Bien> biens= bienService.listerBiens();
                    for (Bien bns: biens) {
                         System.out.println("Reference  :"+ bns.getReference());
                         System.out.println("Description :"+ bns.getDescription());
                         System.out.println("prix :"+ bns.getPrix());
                         System.out.println("Zone :"+ bns.getZone().getNomZone());
                         System.out.println("====================================================================");
                    }
                     
                   break;
                default:
                    break;
            }

        } while (choix!=5);
    }
}
