package com.ar.sgi.service;

        import com.ar.sgi.exceptions.PersistanceLayerException;
        import com.ar.sgi.exceptions.ServiceLayerException;
        import com.ar.sgi.model.Localidad;
        import com.ar.sgi.model.Paginator;
        import com.ar.sgi.model.Provincia;
        import com.ar.sgi.repository.LocalidadRepository;
        import com.ar.sgi.repository.ProvinciaRepository;

        import javax.enterprise.context.ApplicationScoped;
        import javax.inject.Inject;
        import javax.transaction.Transactional;
        import java.util.List;
        import java.util.logging.Level;
        import java.util.logging.Logger;

@ApplicationScoped
public class ProvinciaService {

    private static Logger LOGGER = Logger.getLogger(ProveedorService.class.getName());

    @Inject
    ProvinciaRepository provinciaRepository;

    public Paginator<Provincia>listarTodo(int pagina, int items)throws Exception {
        try {
            return new Paginator<>(provinciaRepository.listar(pagina, items), provinciaRepository.count());
        }catch (PersistanceLayerException pe){
            throw pe;
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_PROVINCIA_EXCEPTION);
        }
    }

    public List<Provincia> listar() throws ServiceLayerException {
        try {
            return provinciaRepository.listAll();
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_PROVINCIA_EXCEPTION);
        }
    }

    public Provincia getById(Long id) throws ServiceLayerException {
        try {
            return provinciaRepository.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_PROVINCIA_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Provincia provincia) throws ServiceLayerException {
        try {
            provincia.setId(null);
            provinciaRepository.persist(provincia);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_PROVINCIA_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Provincia provincia) throws ServiceLayerException {
        try {
            provinciaRepository.getEntityManager().merge(provincia);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PROVINCIA_EXCEPTION);
        }
    }
}
