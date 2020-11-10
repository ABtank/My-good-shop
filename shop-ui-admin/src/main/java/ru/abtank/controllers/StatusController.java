package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Brand;
import ru.abtank.representation.StatusRepr;
import ru.abtank.servise.BrandService;
import ru.abtank.servise.StatusService;

@Controller
@RequestMapping("/statuses")
public class StatusController {

    StatusService statusService;

    @Autowired
    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    @GetMapping
    public String statusesPage(Model model) {
        model.addAttribute("activePage", "Statuses");
        model.addAttribute("statuses", statusService.findAll());
        return "statuses";
    }

    @GetMapping("/{id}/update")
    public String updateStatus(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("activePage", "Statuses");
        model.addAttribute("update", true);
        model.addAttribute("status", statusService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), Brand.class.getSimpleName())));
        return "status_form";
    }

    @GetMapping("/create")
    public String statusCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Statuses");
        model.addAttribute("status", new StatusRepr());
        logger.info("create new StatusRepr");
        return "status_form";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteStatus(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("activePage", "Statuses");
        statusService.deleteById(id);
        return "redirect:/statuses";
    }

    @PostMapping("/save")
    public String saveStatus(Model model, StatusRepr status, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Statuses");
        logger.info("come to try");
        try {
            logger.info("try save");
            statusService.save(status);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating status", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (status.getId() == null) {
                return "redirect:/statuses/create";
            }
            return "redirect:/statuses/" + status.getId() + "/update";
        }
        return "redirect:/statuses";

    }

}
