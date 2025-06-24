package com.firstcrud.usuarioapi.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstcrud.usuarioapi.model.Usuario;
import com.firstcrud.usuarioapi.services.UsuarioService;
import com.firstcrud.usuarioapi.shared.UsuarioDTO;
import com.firstcrud.usuarioapi.view.model.UsuarioRequest;
import com.firstcrud.usuarioapi.view.model.UsuarioResponse;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obterTodos(){
        
        List<UsuarioDTO> usuarios = usuarioService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<UsuarioResponse> resposta = usuarios.stream()
        .map(usuario -> mapper.map(usuario, UsuarioResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponse>> obterPorId(@PathVariable Integer id){
        
        Optional<UsuarioDTO> dto = usuarioService.obterPorId(id);

        UsuarioResponse usuario = new ModelMapper().map(dto, UsuarioResponse.class);

        return new ResponseEntity<>(Optional.of(usuario), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> adicionar(@RequestBody UsuarioRequest usuarioReq){
        
        ModelMapper mapper = new ModelMapper();

        UsuarioDTO usuarioDTO = mapper.map(usuarioReq, UsuarioDTO.class);

        usuarioDTO = usuarioService.adicionar(usuarioDTO);

        return new ResponseEntity<>(mapper.map(usuarioDTO, UsuarioResponse.class), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioReq){
        
        ModelMapper mapper = new ModelMapper();

        UsuarioDTO dto = mapper.map(usuarioReq, UsuarioDTO.class);

        dto = usuarioService.atualizar(id, dto);

        return new ResponseEntity<>(
            mapper.map(dto, UsuarioResponse.class),
            HttpStatus.OK
        );
    }

}
