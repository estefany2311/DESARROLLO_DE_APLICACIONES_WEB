package com.tecsup.reserva.services;

import com.tecsup.reserva.exception.RecursoNoEncontradoException;
import com.tecsup.reserva.exception.ServicioNoDisponibleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Duration;

// Consulta por REST a los otros microservicios para comprobar que los IDs existen
@Component
public class ReferenciasClient {

    @Value("${servicios.huesped.url}")
    private String huespedUrl;

    @Value("${servicios.habitacion.url}")
    private String habitacionUrl;

    private final RestClient restClient;

    public ReferenciasClient() {
        // Tiempos maximos para no dejar la solicitud esperando si un servicio no responde
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(2));
        factory.setReadTimeout(Duration.ofSeconds(5));
        this.restClient = RestClient.builder().requestFactory(factory).build();
    }

    public void verificarHuesped(Long id) {
        verificar(huespedUrl + "/api/huespedes/{id}", id,
                "No existe un huesped con id " + id, "huespedes");
    }

    public void verificarHabitacion(Long id) {
        verificar(habitacionUrl + "/api/habitaciones/{id}", id,
                "No existe una habitacion con id " + id, "habitaciones");
    }

    private void verificar(String url, Long id, String mensajeNoExiste, String servicio) {
        try {
            restClient.get().uri(url, id).retrieve().toBodilessEntity();
        } catch (HttpClientErrorException.NotFound ex) {
            // El servicio respondio 404: el ID no existe
            throw new RecursoNoEncontradoException(mensajeNoExiste);
        } catch (RestClientException ex) {
            // El servicio esta apagado, tarda demasiado o respondio con otro error
            throw new ServicioNoDisponibleException(
                    "No se pudo consultar el servicio de " + servicio);
        }
    }
}
