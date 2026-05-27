package cl.duoc.mineria.reportes.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.mineria.reportes.model.CicloResponseDTO;
import cl.duoc.mineria.reportes.model.OrdenMantencionResponseDTO;

@Service
public class MetricRetrievalService {

    private final WebClient webClient;

    public MetricRetrievalService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<CicloResponseDTO> obtenerCiclosPorTurno(Long turnoId) {
        CicloResponseDTO[] respuesta = this.webClient.get()
            .uri("http://localhost:8088/api/v1/ciclos/turno/" + turnoId)
            .retrieve()
            .bodyToMono(CicloResponseDTO[].class)
            .block();
        
        return respuesta != null ? Arrays.asList(respuesta) : List.of();
    }

    public List<OrdenMantencionResponseDTO> obtenerIncidentesTaller() {
        OrdenMantencionResponseDTO[] respuesta = this.webClient.get()
            .uri("http://localhost:8089/api/v1/mantenciones")
            .retrieve()
            .bodyToMono(OrdenMantencionResponseDTO[].class)
            .block();
        
        return respuesta != null ? Arrays.asList(respuesta) : List.of();
    }
}
