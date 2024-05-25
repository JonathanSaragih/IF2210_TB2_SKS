package jojikanabe.if2210_tb2_sks.classes.kartu;

public class Produk extends Kartu {
    private Integer harga;
    private Integer kalori;

    public Produk(String nama, String image, Integer harga, Integer kalori) {
        super(nama, image);
        this.harga = harga;
        this.kalori = kalori;
    }

    public Produk(Produk original) {
        super(original);
        this.harga = original.harga;
        this.kalori = original.kalori;
    }

    public Integer getHarga() {
        return harga;
    }

    public Integer getKalori() {
        return kalori;
    }
}
