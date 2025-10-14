import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        // buat object menu
        ProductMenu menu = new ProductMenu();

        // atur ukuran menu
        menu.setSize(700, 600);

        // letakkan menu di tengah layar
        menu.setLocationRelativeTo(null);

        // isi menu
        menu.setContentPane(menu.mainPanel);

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan menu
        menu.setVisible(true);

        // agar program ikut berhenti saat menu diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua produk
    private Database database;

    private String idAwal;

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField judulField;
    private JTextField durasiField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> genreComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JSpinner tahunSpinner;
    private JTextField artisField;

    // constructor
    public ProductMenu() {
        //buat objek database
        database = new Database();

        // isi tabel produk
        productTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] genreData = { "...", "Pop", "K-Pop", "Rock", "Jazz", "Hip-Hop", "R&B", "EDM", "Indie Rock", "Indie Pop" };
        genreComboBox.setModel(new DefaultComboBoxModel<>(genreData));

        // atur spinner
        SpinnerNumberModel model = new SpinnerNumberModel(2000, 2000, 2025, 1);
        tahunSpinner.setModel(model);

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ambil value dari textfield dan combobox
                String id = idField.getText(); // id produk
                String judul = judulField.getText(); // judul lagu
                String artis = artisField.getText(); //penyanyi dari lagu tsb
                String durasi =durasiField.getText(); // durasi lagu dalam detik
                String genre = genreComboBox.getSelectedItem().toString(); // genre musik
                int tahun = (int) tahunSpinner.getValue(); //tahun rilis

                StringBuilder errorMessage = new StringBuilder();

                // untuk pesan error yang lebih spesifik
                if (id.isEmpty()) {
                    errorMessage.append("+ ID belum diisi\n");
                }
                if (judul.isEmpty()) {
                    errorMessage.append("+ Judul belum diisi\n");
                }
                if (durasi.isEmpty()) {
                    errorMessage.append("+ Durasi belum diisi\n");
                }
                if (artis.isEmpty()) {
                    errorMessage.append("+ Artis belum diisi\n");
                }
                if (genre.equals("...")) {
                    errorMessage.append("+ Genre belum dipilih\n");
                }

                if (!errorMessage.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Perhatikan kembali form pengisian !\n\n" + errorMessage.toString() ,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (selectedIndex == -1) {
                    // mode add
                    if (checkIfIdExists(id)) {
                        // menampilkan error jika id exist
                        JOptionPane.showMessageDialog(
                                null,
                                "ID " + id + " sudah digunakan, masukkan ID lain.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    insertData();
                } else {
                    // mode update
                    if (!id.equals(idAwal)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "ID tidak bisa diubah.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    updateData();
                }
            }
        });

        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin akan menghapus " + judulField.getText() + "?", "Konfirmasi Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();  // Hanya hapus jika pengguna memilih YES
                }
            }
        });


        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = productTable.getSelectedRow();

                // dapatkan model dari tabel
                DefaultTableModel model = (DefaultTableModel) productTable.getModel();

                // simpan id asli
                idAwal = model.getValueAt(selectedIndex, 1).toString();

                // simpan value dari baris yang dipilih
                idField.setText(model.getValueAt(selectedIndex, 1).toString());
                judulField.setText(model.getValueAt(selectedIndex, 2).toString());
                durasiField.setText(model.getValueAt(selectedIndex, 3).toString());
                artisField.setText(model.getValueAt(selectedIndex, 4).toString());
                genreComboBox.setSelectedItem(model.getValueAt(selectedIndex, 5).toString());
                tahunSpinner.setValue(Integer.parseInt(model.getValueAt(selectedIndex, 6).toString()));

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = { "No", "ID", "Judul", "Durasi", "Artis", "Genre", "Tahun Rilis" };

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try{
            ResultSet resultSet = database.selectQuery("SELECT * FROM products");

            int i = 0;
            while(resultSet.next()){
                Object[] row = new Object[7];
                row[0] = i+1;
                row[1] = resultSet.getString("product_code");
                row[2] = resultSet.getString("title");
                row[3] = resultSet.getInt("duration"); // durasi
                row[4] = resultSet.getString("artist"); // artis
                row[5] = resultSet.getString("genre");
                row[6] = resultSet.getInt("release_year");
                tmp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }

    // untuk mengecek apakah id sudah digunakan atau belum
    private boolean checkIfIdExists(String id) {
        try {
            ResultSet result = database.selectQuery("SELECT 1 FROM products WHERE product_code = '" + id + "' LIMIT 1");
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertData() {
        try {
            // ambil value dari textfield dan combobox
            String id = idField.getText(); // id produk
            String judul = judulField.getText(); // judul lagu
            String artis = artisField.getText(); //penyanyi dari lagu tsb
            int durasi = Integer.parseInt(durasiField.getText()); // durasi lagu dalam detik
            String genre = genreComboBox.getSelectedItem().toString(); // genre musik
            int tahun = (int) tahunSpinner.getValue(); //tahun rilis

            // tambahkan data ke dalam database
            String sqlQuery = "INSERT INTO products VALUES ('" + id + "', '" + judul + "', '" + artis + "', " + durasi + ", '" + genre + "', " + tahun + ")";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Durasi harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        try {
            // Ambil data dari form
            String id = idField.getText();
            String judul = judulField.getText();
            int durasi = Integer.parseInt(durasiField.getText());
            String artis = artisField.getText();
            String genre = genreComboBox.getSelectedItem().toString();
            int tahun = (int) tahunSpinner.getValue();

            String sql = "UPDATE products SET title='" + judul + "', artist='" + artis + "', duration=" + durasi + ", genre='" + genre + "', release_year=" + tahun + " WHERE product_code='" + idAwal + "'";
            database.insertUpdateDeleteQuery(sql);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Durasi harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        String id = idField.getText();

        String sql = "DELETE FROM products WHERE product_code='" + id + "'";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        productTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(
                null,
                "Data berhasil dihapus");

    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        idField.setText("");
        judulField.setText("");
        durasiField.setText("");
        artisField.setText("");
        genreComboBox.setSelectedIndex(0);
        tahunSpinner.setValue(2000); // atau tahun default

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}