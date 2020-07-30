package btb.mp3.bestofthebet.controller.authentication;

import btb.mp3.bestofthebet.exception.ResourceNotFoundException;
import btb.mp3.bestofthebet.model.EnumRole;
import btb.mp3.bestofthebet.model.Role;
import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.model.request.LoginRequest;
import btb.mp3.bestofthebet.model.request.SignupRequest;
import btb.mp3.bestofthebet.model.response.JwtResponse;
import btb.mp3.bestofthebet.model.response.MessageResponse;
import btb.mp3.bestofthebet.repository.RoleRepository;
import btb.mp3.bestofthebet.repository.UserRepository;
import btb.mp3.bestofthebet.service.role.IRoleService;
import btb.mp3.bestofthebet.service.security.jwt.JwtUtils;
import btb.mp3.bestofthebet.service.security.userInformation.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private IRoleService roleService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        String nameRole = roles.get(0);

        if (nameRole.equals("ROLE_ADMIN") || !userDetails.isStatus()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Access Denied, try again in another Server Client !!!"));
        }

        EnumRole role = null;
        EnumRole enumRole[] = EnumRole.values();

        for (EnumRole r : enumRole) {
            if (nameRole.equals(r.toString())) {
                role = r;
            }
        }

        Role resultRole = roleService.findByName(role)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        List<Role> roleList = new ArrayList<>();
        roleList.add(resultRole);

        return ResponseEntity.ok(new JwtResponse(jwt,
                                                                    userDetails.getId(),
                                                                    userDetails.getUsername(),
                                                                    userDetails.getFullName(),
                                                                    userDetails.getEmail(),
                                                                    userDetails.getPhoneNumber(),
                                                                    userDetails.isStatus(),
                                                                    userDetails.getBirthday(),
                                                                    userDetails.getCreateDate(),
                                                                    roleList));
    }

    @PostMapping("/admin/signin")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest,
                                                                HttpServletRequest request) throws Exception {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authToken);

        String origin = request.getHeader("Origin");

        System.out.println(request.getHeader("Origin"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        String nameRole = roles.get(0);

        if (!nameRole.equals("ROLE_ADMIN")) {
            return ResponseEntity.badRequest().body(new MessageResponse("Access Denied, try again in another Server Client !!!"));
        }

        EnumRole role = null;
        EnumRole enumRole[] = EnumRole.values();

        for (EnumRole r : enumRole) {
            if (nameRole.equals(r.toString())) {
                role = r;
            }
        }

        Role resultRole = roleService.findByName(role)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        List<Role> roleList = new ArrayList<>();
        roleList.add(resultRole);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFullName(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),
                userDetails.isStatus(),
                userDetails.getBirthday(),
                userDetails.getCreateDate(),
                roleList));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Please recheck your information"));
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Not Allow: Username is already taken !!!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Not Allow: Email is already in use !!!"));
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setFull_name(signUpRequest.getFull_name());
        user.setPhone_number(signUpRequest.getPhone_number());
        user.setEmail(signUpRequest.getEmail());
        user.setStatus(true);
        user.setBirthday(signUpRequest.getBirthday());
        user.setCreateDate(new Timestamp(new Date().getTime()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully !!!"));
    }
}

