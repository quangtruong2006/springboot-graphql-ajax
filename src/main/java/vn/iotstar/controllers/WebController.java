package vn.iotstar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import vn.iotstar.service.IStorageService;

import java.util.UUID;

@Controller
public class WebController {

	@Autowired
	IStorageService storageService;

	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}

	@GetMapping("/admin")
	public String showAdminPage() {
		return "admin";
	}

	// API REST phụ trợ để hứng file ảnh từ AJAX
	@PostMapping("/upload")
	@ResponseBody
	public String uploadImage(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return "default.jpg";
		}
		// Tạo tên file ngẫu nhiên để không bị trùng
		String uuid = UUID.randomUUID().toString();
		String filename = storageService.getSorageFilename(file, uuid);
		storageService.store(file, filename);
		return filename;
	}

	@GetMapping("/admin-category")
	public String showAdminCategoryPage() {
		return "admin-category";
	}
}