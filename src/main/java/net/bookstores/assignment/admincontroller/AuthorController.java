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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.bookstores.assignment.dao.AuthorDao;
import net.bookstores.assignment.entities.Author;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/author")

public class AuthorController {
    @Autowired
    AuthorDao authorDao;

    @GetMapping("/index")
    public String index(Model model) {
        List<Author> list = authorDao.findAll();
        model.addAttribute("list", list);
        return "admin/authors/index";
    }

    @GetMapping("/create")
    public String createForm() {
        return "admin/authors/insert";
    }

    @PostMapping("/create")
    public String create(@RequestParam("name") String name,
            @RequestParam(value = "bio", required = false) String bio,
            @RequestParam(value = "imagePath", required = false) MultipartFile imageFile) {

        String fileName = saveImageToProject(imageFile);

        // Lưu thông tin vào database
        Author author = Author.builder()
                .name(name)
                .bio((bio != null && !bio.isEmpty()) ? bio : null)
                .imagePath(fileName)
                .build();

        authorDao.save(author);
        return "redirect:/admin/author/index";
    }

    private String saveImageToProject(MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        try {
            String uploadDir = "C:/FPT Polytechnic/SOF3022/Source/assignment/src/main/resources/static/image/author/";
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
    public String updateForm(@RequestParam("id") Integer id, Model model) {
        Optional<Author> author = authorDao.findById(id);
        if (author.isPresent()) {
            model.addAttribute("author", author.get()); // Loại bỏ Optional
            return "admin/authors/update";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("authorId") Integer authorId, @RequestParam("name") String name,
            @RequestParam(value = "bio", required = false) String bio,
            @RequestParam(value = "imagePath", required = false) MultipartFile imageFile) {

        String fileName = saveImageToProject(imageFile);

        // Lưu thông tin vào database
        Author author = Author.builder().authorId(authorId).name(name).bio((bio != null && !bio.isEmpty()) ? bio : null)
                .imagePath(fileName).build();
        authorDao.save(author);
        return "redirect:/admin/author/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        authorDao.deleteById(id);
        return "redirect:/admin/author/index";
    }

}
