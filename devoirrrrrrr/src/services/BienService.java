package services;

import java.util.List;

import entities.Bien;
import repositories.BienRepository;

public class BienService {
    BienRepository bienRepository=new BienRepository();
   
   public  List<Bien>listerBiens(){
    
        return bienRepository.selectAll();
    }
    public void ajouterBien(Bien bien){
        bienRepository.insert(bien);
    }


}
