package vn.iotstar.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cố định lấy thư mục gốc của project và ép buộc có dấu / ở cuối
        String uploadPath = "file:" + System.getProperty("user.dir") + "/uploads/";
        
        System.out.println("========== ĐƯỜNG DẪN CHUẨN CÓ DẤU GẠCH CHÉO: " + uploadPath + " ==========");
        
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
    }
}