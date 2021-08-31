import java.util.*;

public class CountryGameService {

    Map<String, String> countries = new HashMap();
    Map<String, String> remainingCountries = new HashMap<>();

    public CountryGameService() {
        for (var isoCountry : Locale.getISOCountries()) {
            var obj = new Locale("", isoCountry);
            countries.put(obj.getCountry(), obj.getDisplayCountry(Locale.UK));
        }

        // Create the Map of countries that can be used for the game
        countries.forEach((countryCode, countryName) -> remainingCountries.put(countryCode, countryName));
    }

    /**
     * Draw a Country Code from the full Set of Country Codes. Country Codes are picked randomly and removed and cannot
     * be picked twice.
     *
     * @return A randomly picked  country/region code as an uppercase ISO 3166 2-letter code
     */
    public String drawCountryCode() {
        // Acquire a Set of all keys (countryCodes)
        var countryCodeSet = remainingCountries.keySet();

        // Pick a random countryCode from the Set
        var randomCountryCode = countryCodeSet.stream().skip(new Random().nextInt(countryCodeSet.size())).findFirst().orElse(null);

        // Remove the picked entry from the Set of remainingCountries
        remainingCountries.remove(randomCountryCode);

        // Return the randomly picked country code
        return randomCountryCode;
    }

    /**
     * Return whether there are still countries available for playing the Game.
     *
     * @return a {@code boolean} that states whether there are still countries available for playing the Game.
     */
    public boolean isStillCountriesAvailableForGame() {
        return remainingCountries.size() > 0;
    }

    /**
     * List all countries that are available. The countries will be presented in their English (UK) name.
     *
     * @return a {@link Collection} containing the names of all countries in {@link Locale#UK}
     */
    public Collection<String> listAllCountries() {
        return countries.values();
    }
}
