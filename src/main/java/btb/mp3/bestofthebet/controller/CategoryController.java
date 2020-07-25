package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.Category;
import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long id){
        if(categoryService.findById(id).get()!=null){
            Category category = categoryService.findById(id).get();
        return new ResponseEntity<Category>(category,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
