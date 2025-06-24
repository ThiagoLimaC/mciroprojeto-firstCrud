package com.firstcrud.usuarioapi.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstcrud.usuarioapi.model.Usuario;
import com.firstcrud.usuarioapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return lista de usuários
     */
    public List<Usuario> obterTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Metodo para retornar um produto por id
     * @param id do produto
     * @return produto caso seja encontrado
     */
    public Optional<Usuario> obterPorId(Integer id){
        return usuarioRepository.findById(id);
    }

    /**
     * Metodo para cadastrar um usuario na lista
     * @param usuario usuario a ser cadastrado
     * @return lista com os usuários cadastrados no sistema
     */
    public List<Usuario> adicionar(Usuario usuario){

        return usuarioRepository.save(usuario);
    }

    /**
     * Metodo para deletar um usuario do sistema 
     * @param id do usuario a ser deletado
     * @return lista com usuários cadastrados no sistema
     */
    public List<Usuario> deletar(Integer id){
        return usuarioRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar um usuario na lista de usuarios
     * @param usuario novo usuário para atualizar na lista
     * @return usuário atualizado
     */
    public Usuario atualizar(Integer id, Usuario usuario){

        usuario.setId(id);

        return usuarioRepository.save(usuario);
    }
}
