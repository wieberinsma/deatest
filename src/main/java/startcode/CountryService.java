import java.util.*;

public class CountryService {

    Map<String, String> countries = new HashMap();

    public CountryService() {
        for (var isoCountry : Locale.getISOCountries()) {
            var obj = new Locale("", isoCountry);
            countries.put(obj.getCountry(), obj.getDisplayCountry(Locale.UK));
        }
    }

    /**
     * List all countries that are available. The countries will be presented in their English (UK) name.
     *
     * @return a {@link Collection} containing the names of all countries in {@link Locale#UK}
     */
    public Collection<String> listAllCountries() {
        return countries.values();
    }

    /**
     * Return the English (UK) name for the given countryCode. If there is no country available for
     * the give country code, this method will return null.
     *
     * @param countryCode the country/region code as an uppercase ISO 3166 2-letter code
     * @return the English (UK) name of the country or null if none is available
     */
    public String getCountryForCountryCode(final String countryCode) {
        return countries.get(countryCode);
    }

    /**
     * Return a {@link List<String>} of all countries that start with the given start sequence. The check should
     * be case-insensitive
     * <p>
     * For instance, calling this method in the following way: {@code listAllCountriesThatStartWith("ne");},
     * should return a List that contains the countries: Netherlands, Nepal, New Zealand and New Caledonia.
     *
     * @param startSequence The start sequence which will be applied case-insensitive.
     * @return A {@link List} that contains only those countries that start with the same sequence as
     * {@code startSequence}
     */
    public List<String> listAllCountriesThatStartWith(final String startSequence) {
        // Implement me for 1h
        return new ArrayList<>();
    }
}

