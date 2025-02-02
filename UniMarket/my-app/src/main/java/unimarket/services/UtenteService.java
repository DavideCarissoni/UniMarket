package unimarket.services;

import db.CreateDatabase;
import jooq.generated.tables.Utente;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import java.util.List;

@Service
public class UtenteService {

    private int counter = 0;
    private final DSLContext dsl;
    private final BCryptPasswordEncoder passwordEncoder;

    public UtenteService(DSLContext dsl) {
        this.dsl = dsl;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean isEmailUnique(String email) {
        String sql = "SELECT COUNT(*) FROM utente WHERE email = ?";

        try (Connection conn = CreateDatabase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log dell'errore
        }
        return false; // In caso di errore, meglio restituire false per evitare registrazioni duplicate
    }


    public void saveUser(String nome, String cognome, String numeroTelefono, String email, String password) {
        String checkIdSQL = "SELECT COUNT(*) FROM utente WHERE id = ?";
        String insertSQL = "INSERT INTO utente (id, nome, cognome, numero_telefono, email, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = CreateDatabase.getInstance().getConnection()) {
            int newId = generateUniqueId(conn, checkIdSQL);

            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                stmt.setInt(1, newId);
                stmt.setString(2, nome);
                stmt.setString(3, cognome);
                stmt.setString(4, numeroTelefono);
                stmt.setString(5, email);
                stmt.setString(6, passwordEncoder.encode(password)); // Codifica la password

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log dell'errore
        }
    }

    private int generateUniqueId(Connection conn, String checkIdSQL) throws SQLException {
        Random random = new Random();
        int id;

        do {
            id = 1000 + random.nextInt(9000); // Genera un numero tra 1000 e 9999
        } while (isIdExists(conn, checkIdSQL, id));

        return id;
    }

    private boolean isIdExists(Connection conn, String checkIdSQL, int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(checkIdSQL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Se il conteggio è maggiore di 0, l'ID esiste già
                }
            }
        }
        return false;
    }

    public boolean authenticate(String email, String password) {
        String sql = "SELECT password FROM utente WHERE email = ?";

        try (Connection conn = CreateDatabase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString(1);
                    return passwordEncoder.matches(password, hashedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
