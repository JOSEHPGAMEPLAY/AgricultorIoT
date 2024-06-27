package iser.apiOrion.serviceImplement;

import iser.apiOrion.DTO.EstacionDTO;
import iser.apiOrion.DTO.UsuarioEstacionDTO;
import iser.apiOrion.collection.Estacion;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.collection.UsuarioEstacion;
import iser.apiOrion.repository.EstacionRepository;
import iser.apiOrion.repository.TipoCultivoRepository;
import iser.apiOrion.repository.UsuarioEstacionRepository;
import iser.apiOrion.repository.UsuarioRepository;
import iser.apiOrion.service.UsuarioEstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static iser.apiOrion.constant.messageConstant.buildMessage;

@Service
public class UsuarionEstacionServiceImpl implements UsuarioEstacionService {


    /**
     * Repositorio de usuario estacion
     */
    @Autowired
    UsuarioEstacionRepository usuarioEstacionRepository;

    /**
     * Repositorio de una estacion
     */
    @Autowired
    EstacionRepository estacionRepository;

    /**
     * Repositorio de tipo de cultivo
     */
    @Autowired
    TipoCultivoRepository tipoCultivoRepository;

    /**
     * Repositorio de usuario
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Metodo que permite obtener todos los hibernaderos de un usuario
     * @param id id del usuario
     * @return lista de hibernaderos
     */
    @Override
    public ResponseEntity<?> buscarHibernaderosUsuario(String id) {
        try {
            List<UsuarioEstacion> usuarioEstacions = usuarioEstacionRepository.findByIdUsuario(id);

            if (usuarioEstacions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<String> idHibernaderos = usuarioEstacions.stream().map(UsuarioEstacion::getIdEstacion).toList();

            List<Estacion> hibernaderos = estacionRepository.findAllById(idHibernaderos);

            if (hibernaderos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(hibernaderos);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite obtener todos los usuarios de una estacion
     * @param id id de la estacion
     * @return lista de usuarios
     */
    @Override
    public ResponseEntity<?> buscarPorId(String id) {
        try {
            Optional<UsuarioEstacion> usuarioEstacion = usuarioEstacionRepository.findById(id);
            if (usuarioEstacion.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            UsuarioEstacionDTO usuarioEstacionDTO = new UsuarioEstacionDTO();
            Estacion estacion = estacionRepository.findById(usuarioEstacion.get().getIdEstacion()).get();
            usuarioEstacionDTO.setId(usuarioEstacion.get().getId());
            usuarioEstacionDTO.setIdUsuario(usuarioEstacion.get().getIdUsuario());
            usuarioEstacionDTO.setIdEstacion(usuarioEstacion.get().getIdEstacion());
            usuarioEstacionDTO.setCiudad(estacion.getCiudad());
            usuarioEstacionDTO.setDepartamento(estacion.getDepartamento());
            usuarioEstacionDTO.setDetalles(estacion.getDetalles());
            usuarioEstacionDTO.setEncargado(estacion.getEncargado());
            usuarioEstacionDTO.setEstado(estacion.getEstado());
            usuarioEstacionDTO.setIdTipoCultivo(estacion.getIdTipoCultivo());
            usuarioEstacionDTO.setNombre(estacion.getNombre());
            usuarioEstacionDTO.setImagen(estacion.getImagen());
            usuarioEstacionDTO.setNombreTipoCultivo(tipoCultivoRepository.findById(estacion.getIdTipoCultivo()).get().getNombre());
            usuarioEstacionDTO.setNumero_Asociados(usuarioEstacionRepository.findByIdEstacion(estacion.getId()).size());
            return ResponseEntity.ok(usuarioEstacionDTO);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite crear un usuario estacion
     * @param idUsuario id del usuario
     * @param idEstacion id de la estacion
     * @return usuario estacion creado
     */
    @Override
    public ResponseEntity<?> crearUsuarioHibernadero(String idUsuario, String idEstacion) {
        try {
            UsuarioEstacion usuarioEstacion = new UsuarioEstacion();
            usuarioEstacion.setIdUsuario(idUsuario);
            usuarioEstacion.setIdEstacion(idEstacion);
            UsuarioEstacion usuarioEstacionGuardado = usuarioEstacionRepository.save(usuarioEstacion);
            return ResponseEntity.ok(usuarioEstacionGuardado);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite borrar un usuario estacion
     * @param id id del usuario estacion
     * @return mensaje de confirmacion
     */
    @Override
    public ResponseEntity<?> borrarUsuarioHibernadero(String id) {
        try {
            usuarioEstacionRepository.deleteById(id);
            return ResponseEntity.ok(buildMessage("Usuario estacion borrado"));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite obtener los hibernaderos de un usuario
     * @param idUsuario id del usuario
     * @return lista de hibernaderos
     */
    @Override
    public ResponseEntity<?> buscarPorUsuario(String idUsuario) {
        try {
            List<UsuarioEstacion> usuarioEstacions = usuarioEstacionRepository.findByIdUsuario(idUsuario);
            List<EstacionDTO> estaciones = new ArrayList<>();
            for (UsuarioEstacion usuarioEstacion : usuarioEstacions) {
                Optional<Estacion> estacionOptional = estacionRepository.findById(usuarioEstacion.getIdEstacion());
                if (estacionOptional.isEmpty()) {
                    continue;
                }
                Estacion estacion = estacionOptional.get();
                EstacionDTO estacionDTO = new EstacionDTO();
                estacionDTO.setId(estacion.getId());
                estacionDTO.setCiudad(estacion.getCiudad());
                estacionDTO.setDepartamento(estacion.getDepartamento());
                estacionDTO.setDetalles(estacion.getDetalles());
                estacionDTO.setEncargado(estacion.getEncargado());
                estacionDTO.setEstado(estacion.getEstado());
                estacionDTO.setIdTipoCultivo(estacion.getIdTipoCultivo());
                estacionDTO.setNombre(estacion.getNombre());
                estacionDTO.setImagen(estacion.getImagen());
                estacionDTO.setDescripcionTipoCultivo(tipoCultivoRepository.findById(estacion.getIdTipoCultivo()).get().getNombre());
                estacionDTO.setNumero_Asociados(usuarioEstacionRepository.findByIdEstacion(estacion.getId()).size());
                estaciones.add(estacionDTO);
            }

            return ResponseEntity.ok(usuarioEstacions);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite obtener los usuarios de una estacion
     * @param idEstacion id de la estacion
     * @return lista de usuarios
     */
    @Override
    public ResponseEntity<?> buscarPorHibernadero(String idEstacion) {
        try {
            List<UsuarioEstacion> usuarioEstacions = usuarioEstacionRepository.findByIdEstacion(idEstacion);
            return ResponseEntity.ok(usuarioEstacions);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite obtener los usuarios que no estan asociados a una estacion
     * @param idEstacion id de la estacion
     * @return lista de usuarios
     */
    @Override
    public ResponseEntity<?> buscarUsuarioSinInvernadero(String idEstacion) {
        try {
            List<Usuario> usuariosList = usuarioRepository.findAll();
            List<UsuarioEstacion> usuarioEstacions = usuarioEstacionRepository.findByIdEstacion(idEstacion);
            for (UsuarioEstacion usuarioEstacion : usuarioEstacions) {
                Optional<Usuario> usuario = usuarioRepository.findById(usuarioEstacion.getIdUsuario());
                if (usuario.isEmpty()) {
                    continue;
                }
                usuariosList.remove(usuario.get());
            }
            return ResponseEntity.ok(usuariosList);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
