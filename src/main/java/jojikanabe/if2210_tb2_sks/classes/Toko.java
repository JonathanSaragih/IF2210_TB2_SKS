package jojikanabe.if2210_tb2_sks.classes;

import jojikanabe.if2210_tb2_sks.classes.kartu.Produk;

import java.util.HashMap;
import java.util.Map;

public class Toko {
    private Map<Produk, Integer> daftarProduk;

    public Toko() {
        this.daftarProduk = new HashMap<>();
    }

    public void cetakToko() {
        for (Map.Entry<Produk, Integer> entry : daftarProduk.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println("Produk: " + entry.getKey().getNama() + ", Jumlah: " + entry.getValue());
            }
        }
    }

    public Map<Produk, Integer> getDaftarProduk() {
        return daftarProduk;
    }

    public Produk jualProduk(Produk produk, Integer jumlah) {
        if (daftarProduk.containsKey(produk)) {
            Integer stok = daftarProduk.get(produk);
            if (stok >= jumlah) {
                daftarProduk.put(produk, stok - jumlah);
                return produk;
            } else {
                System.out.println("Stok tidak cukup");
            }
        }
        return null;
    }

    public void addStok(Produk produk, Integer jumlah) {
        if (daftarProduk.containsKey(produk)) {
            Integer currentStok = daftarProduk.get(produk);
            daftarProduk.put(produk, currentStok + jumlah);
        } else {
            daftarProduk.put(produk, jumlah);
        }
    }

    /**
     * @param produk
     * @param jumlah
     * @return gulden yang diterima pemain
     */
    public Integer beliProduk(Produk produk, Integer jumlah) {
        Integer temp = 0;
        if (daftarProduk.containsKey(produk)) {
            Integer harga = produk.getHarga();
            Integer stok = daftarProduk.get(produk);
            addStok(produk, jumlah);
            temp = harga * jumlah;
        } else {
            daftarProduk.put(produk, jumlah);
            temp = produk.getHarga() * jumlah;
        }
        return temp;
    }
}
