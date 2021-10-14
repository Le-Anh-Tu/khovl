package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/")
    public String list(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("product", productList);
        return "/list";
    }
    @GetMapping("/product/save")
    public String showCreate(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }
    @PostMapping("/product/save")
    public String save(
            RedirectAttributes redirectAttributes,
            Product product
    ) {
        int id = (int) (Math.random() * 100);
        product.setId(id);
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Saved product successfully");
        return "redirect:/";
    }
    @GetMapping("/product/{id}/edit")
    public String edit( @PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/edit";
    }
    @PostMapping("/product/edit")
    public String edit ( Product product) {
        productService.edit(product.getId(), product);
        return "redirect:/";
    }
    @GetMapping("/product/{id}/delete")
    public String delete(
            Model model,
            @PathVariable("id") int id
    ) {
        model.addAttribute("product", productService.findById(id));
        return "/delete";
    }

    @PostMapping("/product/remove")
    public String remove(Product product, RedirectAttributes redirectAttributes) {
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("success", "Remove product successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/view")
    public String view(@PathVariable ("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }


}
