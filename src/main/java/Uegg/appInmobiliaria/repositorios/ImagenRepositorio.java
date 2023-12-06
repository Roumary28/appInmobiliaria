package Uegg.appInmobiliaria.repositorios;

import Uegg.appInmobiliaria.entidades.Imagen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {
    
    @Query("SELECT i FROM Imagen i WHERE i.inmueble.id = :inmueble_id")
    public List<Imagen> buscarPorIdInmueble(@Param("inmueble_id") String inmueble_id);
    
}
