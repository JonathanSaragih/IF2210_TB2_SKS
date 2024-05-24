package jojikanabe.if2210_tb2_sks.classes.kartu;

import jojikanabe.if2210_tb2_sks.classes.GameState;

public interface Panen {
    void panen(int row, int col, int nomorPemain) throws Exception;

    default void updateLadang(int nomorPemain, int row, int col, Kartu kartu) throws Exception {
        GameState gameState = GameState.getInstance();
        try {
            gameState.getPemain().get(nomorPemain).getLadang().removeKartu(row, col);
            gameState.getPemain().get(nomorPemain).getLadang().setKartu(row, col, kartu);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
