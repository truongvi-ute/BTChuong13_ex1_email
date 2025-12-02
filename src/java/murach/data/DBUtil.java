package murach.data;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    
    private static final EntityManagerFactory emf;
    
    static {
        Map<String, String> properties = new HashMap<>();
        
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        if (dbUrl != null && !dbUrl.isEmpty()) {
            // --- ĐOẠN CODE SỬA ĐỔI Ở ĐÂY ---
            // Kiểm tra: Nếu URL từ Render chưa có "jdbc:postgresql://" thì mới thêm vào.
            // Nếu có rồi (như trường hợp của bạn) thì giữ nguyên.
            String finalUrl = dbUrl;
            if (!dbUrl.startsWith("jdbc:postgresql://")) {
                finalUrl = "jdbc:postgresql://" + dbUrl;
            }
            // -------------------------------

            properties.put("javax.persistence.jdbc.url", finalUrl);
            properties.put("javax.persistence.jdbc.user", dbUser);
            properties.put("javax.persistence.jdbc.password", dbPass);
        }
        
        emf = Persistence.createEntityManagerFactory("emailListPU", properties);
    }

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}