package jojikanabe.if2210_tb2_sks.classes.kartu;

import jojikanabe.if2210_tb2_sks.classes.GameState;

public class Tanaman extends Kartu implements Panen {
    private final Integer umurSiapPanen;
    private Integer umur;

    public Tanaman(String nama, String image, Integer umurSiapPanen, Integer umur) {
        super(nama, image);
        this.umurSiapPanen = umurSiapPanen;
        this.umur = umur;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Integer getUmurSiapPanen() {
        return umurSiapPanen;
    }

    @Override
    public void panen(int row, int col, int nomorPemain) throws Exception {
        GameState gameState = GameState.getInstance();
        nomorPemain -= 1;
        if (umur >= umurSiapPanen) {
            String produkNama = getProdukNama();
            if (produkNama != null) {
                Kartu kartu = gameState.getKartu(produkNama);
                updateLadang(nomorPemain, row, col, kartu);
            } else {
                throw new Exception("Tanaman tidak ditemukan");
            }
        } else {
            throw new Exception("Tanaman belum siap panen");
        }
    }

    @Override
    public boolean isSiapPanen() {
        return umur >= umurSiapPanen;
    }

    private String getProdukNama() {
        switch (nama) {
            case "Biji Labu":
                return "Labu";
            case "Biji Jagung":
                return "Jagung";
            case "Biji Stroberi":
                return "Stroberi";
            default:
                return null;
        }
    }
}
