package btb.mp3.bestofthebet.service.role;

import btb.mp3.bestofthebet.model.EnumRole;
import btb.mp3.bestofthebet.model.Role;
import btb.mp3.bestofthebet.service.IService;

import java.util.Optional;

public interface IRoleService extends IService<Role> {

    public Optional<Role> findByName(EnumRole enumRole);
}
