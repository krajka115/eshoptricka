package sk.tmconsulting.eshoptricka.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity // zadáme @Entity a urobí automaticky import
public class Objednavka { //dalo Objednávka červenou, a vygenerovalo cestu cez ID, aby chyba zmizla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String popis;
    private Farba farba;
    private String znacka;
    private Velkost velkost;
    @Column(nullable = false)
    private int pocet;
    private Date datumObjednavky;
    private String menoZakaznika;

    /*    farby, značku, veľkosť, (príp. ďalšie atribúty) a objednané množstvo (počet kusov), dátum objednávky + meno zákazníka.*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

       public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

      public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public Date getDatumObjednavky() {
        return datumObjednavky;
    }

    public void setDatumObjednavky(Date datumObjednavky) {
        this.datumObjednavky = datumObjednavky;
    }

    public String getMenoZakaznika() {
        return menoZakaznika;
    }

    public void setMenoZakaznika(String menoZakaznika) {
        this.menoZakaznika = menoZakaznika;
    }

    public Farba getFarba() {
        return farba;
    }

    public void setFarba(Farba farba) {
        this.farba = farba;
    }

    public Velkost getVelkost() {
        return velkost;
    }

    public void setVelkost(Velkost velkost) {
        this.velkost = velkost;
    }
}
