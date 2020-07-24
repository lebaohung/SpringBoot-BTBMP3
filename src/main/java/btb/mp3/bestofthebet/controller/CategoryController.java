package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.Category;
import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<List<Category>>(categoryService.findAll(), HttpStatus.OK);
    }
}
