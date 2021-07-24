package com.lerkin.titllist.rest_controller;

import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Navigation.ROLE)
public class RoleController {

    private final RoleService roleService;

    //TODO: js & t.d.
    @PutMapping(Navigation.CHANGE_ROLE)
    public ResponseEntity<?> changeRole(@RequestParam String role, @RequestParam Integer userId) {
        User user = new User();
        Role roleName = Role.valueOf(role);
        user.setRole(roleName);
        user.setId(userId);
        roleService.changeRole(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: returned type, js..
    @GetMapping
    public ResponseEntity<List<Role>> getRoles(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        List<Role> roles = new ArrayList<>();
        if (role.isSuperAdmin()) {
            roles = roleService.getRolesForSuperAdmin();
        } else if (!role.isSimple()) {
            roles = roleService.getRolesForAdmin();
        } else {
//            return "start_page";
        }
        return ResponseEntity.ok(roles);
    }
}
