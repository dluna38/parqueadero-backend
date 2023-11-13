package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.controllers.custom.response.PageResponse;
import co.edu.iudigital.parqueadero.models.Estancia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstanciaRepository extends JpaRepository<Estancia,Long>, JpaSpecificationExecutor<Estancia> {

    Page<Estancia> findAllByVehiculo_Placa(String placa,Pageable pageable);

    Optional<Estancia> findEstanciaByVehiculo_IdAndFechaSalidaIsNull(Long id);
    Page<Estancia> findAllByVehiculo_Dueno_DocumentoIdentificacion(String documento,Pageable pageable);

    @Query("select e from Estancia e join FETCH e.vehiculo")
    Page<Estancia> findAllCustom(Specification<Estancia> specification, Pageable pageable);

    @Query("select e from Estancia e join FETCH e.vehiculo join fetch e.vehiculo.dueno join fetch e.vehiculo.marca join fetch e.vehiculo.tipoVehiculo")
    PageResponse<Estancia> findByIdDetail(Long id);
}
