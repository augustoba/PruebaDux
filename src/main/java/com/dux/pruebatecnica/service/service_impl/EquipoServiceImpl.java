package com.dux.pruebatecnica.service.service_impl;

import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.model.Liga;
import com.dux.pruebatecnica.model.Pais;
import com.dux.pruebatecnica.repository.EquipoRepository;
import com.dux.pruebatecnica.repository.LigaRepository;
import com.dux.pruebatecnica.repository.PaisRepository;
import com.dux.pruebatecnica.service.EquipoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final LigaRepository ligaRepository;
    private final PaisRepository paisRepository;


    public EquipoServiceImpl(EquipoRepository equipoRepository, LigaRepository ligaRepository, PaisRepository paisRepository) {
        this.equipoRepository = equipoRepository;
        this.ligaRepository = ligaRepository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Optional<Equipo> findEquipoById(Integer equipoId){
        return equipoRepository.findById(equipoId);
    }

    @Override
    public Equipo crearEquipo(Equipo equipo){
        Liga liga=ligaRepository.findByNombre(equipo.getLiga().getNombre());
        Pais pais= paisRepository.findByNombre(equipo.getPais().getNombre());

        if (liga ==null){
            ligaRepository.save(equipo.getLiga());
        }
        if (pais == null){
            paisRepository.save(equipo.getPais());
        }
        equipo.setLiga(liga);
        equipo.setPais(pais);

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
