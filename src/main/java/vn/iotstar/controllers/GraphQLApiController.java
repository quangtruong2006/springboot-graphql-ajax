package vn.iotstar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IProductService;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GraphQLApiController {

	@Autowired
	ICategoryService categoryService;
	@Autowired
	IProductService productService;
	@Autowired
	vn.iotstar.repository.CategoryRepository categoryRepository;
	// Cứu cánh: Dùng trực tiếp Repository để xóa dữ liệu chắc chắn 100%
	@Autowired
	ProductRepository productRepository;

	public record CategoryInput(Long categoryId, String categoryName, String icon) {
	}

	public record ProductInput(Long productId, String productName, Double unitPrice, Double discount,
			String description, String images, Integer quantity, Short status, Long categoryId) {
	}

	// ==========================================
	// CÁC HÀM QUERY (LẤY DỮ LIỆU)
	// ==========================================
	@QueryMapping
	public List<Category> getAllCategories() {
		return categoryService.findAll();
	}

	@QueryMapping
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	@QueryMapping
	public List<Product> getProductsSortedByPrice() {
		return productService.findAll().stream().sorted(Comparator.comparing(Product::getUnitPrice))
				.collect(Collectors.toList());
	}

	@QueryMapping
	public List<Product> getProductsByCategoryId(@Argument Long categoryId) {
		return productService.findAll().stream()
				.filter(p -> p.getCategory() != null && p.getCategory().getCategoryId().equals(categoryId))
				.collect(Collectors.toList());
	}

	@QueryMapping
	public List<Product> searchProducts(@Argument String keyword) {
		return productService.findAll().stream()
				.filter(p -> p.getProductName().toLowerCase().contains(keyword.toLowerCase()))
				.collect(Collectors.toList());
	}

	// ==========================================
	// CÁC HÀM MUTATION (THÊM / SỬA / XÓA)
	// ==========================================
	@MutationMapping
	public Category createCategory(@Argument CategoryInput category) {
		Category newCat = new Category();
		newCat.setCategoryName(category.categoryName());
		newCat.setIcon(category.icon());
		return categoryService.save(newCat);
	}

	@MutationMapping
	public Product createProduct(@Argument ProductInput product) {
		Product newPro = new Product();
		newPro.setProductName(product.productName());
		newPro.setUnitPrice(product.unitPrice());
		newPro.setDiscount(product.discount());
		newPro.setDescription(product.description());
		newPro.setImages(product.images());
		newPro.setQuantity(product.quantity());
		newPro.setStatus(product.status());
		newPro.setCreateDate(new Timestamp(new Date().getTime()));

		Category cat = new Category();
		cat.setCategoryId(product.categoryId());
		newPro.setCategory(cat);

		return productService.save(newPro);
	}

	// BỔ SUNG HÀM SỬA BỊ THIẾU: Lưu ảnh mới và cập nhật dữ liệu
	@MutationMapping
	public Product updateProduct(@Argument ProductInput product) {
		Product existPro = new Product();
		existPro.setProductId(product.productId());
		existPro.setProductName(product.productName());
		existPro.setUnitPrice(product.unitPrice());
		existPro.setDiscount(product.discount());
		existPro.setDescription(product.description());
		existPro.setImages(product.images()); // Cập nhật tên ảnh thật vào đây
		existPro.setQuantity(product.quantity());
		existPro.setStatus(product.status());

		Category cat = new Category();
		cat.setCategoryId(product.categoryId());
		existPro.setCategory(cat);

		return productService.save(existPro);
	}

	// NÂNG CẤP HÀM XÓA: Ép Spring Boot xóa thật dưới cơ sở dữ liệu
	@MutationMapping
	public String deleteProduct(@Argument Long productId) {
		try {
			productRepository.deleteById(productId);
			return "Xóa thành công sản phẩm: " + productId;
		} catch (Exception e) {
			return "Xóa thất bại!";
		}
	}

	@MutationMapping
	public Category updateCategory(@Argument CategoryInput category) {
		Category existCat = new Category();
		existCat.setCategoryId(category.categoryId());
		existCat.setCategoryName(category.categoryName());
		existCat.setIcon(category.icon());
		return categoryService.save(existCat);
	}

	@MutationMapping
	public String deleteCategory(@Argument Long categoryId) {
		try {
			categoryRepository.deleteById(categoryId);
			return "Xóa thành công category: " + categoryId;
		} catch (Exception e) {
			return "Xóa thất bại!";
		}
	}
}