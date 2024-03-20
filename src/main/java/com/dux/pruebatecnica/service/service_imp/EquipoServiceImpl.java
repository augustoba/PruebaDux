package com.dux.pruebatecnica.service.service_imp;

import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.repository.EquipoRepository;
import com.dux.pruebatecnica.service.EquipoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public Optional<Equipo> findEquipoById(Integer equipoId){

        return equipoRepository.findById(equipoId);
    }
}
