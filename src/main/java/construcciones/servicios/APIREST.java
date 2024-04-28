package construcciones.servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import construcciones.dao.*;
import construcciones.dto.ProyectoDTO;
import construcciones.entidades.*;
import spark.Spark;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class APIREST {

    private ProyectoDAOInterface daoProyecto;
    private ClienteDAOInterface daoCliente;
    private AvanceDAOInterface daoAvance;
    private MaterialDAOInterface daoMaterial;
    private AvanceMaterialDAOInterface daoAvanceMaterial;
    private ProveedorDAOInterface daoProveedor;
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public APIREST(ProyectoDAOInterface implementacion,
                   ClienteDAOInterface implementacionCliente,
                   AvanceDAOInterface implementacionAvance,
                   MaterialDAOInterface implementacionMaterial,
                   AvanceMaterialDAOInterface implementacionAvanceMaterial,
                   ProveedorDAOInterface implementacionProveedor) {
        Spark.port(8080);
        daoProyecto = implementacion;
        daoCliente = implementacionCliente;
        daoAvance = implementacionAvance;
        daoMaterial = implementacionMaterial;
        daoAvanceMaterial = implementacionAvanceMaterial;
        daoProveedor = implementacionProveedor;

        Spark.before((request, response) -> {
            response.type("application/json");
        });

//        ===================================================================================================
//        ============================================EXCEPTION==============================================
//        ===================================================================================================

        Spark.exception(Exception.class, (e, req, res) -> {
            e.printStackTrace(); // Imprime la excepción en la consola
            res.status(500); // Establece el código de estado HTTP 500
            res.body("Ha ocurrido un error, vuelve a intentarlo"); // Mensaje de error para el cliente
        });


//        ===================================================================================================
//        ============================================PROYECTOS==============================================
//        ===================================================================================================

//        Endpoint para obtener todos los proyectos
        Spark.get("/proyectos", (request, response) -> {
                List<Proyecto> proyectos = daoProyecto.devolverTodos();
                
                return gson.toJson(proyectos);
        });

//        Endpoint para buscar por nombre
        Spark.get("/proyectos/buscar/nombre/:nombre", (request, response) -> {
            String nombre = request.params(":nombre");
            List<Proyecto> proyectos = daoProyecto.buscarPorNombre(nombre);
            
            return gson.toJson(proyectos);
        });

//          Endpoint DTO Prueba
        Spark.get("/proyectos/dto", (request, response) -> {
            List<ProyectoDTO> proyectos = daoProyecto.devolverTodosDTO();

            return gson.toJson(proyectos);
        });

//        Endpoint para obtener el mayor presupuesto
        Spark.get("/proyectos/mayorpresupuesto", (request, response) -> {
            Proyecto mayorPresupuesto = daoProyecto.devolverMayorPresupuesto();
            
            return gson.toJson(mayorPresupuesto);
        });

//        Endpoint para obtener el mayor presupuesto
        Spark.get("/proyectos/menorpresupuesto", (request, response) -> {
            Proyecto menorPresupuesto = daoProyecto.devolverMenorPresupuesto();
            
            return gson.toJson(menorPresupuesto);
        });

        //        Endpoint para obtener los presupuestos entre dos cantidades
        Spark.get("/proyectos/buscar/presupuesto/:min/:max", (request, response) -> {
            Double min = Double.parseDouble(request.params(":min"));
            Double max = Double.parseDouble(request.params(":max"));
            List<Proyecto> proyectos = daoProyecto.devolverPresupuestoEntre(min, max);
            
            return gson.toJson(proyectos);
        });


//        Endpoint para sacar los proyectos de x cliente
        Spark.get("/proyectos/buscar/cliente/:nombre", (request, response) -> {
            String nombre = request.params(":nombre");
            List<Proyecto> proyectos = daoProyecto.devolverProyectosDeClienteLike(nombre);
            
            return gson.toJson(proyectos);
        });

//        Endpoint para sacar los proyectos con una fecha de inicio posterior a la dada
        Spark.get("/proyectos/buscar/fechaanterior/:fecha", (request, response) -> {
            try{
                String fechaStr = request.params(":fecha");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = dateFormat.parse(fechaStr);
                java.sql.Date fecha = new java.sql.Date(parsed.getTime());
                //Date fecha2 = ;

                List<Proyecto> proyectos = daoProyecto.iniciadosDespuesDe(fecha);
                
                return gson.toJson(proyectos);
            }catch (Exception e){
                e.printStackTrace();
                response.status(500);
                return "Error interno en el servidor: " + e.getMessage();
            }

        });

        //        Endpoint para sacar los proyectos con una fecha de inicio anterior a la dada
        Spark.get("/proyectos/buscar/fechaposterior/:fecha", (request, response) -> {
            try{
                String fechaStr = request.params(":fecha");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = dateFormat.parse(fechaStr);
                java.sql.Date fecha = new java.sql.Date(parsed.getTime());

                List<Proyecto> proyectos = daoProyecto.iniciadosAntesDe(fecha);
                
                return gson.toJson(proyectos);
            }catch (Exception e){
                e.printStackTrace();
                response.status(500);
                return "Error interno en el servidor: " + e.getMessage();
            }
        });

//        Endpoint para sacar todas las categorias
        Spark.get("/proyectos/buscar/categoria/:categorias", (request, response) -> {
            String categoriasParam = request.params(":categorias");
            List<String> categorias = Arrays.asList(categoriasParam.split(","));
            List<Proyecto> proyectos = daoProyecto.devolverTodosCategoria(categorias);
            
            return gson.toJson(proyectos);
        });

       // Endpoint para obtener el total de la suma de los presupuestos
        Spark.get("/proyectos/total/presupuestos", (request, response) -> {
            try{
                Double total = daoProyecto.totalProyectos();
                
                return gson.toJson(total);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        });

//        Endpoint para obtener un proyecto por su ID
        Spark.get("/proyectos/id/:id", (request, response) -> {
           Long id = Long.parseLong(request.params(":id"));
           Proyecto proyecto = daoProyecto.buscarPorId(id);
           
           if (proyecto != null){
               return gson.toJson(proyecto);
           } else {
               response.status(404);
               return "Proyecto no encontrado";
           }
        });

//        Endpoint para obtener la media del presupuesto de todos los proyectos
        Spark.get("/proyectos/media/presupuestos/proyectos", (request, response) -> {
            Double media = daoProyecto.mediaPresupuestos();
            
            return gson.toJson(media);
        });

//        Endpoint para obtener la media del presupuesto de todos los proyectos de una categoria
        Spark.get("/proyectos/media/presupuestos/categoria/:cat", (request, response) -> {
            String categoria = request.params(":cat");
            Double media = daoProyecto.mediaPresupuestoCategoria(categoria);
            
            return gson.toJson(media);
        });

//        Endpoint para obtener el numero de proyectos que tenemos
        Spark.get("proyectos/numero/proyectos", (request, response) -> {
            Long numero = daoProyecto.numeroProyectos();
            
            return gson.toJson(numero);
        });

        //        Endpoint para obtener el numero de categorias que tenemos
        Spark.get("proyectos/numero/categorias", (request, response) -> {
            Long numero = daoProyecto.numeroCategorias();
            
            return gson.toJson(numero);
        });

        Spark.get("/proyectos/avances/:idProyecto", (request, response) -> {
            Long id = Long.parseLong(request.params(":idProyecto"));
            List<Avance> avances = daoProyecto.obtenerAvancesDeProyecto(id);
            return gson.toJson(avances);
        });

//        ===================================================================================================
//        Endpoint para crear un nuevo proyecto
        Spark.post("/proyectos",(request, response) -> {
            String body = request.body();
            Proyecto nuevoProyecto = gson.fromJson(body, Proyecto.class);
            System.out.println(body);
            Proyecto creado = daoProyecto.create(nuevoProyecto);
            
            return gson.toJson(creado);
        });

//        // Endpoint crear proyecto con id cliente
//        Spark.post("/proyectos/idCliente", (request, response) ->{
//            String body = request.body();
//            System.out.println("-------------------------------------------------");
//            System.out.println("-------------------------------------------------");
//            System.out.println(body);
//            ProyectoDTO nuevoProyecto = gson.fromJson(body, ProyectoDTO.class);
//            Proyecto creado = daoProyecto.createByDTO(nuevoProyecto);
//
//            return gson.toJson(creado);
//        });

//        Endpoint para editar un proyecto por su ID
        Spark.put("/proyectos/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            String body = request.body();
            Proyecto proyectoActualizado = gson.fromJson(body, Proyecto.class);
            proyectoActualizado.setId(id);
            Proyecto actualizado = daoProyecto.update(proyectoActualizado);
            
            if (actualizado != null){
                return gson.toJson(actualizado);
            } else{
                response.status(404);
                return "Proyecto no encontrado";
            }
        });

//        Endpoint para eliminar un proyecto por su Id
        Spark.delete("/proyectos/:id",(request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            boolean eliminado = daoProyecto.deleteById(id);
            
            if(eliminado){
                return "Proyecto eliminado correctamente";
            }else{
                response.status(404);
                return "Proyecto no encontrado";
            }
        });

//        Endpoint para eliminar todos los proyectos (obviamente no se usaría)
        Spark.delete("/estoy/seguro/de/borrar/todos/los/proyectos", (request, response) -> {
            boolean eliminado = daoProyecto.deleteAll();
            
            if(eliminado){
                return "Proyectso eliminados correctamente";
            }else{
                response.status(404);
                return "Algo falló";
            }
        });

//        Devolver datos paginados de todos los proyectos
        Spark.get("/proyectos/paginado/:pagina/:tam_pagina", (request, response) -> {

            Integer pagina = Integer.parseInt(request.params(":pagina"));
            Integer tam_pagina = Integer.parseInt(request.params(":tam_pagina"));

            List<Proyecto> proyectos = daoProyecto.devolverTodos(pagina,tam_pagina);
            Long totalElementos = daoProyecto.numeroProyectos(); //Obtener el total de proyectos
            RespuestaPaginacion<Proyecto> paginaResultado = new RespuestaPaginacion<>(proyectos,totalElementos,pagina,tam_pagina);

            
            return gson.toJson(paginaResultado);

        });
//        ===================================================================================================
//        Método para listar proyectos con cierta condicion y devolver solo los que los cumplan
        Spark.get("/proyectos/listar/:condicion", (request, response) -> {
            String condicion = request.params(":condicion");
            List<ProyectoDTO> proyectos = daoProyecto.listarProyectosConCondicion(condicion);

            
            return gson.toJson(proyectos);
        });



//        ===================================================================================================
//        =============================================CLIENTES==============================================
//        ===================================================================================================

        Spark.get("/clientes", (request, response) -> {
            List<Cliente> todos = daoCliente.devolverTodos();

            return gson.toJson(todos);
        });

        Spark.get("/clientes/paginados/:pag/:tam", (request, response) -> {
            Integer pag = Integer.parseInt(request.params(":pag"));
            Integer tam = Integer.parseInt(request.params(":tam"));

            List<Cliente> clientes = daoCliente.devolverTodos(pag,tam);
            Long totalElementos = daoCliente.numeroClientes();
            RespuestaPaginacion<Cliente> paginaResultado = new RespuestaPaginacion<>(clientes,totalElementos,pag,tam);

            return gson.toJson(paginaResultado);
        });

        Spark.get("/clientes/num",(request, response) -> {
            Long num = daoCliente.numeroClientes();
            return gson.toJson(num);
        });

        Spark.get("/cliente/proyectos/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            List<Proyecto> proyectos = daoCliente.devolverProyectos(id);
            return gson.toJson(proyectos);
        });

        Spark.get("/cliente/buscar/id/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            Cliente cliente = daoCliente.buscarPorId(id);
            return gson.toJson(cliente);
        });

        Spark.get("/cliente/buscar/razon/:razon", (request, response) -> {
            String razon = request.params(":razon");
            List<Cliente> clientes = daoCliente.buscarPorRazon(razon);
            return gson.toJson(clientes);
        });

        Spark.get("/cliente/buscar/cif/:cif", (request, response) -> {
            List<Cliente> cliente = daoCliente.buscarPorCIF(request.params(":cif"));
            return gson.toJson(cliente);
        });

        Spark.get("/cliente/masproyectos", (request, response) -> {
            Cliente cliente = daoCliente.devolverMasProyectos();
            return gson.toJson(cliente);
        });

        Spark.get("/cliente/menosproyectos", (request, response) -> {
            Cliente cliente = daoCliente.devolverMenosProyectos();
            return gson.toJson(cliente);
        });


        Spark.post("/clientes",(request, response) -> {
            String body = request.body();
            Cliente nuevoCliente = gson.fromJson(body, Cliente.class);

            Cliente creado = daoCliente.create(nuevoCliente);

            return gson.toJson(creado);
        });

        Spark.put("/clientes/editar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            String body = request.body();
            Cliente clienteActualizado = gson.fromJson(body, Cliente.class);
            clienteActualizado.setId(id);
            Cliente actualizado = daoCliente.update(clienteActualizado);

            if (actualizado != null){
                return gson.toJson(actualizado);
            } else{
                response.status(404);
                return "Cliente no encontrado";
            }
        });

        Spark.delete("/clientes/borrar/:id",(request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            boolean eliminado = daoCliente.deleteById(id);

            if(eliminado){
                return "Cliente eliminado correctamente";
            }else{
                response.status(404);
                return "Cliente no encontrado";
            }
        });

