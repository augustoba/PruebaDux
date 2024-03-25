package com.dux.pruebatecnica.service_impl;

import com.dux.pruebatecnica.model.Pais;
import com.dux.pruebatecnica.repository.PaisRepository;
import com.dux.pruebatecnica.service.service_impl.PaisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class PaisServiceImplTest {

        private PaisServiceImpl paisService;

        @Mock
        private PaisRepository paisRepository;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            paisService = new PaisServiceImpl(paisRepository);
        }

        @Test
         void buscarPaisPorNombre() {
            String nombre = "Argentina";
            Pais mockPais = new Pais();
            mockPais.setNombre(nombre);

            when(paisRepository.findByNombre(nombre)).thenReturn(mockPais);

            Pais resultado = paisService.buscarPaisPorNombre(nombre);

            assertEquals(mockPais, resultado);
            verify(paisRepository, times(1)).findByNombre(nombre);
        }

        @Test
         void buscarPaisPorNombreNullSiNoExistePais() {
            String nombre = "Australia";

            when(paisRepository.findByNombre(nombre)).thenReturn(null);

            Pais resultado = paisService.buscarPaisPorNombre(nombre);

            assertEquals(null, resultado);
            verify(paisRepository, times(1)).findByNombre(nombre);
        }


        @Test
         void buscaryCrearPaisDevuelvePaisExistente() {
            String nombre = "Argentina";
            Pais mockPais = new Pais();
            mockPais.setNombre(nombre);

            when(paisRepository.findByNombre(nombre)).thenReturn(mockPais);

            Pais resultado = paisService.buscaryCrearPais(nombre);
            assertNotNull(resultado);
            assertEquals(mockPais, resultado);
            verify(paisRepository, times(1)).findByNombre(nombre);
            verify(paisRepository, never()).save(any());
        }

     @Test
     public void buscaryCrearPais() {
         String nombre = "Nueva Zelanda";

         Pais mockPais = new Pais();
         mockPais.setNombre(nombre);
         when(paisRepository.findByNombre(nombre)).thenReturn(mockPais);

         Pais resultado = paisService.buscaryCrearPais(nombre);

         assertEquals(nombre, resultado.getNombre());

         verify(paisRepository, times(1)).findByNombre(nombre);
         verify(paisRepository, never()).save(any()); //
     }
    }
