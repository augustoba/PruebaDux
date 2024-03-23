package com.dux.pruebatecnica.service.service_impl;

import com.dux.pruebatecnica.dto.mapper.EquipoMapper;
import com.dux.pruebatecnica.dto.request.EquipoRequestDTO;
import com.dux.pruebatecnica.dto.response.EquipoResponseDTO;
import com.dux.pruebatecnica.exception.EquipoExistenteException;
import com.dux.pruebatecnica.exception.EquipoNoEncontradoException;
import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.model.Liga;
import com.dux.pruebatecnica.model.Pais;
import com.dux.pruebatecnica.repository.EquipoRepository;
import com.dux.pruebatecnica.service.EquipoService;
import com.dux.pruebatecnica.service.LigaService;
import com.dux.pruebatecnica.service.PaisService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final LigaService ligaService;
    private final PaisService paisService;
    private final EquipoMapper equipoMapper;


    public EquipoServiceImpl(EquipoRepository equipoRepository, LigaService ligaService, PaisService paisService, EquipoMapper equipoMapper) {
        this.equipoRepository = equipoRepository;
        this.ligaService = ligaService;
        this.paisService = paisService;
        this.equipoMapper = equipoMapper;
    }

    @Override
    public Optional<Equipo> findEquipoById(Integer equipoId){
        return equipoRepository.findById(equipoId);
    }

    @Override
    @Transactional
    public EquipoResponseDTO crearEquipo(EquipoRequestDTO equipoRequestDTO){
        Equipo equipo = new Equipo();
        Liga liga=ligaService.buscarLigaPorNombre(equipoRequestDTO.getLiga());
        Pais pais= paisService.buscarPaisPorNombre(equipoRequestDTO.getPais());

        if (equipoRepository.findByNombre(equipoRequestDTO.getNombre()) ==null){
            equipo.setNombre(equipoRequestDTO.getNombre());
        }else{
            throw new EquipoExistenteException();
        }
        if (liga ==null) liga=ligaService.crearLiga(equipoRequestDTO.getLiga());
        equipo.setLiga(liga);
        if (pais == null) pais=paisService.crearPais(equipoRequestDTO.getPais());
        equipo.setPais(pais);
        equipoRepository.save(equipo);

        return equipoMapper.toEquipoResponseDTO(equipo);
    }

    @Override
    public List<EquipoResponseDTO> listarEquipos(){
        List<Equipo> equipos = equipoRepository.findAll();
        return equipoMapper.toEquipoResponseDTOList(equipos);
    }

    @Override
    public Equipo encontrarPorNombre(String nombre){
        return equipoRepository.findByNombre(nombre);
    }
    @Override
    public List<Equipo> equiposListaNombres(String nombre){
        return equipoRepository.findByNombreEquipoLigaPais(nombre);
    }
    @Override
    public void eliminarEquipo(Integer equipoId){
        Optional<Equipo> equipo = findEquipoById(equipoId);
        if (equipo.isPresent()){
            equipoRepository.delete(equipo.get());
        }else{
            throw  new EquipoNoEncontradoException();
        }
    }
@Override
    public EquipoResponseDTO actualizarEquipo(Integer idEquipo, EquipoRequestDTO equipoRequestDTO){
        Optional<Equipo> equipoObj = equipoRepository.findById(idEquipo);
        Pais pais =  paisService.buscarPaisPorNombre(equipoRequestDTO.getPais());
        Liga liga = ligaService.buscarLigaPorNombre(equipoRequestDTO.getLiga());

        if (equipoObj.isPresent()){
            Equipo equipoViejo = equipoObj.get();
            Equipo equipoRequest=equipoRepository.findByNombre(equipoRequestDTO.getNombre());

           if (equipoRequest==null){
               equipoViejo.setNombre(equipoRequestDTO.getNombre());
           }else {
               throw new EquipoExistenteException();
           }
           if (pais == null){
               equipoViejo.setPais(paisService.crearPais(equipoRequestDTO.getPais()));
           }else {
               equipoViejo.setPais(pais);
           }
           if (liga== null){
               equipoViejo.setLiga(ligaService.crearLiga(equipoRequestDTO.getLiga()));
           }else {
               equipoViejo.setLiga(liga);
           }
            equipoRepository.save(equipoViejo);
            return equipoMapper.toEquipoResponseDTO(equipoViejo);
        }else {
            throw  new EquipoNoEncontradoException();
        }

    }


}
