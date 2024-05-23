package jojikanabe.if2210_tb2_sks.classes.kartu;

public class HewanHerbivora extends Hewan {
    public HewanHerbivora(String nama, Integer beratBadanSiapPanen, Integer beratBadan) {
        super(nama, beratBadanSiapPanen, beratBadan);
    }

    @Override
    void makan(Integer beratBadan) {
        this.beratBadan = beratBadan;
    }
}
