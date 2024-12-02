package sg.edu.nus.iss.vttp5a_day16l.restservice;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.vttp5a_day16l.constant.Url;
import sg.edu.nus.iss.vttp5a_day16l.model.Country;

@Service
public class CountryRestService {
    
    RestTemplate restTemplate = new RestTemplate();

    public List<Country> getCountries(){
        
        String countryData = restTemplate.getForObject(Url.COUNTRYURL, String.class);

        JsonReader jReader = Json.createReader(new StringReader(countryData));
        JsonObject jObject = jReader.readObject();

        JsonObject jDataObject = jObject.getJsonObject("data");

        List<Country> countries = new ArrayList<>();

        Set<Entry<String, JsonValue>> entries = jDataObject.entrySet();

        for (Entry<String, JsonValue> entry : entries) {
            Country c = new Country();
            c.setCountryCode(entry.getKey());
            c.setCountryName(entry.getValue().asJsonObject().getString("country"));
            countries.add(c);
        }

        return countries;

    }

    /*
     *  .entrySet() returns a Set of Map.Entry<String, JsonValue> 
     *      String is the country code
     *      JsonValue is the associated JSON object
     * 
     *  {
     *  "status": "OK",
     *  "status-code": 200,
     *  "version": "1.0",
     *  "access": "public",
     *  "total": 249,
     *  "offset": 0,
     *  "limit": 100,
     *  "data": {
     *      "DZ": {
     *          "country": "Algeria",
     *          "region": "Africa"
     *      },
     *      "AO": {
     *          "country": "Angola",
     *          "region": "Africa"
     *      },
     *      "BJ": {
     *          "country": "Benin",
     *          "region": "Africa"
     *      },
     *  
     */
    
}
