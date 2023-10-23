package sk.tmconsulting.eshoptricka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.tmconsulting.eshoptricka.model.Objednavka;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;
import sk.tmconsulting.eshoptricka.service.ObjednavkaService;
import sk.tmconsulting.eshoptricka.service.PouzivatelService;

@Controller // urobí automatický import hore vyššie
public class EshopTrickaController {
    //dependency injection
    @Autowired// spraví na pozadí objekt
    ObjednavkaService objednavkaServiceObjekt;
    @Autowired
    PouzivatelService pouzivatelServiceObjekt;

    @GetMapping ("/")  //koreňová adresa ("/")
    public  String uvodnaStranka (){
        return "index"; // potom ideme do templates a vytvoríme html
    }

    /*@GetMapping("/pridaj-objednavku") //URI adresa
    public String pridajNovuObjednavku(Model model){ // názov metody môže byť akýkoľvek aj zhodný s URI adresou
        Objednavka objednavkaObjekt = new Objednavka(); // najskôr vytvarame prazdu objednavku
        model.addAttribute("objednavkaObjektThymeleaf", objednavkaObjekt); // nazov atributu môžeme nazvať akokolvek , potom s ním treba pracovať v thymeleaf stranke
        return "pridaj-novu-objednavku"; // odkaz na stranku html v templates s pridaním novej objednávky
    }*/
    @GetMapping("/pridaj-objednavku") // URI adresa
    public String pridajNovuObjednavku(Model model) { // Nazov metody moze byt akykolvek, ale moze byt aj zhodny s URI adresou
        Objednavka objednavkaObjekt = new Objednavka(); // Najprv vytvarame prazdnu objednavku
        model.addAttribute("objednavkaObjektThymeleaf", objednavkaObjekt); // Nazov atributu mozeme nazvat akokolvek, ale potom by sme mali s nim pracovat aj v HTML, resp. Thymeleaf stranke

        // Ziskame aktualne prihlaseneho pouzivatela
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernamePouzivatela = auth.getName(); // Ziskame username prihlaseneho pouzivatela

        Pouzivatel prihlasenyPouzivatel = pouzivatelServiceObjekt.najdiPodlaUsername(usernamePouzivatela);
        objednavkaObjekt.setPouzivatel(prihlasenyPouzivatel);
        model.addAttribute("pouzivatelObjektThymeleaf", prihlasenyPouzivatel);
        return "pridaj-novu-objednavku"; // Odkaz na stranku s pridanim novej objednavky
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

    @GetMapping("/login") // uri adresa, ktoru vidim v internetovom prehliadaci, tu nam vygeneroval webSecurityCongfig v metode configure a my musíme na to nejakým spôsobom reagovať...
    public String loginFormular (){
        return "login"; // ...čiže zobrazime login.html, ktorý sa nachadza v templates
    }

  /*  @GetMapping ("/logout")
    public String logoutFormular (){
        return "redirect:/"; // presmerovanie na hlavnu stranku
    }
*/ // nefunguje

    @GetMapping("/registruj-pouzivatela")
    public String registrujPouzivatela(Model model) {
        Pouzivatel pouzivatelObjekt = new Pouzivatel();
        model.addAttribute("pouzivatelObjektThymeleaf", pouzivatelObjekt);
        return "registruj-noveho-pouzivatela";
    }

    @PostMapping("/uloz-pouzivatela") // PostMapping očakava, resp.spracuje obsah formularových prvkov
    public String ulozPouzivatela(@ModelAttribute("pouzivatelObjektThymeleaf") Pouzivatel pouzivatelObjekt) {
        // objednavkaObjekt musi byt ulozena
        pouzivatelServiceObjekt.ulozPouzivatela(pouzivatelObjekt);
        return "uloz-noveho-pouzivatela";
    }
    @GetMapping("/zobraz-vsetkych-pouzivatelov")
    public String zobrazPouzivatelov(Model model){
        model.addAttribute("vsetciPouzivateliaThymeleaf", pouzivatelServiceObjekt.ziskajVsetkychPouzivatelov());
        return "zobraz-vsetkych-pouzivatelov";
    }


}
