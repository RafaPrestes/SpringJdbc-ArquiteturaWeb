package com.example.aula03.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.aula03.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private static String INSERT = "insert into usuario(nome,email,senha) values(?,?,?)";
    private static String SELECT_ALL = "select * from tb_usuario";
    private static String DELETE =  "delete from usuario where id= ?";
    private static String UPDATE = "update usuario(nome,email,senha) values (?,?,?)";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Usuario inserir(Usuario usuario){
        jdbcTemplate.update(INSERT, new Object[]{usuario.getNome(), usuario.getEmail(), usuario.getSenha()});
        return usuario;
    }

    public int remover(int usuario) {
        jdbcTemplate.update(DELETE);
        return usuario;
    }

    public Usuario remover(Usuario usuario) {
        jdbcTemplate.update(DELETE);
        return usuario;
    }

    public Usuario update(Usuario usuario) {
        jdbcTemplate.update(UPDATE);
        return usuario;
    }

    public List<Usuario> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Usuario>(){

            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Usuario(
                    rs.getInt("id"), 
                    rs.getString("nome"), 
                    rs.getString("email"), 
                    rs.getString("senha")
                    );
            }

        });
    }
}
