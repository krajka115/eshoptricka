package sk.tmconsulting.eshoptricka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tmconsulting.eshoptricka.model.Objednavka;
import sk.tmconsulting.eshoptricka.repository.ObjednavkaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjednavkaService {
    @Autowired //vytvorí tato anotácia objekt ObjednavkaRepository objednavkaRepositoryObjekt = new ObjednavkaRepository
    private ObjednavkaRepository objednavkaRepositoryObjekt;

    public void ulozObjednavku(Objednavka objednavkaObjekt) {
        objednavkaRepositoryObjekt.save(objednavkaObjekt);
    }
    public List<Objednavka> ziskajVsetkyObjednavky(){
        return objednavkaRepositoryObjekt.findAll();
    }

    public void aktualizujObjednavku(Objednavka objednavkaObjekt){
        objednavkaRepositoryObjekt.save(objednavkaObjekt); // môžeme volať aj ulozObjednavku
    }
    public Objednavka ziskajObjednavkuPodlaId(Long id){
        return objednavkaRepositoryObjekt.findById(id).get();
    }

    public void odstranObjednavku(long id){
        objednavkaRepositoryObjekt.deleteById(id);
    }

}
