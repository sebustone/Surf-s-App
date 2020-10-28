package at.wifi.swdev.surf.controller;

import at.wifi.swdev.surf.api.WeatherData;
import at.wifi.swdev.surf.domain.Data;
import at.wifi.swdev.surf.domain.DataRepository;
import at.wifi.swdev.surf.domain.GuestBookEntry;
import at.wifi.swdev.surf.service.SurfService;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class WebController {
    
    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private SurfService surfService;

    private static final String ENTRIES_TEMPLATE_ID = "entries";
    private static final String NEW_ENTRY_TEMPLATE_ID = "newEntry";

    private static final String GUESTBOOK_FORM_HEADER_ID = "formHeader";

    private static final String GUESTBOOK_TEMPLATE = "guestbook";
    private static final String HOMEPAGE_REDIRECT = "redirect:/guestbook";

    @GetMapping("/guestbook")
    public String displayGuestBook(Model model) {

        model.addAttribute(GUESTBOOK_FORM_HEADER_ID, "Add a New Comment");
        model.addAttribute(ENTRIES_TEMPLATE_ID, this.surfService.findAllEntries());
        model.addAttribute(NEW_ENTRY_TEMPLATE_ID, new GuestBookEntry());

        return GUESTBOOK_TEMPLATE;
    }

    @PostMapping("/guestbook")
    public String addComment(Model model,
            @Valid @ModelAttribute(NEW_ENTRY_TEMPLATE_ID) GuestBookEntry newEntry,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            this.surfService.save(newEntry);
            return HOMEPAGE_REDIRECT;
        } else {
            model.addAttribute(GUESTBOOK_FORM_HEADER_ID, "Please Correct the Comment");
            model.addAttribute(ENTRIES_TEMPLATE_ID, this.surfService.findAllEntries());
            return GUESTBOOK_TEMPLATE;
        }
    }
    
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Integer id) {
        this.surfService.deleteGuestBookEntryById(id);

        return HOMEPAGE_REDIRECT;
    }

    @GetMapping("update/{id}")
    public String editComment(Model model, @PathVariable Integer id) {

        model.addAttribute(GUESTBOOK_FORM_HEADER_ID, "Please Change the Comment");
        model.addAttribute(ENTRIES_TEMPLATE_ID, this.surfService.findAllEntries());
        model.addAttribute(NEW_ENTRY_TEMPLATE_ID, this.surfService.findGuestBookEntryById(id));

        return GUESTBOOK_TEMPLATE;
    }

    @PostMapping("update/{id}")
    public String saveComment(Model model,
            @PathVariable Integer id,
            @Valid @ModelAttribute(NEW_ENTRY_TEMPLATE_ID) GuestBookEntry newEntry,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            GuestBookEntry current = this.surfService.findGuestBookEntryById(id);

            current.setUser(newEntry.getUser());
            current.setComment(newEntry.getComment());

            this.surfService.save(current);

            return HOMEPAGE_REDIRECT;
        } else {
            model.addAttribute(GUESTBOOK_FORM_HEADER_ID, "Please Correct the Comment");
            model.addAttribute(ENTRIES_TEMPLATE_ID, this.surfService.findAllEntries());
            return GUESTBOOK_TEMPLATE;
        }
    }

    @GetMapping({"/", "start"})
    public String start(Model model) {
        LOG.info("Startseite anzeigen");
        fillupSpots(model);
        return "start";
    }

    @GetMapping("/achensee")
    public String achensee(Model model) {
        LOG.info("Seeseite anzeigen");
        fillupLakes(model, "Achensee");
        return "achensee";
    }

    @GetMapping("/mattsee")
    public String mattsee(Model model) {
        LOG.info("Seeseite anzeigen");
        fillupLakes(model, "Mattsee");
        return "mattsee";
    }

    @GetMapping("/neusiedlersee")
    public String neusiedlersee(Model model) {
        LOG.info("Seeseite anzeigen");
        fillupLakes(model, "Podersdorf");
        return "neusiedlersee";
    }

    @GetMapping("/traunsee")
    public String traunsee(Model model) {
        LOG.info("Seeseite anzeigen");
        fillupLakes(model, "Ebensee");
        return "traunsee";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }

    public void fillupSpots(Model model) {

        LOG.info("Listenelemente anzeigen");

        List<WeatherData> weatherdata = surfService.getWeatherData();

        model.addAttribute("items", weatherdata);
    }

    public void fillupLakes(Model model, String selectedSpot) {

        List<Data> datas = dataRepository.findAll();

        Collections.reverse(datas);

        Predicate<Data> byName = data -> selectedSpot.equals(data.getName());

        List<Data> data = datas.stream().filter(byName).limit(24)
                .collect(Collectors.toList());

        int size = data.size();
        String[] local_time = new String[size];
        float[] temp_c = new float[size];
        float[] wind_kph = new float[size];
        float[] gust_kph = new float[size];
        for (int pos = 0; pos < data.size(); pos++) {

            local_time[pos] = data.get(pos).getLocal_time();
            temp_c[pos] = data.get(pos).getTemp_c();
            wind_kph[pos] = data.get(pos).getWind_kph();
            gust_kph[pos] = data.get(pos).getGust_kph();
        }

        model.addAttribute("graphLocal_time", local_time);
        model.addAttribute("graphTemp_c", temp_c);
        model.addAttribute("graphWind_kph", wind_kph);
        model.addAttribute("graphGust_kph", gust_kph);

        model.addAttribute("items", data);

        LOG.info("Datenelemente anzeigen");
        LOG.info(data.toString());
    }

}
