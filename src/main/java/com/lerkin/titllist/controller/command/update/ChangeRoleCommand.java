package com.lerkin.titllist.controller.command.update;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.role.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleCommand implements Command {

    private final RoleService roleService = ServiceFactory.getRoleService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String roleName = req.getParameter("role");
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        User user = new User();
        Role role = Role.valueOf(roleName);
        user.setRole(role);
        user.setId(userId);
        roleService.changeRole(user);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK);
        ResponseUtil.sendResponse(resp, responseEntity);
    }


}
