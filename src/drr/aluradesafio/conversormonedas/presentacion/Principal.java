package drr.aluradesafio.conversormonedas.presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import drr.aluradesafio.conversormonedas.dominio.Opcion;


import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        boolean salir = false;
        while (!salir){
            try{
                salir = construirMenu();
            } catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }

    }

    private static boolean construirMenu(){
        Scanner consola = new Scanner(System.in);
        boolean salir = false;
        try{
            ClassLoader classLoader = Principal.class.getClassLoader();
            InputStreamReader lectura = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream("drr/aluradesafio/conversormonedas/recursos/opciones.json")));

            Gson gson = new Gson();
            Type menuLista = new TypeToken<List<Opcion>>() {}.getType();
            List<Opcion> opciones = gson.fromJson(lectura, menuLista);
            lectura.close();

            System.out.println("***********************************************************");
            System.out.println("*** Bienvenido(a) al Conversor de Monedas ***");

            for (Opcion opcion : opciones) {
                System.out.println(opcion);
            }

            System.out.println("***********************************************************");
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
                    System.out.println("Moneda base: " + opcionSeleccionada.getBase());
                    System.out.println("Moneda cambio: " + opcionSeleccionada.getCambio());
                }
            } else {
                System.out.println("Opción inválida");
            }


        } catch (Exception e) {
            System.out.println("Error al construir menu: " + e.getMessage());
        }

        return salir;
    }
}