//        ===================================================================================================
//        ==============================================AVANCES==============================================
//        ===================================================================================================

        Spark.get("/avances", (request, response) -> {
            List<Avance> todos = daoAvance.devolverTodos();
            return gson.toJson(todos);
        });

        Spark.get("/avances/buscar/id/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            Avance avance = daoAvance.buscarPorId(id);
            return gson.toJson(avance);
        });

        Spark.get("/avances/buscar/desc/:desc", (request, response) -> {
           List<Avance> avances = daoAvance.buscarPorDesc(request.params(":desc"));
           return gson.toJson(avances);
        });

        Spark.get("/avances/buscar/fecha/:fec", (request, response) -> {
            try{
                String fechaStr = request.params(":fec");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = dateFormat.parse(fechaStr);

                List<Avance> avances = daoAvance.buscarPorFechaPosterior(fecha);
                return gson.toJson(avances);
            }catch (Exception e){
                e.printStackTrace();
                return "Error interno...";
            }
        });

        Spark.get("/avances/buscar/porcentaje/:porc", (request, response) -> {
            int porcentaje = Integer.parseInt(request.params(":porc"));
            List<Avance> avances = daoAvance.buscarPorPorcentaje(porcentaje);
            return gson.toJson(avances);
        });

        Spark.get("/avances/materiales/:idAvance",(request, response) -> {
            Long id = Long.parseLong(request.params(":idAvance"));
            List<Material> materiales = daoAvance.obtenerMaterialesDeAvance(id);
            return gson.toJson(materiales);
        });

        Spark.post("/avances",(request, response) -> {
            String body = request.body();
            Avance nuevoAvance = gson.fromJson(body, Avance.class);
            Avance creado = daoAvance.create(nuevoAvance);
            return gson.toJson(creado);
        });

        Spark.put("/avances/editar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            String body = request.body();
            Avance avanceActualizado = gson.fromJson(body, Avance.class);
            avanceActualizado.setId(id);
            Avance actualizado = daoAvance.update(avanceActualizado);

            if(actualizado != null){
                return gson.toJson(actualizado);
            }else{
                response.status(404);
                return "Avance no encontrado";
            }
        });

        Spark.delete("/avances/borrar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            boolean eliminado = daoAvance.delete(id);

            if(eliminado){
                return "Avance eliminado correctamente";
            }else{
                response.status(404);
                return "Avance no encontrado";
            }
        });

