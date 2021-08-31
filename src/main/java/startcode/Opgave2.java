public class Opgave2 {

    public static void main(String[] args) {
        System.out.println("Welkom bij Opgave 2 -----------");

        var countryGameService = new CountryGameService();

        var player = new CountryGamePlayer(countryGameService);
        player.playGame();
    }
}
