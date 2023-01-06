!function() {
    var e = window.parent;
    dialog = e.$EDITORUI[window.frameElement.id.replace(/_iframe$/, "")],
    editor = dialog.editor,
    UE = e.UE,
    domUtils = UE.dom.domUtils,
    utils = UE.utils,
    browser = UE.browser,
    ajax = UE.ajax,
    $G = function(e) {
        return document.getElementById(e)
    }
    ,
    $focus = function(t) {
        setTimeout(function() {
            if (browser.ie) {
                var e = t.createTextRange();
                e.collapse(!1),
                e.select()
            } else
                t.focus()
        }, 0)
    }
    ,
    utils.loadFile(document, {
        href: editor.options.themePath + editor.options.theme + "/dialogbase.css?cache=" + Math.random(),
        tag: "link",
        type: "text/css",
        rel: "stylesheet"
    }),
    lang = editor.getLang(dialog.className.split("-")[2]),
    lang && domUtils.on(window, "load", function() {
        var e = editor.options.langPath + editor.options.lang + "/images/";
        for (var t in lang.static) {
            var o = $G(t);
            if (o) {
                var a = o.tagName
                  , i = lang.static[t];
                switch (i.src && ((i = utils.extend({}, i, !1)).src = e + i.src),
                i.style && ((i = utils.extend({}, i, !1)).style = i.style.replace(/url\s*\(/g, "url(" + e)),
                a.toLowerCase()) {
                case "var":
                    o.parentNode.replaceChild(document.createTextNode(i), o);
                    break;
                case "select":
                    for (var s, r = o.options, n = 0; s = r[n]; )
                        s.innerHTML = i.options[n++];
                    for (var l in i)
                        "options" != l && o.setAttribute(l, i[l]);
                    break;
                default:
                    domUtils.setAttributes(o, i)
                }
            }
        }
    })
}();