//        ===================================================================================================
//        ===========================================MATERIALES==============================================
//        ===================================================================================================

        Spark.get("/materiales", (request, response) -> {
            List<Material> materiales = daoMaterial.devolverTodos();
            return gson.toJson(materiales);
        });

        Spark.get("/materiales/buscar/id/:id", (request, response) -> {
           Long id = Long.parseLong(request.params(":id"));
           Material material = daoMaterial.buscarPorId(id);
           return gson.toJson(material);
        });

        Spark.get("/materiales/cantidad/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            int cantidad = daoMaterial.cantidadDeID(id);
            return gson.toJson(cantidad);
        });

        Spark.get("/materiales/buscar/nombre/:nom", (request, response) -> {
            List<Material> materiales = daoMaterial.buscarPorNombre(request.params(":nom"));
            return gson.toJson(materiales);
        });

        Spark.get("/materiales/buscar/coste/:cos", (request, response) -> {
            BigDecimal coste = BigDecimal.valueOf(Long.parseLong(request.params(":cos")));
            List<Material> materiales = daoMaterial.buscarPorCosteSuperior(coste);
            return gson.toJson(materiales);
        });

        Spark.get("/materiales/buscar/cantidad/:can", (request, response) -> {
            int cantidad = Integer.parseInt(request.params(":can"));
            List<Material> materiales = daoMaterial.buscarPorCantidadSuperior(cantidad);
            return gson.toJson(materiales);
        });

        Spark.get("/materiales/proveedores/:idMat", (request, response) -> {
            Long id = Long.parseLong(request.params(":idMat"));
            List<Proveedor> proveedores = daoMaterial.proveedoresDeMaterial(id);
            return gson.toJson(proveedores);
        });

        Spark.get("/materiales/avances/:idMat",(request, response) -> {
            Long id = Long.parseLong(request.params(":idMat"));
            List<Avance> avances = daoMaterial.avancesDeMaterial(id);
            return gson.toJson(avances);
        });

        Spark.post("/materiales", (request, response) -> {
            String body = request.body();
            Material nuevoMaterial = gson.fromJson(body, Material.class);
            Material creado = daoMaterial.create(nuevoMaterial);
            return gson.toJson(creado);
        });

        Spark.put("/materiales/editar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            String body = request.body();
            Material materialActualizado = gson.fromJson(body, Material.class);
            materialActualizado.setId(id);
            Material actualizado = daoMaterial.update(materialActualizado);
            if(actualizado != null){
                return gson.toJson(actualizado);
            }else{
                response.status(404);
                return "Material no encontrado";
            }
        });

        Spark.delete("/materiales/borrar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            boolean eliminado = daoMaterial.delete(id);
            if(eliminado){
                return "Material eliminado correctamente";
            }else{
                response.status(404);
                return "Material no encontrado o usado en otra tabla";
            }
        });

