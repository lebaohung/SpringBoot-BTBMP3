package btb.mp3.bestofthebet.service.role;

import btb.mp3.bestofthebet.model.EnumRole;
import btb.mp3.bestofthebet.model.Role;
import btb.mp3.bestofthebet.repository.RoleRepository;
import btb.mp3.bestofthebet.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll(){ return null;}
    public Optional<Role> findById(Long id){return null;}
    public void save(Role model) {}
    public void delete(Long id){}

    public Optional<Role> findByName(EnumRole enumRole) {
        return roleRepository.findByName(enumRole);
    }
}
