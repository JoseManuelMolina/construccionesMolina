package construcciones.dao;

import construcciones.entidades.AvanceMaterial;

import java.util.List;

public interface AvanceMaterialDAOInterface {

    List<AvanceMaterial> obtenerTodos();
    AvanceMaterial buscarPorId(Long id);
    List<AvanceMaterial> buscarPorMaterial(Long id);
    List<AvanceMaterial> buscarPorAvance(Long id);

    AvanceMaterial create(Long avanceId, Long materialId, Long cantidad);
    AvanceMaterial update(AvanceMaterial avanceMaterial);
    boolean delete(Long id);

}
