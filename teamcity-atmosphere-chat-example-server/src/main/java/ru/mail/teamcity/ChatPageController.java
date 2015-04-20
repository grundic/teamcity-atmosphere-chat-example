package ru.mail.teamcity;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: g.chernyshev
 * Date: 20.04.15
 */
public class ChatPageController extends BaseController {

    private final String myJspPagePath;

    public ChatPageController(
            @NotNull WebControllerManager webControllerManager,
            @NotNull final PluginDescriptor pluginDescriptor
            ) {
        myJspPagePath = pluginDescriptor.getPluginResourcesPath("chat.jsp");
        webControllerManager.registerController("/chatPage.html", this);
    }

    @Nullable
    @Override
    protected ModelAndView doHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        final ModelAndView modelAndView = new ModelAndView(myJspPagePath);
        return modelAndView;
    }
}
