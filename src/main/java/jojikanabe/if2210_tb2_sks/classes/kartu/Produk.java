package jojikanabe.if2210_tb2_sks.classes.kartu;

public class Produk extends Kartu {
    private Integer harga;
    private Integer kalori;

    public Produk(String nama, Integer harga, Integer kalori) {
        super(nama);
        this.harga = harga;
        this.kalori = kalori;
    }

    public Integer getHarga() {
        return harga;
    }

    public Integer getKalori() {
        return kalori;
    }
}
