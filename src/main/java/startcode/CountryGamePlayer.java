import java.util.Random;

public class CountryGamePlayer {

    private CountryGameService countryGameService;

    public CountryGamePlayer(final CountryGameService countryGameService) {
        this.countryGameService = countryGameService;
    }

    public void playGame() {
        while (countryGameService.isStillCountriesAvailableForGame()) {
            // Draw a random Country Code
            var drawnCountryCode = countryGameService.drawCountryCode();

            // Try to guess which country belongs to this country code.
            System.out.println("(" + Thread.currentThread().getName() + ") I think country-code: " + drawnCountryCode + " belongs to: " + guessCountry());
        }
    }

    private String guessCountry() {
        return countryGameService.listAllCountries().stream().skip(new Random().nextInt(countryGameService.listAllCountries().size())).findFirst().orElse(null);
    }

}
