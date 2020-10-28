package at.wifi.swdev.surf.service;

import at.wifi.swdev.surf.api.WeatherData;
import at.wifi.swdev.surf.domain.Data;
import at.wifi.swdev.surf.domain.DataRepository;
import at.wifi.swdev.surf.domain.GuestBookEntry;
import at.wifi.swdev.surf.domain.GuestBookEntryRepository;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Service
public class SurfService {

    @Autowired
    private GuestBookEntryRepository guestBookEntryRepository;
  
    @Autowired
    private DataRepository dataRepository;

    public List <GuestBookEntry> findAllEntries () {
        return this.guestBookEntryRepository.findAll ();
    }

    public GuestBookEntry findGuestBookEntryById (Integer id) {
        return this.guestBookEntryRepository.findGuestBookEntryById (id);
    }

    public List<GuestBookEntry> findGuestBookEntriesByUser (String user) {
        return this.guestBookEntryRepository.findGuestBookEntriesByUser (user);
    }

    public void deleteGuestBookEntryById (Integer id) {
        this.guestBookEntryRepository.deleteGuestBookEntryById (id);
    }

    public void save (GuestBookEntry newEntry) {
        this.guestBookEntryRepository.save (newEntry);
    }

    private static final Logger LOG = LoggerFactory.getLogger(SurfService.class);

//    @Autowired
//    private DataRepository dataRepository;

    private List<WeatherData> weatherData;

    @Value("${data.achensee.url}")
    private String achenseeUrl;

    @Value("${data.mattsee.url}")
    private String mattseeUrl;

    @Value("${data.neusiedlersee.url}")
    private String neusiedlerseeUrl;

    @Value("${data.traunsee.url}")
    private String traunseeUrl;

    public SurfService() {
        LOG.info("Leerkonstruktor SurfService");
    }

    public SurfService(String achenseeUrl, String mattseeUrl, String neusiedlerseeUrl, String traunseeUrl) {
        this.achenseeUrl = achenseeUrl;
        this.mattseeUrl = mattseeUrl;
        this.neusiedlerseeUrl = neusiedlerseeUrl;
        this.traunseeUrl = traunseeUrl;
    }

    @PostConstruct
    public void init() {
        getWeatherData();
    }

    public List<WeatherData> getWeatherData() {
        RestTemplate restTemplate = new RestTemplate();
        WeatherData achenseeData = restTemplate.getForObject(achenseeUrl, WeatherData.class);
        WeatherData mattseeData = restTemplate.getForObject(mattseeUrl, WeatherData.class);
        WeatherData neusiedlerseeData = restTemplate.getForObject(neusiedlerseeUrl, WeatherData.class);
        WeatherData traunseeData = restTemplate.getForObject(traunseeUrl, WeatherData.class);

        LOG.info(achenseeData.getLocation().toString());
 
        weatherData = new ArrayList<>();
        weatherData.add(achenseeData);
        weatherData.add(mattseeData);
        weatherData.add(neusiedlerseeData);
        weatherData.add(traunseeData);

        return weatherData;
    }

    public void setWeatherData(List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }

    public Data saveWeatherData(WeatherData weatherData) {

        Data data = new Data();
        data.setTemp_c(weatherData.getCurrent().getTemp_c());
        data.setIcon(weatherData.getCurrent().getCondition().getIcon());
        data.setWind_kph(weatherData.getCurrent().getWind_kph());
        data.setWind_dir(weatherData.getCurrent().getWind_dir());
        data.setUv(weatherData.getCurrent().getUv());
        data.setGust_kph(weatherData.getCurrent().getGust_kph());
        data.setName(weatherData.getLocation().getName());
        data.setRegion(weatherData.getLocation().getRegion());
        data.setCountry(weatherData.getLocation().getCountry());
        data.setLat(weatherData.getLocation().getLat());
        data.setLon(weatherData.getLocation().getLon());
        data.setTz_id(weatherData.getLocation().getTz_id());
        data.setLocaltime_epoch(weatherData.getLocation().getLocaltime_epoch());
        data.setLocal_time(weatherData.getLocation().getLocaltime());

        dataRepository.save(data);

        LOG.info("Check", data.toString());

        return data;
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void scheduleWeatherData() {

        RestTemplate restTemplate = new RestTemplate();
        WeatherData achenseeData = restTemplate.getForObject(achenseeUrl, WeatherData.class);
        WeatherData mattseeData = restTemplate.getForObject(mattseeUrl, WeatherData.class);
        WeatherData neusiedlerseeData = restTemplate.getForObject(neusiedlerseeUrl, WeatherData.class);
        WeatherData traunseeData = restTemplate.getForObject(traunseeUrl, WeatherData.class);
 
        saveWeatherData(achenseeData);
        saveWeatherData(mattseeData);
        saveWeatherData(neusiedlerseeData);
        saveWeatherData(traunseeData);
    }
}
