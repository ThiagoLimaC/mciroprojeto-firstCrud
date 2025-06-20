package com.firstcrud.usuarioapi.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.firstcrud.usuarioapi.model.Usuario;
import com.firstcrud.usuarioapi.model.exception.ResourceNotFoundException;

@Repository
public class UsuarioRepository {
    
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * @return lista de usuários
     */
    public List<Usuario> obterTodos() {
        return usuarios;
    }

    /**
     * Metodo para retornar um produto por id
     * @param id do produto
     * @return produto caso seja encontrado
     */
    public Optional<Usuario> obterPorId(Integer id){
        return usuarios
        .stream()
        .filter(usuario -> usuario.getId() == id)
        .findFirst();
    }

    /**
     * Metodo para cadastrar um usuario na lista
     * @param usuario usuario a ser cadastrado
     * @return lista com os usuários cadastrados no sistema
     */
    public List<Usuario> adicionar(Usuario usuario){

        ultimoId++;

        usuario.setId(ultimoId);
        usuarios.add(usuario);

        return usuarios;
    }

    /**
     * Metodo para deletar um usuario do sistema 
     * @param id do usuario a ser deletado
     * @return lista com usuários cadastrados no sistema
     */
    public List<Usuario> deletar(Integer id){
        usuarios.removeIf(usuario -> usuario.getId() == id);
        return usuarios;
    }

    /**
     * Metodo para atualizar um usuario na lista de usuarios
     * @param usuario novo usuário para atualizar na lista
     * @return usuário atualizado
     */
    public Usuario atualizar(Usuario usuario){

        // encontrar o usuario na lista
        Optional<Usuario> usuarioEncontrado = obterPorId(usuario.getId());

        if (usuarioEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        // remover o usuário antigo na lista
        deletar(usuario.getId());

        // adicionar o novo usuario atualizado na lista
        usuarios.add(usuario);

        return usuario;

    }
    
}
