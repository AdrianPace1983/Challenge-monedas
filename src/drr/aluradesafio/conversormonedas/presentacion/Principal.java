package drr.aluradesafio.conversormonedas.presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import drr.aluradesafio.conversormonedas.dominio.Consultas;
import drr.aluradesafio.conversormonedas.dominio.Convertidor;
import drr.aluradesafio.conversormonedas.dominio.Opcion;
import drr.aluradesafio.conversormonedas.servicio.ConvertirMoneda;
import drr.aluradesafio.conversormonedas.servicio.IConvertirMoneda;
import io.github.cdimascio.dotenv.Dotenv;


import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        boolean salir = false;
        Dotenv de = Dotenv.load();
        String apiKey = de.get("API_KEY");

        while (!salir){
            try{
                salir = construirMenu(apiKey);
            } catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }
    }

    private static boolean construirMenu(String apiKey){
        Scanner consola = new Scanner(System.in);
        boolean salir = false;
        try{
            // Las opciones del menú serán cargadas desde un JSON.
            ClassLoader classLoader = Principal.class.getClassLoader();
            InputStreamReader lectura = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream("drr/aluradesafio/conversormonedas/recursos/opciones.json")));

            Gson gson = new Gson();
            Type menuLista = new TypeToken<List<Opcion>>() {}.getType();
            List<Opcion> opciones = gson.fromJson(lectura, menuLista);
            lectura.close();

            System.out.println("**************************************************************************");
            System.out.println("*** Bienvenido(a) al Conversor de Monedas ***");

            //Creación del menú
            for (Opcion opcion : opciones) {
                System.out.println(opcion);
            }

            System.out.println("**************************************************************************");
            System.out.println();
            System.out.print("Elija una opción válida: ");
            int opcionElegida = consola.nextInt();

            Opcion opcionSeleccionada = opciones.stream()
                    .filter(op -> op.getId() == opcionElegida)
                    .findFirst()
                    .orElse(null);

            if (opcionSeleccionada != null) {
                if (opcionSeleccionada.getId() == 9) {
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                } else {
                    System.out.println("Opción seleccionada: " + opcionSeleccionada.getDescripcion());
                    IConvertirMoneda convertirMoneda = new ConvertirMoneda();
                    ejecutarConversion(opcionSeleccionada, convertirMoneda, apiKey);
                }
            } else {
                System.out.println("Opción inválida");
            }
        } catch (Exception e) {
            System.out.println("Error al construir menu: " + e.getMessage());
        }
        return salir;
    }

    private static void ejecutarConversion(Opcion opcion, IConvertirMoneda convertirMoneda, String apiKey) {
        Scanner consola = new Scanner(System.in);

        System.out.print("Ingrese el valor que se va a convertir: ");
        try{
            var montoAConvertir = consola.nextDouble();
            Convertidor convertidor = convertirMoneda.convertirMoneda(opcion, montoAConvertir, apiKey);
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("El valor " + montoAConvertir + convertidor.toString());
            System.out.println("--------------------------------------------------------------------------");
            Consultas consultas = convertirMoneda.numeroConsultas(apiKey);
            System.out.println(">>>>>>>>>>" + consultas.toString());
            System.out.println();
        } catch (Exception e){
            System.out.println("Error al convertir moneda: " + e.getMessage());
        }
    }
}
