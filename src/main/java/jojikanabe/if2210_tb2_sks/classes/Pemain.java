package jojikanabe.if2210_tb2_sks.classes;

import jojikanabe.if2210_tb2_sks.classes.kartu.Kartu;

import java.util.ArrayList;
import java.util.List;

public class Pemain {
    private final String nama;
    private Integer gulden;
    private Deck deck;
    private List<Kartu> deckAktif;

    public Pemain(String nama, Integer gulden, int jumlahDeck, int jumlahDeckAktif) {
        this.nama = nama;
        this.gulden = gulden;
        this.deck = new Deck(jumlahDeck);
        this.deckAktif = new ArrayList<>(jumlahDeckAktif);
    }

    public void addKartu(Kartu kartu, int index) {
        this.deckAktif.add(index, kartu);
    }

    public String getNama() {
        return nama;
    }

    public Integer getGulden() {
        return gulden;
    }

    public void setGulden(Integer gulden) {
        this.gulden = gulden;
    }

}
