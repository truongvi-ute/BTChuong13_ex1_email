package murach.data;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    
    private static final EntityManagerFactory emf;
    
    static {
        try {
            Map<String, String> properties = new HashMap<>();
            
            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPass = System.getenv("DB_PASS");

            // --- DÒNG DEBUG QUAN TRỌNG ---
            // In giá trị đọc được ra Log để kiểm tra
            System.out.println("=== DEBUG RENDER ENV ===");
            System.out.println("DB_URL read: " + dbUrl);
            System.out.println("DB_USER read: " + dbUser);
            // -----------------------------

            if (dbUrl != null && !dbUrl.isEmpty()) {
                // Logic ghép chuỗi kết nối
                String finalUrl = "jdbc:postgresql://" + dbUrl;
                System.out.println("Final JDBC URL: " + finalUrl); // In URL cuối cùng ra để soi lỗi
                
                properties.put("javax.persistence.jdbc.url", finalUrl);
                properties.put("javax.persistence.jdbc.user", dbUser);
                properties.put("javax.persistence.jdbc.password", dbPass);
            }
            
            emf = Persistence.createEntityManagerFactory("emailListPU", properties);
            
        } catch (Throwable t) {
            // Nếu có lỗi, in chi tiết ra Console của Render
            System.err.println("!!! CRITICAL ERROR: KHONG THE KHOI TAO DBUTIL !!!");
            t.printStackTrace(); // In toàn bộ lỗi ra
            throw new ExceptionInInitializerError(t); // Báo cho Tomcat biết app đã hỏng
        }
    }

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}