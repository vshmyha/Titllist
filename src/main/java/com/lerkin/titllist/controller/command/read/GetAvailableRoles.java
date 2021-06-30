package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.entity_db.Role;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.role.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAvailableRoles implements Command {

    private final RoleService roleService = ServiceFactory.getRoleService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        List<Role> roles = roleService.getAvailableRoles(role);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, roles);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
