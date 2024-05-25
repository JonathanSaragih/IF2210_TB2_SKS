package jojikanabe.if2210_tb2_sks.classes.kartu;

import jojikanabe.if2210_tb2_sks.classes.GameState;

public class ItemDelay extends Item {
    public ItemDelay(String image) {
        super("Delay", image);
    }

    public ItemDelay(ItemDelay original) {
        super(original);
    }

    @Override
    public void efek(int row, int col, int nomorPemain) throws Exception {
        GameState gameState = GameState.getInstance();
        nomorPemain -= 1;
        Kartu kartu = gameState.getPemain().get(nomorPemain).getLadang().getKartu(row, col);
        if (kartu != null) {
            if (kartu instanceof Tanaman) {
                Tanaman tanaman = (Tanaman) kartu;
                if (tanaman.getUmur() < 2) {
                    tanaman.setUmur(0);
                } else {
                    tanaman.setUmur(tanaman.getUmur() - 2);
                }
            } else if (kartu instanceof Hewan) {
                Hewan hewan = (Hewan) kartu;
                if (hewan.getBeratBadan() < 5) {
                    hewan.setBeratBadan(0);
                } else {
                    hewan.setBeratBadan(hewan.getBeratBadan() - 5);
                }
            } else {
                throw new Exception("Kartu yang diberikan bukan hewan atau tanaman");
            }
        } else {
            throw new Exception("Tidak ada kartu di posisi tersebut!");
        }
    }
}
