package murach.data;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    
    private static final EntityManagerFactory emf;
    
    static {
        Map<String, String> properties = new HashMap<>();
        
        // Lấy thông tin từ Biến môi trường (Environment Variables) của Render
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        // Nếu tìm thấy biến môi trường (đang chạy trên Server)
        if (dbUrl != null && !dbUrl.isEmpty()) {
            properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + dbUrl); // Lưu ý format URL bên dưới
            properties.put("javax.persistence.jdbc.user", dbUser);
            properties.put("javax.persistence.jdbc.password", dbPass);
            // Giữ nguyên driver và các cấu hình khác từ persistence.xml
        }
        
        // Tạo EMF với các thuộc tính ghi đè (nếu có)
        emf = Persistence.createEntityManagerFactory("emailListPU", properties);
    }

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}