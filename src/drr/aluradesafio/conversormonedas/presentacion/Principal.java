package drr.aluradesafio.conversormonedas.presentacion;

import io.github.cdimascio.dotenv.Dotenv;

public class Principal {
    public static void main(String[] args) {
        boolean salir = false;
        Dotenv de = Dotenv.load();
        String apiKey = de.get("API_KEY");
        Menu menu = new Menu();

        while (!salir){
            try{
                salir = menu.construirMenu(apiKey);
            } catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }
    }
}
