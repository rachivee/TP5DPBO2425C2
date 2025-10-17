# TP5DPBO2425C2

Saya Farah Maulida dengan NIM 2410024 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berbasis Objek untuk keberkahan-Nya maka saya tidak akan melakukan kecurangan seperti yang telah di spesifikasikan Aamiin.

# Desain dan Alur Program

Pada praktikum kali ini, saya menggunakan tema daftar lagu yang didengarkan. Program ini dibuat menggunakan bahasa pemrograman Java. Terdapat tiga kelas utama dalam program ini, yaitu Product, ProductMenu, dan yang terakhir adalah Database. Kelas Product berfungsi untuk merepresentasikan data dari setiap lagu, sedangkan kelas ProductMenu menangani logika antarmuka dan interaksi pengguna, lalu Database berhubungan dengan koneksi atau hubungan antara program dan database.

1. Kelas Product memiliki enam atribut utama, yaitu id, judul, artis, durasi, genre, dan tahun. Atribut-atribut tersebut menggambarkan informasi dasar tentang sebuah lagu. Misalnya, id digunakan sebagai kode unik untuk membedakan satu lagu dengan yang lain, sedangkan judul dan artis menggambarkan isi lagu. Durasi menyimpan informasi lama waktu lagu diputar dalam satuan detik, genre menunjukkan kategori musik seperti Pop, R&B, atau Indie, dan tahun mencatat tahun rilis lagu tersebut. Kelas ini juga menyediakan konstruktor untuk menginisialisasi objek serta metode setter dan getter untuk mengatur dan mengambil nilai atribut secara aman.

2. Kelas ProductMenu merupakan kelas utama dalam program ini. Di dalam kelas ini, digunakan berbagai komponen GUI seperti JTextField, JComboBox, JSpinner, JTable, dan JButton untuk menciptakan tampilan input dan tabel yang interaktif. Pengguna dapat melihat daftar lagu yang ada, menambahkan lagu baru, mengedit lagu yang sudah ada, serta menghapus lagu dari daftar.
   
3. Kelas Database bertugas untuk mengelola koneksi dan operasi pada database. Kelas ini memiliki atribut connection untuk menyimpan objek koneksi ke database dan statement untuk mengeksekusi query SQL. Dengan konstruktor, kelas ini menghubungkan aplikasi dengan database dan membuat statement untuk menjalankan perintah SQL. Metode yang disediakan antara lain untuk mengeksekusi query SELECT yang mengembalikan hasil dalam bentuk ResultSet, serta untuk menjalankan query INSERT, UPDATE, atau DELETE yang mengubah data dalam database.
