package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Controller
public class SampleController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("name", "API");
        model.addAttribute("get", "GET /sample");
        model.addAttribute("post", "POST /sample");
        return "sample/index";
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/sample" , method = RequestMethod.GET)
    public String index(Model model) {
        List<User> list = jdbcTemplate.queryForObject("select * from mysql.users", new UserMapper());
        model.addAttribute("list", list);
        return "sample/index";
    }

    @RequestMapping(path = "/sample/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable("id") int id) {
        List<User> list = jdbcTemplate.queryForObject("select * from mysql.users where id = ? ", new Object[] { id }, new UserMapper());
        model.addAttribute("list", list);
        return "sample/index";
    }

    @ModelAttribute
    public UserForm userForm() {
        return new UserForm();
    }
    @RequestMapping(path = "/sample", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute UserForm userForm) {
       jdbcTemplate.update("INSERT INTO users (name) values (?)", userForm.getName());
       return "redirect:/sample";
    }

    @RequestMapping(path = "/sample/{id}", method = RequestMethod.DELETE)
    public String destory(Model model, @PathVariable("id") int id) {
        jdbcTemplate.update("delete from users where id = ? ", id);
        return "redirect:/sample";
    }

}