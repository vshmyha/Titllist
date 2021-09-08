package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.exception.SessionInvalidException;
import com.lerkin.titllist.exception.UserIsBlockedException;
import com.lerkin.titllist.service.authority.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UpdateAuthorityCommand implements Command {
    private final AuthorityService authorityService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (Objects.nonNull(session)) {
            User user = (User) session.getAttribute("user");
            if (Objects.nonNull(user)) {
                Role role = authorityService.userRole(user.getId());
                user.setRole(role);
                if (role.isBlocked()) {
                    throw new UserIsBlockedException();
                }
            } else {
                throw new SessionInvalidException();
            }
        } else {
            throw new SessionInvalidException();
        }
    }
}
