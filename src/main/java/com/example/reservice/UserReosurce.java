package com.example.reservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserReosurce.USERS)
public class UserReosurce {
    public static final String USERS= "/api/v0/users";

    @Autowired
    UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok().body(userController.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
        return ResponseEntity.ok().body(userController.getUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> removeUser(@PathVariable int id){
        UserDTO u =userController.getUser(id);
        userController.removeUser(id);
        return ResponseEntity.ok().body(u);
    }



    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user){
        userController.newUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> replaceUser(@PathVariable int id, @RequestBody UserDTO user) {
        user.setId(id);
        userController.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody JsonPatch patch) {
        try {
            UserDTO user = userController.getUser(id);
            UserDTO userPatched = applyPatchToUser(patch, user);
            userController.updateUser(userPatched);
            return ResponseEntity.ok(userPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private UserDTO applyPatchToUser(JsonPatch patch, UserDTO targetUser) throws JsonPatchException, JsonProcessingException{
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, UserDTO.class);
    }

}
