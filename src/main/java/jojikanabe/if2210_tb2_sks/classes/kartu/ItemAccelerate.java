package jojikanabe.if2210_tb2_sks.classes.kartu;

import jojikanabe.if2210_tb2_sks.classes.GameState;

public class ItemAccelerate extends Item {
    public ItemAccelerate(String image) {
        super("Accelerate", image);
    }

    public ItemAccelerate(ItemAccelerate original) {
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
                tanaman.setUmur(tanaman.getUmur() + 2);
            } else if (kartu instanceof Hewan) {
                Hewan hewan = (Hewan) kartu;
                hewan.setBeratBadan(hewan.getBeratBadan() + 2);
            } else {
                throw new Exception("Kartu yang diberikan bukan hewan atau tanaman");
            }
        } else {
            throw new Exception("Tidak ada kartu di posisi tersebut!");
        }
    }
}
