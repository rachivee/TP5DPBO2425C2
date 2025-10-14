public class Product {
    private String id;
    private String judul;
    private String artis;
    private int durasi;
    private String genre;
    private int tahun;

    // Constructor untuk produk musik
    public Product(String id, String judul, String artis, int durasi, String genre, int tahun) {
        this.id = id;
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
        this.genre = genre;
        this.tahun = tahun;
    }

    // Setter dan Getter untuk atribut musik
    public void setId(String id) {
        this.id = id;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getArtis() {
        return artis;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getGenre() {
        return genre;
    }

    public int getTahun() {
        return tahun;
    }
}
