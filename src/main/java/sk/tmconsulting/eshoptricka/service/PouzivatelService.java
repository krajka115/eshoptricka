package sk.tmconsulting.eshoptricka.service;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tmconsulting.eshoptricka.model.Objednavka;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;
import sk.tmconsulting.eshoptricka.repository.PouzivatelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PouzivatelService {
    @Autowired
    PouzivatelRepository pouzivatelRepositoryObjekt;

    public void ulozPouzivatela(Pouzivatel pouzivatelObjekt) {
        pouzivatelRepositoryObjekt.save(pouzivatelObjekt);
    }


    public Pouzivatel najdiPodlaUsername(String username) {
        return pouzivatelRepositoryObjekt.findByUsername(username);
    }
    public List<Pouzivatel> ziskajVsetkychPouzivatelov(){
        return pouzivatelRepositoryObjekt.findAll();
    }

}
