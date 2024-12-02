package sg.edu.nus.iss.vttp5a_day16l.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.vttp5a_day16l.model.Country;
import sg.edu.nus.iss.vttp5a_day16l.restservice.CountryRestService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/countries")
public class CountryRestController {
    
    @Autowired
    CountryRestService countryRestService;

    @GetMapping()
    public ResponseEntity<List<Country>> countriesPageAPI() {
        
        List<Country> countryList = countryRestService.getCountries();

        return ResponseEntity.ok().body(countryList);
    }
    
}
