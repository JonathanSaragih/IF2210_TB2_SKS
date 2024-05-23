package jojikanabe.if2210_tb2_sks.classes.kartu;

public class HewanKarnivora extends Hewan {
    public HewanKarnivora(String nama,String image, Integer beratBadanSiapPanen, Integer beratBadan) {
        super(nama,image, beratBadanSiapPanen, beratBadan);
    }

    @Override
    void makan(Integer beratBadan) {
        this.beratBadan = beratBadan;
    }
}
