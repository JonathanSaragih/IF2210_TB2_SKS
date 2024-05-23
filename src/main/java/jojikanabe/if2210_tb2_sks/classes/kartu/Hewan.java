package jojikanabe.if2210_tb2_sks.classes.kartu;

public abstract class Hewan extends Kartu implements Panen {
    protected final Integer beratBadanSiapPanen;
    protected Integer beratBadan;

    public Hewan(String nama, Integer beratBadanSiapPanen, Integer beratBadan) {
        super(nama);
        this.beratBadanSiapPanen = beratBadanSiapPanen;
        this.beratBadan = beratBadan;
    }

    Integer getBeratBadan() {
        return beratBadan;
    }

    abstract void makan(Integer berat_badan);

    @Override
    public void panen() {
    }
}
