package services;

import java.util.List;

import entities.Zone;
import repositories.ZoneRepository;

public class ZoneService {
    ZoneRepository zoneRepository=new ZoneRepository();
   
   public  List<Zone>listerZone(){
    
        return zoneRepository.selectAll();
    }
    public void ajouterZone(Zone zone){
        zoneRepository.insert(zone);
    }

    public  Zone rechercherZoneParId(int id){
        return zoneRepository.selectZoneById(id);
    }

}