//        ===================================================================================================
//        ======================================AVANCE MATERIALES============================================
//        ===================================================================================================
        Spark.get("/avancesmateriales", (request, response) -> {
            List<AvanceMaterial> avanceMateriales = daoAvanceMaterial.obtenerTodos();
            return gson.toJson(avanceMateriales);
        });

        Spark.get("/avancesmateriales/buscar/id/:id", (request, response) -> {
           Long id = Long.parseLong(request.params(":id"));
           AvanceMaterial avanceMaterial = daoAvanceMaterial.buscarPorId(id);
           return gson.toJson(avanceMaterial);
        });

        Spark.get("/avancesmateriales/buscar/material/:id",(request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            List<AvanceMaterial> avancesMateriales = daoAvanceMaterial.buscarPorMaterial(id);
            return gson.toJson(avancesMateriales);
        });

        Spark.get("/avancesmateriales/buscar/avance/:id",(request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            List<AvanceMaterial> avancesMateriales = daoAvanceMaterial.buscarPorAvance(id);
            return gson.toJson(avancesMateriales);
        });

        Spark.post("/avancesmaterial/crear/:idAvance/:idMaterial/:cantidad", (request, response) -> {
            Long idAvance = Long.parseLong(request.params(":idAvance"));
            Long idMaterial = Long.parseLong(request.params(":idMaterial"));
            Long cantidad = Long.parseLong(request.params(":cantidad"));
            AvanceMaterial nuevo = daoAvanceMaterial.create(idAvance, idMaterial, cantidad);
            return gson.toJson(nuevo);
        });

        Spark.put("/avancesmaterial/editar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            String body = request.body();
            AvanceMaterial avanceMaterialActualizado = gson.fromJson(body, AvanceMaterial.class);
            avanceMaterialActualizado.setId(id);
            AvanceMaterial actualizado = daoAvanceMaterial.update(avanceMaterialActualizado);
            if(actualizado != null){
                return gson.toJson(actualizado);
            }else{
                response.status(404);
                return "Avance-Material no encontrado";
            }
        })  ;

        Spark.delete("/avancesmaterial/borrar/:id",(request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            boolean eliminado = daoAvanceMaterial.delete(id);
            if(eliminado){
                return "Avance-Material eliminado correctamente";
            }else{
                response.status(404);
                return "Avance-Material no encontrado o usado en otra tabla";
            }
        });

