package jojikanabe.if2210_tb2_sks.classes.kartu;

public abstract class Kartu {
    protected String nama;

    public Kartu(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
}
