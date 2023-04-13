package com.dz.OnlineBooking.Controller;

import com.dz.OnlineBooking.Model.User;
import com.dz.OnlineBooking.Repository.UserRepository;

import com.dz.OnlineBooking.result.Result;
import com.dz.OnlineBooking.util.DataValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Api(tags = "01-User", value = "UserApi")
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());
    DataValidation dataValidation = new DataValidation();

    @Autowired
    private UserRepository userRepository;


    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ApiOperation(value = "User register", notes = "User register")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestBody User user) {
        if(user.getUsername().isEmpty()||user.getPassword().isEmpty()||user.getEmail().isEmpty()){
            //validate user info
            return new Result("400", "Username, password and email cannot be empty");
        }
        logger.info("user info: " + userRepository.findByUsername(user.getUsername()));
        if(!userRepository.findByUsername(user.getUsername()).isEmpty()){
            return new Result("400", "Username already exists");
        }
        //validate email
        if(!userRepository.findByemail(user.getEmail()).isEmpty()){
            return new Result("400", "Email already exists");
        }
        //validate password
        if(!dataValidation.passwordValid(user.getPassword())){
            return new Result("400", "Password must be at least 6 characters long");
        }
        return new Result("200", "success", userRepository.save(user));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ApiOperation(value = "Find user by Id", notes = "Find user by Id")
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public Result find(@RequestBody String id){
        if(dataValidation.userIdValid(id)&&userRepository.findById(id).isPresent()){
            return new Result("200", "success",userRepository.findById(id));
        }
        return new Result("400", "Invalid user id", null);
    }
}
