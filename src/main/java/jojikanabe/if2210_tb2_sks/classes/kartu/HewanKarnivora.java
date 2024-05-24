package jojikanabe.if2210_tb2_sks.classes.kartu;

public class HewanKarnivora extends Hewan {
    public HewanKarnivora(String nama, String image, Integer beratBadanSiapPanen, Integer beratBadan) {
        super(nama, image, beratBadanSiapPanen, beratBadan);
    }

    @Override
    public void makan(Kartu kartu) throws Exception {
        String[] makanan = {"Sirip Hiu", "Daging Kuda", "Daging Domba", "Daging Beruang"};
        if (kartu instanceof Produk) {
            Produk produk = (Produk) kartu;
            for (String s : makanan) {
                if (produk.getNama().equals(s)) {
                    this.beratBadan += produk.getKalori();
                    return;
                }
            }
            throw new Exception("Hewan tidak bisa makan produk ini");
        } else {
            throw new Exception("Hewan tidak bisa makan kartu ini");
        }
    }
}
