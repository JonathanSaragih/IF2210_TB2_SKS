package jojikanabe.if2210_tb2_sks.classes;

import jojikanabe.if2210_tb2_sks.classes.kartu.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Kelas GameState merupakan kelas yang menyimpan data permainan.
 * Kelas ini memakai design pattern singleton.
 */
public class GameState implements ConfigController {
    private static volatile GameState instance = null;
    public Integer giliran;
    private Toko toko;
    private List<Pemain> pemain;
    private List<Kartu> dataKartu;

    private GameState() {
        this.loadKartu();
        this.loadToko();
    }

    public static GameState getInstance() {
        if (instance == null) {
            synchronized (GameState.class) {
                if (instance == null) {
                    instance = new GameState();
                }
            }
        }
        return instance;
    }

    public Toko getToko() {
        return toko;
    }

    public void NewGame() {
        this.pemain = new ArrayList<>();
        this.pemain.add(new Pemain("Pemain 1", 1000, 40, 0));
        this.pemain.add(new Pemain("Pemain 2", 1000, 40, 0));
        this.giliran = 1;
    }

    public void LoadGame() {
        this.pemain = new ArrayList<>();
        this.loadConfig("config");
    }

    private void loadToko() {
        this.toko = new Toko();
        for (Kartu kartu : dataKartu) {
            if (kartu instanceof Produk) {
                toko.addStok((Produk) kartu, 0);
            }
        }
    }

    public List<Kartu> getDataKartu() {
        return dataKartu;
    }


    private void loadKartu() {
        this.dataKartu = new ArrayList<>();

        // Hewan
        dataKartu.add(new HewanKarnivora("Hiu Darat","src/main/resources/assets/kartu/Hewan/hiu_darat.png", 20, 0));
        dataKartu.add(new HewanHerbivora("Sapi","src/main/resources/assets/kartu/Hewan/sapi.png", 10, 0));
        dataKartu.add(new HewanHerbivora("Domba","src/main/resources/assets/kartu/Hewan/domba.png", 12, 0));
        dataKartu.add(new HewanHerbivora("Kuda","src/main/resources/assets/kartu/Hewan/kuda.png", 14, 0));
        dataKartu.add(new HewanOmnivora("Ayam","src/main/resources/assets/kartu/Hewan/ayam.png", 5, 0));
        dataKartu.add(new HewanOmnivora("Beruang","src/main/resources/assets/kartu/Hewan/beruang.png", 25, 0));

        // Tanaman
        dataKartu.add(new Tanaman("Biji Jagung","src/main/resources/assets/kartu/Tanaman/biji_jagung.png", 3, 0));
        dataKartu.add(new Tanaman("Biji Labu","src/main/resources/assets/kartu/Tanaman/biji_labu.png", 5, 0));
        dataKartu.add(new Tanaman("Biji Stroberi","src/main/resources/assets/kartu/Tanaman/biji_stroberi.png", 4, 0));

        // Produk
        dataKartu.add(new Produk("Sirip Hiu","src/main/resources/assets/kartu/Produk/sirip.png", 500, 12));
        dataKartu.add(new Produk("Susu","src/main/resources/assets/kartu/Produk/susu.png", 100, 4));
        dataKartu.add(new Produk("Daging Domba","src/main/resources/assets/kartu/Produk/dagingdomba.png", 120, 6));
        dataKartu.add(new Produk("Daging Kuda","src/main/resources/assets/kartu/Produk/dagingkuda.png", 150, 8));
        dataKartu.add(new Produk("Telur","src/main/resources/assets/kartu/Produk/telur.png", 50, 2));
        dataKartu.add(new Produk("Daging Beruang","src/main/resources/assets/kartu/Produk/dagingberuang.png", 500, 12));
        dataKartu.add(new Produk("Jagung","src/main/resources/assets/kartu/Produk/jagung.png", 150, 3));
        dataKartu.add(new Produk("Labu","src/main/resources/assets/kartu/Produk/pumpkin.png", 500, 10));
        dataKartu.add(new Produk("Stroberi","src/main/resources/assets/kartu/Produk/strawberry.png", 350, 5));

        // Item
        dataKartu.add(new ItemAccelerate("src/main/resources/assets/kartu/item_Accelerate.png"));
        dataKartu.add(new ItemDelay("src/main/resources/assets/kartu/delay.png"));
        dataKartu.add(new ItemInstantHarvest("src/main/resources/assets/kartu/instant_harvest.png"));
        dataKartu.add(new ItemDestroy("src/main/resources/assets/kartu/destroy.png"));
        dataKartu.add(new ItemProtect("src/main/resources/assets/kartu/protect.png"));
        dataKartu.add(new ItemTrap("src/main/resources/assets/kartu/trap.png"));

    }

    public void nextTurn() {
        giliran = giliran == 1 ? 2 : 1;
    }

    public Kartu getKartu(String nama) {
        for (Kartu kartu : dataKartu) {
            if (kartu.getNama().equals(nama)) {
                return kartu;
            }
        }
        return null;
    }

    @Override
    public void loadConfig(String folderName) {
        // Load game state
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + folderName + "/gamestate.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    giliran = Integer.parseInt(line);
                } else if (i == 1) {

                } else {
                    String data[] = line.split(" ");

                    String nama = Helper.convertString(data[0]);
                    Integer jumlah = Integer.parseInt(data[1]);
                    for (Kartu kartu : dataKartu) {
                        if (kartu.getNama().equals(nama)) {
                            toko.addStok((Produk) kartu, jumlah);
                        }
                    }
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load pemain
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + folderName + "/player1.txt"))) {
            String line;
            int i = 0;
            int jumlahDeck = 40;
            int jumlahDeckAktif = 0;
            int gulden = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    gulden = Integer.parseInt(line);
                } else if (i == 1) {
                    jumlahDeck = Integer.parseInt(line);
                    pemain.add(new Pemain("Pemain 1", gulden, jumlahDeck, jumlahDeckAktif));
                } else if (i == 2) {
                    jumlahDeckAktif = Integer.parseInt(line);
                } else if (i < 3 + jumlahDeckAktif) {
                    String data[] = line.split(" ");
                    int posisi = Helper.convertStringToNumber(data[0]);
                    String nama = Helper.convertString(data[1]);
                    pemain.get(0).addKartu(getKartu(nama), posisi);
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + folderName + "/player2.txt"))) {
            String line;
            int i = 0;
            int jumlahDeck = 40;
            int jumlahDeckAktif = 0;
            int gulden = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    gulden = Integer.parseInt(line);
                } else if (i == 1) {
                    jumlahDeck = Integer.parseInt(line);
                    pemain.add(new Pemain("Pemain 2", gulden, jumlahDeck, jumlahDeckAktif));
                } else if (i == 2) {
                    jumlahDeckAktif = Integer.parseInt(line);
                } else if (i < 3 + jumlahDeckAktif) {
                    String data[] = line.split(" ");
                    int posisi = Helper.convertStringToNumber(data[0]);
                    String nama = Helper.convertString(data[1]);
                    pemain.get(1).addKartu(getKartu(nama), posisi);
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveConfig(String foldername) {
    }
}
