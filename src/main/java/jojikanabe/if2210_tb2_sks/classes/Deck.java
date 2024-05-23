package jojikanabe.if2210_tb2_sks.classes;

import jojikanabe.if2210_tb2_sks.classes.kartu.HewanOmnivora;
import jojikanabe.if2210_tb2_sks.classes.kartu.Kartu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Kartu> kartu;

    public Deck(int jumlahDeck) {
        this.kartu = new ArrayList<>(jumlahDeck);

        List<Kartu> dataKartu = GameState.getInstance().getDataKartu();

        Random random = new Random();

        while (kartu.size() < jumlahDeck) {
            int index = random.nextInt(dataKartu.size());
            Kartu selectedKartu = dataKartu.get(index);

            if (!(selectedKartu instanceof HewanOmnivora) || !selectedKartu.getNama().equals("Beruang")) {
                kartu.add(selectedKartu);
            }
        }
    }

    public void addKartu(Kartu kartu) {
        this.kartu.add(kartu);
    }

    public Kartu getKartuRandom() {
        Random random = new Random();
        int index = random.nextInt(kartu.size());
        Kartu temp = kartu.get(index);
        kartu.remove(index);
        return temp;
    }
}
