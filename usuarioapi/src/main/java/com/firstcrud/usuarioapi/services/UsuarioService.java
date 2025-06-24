package com.firstcrud.usuarioapi.services;

import java.lang.classfile.ClassFile.Option;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstcrud.usuarioapi.model.Usuario;
import com.firstcrud.usuarioapi.model.exception.ResourceNotFoundException;
import com.firstcrud.usuarioapi.repository.UsuarioRepository;
import com.firstcrud.usuarioapi.shared.UsuarioDTO;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return lista de usuários
     */
    public List<UsuarioDTO> obterTodos() {
        
        // Retorna uma lista de usuários
        List<Usuario> usuarios = usuarioRepository.findAll();

        // retorna uma lista de usuariodto 
        return usuarios.stream().
        map(usuario -> new ModelMapper().map(usuario, UsuarioDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Metodo para retornar um produto por id
     * @param id do produto
     * @return produto caso seja encontrado
     */
    public Optional<UsuarioDTO> obterPorId(Integer id){
        
        // Obtendo um optional de usuario por id
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        // Se não encontrar lança uma exception
        if (usuario.isEmpty()){
            throw new ResourceNotFoundException("Usuario com id : " + id + " não encontrado");
        }

        // Convertendo o meu optional de usuario em um usuarioDto
        UsuarioDTO dto = new ModelMapper().map(usuario, UsuarioDTO.class);

        // retornando um optional de usuario
        return Optional.of(dto);
    }

    /**
     * Metodo para cadastrar um usuario na lista
     * @param usuario usuario a ser cadastrado
     * @return lista com os usuários cadastrados no sistema
     */
    public UsuarioDTO adicionar(UsuarioDTO usuarioDto){

        usuarioDto.setId(null);

        
        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o usuarioDto em um usuario
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);

        
        usuarioRepository.save(usuario);
        
        // Recuperando o id e inserindo no dto para retornar 
        usuarioDto.setId(usuario.getId());

        // Retornar produto atualizado

        return usuarioDto;
    }

    /**
     * Metodo para deletar um usuario do sistema 
     * @param id do usuario a ser deletado
     * @return lista com usuários cadastrados no sistema
     */
    public void deletar(Integer id){
        
        
        // Verificar se o usuario realmente existe
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuario com id: " + id + " não foi encontrado");
        }

        usuarioRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar um usuario na lista de usuarios
     * @param usuario novo usuário para atualizar na lista
     * @return usuário atualizado
     */
    public UsuarioDTO atualizar(Integer id, UsuarioDTO usuarioDto){

        // Passar o id para o usuarioDto
        usuarioDto.setId(id);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter um DTO em um usuario
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);

        usuarioRepository.save(usuario);

        return usuarioDto;
    }
}
