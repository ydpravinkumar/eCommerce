package com.example.supplychainback.Service;

import com.example.supplychainback.API.Model.LoginBody;
import com.example.supplychainback.API.Model.RegistrationBody;
import com.example.supplychainback.exception.useralreadyexists;
import com.example.supplychainback.model.Dao.LocalUserDao;
import com.example.supplychainback.model.LocalUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class manages all the user related events such as login and registration
 */
@Transactional
@Service
public class UserService {

    private JWTService jwtService;
    public UserService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    private EncryptionService encryptionService;
    public UserService(LocalUserDao localuserdao) {
        this.localuserdao = localuserdao;
    }

    private LocalUserDao localuserdao;

    @Autowired
    public UserService(JWTService jwtService, EncryptionService encryptionService, LocalUserDao localuserdao) {
        this.jwtService = jwtService;
        this.encryptionService = encryptionService;
        this.localuserdao = localuserdao;
    }

    public UserService() {
    }

    /**
     * When the user clicks submit post entering the details for registration
     * @param registrationBody The details that the user entered from front end
     * @return
     * @throws useralreadyexists if the user already exists fail the registration and throw an error
     */
    public LocalUser registerUser(RegistrationBody registrationBody) throws useralreadyexists {
        if(localuserdao.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() ||
                localuserdao.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new useralreadyexists();
        }
/**
 * Update the table with the details of the user
 */
        LocalUser localUser = new LocalUser();
        localUser.setEmail(registrationBody.getEmail());
        localUser.setFirsname(registrationBody.getFirstname());
        localUser.setLastname(registrationBody.getLastname());
        localUser.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        localUser.setUsername(registrationBody.getUsername());
        localUser.setAddress(registrationBody.getAddress());
        localuserdao.save(localUser);
        return localUser;
    }

    /**
     *
     * @param loginBody Take username and password as part of the login body
     * @return jwt token
     */

    public String loginUser(LoginBody loginBody){
        Optional<LocalUser> opuser = localuserdao.findByUsernameIgnoreCase(loginBody.getUsername());
        if(opuser.isPresent()){
        LocalUser user = opuser.get();
        if(encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){

            return jwtService.generateJWT(user) +"IDIDIDIDIDID"+ localuserdao.findIdByUsername(loginBody.getUsername());
        }
        }
        return null;
    }

}
