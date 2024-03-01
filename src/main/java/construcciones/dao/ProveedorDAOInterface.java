package construcciones.dao;

import construcciones.entidades.Material;
import construcciones.entidades.Proveedor;

import java.util.List;

public interface ProveedorDAOInterface {
    List<Proveedor> todos();
    Proveedor buscarPorId(Long id);
    Proveedor create(Proveedor proveedor);
    Proveedor update(Proveedor proveedor);
    boolean delete(Long id);
    List<Material> obtenerMaterialesProveedor(Long idProveedor);
    boolean agregarMaterialAProveedor(Long idProveedor, Long idMaterial);
    boolean eliminarMaterialAProveedor(Long idProveedor, Long idMaterial);
}
