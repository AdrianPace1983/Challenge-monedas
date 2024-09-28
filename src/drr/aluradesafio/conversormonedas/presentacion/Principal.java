package drr.aluradesafio.conversormonedas.presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import drr.aluradesafio.conversormonedas.dominio.Consultas;
import drr.aluradesafio.conversormonedas.dominio.Convertidor;
import drr.aluradesafio.conversormonedas.dominio.Opcion;
import drr.aluradesafio.conversormonedas.servicio.ConvertirMoneda;
import drr.aluradesafio.conversormonedas.servicio.GenerarHistorial;
import drr.aluradesafio.conversormonedas.servicio.IConvertirMoneda;
import io.github.cdimascio.dotenv.Dotenv;


import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.InputMismatchException;
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
            // Llamamos al metodo que cargara las opciones desde un archivo JSON.
            List<Opcion> opciones = cargarOpciones();
            // Mostramos el mennu.
            mostrarMenu(opciones);

            System.out.print("Escriba una opción válida o 99 para salir: ");
            int opcionElegida = consola.nextInt();

            Opcion opcionSeleccionada = opciones.stream()
                    .filter(op -> op.getId() == opcionElegida)
                    .findFirst()
                    .orElse(null);

            if (opcionSeleccionada != null) {
                if (opcionSeleccionada.getId() == 99) {
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                } else if (opcionSeleccionada.getId() == 98) {
                    GenerarHistorial generarHistorial = new GenerarHistorial();
                    generarHistorial.mostrarHistorial();
                } else {
                    manejarSeleccion(opcionSeleccionada, apiKey);
                }
            } else {
                System.out.println("Opción inválida");
            }
        } catch (InputMismatchException ex){
            System.out.println("Opción inválida");
        } catch (Exception e) {
            System.out.println("Error al construir menu: " + e);
        }
        return salir;
    }

    private static List<Opcion> cargarOpciones() throws Exception {
        // Las opciones del menú serán cargadas desde un JSON.
        ClassLoader classLoader = Principal.class.getClassLoader();
        InputStreamReader lectura = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream("drr/aluradesafio/conversormonedas/recursos/opciones.json")));

        Gson gson = new Gson();
        Type menuLista = new TypeToken<List<Opcion>>() {}.getType();
        List<Opcion> opciones = gson.fromJson(lectura, menuLista);
        lectura.close();
        return opciones;
    }

    private static void mostrarMenu(List<Opcion> opciones){
        System.out.println("**************************************************************************");
        System.out.println("*** Bienvenido(a) al Conversor de Monedas ***");
        //Creación del menú
        for (Opcion opcion : opciones) {
            System.out.println(opcion);
        }
        System.out.println("**************************************************************************");
        System.out.println();
    }

    private static void manejarSeleccion(Opcion opcionSeleccionada, String apiKey){
        System.out.println("Opción seleccionada: " + opcionSeleccionada.getDescripcion());
        IConvertirMoneda convertirMoneda = new ConvertirMoneda();
        ejecutarConversion(opcionSeleccionada, convertirMoneda, apiKey);
    }

    private static void ejecutarConversion(Opcion opcion, IConvertirMoneda convertirMoneda, String apiKey) {
        Scanner consola = new Scanner(System.in);

        boolean salir = false;

        System.out.print("Ingrese el valor que se va a convertir: ");
        try{
            double montoAConvertir = consola.nextDouble();
            if (montoAConvertir <= 0) {
                System.out.println("El valor a convertir debe ser un número positivo.");
                return;
            }
            Convertidor convertidor = convertirMoneda.convertirMoneda(opcion, montoAConvertir, apiKey);
            GenerarHistorial generarHistorial = new GenerarHistorial();
            generarHistorial.generarHistorial(convertidor, montoAConvertir);
            Consultas consultas = convertirMoneda.numeroConsultas(apiKey);
            imprimirResultado(convertidor, montoAConvertir, consultas);
        } catch (InputMismatchException ex){
            System.out.println("Valor ingresado no es valido, por favor vuelva a intentar.");
        } catch (Exception e){
            System.out.println("Error al convertir moneda: " + e);
        }
    }

    private static void imprimirResultado(Convertidor convertidor, double montoAConvertir, Consultas consultas) throws Exception{
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("El valor " + montoAConvertir + convertidor);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(">>>>>>>>>>" + consultas);
        System.out.println();
        System.out.println("Presiona Enter para continuar...");
        System.in.read();
    }
}
