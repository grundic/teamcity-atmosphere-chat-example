package ru.mail.teamcity;

import jetbrains.buildServer.web.openapi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * User: g.chernyshev
 * Date: 20/04/15
 * Time: 02:04
 */
public class ChatLinkInjector extends SimplePageExtension {

    private final PluginDescriptor pluginDescriptor;

    public ChatLinkInjector(@NotNull PagePlaces pagePlaces, @NotNull PluginDescriptor pluginDescriptor) {
        super(pagePlaces);
        setPlaceId(PlaceId.ALL_PAGES_HEADER);
        this.pluginDescriptor = pluginDescriptor;
        register();
    }

    @NotNull
    @Override
    public String getIncludeUrl() {
        return "/include.jsp";
    }

    @NotNull
    @Override
    public String getPluginName() {
        return "Chat";
    }

    @NotNull
    @Override
    public List<String> getJsPaths() {
        List<String> paths = new ArrayList<String>();
        paths.add(pluginDescriptor.getPluginResourcesPath("js/addChatLink.js"));
        return paths;
    }

}
