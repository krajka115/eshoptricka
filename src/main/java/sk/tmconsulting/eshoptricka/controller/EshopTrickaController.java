package sk.tmconsulting.eshoptricka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.tmconsulting.eshoptricka.model.Objednavka;
import sk.tmconsulting.eshoptricka.service.ObjednavkaService;

@Controller // urobí automatický import hore vyššie
public class EshopTrickaController {
    @Autowired// spraví na pozadí objekt
    ObjednavkaService objednavkaServiceObjekt;

    @GetMapping ("/")  //koreňová adresa ("/")
    public  String uvodnaStranka (){
        return "index"; // potom ideme do templates a vytvoríme html
    }

    @GetMapping("/pridaj-objednavku") //URI adresa
    public String pridajNovuObjednavku(Model model){ // názov metody môže byť akýkoľvek aj zhodný s URI adresou
        Objednavka objednavkaObjekt = new Objednavka(); // najskôr vytvarame prazdu objednavku
        model.addAttribute("objednavkaObjektThymeleaf", objednavkaObjekt); // nazov atributu môžeme nazvať akokolvek , potom s ním treba pracovať v thymeleaf stranke
        return "pridaj-novu-objednavku"; // odkaz na stranku html v templates s pridaním novej objednávky
    }

    @PostMapping("uloz-objednavku")//lomka môže aj nemusí byť v názve ako v tých predošlých
    public String ulozObjednavku(@ModelAttribute("objednavkaObjektThymeleaf")Objednavka objednavkaObjekt){
        //objednávkaObjekt musí byť uložena, vytvoríme si service a objednavkaService
        objednavkaServiceObjekt.ulozObjednavku(objednavkaObjekt);
        return "uloz-novu-objednavku";
    }

    @GetMapping("zobraz-vsetky-objednavky")
    public String zobrazObjednavky(Model model){
        model.addAttribute("vsetkyObjednavkyThymeleaf", objednavkaServiceObjekt.ziskajVsetkyObjednavky());
        return "zobraz-vsetky-objednavky";
    }

    @GetMapping("/uprav-objednavku/{id}")
    public String upravObjednavku(@PathVariable(value="id")long id, Model model) {
        Objednavka objednavkaObjektPodlaId = objednavkaServiceObjekt.ziskajObjednavkuPodlaId(id);
        model.addAttribute("objednavkaObjektPodlaIdThymeleaf", objednavkaObjektPodlaId);
        return "uprav-objednavku";
    }
    @PostMapping("uloz-upravenu-objednavku")//lomka môže aj nemusí byť v názve ako v tých predošlých
    public String ulozUpravenuObjednavku(@ModelAttribute("objednavkaObjektThymeleaf")Objednavka objednavkaObjekt){
        //objednávkaObjekt musí byť uložena, vytvoríme si service a objednavkaService
        objednavkaServiceObjekt.ulozObjednavku(objednavkaObjekt);
        return "uloz-upravenu-objednavku";
    }

    //controller musí reagovať na id plus adresu html
    @GetMapping("odstran-objednavku/{id}")
    public String odstranObjednavku(@PathVariable(value = "id")long id) {
        objednavkaServiceObjekt.odstranObjednavku(id);
        return "objednavka-odstranena";
    }


}
