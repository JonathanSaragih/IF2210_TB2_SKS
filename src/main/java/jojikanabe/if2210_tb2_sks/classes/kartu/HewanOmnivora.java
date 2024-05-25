package jojikanabe.if2210_tb2_sks.classes.kartu;

public class HewanOmnivora extends Hewan {
    public HewanOmnivora(String nama, String image, Integer beratBadanSiapPanen, Integer beratBadan) {
        super(nama, image, beratBadanSiapPanen, beratBadan);
    }

    public HewanOmnivora(HewanOmnivora original) {
        super(original);
    }

    @Override
    public void makan(Kartu kartu) throws Exception {
        if (kartu instanceof Produk) {
            Produk produk = (Produk) kartu;
            this.beratBadan += produk.getKalori();
        } else {
            throw new Exception("Hewan tidak bisa makan kartu ini");
        }
    }
}
