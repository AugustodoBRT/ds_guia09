package com.cefet.ds_guia09.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ds_guia09.dto.TipoDTO;
import com.cefet.ds_guia09.entities.Tipo;
import com.cefet.ds_guia09.repositories.TipoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    // buscar todos
    public List<TipoDTO> findAll() {
        List<Tipo> listaTipo = tipoRepository.findAll();
        return listaTipo.stream().map(TipoDTO::new).toList();
    }

    public TipoDTO findById(Long id) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo não encontrado com ID: " + id));
        return new TipoDTO(tipo);
    }

    public TipoDTO insert(TipoDTO tipoDTO) {
        Tipo tipo = new Tipo();
        tipo.setDescricao(tipoDTO.getDescricao());
        Tipo tipoSalvo = tipoRepository.save(tipo);
        return new TipoDTO(tipoSalvo);
    }

    public TipoDTO update(Long id, TipoDTO novoTipoDTO) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo não encontrado com ID: " + id));
        tipo.setDescricao(novoTipoDTO.getDescricao());
        Tipo atualizado = tipoRepository.save(tipo);
        return new TipoDTO(atualizado);
    }

    public void delete(Long id) {
        if (!tipoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tipo não encontrado com ID: " + id);
        }
        tipoRepository.deleteById(id);
    }

}
