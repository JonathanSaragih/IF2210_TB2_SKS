package jojikanabe.if2210_tb2_sks.classes.kartu;

public class Tanaman extends Kartu implements Panen {
    private final Integer umurSiapPanen;
    private Integer umur;

    public Tanaman(String nama, Integer umurSiapPanen, Integer umur) {
        super(nama);
        this.umurSiapPanen = umurSiapPanen;
        this.umur = umur;
    }

    @Override
    public void panen() {
        System.out.println("Tanaman panen");
    }
}
