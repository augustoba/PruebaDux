package com.dux.pruebatecnica.service_impl;

import com.dux.pruebatecnica.model.Liga;
import com.dux.pruebatecnica.repository.LigaRepository;
import com.dux.pruebatecnica.service.LigaService;
import com.dux.pruebatecnica.service.service_impl.LigaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LigaServiceImplTest {

    private final LigaRepository ligaRepository = Mockito.mock(LigaRepository.class);
    private final LigaService ligaService = new LigaServiceImpl(ligaRepository);

    @Test
     void buscarLigaPorNombredevuelveLigaExistente() {
        String nombre = "LigaTest";
        Liga mockLiga = new Liga();
        mockLiga.setNombre(nombre);

        when(ligaRepository.findByNombre(nombre)).thenReturn(mockLiga);

        Liga resultado = ligaService.buscarLigaPorNombre(nombre);

        assertNotNull(resultado);
        assertEquals(mockLiga, resultado);
        verify(ligaRepository, times(1)).findByNombre(nombre);
    }

    @Test
     void buscarLigaPorNombreNullSiNoExiste() {
        String nombre = "LigaNoExistente";

        when(ligaRepository.findByNombre(nombre)).thenReturn(null);

        Liga resultado = ligaService.buscarLigaPorNombre(nombre);

        assertNull(resultado);
        verify(ligaRepository, times(1)).findByNombre(nombre);
    }

    @Test
     void buscaryCrearLigaDevuelveLigaExistente() {
        String nombre = "LigaExistente";
        Liga mockLiga = new Liga();
        mockLiga.setNombre(nombre);

        when(ligaRepository.findByNombre(nombre)).thenReturn(mockLiga);

        Liga resultado = ligaService.buscaryCrearLiga(nombre);

        assertNotNull(resultado);
        assertEquals(mockLiga, resultado);
        verify(ligaRepository, times(1)).findByNombre(nombre);
        verify(ligaRepository, times(0)).save(any());
    }

    @Test
    void buscaryCrearLigaPersistirNuevaLiga() {
       String nombre = "Super Liga";

       Liga mockLiga = new Liga();
       mockLiga.setNombre(nombre);
       when(ligaRepository.findByNombre(nombre)).thenReturn(mockLiga);

       Liga resultado = ligaService.buscaryCrearLiga(nombre);

       assertEquals(nombre, resultado.getNombre());
       verify(ligaRepository, times(1)).findByNombre(nombre);
       verify(ligaRepository, never()).save(any());
    }


}