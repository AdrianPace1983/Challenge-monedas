package drr.aluradesafio.conversormonedas.servicio;

import drr.aluradesafio.conversormonedas.dominio.Convertidor;
import drr.aluradesafio.conversormonedas.dominio.Historial;

public interface IGenerarHistorial {
    public void generarHistorial(Convertidor convertidor, double montoCambio);
}
