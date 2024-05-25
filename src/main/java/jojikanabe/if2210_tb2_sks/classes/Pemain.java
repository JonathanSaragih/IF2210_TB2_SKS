package jojikanabe.if2210_tb2_sks.classes;

import jojikanabe.if2210_tb2_sks.classes.kartu.Kartu;

import java.util.ArrayList;
import java.util.List;

public class Pemain {
    private final String nama;
    private Integer gulden;
    private Deck deck;
    private Ladang ladang;
    private List<Kartu> deckAktif;

    public Pemain(String nama, Integer gulden, int jumlahDeck, int jumlahDeckAktif) {
        this.nama = nama;
        this.gulden = gulden;
        this.deck = new Deck(jumlahDeck);
        this.deckAktif = new ArrayList<>(jumlahDeckAktif);
        this.ladang = new Ladang();
    }

    public void addKartu(Kartu kartu, int index) {
        this.deckAktif.add(index, kartu);
    }

    public void removeKartu(Kartu kartu) {
        this.deckAktif.remove(kartu);
    }

    public String getNama() {
        return nama;
    }

    public Deck getDeck() {
        return deck;
    }

    public Ladang getLadang() {
        return ladang;
    }

    public void setLadang(Ladang ladang) {
        this.ladang = ladang;
    }

    public Integer getGulden() {
        return gulden;
    }

    public void setGulden(Integer gulden) {
        this.gulden = gulden;
    }

    public void ShuffleDeck() {
        int i = 0;
        if (deckAktif.size() <= 2) {
            i = 4;
        } else if (deckAktif.size() == 3) {
            i = 3;
        } else if (deckAktif.size() == 4) {
            i = 2;
        } else if (deckAktif.size() == 5) {
            i = 1;
        }

        for (int j = 0; j < i; j++) {
            Kartu temp = deck.getKartuRandom();
            deckAktif.add(temp);
        }
    }

    public List<Kartu> getDeckAktif() {
        return deckAktif;
    }

    public void addGulden(Integer gulden) {
        this.gulden += gulden;
    }
}
