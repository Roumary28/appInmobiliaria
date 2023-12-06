
package Uegg.appInmobiliaria.repositorios;

import Uegg.appInmobiliaria.entidades.Oferta;
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
public interface OfertaRepositorio extends JpaRepository<Oferta, String>{
    
    //Buscar todas las ofertas hechas sobre un inmueble
    @Query("SELECT o FROM Oferta o WHERE o.inmueble.id =  :id")
    public List<Oferta> buscarPorInmueble(@Param("id") String id);
    
    //Buscar la oferta mayor hecha sobre un inmueble
    @Query("SELECT (o.usuarioCliente.denominacion), MAX(o.montoOferta) FROM Oferta o WHERE o.inmueble.id =  :id")
    public Oferta buscarOfertaMayor(@Param("id") String id);
    
    //Buscar todas las ofertas hechas por un cliente
    @Query("SELECT o FROM Oferta o WHERE o.usuarioCliente.id = :id")
    public List<Oferta> buscarPorCliente(@Param("id") String id);
    
    //Contar las ofertas echas sobre un inmueble
    @Query("SELECT COUNT(o.inmueble) FROM Oferta o WHERE o.inmueble.id = :id")
    public Oferta contarOfertasPorInmueble(@Param("id") String id);

}
