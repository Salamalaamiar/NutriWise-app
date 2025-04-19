package ma.ac.esi.nutriWise.service;

import ma.ac.esi.nutriWise.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean findUserByCredentials(String login, String password) {
        
        return userRepository.userExists(login, password);
    }
}
