package Uegg.appInmobiliaria.repositorios;

import Uegg.appInmobiliaria.entidades.Comentario;
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
public interface ComentarioRepositorio extends JpaRepository<Comentario, String> {

    @Query("SELECT c FROM Comentario c WHERE c.inmueble.id = id")
    public List<Comentario> buscarPorInmueble(@Param("id") String id);
    
    @Query("SELECT c FROM Comentario c WHERE c.usuario.id = id")
    public List<Comentario> buscarPorUsuario(@Param("id") String id);
}
