//package btb.mp3.bestofthebet.service.security.playlist.Impl;
//
//import btb.mp3.bestofthebet.model.PlayList;
//import btb.mp3.bestofthebet.repository.playlist.PlaylistRepository;
//import btb.mp3.bestofthebet.service.security.playlist.PlaylistService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PlaylistServiceIpml implements PlaylistService {
//
//    @Autowired
//    private PlaylistRepository playlistRepository;
//
//    @Override
//    public Page<PlayList> findAll(Pageable pageable) {
//        return playlistRepository.findAll(pageable);
//    }
//
//    @Override
//    public Optional<PlayList> findById(Long id) {
//        return playlistRepository.findById(id);
//    }
//
//    @Override
//    public void save(PlayList playList) {
//        playlistRepository.save(playList);
//    }
//
//    @Override
//    public void remove(Long id) {
//        playlistRepository.deleteById(id);
//    }
//}
