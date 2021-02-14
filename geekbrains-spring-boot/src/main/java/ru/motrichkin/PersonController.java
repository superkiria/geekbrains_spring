package ru.motrichkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.motrichkin.persistence.Person;
import ru.motrichkin.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String allPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "persons";
    }

    @GetMapping("/form")
    public String formPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person_form";
    }

    @PostMapping("/form")
    public String newPerson(Person person) {
        personService.insert(person);
        return "redirect:/persons";
    }
}