//        ===================================================================================================
//        =============================================PROVEEDOR=============================================
//        ===================================================================================================

        Spark.get("/proveedores",(request, response) -> {
           List<Proveedor> todos = daoProveedor.todos();
           return gson.toJson(todos);
        });

        Spark.get("/proveedores/buscar/id/:id", (request, response) -> {
           Long id = Long.parseLong(request.params(":id"));
           Proveedor proveedor = daoProveedor.buscarPorId(id);
           return gson.toJson(proveedor);
        });

        Spark.get("/proveedores/materiales/:idProv", (request, response) -> {
            Long id = Long.parseLong(request.params(":idProv"));
            List<Material> materiales = daoProveedor.obtenerMaterialesProveedor(id);
            return gson.toJson(materiales);
        });

        Spark.put("/proveedores/materiales/:idProv/:idMat", (request, response) -> {
           Long idProv = Long.parseLong(request.params("idProv"));
           Long idMat = Long.parseLong(request.params("idMat"));
           boolean estado = daoProveedor.agregarMaterialAProveedor(idProv, idMat);
           if(estado){
               return "Material agregado correctamente";
           }else{
               return "Material no agregado, revise su petición";
           }
        });

        Spark.delete("/proveedores/materiales/:idProv/:idMat", (request, response) -> {
            Long idProv = Long.parseLong(request.params("idProv"));
            Long idMat = Long.parseLong(request.params("idMat"));
            boolean estado = daoProveedor.eliminarMaterialAProveedor(idProv,idMat);
            if(estado){
                return "Material eliminado correctamente";
            }else{
                return "Material no eliminado, revise su petición";
            }
        });

        Spark.post("/proveedores",(request, response) -> {
            String body = request.body();
            Proveedor nuevoProveedor = gson.fromJson(body, Proveedor.class);
            Proveedor creado = daoProveedor.create(nuevoProveedor);
            return gson.toJson(creado);
        });

        Spark.put("/proveedores/editar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            String body = request.body();
            Proveedor proveedorActualizad = gson.fromJson(body, Proveedor.class);
            proveedorActualizad.setId(id);
           Proveedor actualizado = daoProveedor.update(proveedorActualizad);
           if(actualizado != null){
               return gson.toJson(actualizado);
           }else{
               response.status(404);
               return "Proveedor no encontrado";
           }
        });

        Spark.delete("/proveedores/borrar/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            boolean eliminado = daoProveedor.delete(id);
            if(eliminado){
                return "Proveedor eliminado correctamente";
            }else{
                response.status(404);
                return "Proveedor no encontrado o usado en otra tabla";
            }
        });


//        ===================================================================================================
        //En caso de intentar un endpoint incorrecto
        Spark.notFound((request, response) -> {
            
            return "{\"error\": \"Ruta no encontrada\",\"hint1\": \"/proyectos\"," +
                    "\"hint2\": \"/clientes\",\"hint3\": \"/avances\"," +
                    "\"hint4\": \"/materiales\",\"hint5\": \"/proveedores\"}";
        });
    }
}
