package ru.mail.teamcity;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereInterceptor;
import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.BroadcastOnPostAtmosphereInterceptor;
import org.atmosphere.interceptor.HeartbeatInterceptor;
import org.atmosphere.interceptor.IdleResourceInterceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: g.chernyshev
 * Date: 19/04/15
 * Time: 23:57
 */
public class ChatController extends BaseController {
    private AtmosphereFramework atmosphereFramework;

    public ChatController(
            @NotNull SBuildServer server,
            @NotNull ChatUpdateHandler chatUpdateHandler,
            @NotNull WebControllerManager webControllerManager
            ) {
        super(server);
        this.atmosphereFramework = createAtmosphereFramework(chatUpdateHandler);
        webControllerManager.registerController("/chat.html", this);
    }

    @Nullable
    @Override
    protected ModelAndView doHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", Boolean.TRUE);
        atmosphereFramework.doCometSupport(AtmosphereRequest.wrap(httpServletRequest), AtmosphereResponse.wrap(httpServletResponse));
        return null;

    }

    private AtmosphereFramework createAtmosphereFramework(@NotNull ChatUpdateHandler updateHandler) {
        AtmosphereFramework atmosphereFramework = new AtmosphereFramework();

        List<AtmosphereInterceptor> interceptors = new ArrayList<AtmosphereInterceptor>();
        interceptors.add(new AtmosphereResourceLifecycleInterceptor());
        interceptors.add(new HeartbeatInterceptor());
        interceptors.add(new IdleResourceInterceptor());
        interceptors.add(new TrackMessageSizeInterceptor());
        interceptors.add(new BroadcastOnPostAtmosphereInterceptor());

        atmosphereFramework.addAtmosphereHandler("/", updateHandler, interceptors);

        atmosphereFramework.init();
        return atmosphereFramework;
    }
}
