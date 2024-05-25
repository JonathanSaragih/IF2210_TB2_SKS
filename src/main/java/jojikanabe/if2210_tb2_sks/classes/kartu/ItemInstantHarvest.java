package jojikanabe.if2210_tb2_sks.classes.kartu;

import jojikanabe.if2210_tb2_sks.classes.GameState;

public class ItemInstantHarvest extends Item {
    public ItemInstantHarvest(String image) {
        super("Instant Harvest", image);
    }

    public ItemInstantHarvest(ItemInstantHarvest original) {
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
                tanaman.setUmur(tanaman.getUmurSiapPanen());
                tanaman.panen(row, col, nomorPemain);
            } else if (kartu instanceof Hewan) {
                Hewan hewan = (Hewan) kartu;
                hewan.setBeratBadan(hewan.getBeratBadanSiapPanen());
                hewan.panen(row, col, nomorPemain);
            } else {
                throw new Exception("Kartu yang diberikan bukan hewan atau tanaman");
            }
        } else {
            throw new Exception("Tidak ada kartu di posisi tersebut!");
        }
    }
}
