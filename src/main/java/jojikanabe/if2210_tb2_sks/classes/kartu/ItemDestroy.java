package jojikanabe.if2210_tb2_sks.classes.kartu;

import jojikanabe.if2210_tb2_sks.classes.GameState;

public class ItemDestroy extends Item {
    public ItemDestroy(String image) {
        super("Destroy", image);
    }

    @Override
    public void efek(int row, int col, int nomorPemain) throws Exception {
        GameState gameState = GameState.getInstance();
        nomorPemain -= 1; // Adjust for zero-based indexing
        Kartu kartu = gameState.getPemain().get(nomorPemain).getLadang().getKartu(row, col);
        if (kartu != null) { // Check if the kartu is present
            gameState.getPemain().get(nomorPemain).getLadang().removeKartu(row, col);
        } else {
            throw new Exception("Tidak ada kartu di posisi tersebut!");
        }
    }
}
