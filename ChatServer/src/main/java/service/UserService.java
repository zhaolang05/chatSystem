package service;

import comm.entity.User;
import interface_adapter.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserService {
@Autowired
private DataService dataService;
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }
    public boolean isExistName(String name)
    {
        return true;
    }
    public void addUser(User user)
    {

    }

}
