package com.dux.pruebatecnica.service.service_impl;

import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.model.Liga;
import com.dux.pruebatecnica.model.Pais;
import com.dux.pruebatecnica.repository.EquipoRepository;
import com.dux.pruebatecnica.repository.LigaRepository;
import com.dux.pruebatecnica.repository.PaisRepository;
import com.dux.pruebatecnica.service.EquipoService;
import com.dux.pruebatecnica.service.LigaService;
import com.dux.pruebatecnica.service.PaisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final LigaService ligaService;
    private final PaisService paisService;


    public EquipoServiceImpl(EquipoRepository equipoRepository, LigaService ligaService, PaisService paisService) {
        this.equipoRepository = equipoRepository;
        this.ligaService = ligaService;
        this.paisService = paisService;
    }

    @Override
    public Optional<Equipo> findEquipoById(Integer equipoId){
        return equipoRepository.findById(equipoId);
    }

    @Override
    public Equipo crearEquipo(Equipo equipo){
        Liga liga=ligaService.buscarLigaPorNombre(equipo.getLiga().getNombre());
        Pais pais= paisService.buscarPaisPorNombre(equipo.getPais().getNombre());

        if (liga ==null){
            equipo.setLiga(ligaService.crearLiga(equipo.getLiga().getNombre()));
        }
        if (pais == null){
            equipo.setPais(paisService.crearPais(equipo.getPais().getNombre()));
        }

        return equipoRepository.save(equipo);
    }

    @Override
    public List<Equipo> listarEquipos(){
        return equipoRepository.findAll();
    }

    @Override
    public Equipo encontrarPorNombre(String nombre){
        return equipoRepository.findByNombre(nombre);    }
}
