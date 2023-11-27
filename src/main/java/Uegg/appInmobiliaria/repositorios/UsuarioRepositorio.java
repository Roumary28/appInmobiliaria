package Uegg.appInmobiliaria.repositorios;

import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.enums.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gimenez Victor
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{

    @Query("SELECT u FROM Usuario u WHERE u.dni = :dni ")
    public Usuario buscarPorDni(@Param("dni") Long dni);
    
    @Query("SELECT u FROM Usuario u WHERE u.idTributario = :idTributario ")
    public Usuario buscarPorIdTributario(@Param("idTributario") Long idTributario);
    
    @Query("SELECT u FROM Usuario u WHERE u.email =  :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    @Query("SELECT u FROM UsuariO u WHERE u.rol = :rol")
    public List<Usuario> buscarPorRol(Rol rol);
    
}
