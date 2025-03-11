package net.bookstores.assignment.admincontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.bookstores.assignment.dao.AuthorDao;
import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.dao.CategoryDao;
import net.bookstores.assignment.dao.PublisherDao;
import net.bookstores.assignment.entities.Author;
import net.bookstores.assignment.entities.Book;
import net.bookstores.assignment.entities.Category;
import net.bookstores.assignment.entities.Publisher;

@Controller
@RequestMapping("/admin/book")
public class BookController {
    @Autowired
    BookDao bookDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    AuthorDao authorDao;
    @Autowired
    PublisherDao publisherDao;

    @GetMapping("/index")
    public String danhSach(Model model) {
        // List<Book> list = bookDao.findAll();
        List<Book> list = bookDao.danhSachGiamDan();
        model.addAttribute("list", list);
        return "/admin/book/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        List<Author> authorList = authorDao.findAll();
        List<Category> categoryList = categoryDao.findAll();
        List<Publisher> publisherList = publisherDao.findAll();
        model.addAttribute("author", authorList);
        model.addAttribute("category", categoryList);
        model.addAttribute("publisher", publisherList);
        return "/admin/book/insert";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("title") String title,
            @RequestParam("author") Integer authorId,
            @RequestParam("category") Integer categoryId,
            @RequestParam("publisher") Integer publisherId,
            @RequestParam("price") Float price,
            @RequestParam("discountedPrice") Float discountedPrice,
            @RequestParam("quantity") Integer quantity,
            @RequestParam(value = "description", required = false, defaultValue = "Không có mô tả") String description,
            @RequestParam("imagePath") MultipartFile imageFile) { // Sử dụng MultipartFile để nhận file ảnh

        String fileName = saveImageToProject(imageFile);
        Author author = Author.builder().authorId(authorId).build();
        Category category = Category.builder().categoryId(categoryId).build();
        Publisher publisher = Publisher.builder().publisherId(publisherId).build();
        Float discountPercentage = ((price - discountedPrice) / price) * 100;

        Book book = Book.builder().title(title).author(author).category(category).publisher(publisher).price(price)
                .discountedPrice(discountedPrice).discountPercentage(discountPercentage).quantity(quantity)
                .description(description).imagePath(fileName).build();

        bookDao.save(book);

        return "redirect:/admin/book/index";
    }

    private String saveImageToProject(MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        try {
            String uploadDir = "C:/FPT Polytechnic/SOF3022/Source/assignment/src/main/resources/static/image/book/";
            String fileName = imageFile.getOriginalFilename();
            Path path = Paths.get(uploadDir, fileName);

            Files.createDirectories(path.getParent()); // Tạo thư mục nếu chưa tồn tại
            Files.write(path, imageFile.getBytes()); // Lưu file vào thư mục

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage(), e);
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") Integer id) {
        Optional<Book> book = bookDao.findById(id);
        if (book.isPresent()) {
            List<Author> authorList = authorDao.findAll();
            List<Category> categoryList = categoryDao.findAll();
            List<Publisher> publisherList = publisherDao.findAll();
            model.addAttribute("author", authorList);
            model.addAttribute("category", categoryList);
            model.addAttribute("publisher", publisherList);
            model.addAttribute("book", book.get());
            return "/admin/book/update";
        } else {
            return "redirect:/admin/book/index";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("bookId") Integer bookId,
            @RequestParam("title") String title,
            @RequestParam("author") Integer authorId,
            @RequestParam("category") Integer categoryId,
            @RequestParam("publisher") Integer publisherId,
            @RequestParam("price") Float price,
            @RequestParam("discountedPrice") Float discountedPrice,
            @RequestParam("quantity") Integer quantity,
            @RequestParam(value = "description", required = false, defaultValue = "Không có mô tả") String description,
            @RequestParam(value = "imagePath", required = false) MultipartFile imageFile) {

        String img;
        if (imageFile.isEmpty() || imageFile == null) {
            Optional<Book> optionalBook = bookDao.findById(bookId);
            if (optionalBook.isPresent()) {
                img = optionalBook.get().getImagePath();
                Author author = Author.builder().authorId(authorId).build();
                Category category = Category.builder().categoryId(categoryId).build();
                Publisher publisher = Publisher.builder().publisherId(publisherId).build();
                Float discountPercentage = ((price - discountedPrice) / price) * 100;

                Book bookSave = Book.builder().bookId(bookId).title(title).author(author).category(category)
                        .publisher(publisher).price(price).discountedPrice(discountedPrice)
                        .discountPercentage(discountPercentage).quantity(quantity).description(description)
                        .imagePath(img)
                        .build();
                bookDao.save(bookSave);
            } else {
                return "redirect:/admin/book/index";
            }
        } else {
            String fileName = saveImageToProject(imageFile);
            Author author = Author.builder().authorId(authorId).build();
            Category category = Category.builder().categoryId(categoryId).build();
            Publisher publisher = Publisher.builder().publisherId(publisherId).build();
            Float discountPercentage = ((price - discountedPrice) / price) * 100;

            Book bookSave = Book.builder().bookId(bookId).title(title).author(author).category(category)
                    .publisher(publisher).price(price).discountedPrice(discountedPrice)
                    .discountPercentage(discountPercentage).quantity(quantity).description(description)
                    .imagePath(fileName).build();
            bookDao.save(bookSave);
        }

        return "redirect:/admin/book/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        bookDao.deleteById(id);
        return "redirect:/admin/book/index";
    }

}
