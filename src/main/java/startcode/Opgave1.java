public class Opgave1 {

    private CountryService countryService;

    public Opgave1() {
        countryService = new CountryService();
    }

    public static void main(String[] args) {
        System.out.println("Welkom bij Opgave 1 -----------");
        var opgave1 = new Opgave1();

        // Opgave 1b
        System.out.println("Opgave 1b ---------------------");
        opgave1.opgave1b();

        // Opgave 1d
        System.out.println("\nOpgave 1d ---------------------");
        opgave1.opgave1d();

        // Opgave 1e
        System.out.println("\nOpgave 1e ---------------------");
        opgave1.opgave1e();

        // Opgave 1g
        System.out.println("\nOpgave 1g ---------------------");
        opgave1.opgave1g();

        // Opgave 1h
        System.out.println("\nOpgave 1h ---------------------");
        opgave1.opgave1h();
    }

    public void opgave1b() {
        var countries = countryService.listAllCountries();

        countries.forEach(country -> System.out.println(country));
    }

    public void opgave1d() {
        System.out.println(countryService.getCountryForCountryCode("US"));
    }

    public void opgave1e() {
        System.out.println(countryService.getCountryForCountryCode("TEST"));
    }

    public void opgave1g() {
        System.out.println(countryService.getCountryForCountryCode("NL"));
    }

    public void opgave1h() {
        var countries = countryService.listAllCountriesThatStartWith("ne");

        countries.forEach(country -> System.out.println(country));
    }
}
