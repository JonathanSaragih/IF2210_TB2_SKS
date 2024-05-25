package jojikanabe.if2210_tb2_sks.classes.kartu;

import jojikanabe.if2210_tb2_sks.classes.GameState;

public abstract class Hewan extends Kartu implements Panen {
    protected final Integer beratBadanSiapPanen;
    protected Integer beratBadan;

    public Hewan(String nama, String image, Integer beratBadanSiapPanen, Integer beratBadan) {
        super(nama, image);
        this.beratBadanSiapPanen = beratBadanSiapPanen;
        this.beratBadan = beratBadan;
    }

    public Hewan(Hewan original) {
        super(original);
        this.beratBadanSiapPanen = original.beratBadanSiapPanen;
        this.beratBadan = original.beratBadan;
    }

    public Integer getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(Integer beratBadan) {
        this.beratBadan = beratBadan;
    }

    public Integer getBeratBadanSiapPanen() {
        return beratBadanSiapPanen;
    }

    public abstract void makan(Kartu kartu) throws Exception;

    private String getProdukNama() {
        switch (nama) {
            case "Hiu Darat":
                return "Sirip Hiu";
            case "Kuda":
                return "Daging Kuda";
            case "Domba":
                return "Daging Domba";
            case "Sapi":
                return "Susu";
            case "Ayam":
                return "Telur";
            case "Beruang":
                return "Daging Beruang";
            default:
                return null;
        }
    }

    @Override
    public void panen(int row, int col, int nomorPemain) throws Exception {
        GameState gameState = GameState.getInstance();
        nomorPemain -= 1;
        if (beratBadan >= beratBadanSiapPanen) {
            String produkNama = getProdukNama();
            if (produkNama != null) {
                Kartu kartu = gameState.getKartu(produkNama);
                updateLadang(nomorPemain, row, col, kartu);
            } else {
                throw new Exception("Hewan tidak ditemukan");
            }
        } else {
            throw new Exception("Hewan belum siap panen");
        }
    }

    @Override
    public boolean isSiapPanen() {
        return beratBadan >= beratBadanSiapPanen;
    }
}
