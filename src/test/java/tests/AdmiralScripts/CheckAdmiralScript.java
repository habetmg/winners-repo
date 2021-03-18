package tests.AdmiralScripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckAdmiralScript extends BaseTest {

    @Test(description = "Checking Admiral-Script on header")
    public void checkingAdmiralScriptOnHeader() {
        Assert.assertTrue(mainPage.getPageSource().contains("\n" +
                "              !(function(o,n,t){t=o.createElement(n),o=o.getElementsByTagName(n)[0],t.async=1,\n" +
                "                t.src=\"https://annoyedairport.com/v2dkn3F2LBB_8Vh0OVg4Ba3CX8myzS4cVosdQRBDX5sY5C2ESSlWFoDaxu5-DkF72\",\n" +
                "                o.parentNode.insertBefore(t,o)})(document,\"script\"),(function(o,n){function t(){(t.q=t.q||[]).push(arguments)}\n" +
                "                t.v=1,o.admiral=o.admiral||t})(window);!(function(o,t,n,c){function e(n){(function(){try{\n" +
                "                return(localStorage.getItem(\"v4ac1eiZr0\")||\"\").split(\",\")[4]>0}catch(o){}return!1})()&&(n=o[t].pubads())\n" +
                "                &&n.setTargeting(\"admiral-engaged\",\"true\")}(c=o[t]=o[t]||{}).cmd=c.cmd||[],typeof c.pubads===n?e():typeof \n" +
                "                c.cmd.unshift===n?c.cmd.unshift(e):c.cmd.push(e)})(window,\"googletag\",\"function\");;;!(function(t,n,i,e,o)\n" +
                "                {function a(){for(var t=[],i=0;i<arguments.length;i++)t.push(arguments[i]);if(!t.length)return o;\"ping\"===t[0]?\n" +
                "                t[2]({gdprAppliesGlobally:!!n.__cmpGdprAppliesGlobally,cmpLoaded:!1,cmpStatus:\"stub\"}):t.length>0&&o.push(t)}\n" +
                "                function c(t){if(t&&t.data){var e,o=\"string\"==typeof t.data&&t.data.indexOf(\"__tcfapiCall\")>=0;(e=o?((function(t)\n" +
                "                {try{return JSON.parse(t)}catch(n){}})(t.data)||{}).__tcfapiCall:(t.data||{}).__tcfapiCall)&&n[i](e.command,\n" +
                "                e.version,(function(n,i){var a={__tcfapiReturn:{returnValue:n,success:i,callId:e.callId}};t.source.postMessage(o?JSON.stringify(a):\n" +
                "                a,\"*\")}),e.parameter)}}!(function f(){if(!window.frames[e]){var n=t.body;if(n){var i=t.createElement(\"iframe\");i.style.display=\"none\",\n" +
                "                i.name=e,n.appendChild(i)}else setTimeout(f,5)}})(),o=[],a.v=1,\"function\"!=typeof n[i]&&(n[i]=n[i]||a,n.addEventListener?n.addEventListener\n" +
                "                (\"message\",c,!1):n.attachEvent&&n.attachEvent(\"onmessage\",c))})(document,window,\"__tcfapi\",\"__tcfapiLocator\");\n" +
                "                "));
    }
}


