package com.dux.pruebatecnica.service.service_imp;

import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.repository.EquipoRepository;
import com.dux.pruebatecnica.service.EquipoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }
    @Override
    public Optional<Equipo> findEquipoById(Integer equipoId){
        return equipoRepository.findById(equipoId);
    }

    @Override
    public Equipo crearEquipo(Equipo equipo){
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
