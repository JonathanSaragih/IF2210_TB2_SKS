package jojikanabe.if2210_tb2_sks.classes.kartu;

public abstract class Kartu {
    protected String nama;
    protected final String image;

    public Kartu(String nama, String image) {
        this.nama = nama;
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public String getImage() {
        return image;
    }
}
