package iser.apiOrion.serviceImplement;


import iser.apiOrion.auth.serviceImpl.JwtTokenProvider;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.repository.UsuarioRepository;
import iser.apiOrion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<?> insertar(Usuario usuario) {
        try {
            String password = JwtTokenProvider.passwordEncoder(usuario.getClave());
            usuario.setClave(password);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> actualizar(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> eliminar(String id) {
        try {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> buscar(String id) {
        try {
            return ResponseEntity.ok().body(usuarioRepository.findById(id));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            return ResponseEntity.ok().body(usuarioRepository.findAll());
        } catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
