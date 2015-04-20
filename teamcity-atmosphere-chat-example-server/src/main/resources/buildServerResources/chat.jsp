<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/include.jsp" %>


<bs:page>
<jsp:attribute name="head_include">
<bs:linkScript>
    ${teamcityPluginResourcesPath}js/chat.js
</bs:linkScript>
</jsp:attribute>

<jsp:attribute name="body_include">
    <style>
        div .chat * {
            font-family: tahoma;
            font-size: 12px;
            padding: 0px;
            margin: 0px;
        }

        div .chat p {
            line-height: 18px;
        }

        div .chat div {
            width: 500px;
            margin-left: auto;
            margin-right: auto;
        }

        div .chat #chat-content {
            padding: 5px;
            background: #ddd;
            border-radius: 5px;
            border: 1px solid #CCC;
            margin-top: 10px;
        }

        div .chat #chat-header {
            padding: 5px;
            background: #f5deb3;
            border-radius: 5px;
            border: 1px solid #CCC;
            margin-top: 10px;
        }

        div .chat #chat-input {
            border-radius: 2px;
            border: 1px solid #ccc;
            margin-top: 10px;
            padding: 5px;
            width: 400px;
        }

        div .chat #chat-status {
            width: 88px;
            display: block;
            float: left;
            margin-top: 15px;
        }
    </style>

    <div class="chat">
        <div id="chat-header"><h3>Atmosphere Chat. Default transport is WebSocket, fallback is long-polling</h3></div>
        <div id="chat-content"></div>
        <div>
            <span id="chat-status">Connecting...</span>
            <input type="text" id="chat-input" disabled="disabled"/>
        </div>
    </div>
</jsp:attribute>
</bs:page>
