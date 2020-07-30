package btb.mp3.bestofthebet.service.user;

import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    User finByID(Long id);

    List<Object> findTop7ByCreateDate();

    List<Object> findByMonth();
}
